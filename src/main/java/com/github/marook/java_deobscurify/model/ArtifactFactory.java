/*
 *
 * Copyright 2011 Markus Pielmeier
 *
 * This file is part of java-deobscurify.
 *
 * java-deobscurify is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-deobscurify is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with java-deobscurify.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.github.marook.java_deobscurify.model;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ModifierSet;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArtifactFactory {

	private Set<String> getImports(final CompilationUnit cu) {
		final List<ImportDeclaration> cuImports = cu.getImports();

		if (cuImports == null) {
			return Collections.emptySet();
		}

		final Set<String> imports = new HashSet<String>();

		for (final ImportDeclaration d : cuImports) {
			imports.add(d.getName().toString());
		}

		return imports;
	}

	private Visibility getVisibility(final int modifiers) {
		if ((modifiers & ModifierSet.PRIVATE) > 0) {
			return Visibility.PRIVATE;
		}
		if ((modifiers & ModifierSet.PROTECTED) > 0) {
			return Visibility.PROTECTED;
		}
		if ((modifiers & ModifierSet.PUBLIC) > 0) {
			return Visibility.PUBLIC;
		}

		return Visibility.PACKAGE;
	}

	private Parameter getParameter(final japa.parser.ast.body.Parameter p,
			final TypeFactory typeFactory) {
		return new Parameter(typeFactory.getType(p.getType()));
	}

	private List<Parameter> getMethodParameters(
			final japa.parser.ast.body.MethodDeclaration md,
			final TypeFactory typeFactory) {
		final List<japa.parser.ast.body.Parameter> mdps = md.getParameters();

		if (mdps == null) {
			return Collections.emptyList();
		}

		final List<Parameter> parameters = new ArrayList<Parameter>(mdps.size());

		for (final japa.parser.ast.body.Parameter p : mdps) {
			parameters.add(getParameter(p, typeFactory));
		}

		return parameters;
	}

	private MethodDeclaration getMethod(
			final japa.parser.ast.body.MethodDeclaration md,
			final TypeFactory typeFactory) {
		final Type returnType = typeFactory.getType(md.getType());

		final List<String> impl = Arrays.asList(md.getBody().toString()
				.split("\n"));

		return new MethodDeclaration(getVisibility(md.getModifiers()),
				md.getName(), returnType, getMethodParameters(md, typeFactory),
				impl);
	}

	private List<MethodDeclaration> getMethods(
			final japa.parser.ast.body.TypeDeclaration type,
			final TypeFactory typeFactory) {
		final List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();

		if (type.getMembers() != null) {
			for (final BodyDeclaration bd : type.getMembers()) {
				if (!(bd instanceof japa.parser.ast.body.MethodDeclaration)) {
					continue;
				}

				final japa.parser.ast.body.MethodDeclaration md = (japa.parser.ast.body.MethodDeclaration) bd;
				methods.add(getMethod(md, typeFactory));
			}
		}

		return methods;
	}

	private TypeDeclaration getTypeDeclaration(
			final japa.parser.ast.body.TypeDeclaration type,
			final TypeFactory typeFactory) {
		return new TypeDeclaration(type.getName(),
				getMethods(type, typeFactory));
	}

	private List<TypeDeclaration> getTypeDeclarations(final CompilationUnit cu,
			final TypeFactory typeFactory) {
		final List<japa.parser.ast.body.TypeDeclaration> types = cu.getTypes();

		if (types == null) {
			return Collections.emptyList();
		}

		final List<TypeDeclaration> decs = new ArrayList<TypeDeclaration>(
				types.size());

		for (final japa.parser.ast.body.TypeDeclaration type : types) {
			decs.add(getTypeDeclaration(type, typeFactory));
		}

		return decs;
	}

	public Artifact createArtifact(final String name, final InputStream in)
			throws IOException {
		final CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Input stream must contain a valid java file.", e);
		}

		final Set<String> imports = getImports(cu);

		final PackageDeclaration pakage = cu.getPakage();
		final TypeFactory typeFactory = new TypeFactory((pakage == null) ? ""
				: pakage.getName().toString(), imports);

		return new Artifact(name, imports, getTypeDeclarations(cu, typeFactory));
	}

	private Artifact createArtifact(final String name, final File f)
			throws IOException {
		final FileInputStream in = new FileInputStream(f);

		try {
			return createArtifact(name, in);
		} finally {
			in.close();
		}
	}

	private void appendArtifactsFromDirectory(
			final Collection<Artifact> artifacts, final File directory,
			final ArtifactNameGenerator nameGenerator) throws IOException {
		final FileFilter javaFileFilter = new JavaSourceFileFilter();

		for (final File f : directory.listFiles()) {
			if (f.isDirectory()) {
				appendArtifactsFromDirectory(artifacts, f, nameGenerator);
			} else if (javaFileFilter.accept(f)) {
				try {
					final String name = nameGenerator.getArtifactName(f);

					artifacts.add(createArtifact(name, f));
				} catch (final Exception e) {
					System.err.println("Can't parse " + f);
					e.printStackTrace(System.err);
					// TODO replace this with a real error handling
				}
			}
		}
	}

	public Collection<Artifact> parseArtifactsFromDirectory(final File directory)
			throws IOException {
		final List<Artifact> artifacts = new ArrayList<Artifact>();

		appendArtifactsFromDirectory(artifacts, directory,
				new ArtifactNameGenerator(directory));

		return artifacts;
	}

}

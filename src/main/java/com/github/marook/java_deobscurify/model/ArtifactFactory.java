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

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

	public Artifact createArtifact(final String name, final InputStream in)
			throws IOException {
		final CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Input stream must contain a valid java file.", e);
		}

		return new Artifact(name, getImports(cu));
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
				try{
					final String name = nameGenerator.getArtifactName(f);

					artifacts.add(createArtifact(name, f));
				}
				catch(final Exception e){
					System.err.println("Can't parse " + f);
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

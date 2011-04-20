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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArtifactFactory {
	
	private Set<String> getImports(final CompilationUnit cu){
		final List<ImportDeclaration> cuImports = cu.getImports();

		if(cuImports == null){
			return Collections.emptySet();
		}
		
		final Set<String> imports = new HashSet<String>();
		
		for(final ImportDeclaration d : cuImports){
			imports.add(d.getName().toString());
		}
		
		return imports;
	}
	
	public Artifact createArtifact(final String name, final InputStream in) throws IOException {
		final CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Input stream must contain a valid java file.", e);
		}
		
		return new Artifact(name, getImports(cu));
	}
	
}

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

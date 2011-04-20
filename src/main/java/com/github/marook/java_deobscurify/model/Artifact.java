package com.github.marook.java_deobscurify.model;

import java.util.Collections;
import java.util.Set;

public class Artifact {
	
	private final String name;
	
	private final Set<String> imports;
	
	public Artifact(final String name, final Set<String> imports){
		if(name == null){
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		this.imports = Collections.unmodifiableSet(imports);
	}
	
	public String getName(){
		return name;
	}
	
	public Set<String> getImports(){
		return imports;
	}
	
}

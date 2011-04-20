package com.github.marook.java_deobscurify.model;

public class Artifact {
	
	private final Class<?> clazz;
	
	public Artifact(final Class<?> clazz){
		if(clazz == null){
			throw new IllegalArgumentException();
		}
		
		this.clazz = clazz;
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	
}

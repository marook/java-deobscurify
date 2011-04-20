package com.github.marook.java_deobscurify.model;

public class ArtifactFactory {


	public Artifact createArtifact(final Class<?> c) {
		return new Artifact(c);
	}
	
}

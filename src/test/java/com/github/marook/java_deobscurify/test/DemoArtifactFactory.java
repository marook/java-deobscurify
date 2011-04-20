package com.github.marook.java_deobscurify.test;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.ArtifactFactory;

public class DemoArtifactFactory {
	
	private final ArtifactFactory artifactFactory;
	
	private final String testArtifactBasePath;
	
	public DemoArtifactFactory(final ArtifactFactory artifactFactory, final String testArtifactBasePath){
		if(artifactFactory == null){
			throw new IllegalArgumentException();
		}
		if(testArtifactBasePath == null){
			throw new IllegalArgumentException();
		}
		
		this.artifactFactory = artifactFactory;
		this.testArtifactBasePath = testArtifactBasePath;
	}
	
	public String createTestClassName(final String testClassName){
		final String name = testArtifactBasePath + testClassName + ".java";
		
		return name;
	}
	
	public Artifact createArtifact(final String className) throws IOException{
		final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(className);
		
		Assert.assertNotNull("No such test class exists: " + className, in);
		
		final Artifact a;
		try{
			a = artifactFactory.createArtifact(className, in);
		}
		finally{
			in.close();
		}
		
		return a;
	}
	
	public Artifact createTestArtifact(final String testClassName) throws IOException {
		final String name = createTestClassName(testClassName);
		final Artifact a = createArtifact(name);
		
		return a;
	}
	

}

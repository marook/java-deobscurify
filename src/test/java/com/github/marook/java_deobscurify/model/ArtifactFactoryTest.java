package com.github.marook.java_deobscurify.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ArtifactFactoryTest {
	
	private ArtifactFactory factory;
	
	@Before
	public void setUpFactory(){
		factory = new ArtifactFactory();
	}
	
	@Test
	public void instantiateArtifactFromClass(){
		final Class<?> c = this.getClass();
		
		final Artifact a = factory.createArtifact(c);
		Assert.assertEquals(c, a.getClazz());
	}
	
	@Test(expected = Exception.class)
	public void exceptionOnNullInstantiate(){
		factory.createArtifact(null);
	}
	
}

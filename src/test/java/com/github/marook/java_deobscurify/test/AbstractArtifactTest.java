package com.github.marook.java_deobscurify.test;

import org.junit.Before;

import com.github.marook.java_deobscurify.model.ArtifactFactory;

public abstract class AbstractArtifactTest {

	protected ArtifactFactory factory;

	protected DemoArtifactFactory testFactory;

	@Before
	public void setUpFactory() {
		factory = new ArtifactFactory();
		testFactory = new DemoArtifactFactory(factory,
				getTestArtifactBasePath());
	}
	
	protected abstract String getTestArtifactBasePath();

}

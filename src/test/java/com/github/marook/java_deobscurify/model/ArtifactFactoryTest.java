package com.github.marook.java_deobscurify.model;

import java.io.IOException;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.github.marook.java_deobscurify.test.AbstractArtifactTest;

public class ArtifactFactoryTest extends AbstractArtifactTest {

	@Override
	protected String getTestArtifactBasePath() {
		return "com/github/marook/java_deobscurify/model/";
	}

	@Test
	public void instantiateArtifactFromInputStream() throws IOException {
		final String name = testFactory.createTestClassName("EmptyClass");
		final Artifact a = testFactory.createArtifact(name);

		Assert.assertEquals(name, a.getName());
	}

	@Test(expected = Exception.class)
	public void exceptionOnParsingBrokenClass() throws IOException {
		testFactory.createTestArtifact("BrokenClass");
	}

	@Test
	public void parseImportsFromClass() throws IOException {
		final Artifact a = testFactory.createTestArtifact("ImportsClass");

		final Set<String> imports = a.getImports();
		Assert.assertEquals(2, imports.size());
		Assert.assertTrue(imports.contains("java.util.List"));
		Assert.assertTrue(imports.contains("java.util.Date"));
	}

}

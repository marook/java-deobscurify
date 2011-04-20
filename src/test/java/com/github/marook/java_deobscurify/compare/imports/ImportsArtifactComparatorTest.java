package com.github.marook.java_deobscurify.compare.imports;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.test.AbstractArtifactTest;

public class ImportsArtifactComparatorTest extends AbstractArtifactTest {

	private ImportsArtifactComparator comparator;

	@Before
	public void setUpComparator() {
		comparator = new ImportsArtifactComparator();
	}

	@Override
	protected String getTestArtifactBasePath() {
		return "com/github/marook/java_deobscurify/compare/imports/";
	}

	@Test
	public void equalImportsGetDistance0() throws IOException {
		final Artifact artifactA = testFactory
				.createTestArtifact("TwoImportsA");
		final Artifact artifactB = testFactory
				.createTestArtifact("TwoImportsB");

		final double delta = 0.0001;
		Assert.assertEquals(1.0, comparator.getDistance(artifactA, artifactB),
				delta);
		Assert.assertEquals(1.0, comparator.getDistance(artifactB, artifactA),
				delta);
	}

}

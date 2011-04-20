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

		final double distance = 0.0;
		final double delta = 0.0001;
		Assert.assertEquals(distance,
				comparator.getDistance(artifactA, artifactB), delta);
		Assert.assertEquals(distance,
				comparator.getDistance(artifactB, artifactA), delta);
	}

	@Test
	public void oneNewImportIsMoreLikelyThanOneLess() throws IOException {
		final Artifact oneImportArtifact = testFactory
				.createTestArtifact("OneImport");
		final Artifact twoImportsArtifact = testFactory
				.createTestArtifact("TwoImportsA");
		final Artifact threeImportsArtifact = testFactory
				.createTestArtifact("ThreeImports");

		final double twoToOneDistance = comparator.getDistance(
				twoImportsArtifact, oneImportArtifact);
		final double twoToThreeDistance = comparator.getDistance(
				twoImportsArtifact, threeImportsArtifact);

		Assert.assertTrue(twoToThreeDistance < twoToOneDistance);
	}

	@Test
	public void oneNewImportIsMoreLikelyThanTwoNewImports() throws IOException {
		final Artifact oneImportArtifact = testFactory
				.createTestArtifact("OneImport");
		final Artifact twoImportsArtifact = testFactory
				.createTestArtifact("TwoImportsA");
		final Artifact threeImportsArtifact = testFactory
				.createTestArtifact("ThreeImports");

		final double oneNewDistance = comparator.getDistance(oneImportArtifact,
				twoImportsArtifact);
		final double twoNewDistance = comparator.getDistance(oneImportArtifact,
				threeImportsArtifact);

		Assert.assertTrue(oneNewDistance < twoNewDistance);
	}

}

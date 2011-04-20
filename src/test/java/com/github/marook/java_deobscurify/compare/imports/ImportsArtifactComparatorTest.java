/*
 *
 * Copyright 2011 Markus Pielmeier
 *
 * This file is part of java-deobscurify.
 *
 * java-deobscurify is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-deobscurify is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with java-deobscurify.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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

	@Test
	public void oneDifferentImportIsMoreLikelyThanTwoDifferentImports()
			throws IOException {
		final Artifact artifact = testFactory.createTestArtifact("TwoImportsA");
		final Artifact oneDifferentArtifact = testFactory
				.createTestArtifact("TwoImportsAOneDifferent.java");
		final Artifact twoDifferentArtifact = testFactory
				.createTestArtifact("TwoImportsATwoDifferent.java");

		final double oneDifferentDistance = comparator.getDistance(artifact,
				oneDifferentArtifact);
		final double twoDifferentDistance = comparator.getDistance(artifact,
				twoDifferentArtifact);

		Assert.assertTrue(oneDifferentDistance < twoDifferentDistance);
	}

}

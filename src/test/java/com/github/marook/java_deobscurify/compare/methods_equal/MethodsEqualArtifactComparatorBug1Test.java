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

package com.github.marook.java_deobscurify.compare.methods_equal;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.DistanceValidator;
import com.github.marook.java_deobscurify.test.AbstractArtifactTest;

public class MethodsEqualArtifactComparatorBug1Test extends
		AbstractArtifactTest {

	private MethodsEqualArtifactComparator comparator;

	@Before
	public void setUpComparator() {
		comparator = new MethodsEqualArtifactComparator();
	}

	@Override
	protected String getTestArtifactBasePath() {
		return "com/github/marook/java_deobscurify/compare/methods_equal/bug1/";
	}

	@Test
	public void validDistanceFromExplosionToBj() throws IOException {
		final Artifact from = testFactory.createTestArtifact("From");
		final Artifact to = testFactory.createTestArtifact("To");

		final double d = comparator.getDistance(from, to);

		Assert.assertTrue(DistanceValidator.isValidDistance(d));
	}

}

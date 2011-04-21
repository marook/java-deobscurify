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

package com.github.marook.java_deobscurify.compare.merge;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.compare.mock.StaticMockArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.MockArtifactFactory;

public class MergeArtifactComparatorTest {

	private MockArtifactFactory mockArtifactFactory;

	@Before
	public void setUpMockArtifactFactory() {
		mockArtifactFactory = new MockArtifactFactory();
	}

	@Test
	public void childComparatorsAreWeighted() {
		final Artifact from = mockArtifactFactory.createMockArtifact();
		final Artifact to = mockArtifactFactory.createMockArtifact();

		final ArtifactComparator child1Comp = new StaticMockArtifactComparator(
				0.4);
		final ArtifactComparator child2Comp = new StaticMockArtifactComparator(
				0.6);

		final ArtifactComparator mergeComp = new MergeArtifactComparator(
				Arrays.asList(new ChildComparator[] {
						new ChildComparator(child1Comp, 2.0),
						new ChildComparator(child2Comp, 0.5) }));

		Assert.assertEquals(0.44, mergeComp.getDistance(from, to), 0.001);
	}

}

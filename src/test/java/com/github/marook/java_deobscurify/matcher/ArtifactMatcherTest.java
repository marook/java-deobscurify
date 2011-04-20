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

package com.github.marook.java_deobscurify.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.compare.mock.GenericMockArtifactComparator;
import com.github.marook.java_deobscurify.compare.mock.GenericMockArtifactComparator.Distance;
import com.github.marook.java_deobscurify.compare.mock.StaticMockArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.DistanceValidator;

public class ArtifactMatcherTest {

	private Artifact obscurifiedArtifact;

	private Artifact createMockArtifact() {
		return Mockito.mock(Artifact.class);
	}

	@Before
	public void setUpObscurifiedArtifact() {
		obscurifiedArtifact = createMockArtifact();
	}

	@Test
	public void noDistancesBiggerThanMaxDistanceAreFound() {
		final ArtifactComparator comparator = new StaticMockArtifactComparator(
				0.5);
		final Artifact clearTextArtifact = createMockArtifact();
		final Collection<Artifact> clearTextArtifacts = Collections
				.singletonList(clearTextArtifact);

		Assert.assertTrue(new ArtifactMatcher(comparator, 0.4)
				.findMatchingArtifacts(obscurifiedArtifact, clearTextArtifacts)
				.getClearTextArtifacts().isEmpty());
		Assert.assertTrue(clearTextArtifact == new ArtifactMatcher(comparator,
				0.6)
				.findMatchingArtifacts(obscurifiedArtifact, clearTextArtifacts)
				.getClearTextArtifacts().get(0).getArtifact());
	}

	@Test
	public void clearTextArtifactsAreSortedByDistance() {
		final Artifact clearText1Artifact = createMockArtifact();
		final Artifact clearText2Artifact = createMockArtifact();

		final List<Artifact> createTextArtifacts = Arrays
				.asList(new Artifact[] { clearText2Artifact, clearText1Artifact });

		final List<Distance> distances = new ArrayList<Distance>();
		distances
				.add(new Distance(clearText1Artifact, obscurifiedArtifact, 0.3));
		distances
				.add(new Distance(clearText2Artifact, obscurifiedArtifact, 0.6));

		final ArtifactComparator comparator = new GenericMockArtifactComparator(
				distances);

		final Match m = new ArtifactMatcher(comparator,
				DistanceValidator.MAX_DISTANCE).findMatchingArtifacts(
				obscurifiedArtifact, createTextArtifacts);

		Assert.assertEquals(2, m.getClearTextArtifacts().size());
		Assert.assertEquals(clearText1Artifact, m.getClearTextArtifacts()
				.get(0).getArtifact());
		Assert.assertEquals(clearText2Artifact, m.getClearTextArtifacts()
				.get(1).getArtifact());
	}

}

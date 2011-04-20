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

import java.util.Collection;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.DistanceValidator;

public class ArtifactMatcher {

	private final ArtifactComparator comparator;

	private final double maxDistance;

	public ArtifactMatcher(final ArtifactComparator comparator,
			final double maxDistance) {
		if (comparator == null) {
			throw new IllegalArgumentException();
		}
		DistanceValidator.validateDistance(maxDistance);

		this.comparator = comparator;
		this.maxDistance = maxDistance;
	}

	public Match findMatchingArtifacts(final Artifact obscurifiedArtifact,
			final Collection<Artifact> clearTextArtifacts) {
		// TODO
		return null;
	}

}

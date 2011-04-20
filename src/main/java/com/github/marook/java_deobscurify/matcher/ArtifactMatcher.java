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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.matcher.Match.ArtifactDistance;
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
		final List<ArtifactDistance> distances = new ArrayList<ArtifactDistance>();
		
		for(final Artifact a : clearTextArtifacts){
			final double distance = comparator.getDistance(a, obscurifiedArtifact);
			
			if(distance > maxDistance){
				continue;
			}
			
			distances.add(new ArtifactDistance(a, distance));
		}
		
		Collections.sort(distances, new Comparator<ArtifactDistance>() {
			@Override
			public int compare(final ArtifactDistance o1, final ArtifactDistance o2) {
				final double d1 = o1.getClearToObscurifiedDistance();
				final double d2 = o2.getClearToObscurifiedDistance();
				
				if(d1 < d2){
					return -1;
				}
				
				if(d2 < d1){
					return 1;
				}
				
				return 0;
			}
		});

		return new Match(obscurifiedArtifact, distances);
	}

}

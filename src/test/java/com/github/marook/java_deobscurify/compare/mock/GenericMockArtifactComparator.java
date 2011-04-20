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

package com.github.marook.java_deobscurify.compare.mock;

import java.util.List;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.DistanceValidator;

public class GenericMockArtifactComparator implements ArtifactComparator {

	private final List<Distance> distances;

	public GenericMockArtifactComparator(final List<Distance> distances) {
		if (distances == null) {
			throw new IllegalArgumentException();
		}

		this.distances = distances;
	}

	@Override
	public double getDistance(final Artifact from, final Artifact to) {
		for (final Distance d : distances) {
			if(d.getFrom() != from){
				continue;
			}
			
			if(d.getTo() != to){
				continue;
			}
			
			return d.getDistance();
		}

		throw new IllegalStateException("No distance found for from " + from
				+ " to " + to);
	}

	public static class Distance {

		private final Artifact from;

		private final Artifact to;

		private final double distance;

		public Distance(final Artifact from, final Artifact to,
				final double distance) {
			if (from == null) {
				throw new IllegalArgumentException();
			}
			if (to == null) {
				throw new IllegalArgumentException();
			}
			DistanceValidator.validateDistance(distance);

			this.from = from;
			this.to = to;
			this.distance = distance;
		}
		
		public Artifact getFrom() {
			return from;
		}
		
		public Artifact getTo() {
			return to;
		}
		
		public double getDistance() {
			return distance;
		}

	}

}

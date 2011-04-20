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

import java.util.Collections;
import java.util.List;

import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.DistanceValidator;

public class Match {

	private final Artifact obscurifiedArtifact;

	private final List<ArtifactDistance> clearTextArtifacts;

	public Match(final Artifact obscurifiedArtifact,
			final List<ArtifactDistance> clearTextArtifacts) {
		if (obscurifiedArtifact == null) {
			throw new IllegalArgumentException();
		}
		// TODO make sure that clearTextArtifacts is sorted by distance

		this.obscurifiedArtifact = obscurifiedArtifact;
		this.clearTextArtifacts = Collections
				.unmodifiableList(clearTextArtifacts);
	}

	public Artifact getObscurifiedArtifact() {
		return obscurifiedArtifact;
	}

	public List<ArtifactDistance> getClearTextArtifacts() {
		return clearTextArtifacts;
	}

	public static class ArtifactDistance {

		private final Artifact artifact;

		private final double clearToObscurifiedDistance;

		public ArtifactDistance(final Artifact artifact,
				final double clearToObscurifiedDistance) {
			if (artifact == null) {
				throw new IllegalArgumentException();
			}
			DistanceValidator.validateDistance(clearToObscurifiedDistance);

			this.artifact = artifact;
			this.clearToObscurifiedDistance = clearToObscurifiedDistance;
		}

		public Artifact getArtifact() {
			return artifact;
		}

		public double getClearToObscurifiedDistance() {
			return clearToObscurifiedDistance;
		}

	}

}

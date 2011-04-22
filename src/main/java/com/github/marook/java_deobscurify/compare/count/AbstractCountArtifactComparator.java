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

package com.github.marook.java_deobscurify.compare.count;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;

public abstract class AbstractCountArtifactComparator implements
		ArtifactComparator {

	private final double equalWeight;

	private final double removedWeight;

	private final double addedWeight;
	
	protected AbstractCountArtifactComparator(){
		this(1.0, 1.0, 1.0);
	}

	protected AbstractCountArtifactComparator(final double equalWeight,
			final double removedWeight, final double addedWeight) {
		this.equalWeight = equalWeight;
		this.removedWeight = removedWeight;
		this.addedWeight = addedWeight;
	}

	protected abstract int getNumberOfElements(Artifact a);

	protected abstract int getNumberOfEqualElements(Artifact a1, Artifact a2);

	@Override
	public double getDistance(final Artifact from, final Artifact to) {
		final int equal = getNumberOfEqualElements(from, to);
		final int removed = getNumberOfElements(from) - equal;
		final int added = getNumberOfElements(to) - equal;

		if (equal == 0 && removed == 0 && added == 0) {
			return 0;
		}

		return (removedWeight * removed + addedWeight * added - equalWeight
				* equal)
				/ (removedWeight * removed + addedWeight * added + equalWeight
						* equal) / 2.0 + 0.5;
	}

}

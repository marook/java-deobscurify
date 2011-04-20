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

import java.util.HashSet;
import java.util.Set;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;

public class ImportsArtifactComparator implements ArtifactComparator {

	private static final double EQUAL_WEIGHT = 1.0;

	private static final double REMOVED_WEIGHT = 1.2;

	private static final double ADDED_WEIGHT = 0.8;

	@Override
	public double getDistance(final Artifact from, final Artifact to) {
		final Set<String> fromImports = from.getImports();
		final Set<String> toImports = to.getImports();

		final Set<String> equalImports = new HashSet<String>(fromImports);
		equalImports.retainAll(toImports);

		final int equal = equalImports.size();
		final int removed = fromImports.size() - equal;
		final int added = toImports.size() - equal;
		
		if(equal == 0 && removed == 0 && added == 0){
			return 0;
		}
		
		return (REMOVED_WEIGHT * removed + ADDED_WEIGHT * added - EQUAL_WEIGHT
				* equal)
				/ (REMOVED_WEIGHT * removed + ADDED_WEIGHT * added + EQUAL_WEIGHT
						* equal) / 2.0 + 0.5;
	}

}

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

import com.github.marook.java_deobscurify.compare.count.AbstractCountArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;

public class ImportsArtifactComparator extends AbstractCountArtifactComparator {

	private static final double EQUAL_WEIGHT = 1.0;

	private static final double REMOVED_WEIGHT = 1.2;

	private static final double ADDED_WEIGHT = 0.8;

	public ImportsArtifactComparator() {
		super(EQUAL_WEIGHT, REMOVED_WEIGHT, ADDED_WEIGHT);
	}

	@Override
	protected int getNumberOfElements(final Artifact a) {
		return a.getImports().size();
	}

	@Override
	protected int getNumberOfEqualElements(final Artifact a1, final Artifact a2) {
		final Set<String> equalImports = new HashSet<String>(a1.getImports());
		equalImports.retainAll(a2.getImports());
		
		return equalImports.size();
	}

}

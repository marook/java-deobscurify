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

package com.github.marook.java_deobscurify.compare.method_impl_equal;

import java.util.Collection;

import com.github.marook.java_deobscurify.compare.ValidatingArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.MethodDeclaration;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class MethodImplEqualArtifactComparator extends
		ValidatingArtifactComparator {

	private double getMethodDistance(final MethodDeclaration from,
			final MethodDeclaration to) {
		final int totalLines = from.getImplSource().size()
				+ to.getImplSource().size();

		if (totalLines == 0) {
			return 0.0;
		}

		final Patch p = DiffUtils
				.diff(from.getImplSource(), to.getImplSource());

		int changedLines = 0;
		for (final Delta d : p.getDeltas()) {
			changedLines += d.getOriginal().getLines().size();
			changedLines += d.getRevised().getLines().size();
		}

		return 1.0 - (double) changedLines / totalLines;
	}

	private double getBestMatchingMethodDistance(final MethodDeclaration from,
			final Collection<MethodDeclaration> tos) {
		double distance = 1;

		for (final MethodDeclaration to : tos) {
			final double d = getMethodDistance(from, to);

			if (d < distance) {
				distance = d;
			}
		}

		return distance;
	}

	@Override
	protected double getDistanceInternal(final Artifact from, final Artifact to) {
		final Collection<MethodDeclaration> fromMethods = from.getAllMethods();

		if (fromMethods.size() == 0) {
			return 0.0;
		}

		final Collection<MethodDeclaration> toMethods = to.getAllMethods();

		double sum = 0;
		for (final MethodDeclaration fromMethod : fromMethods) {
			sum += getBestMatchingMethodDistance(fromMethod, toMethods);
		}

		return sum / fromMethods.size();
	}

}

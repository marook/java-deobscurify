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

package com.github.marook.java_deobscurify;

import java.io.File;
import java.util.Collection;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.compare.imports.ImportsArtifactComparator;
import com.github.marook.java_deobscurify.matcher.ArtifactMatcher;
import com.github.marook.java_deobscurify.matcher.Match;
import com.github.marook.java_deobscurify.matcher.MatchPrinter;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.ArtifactFactory;

public class FindMatches {

	private static ArtifactComparator createArtifactComparator() {
		return new ImportsArtifactComparator();
	}

	public static void main(final String[] args) {
		try {
			final File clearTextDir = new File(args[0]);
			final File obscurifiedDir = new File(args[1]);

			final ArtifactFactory artifactFactory = new ArtifactFactory();
			final Collection<Artifact> clearTextArtifacts = artifactFactory
					.parseArtifactsFromDirectory(clearTextDir);
			final Collection<Artifact> obscurifiedArtifacts = artifactFactory
					.parseArtifactsFromDirectory(obscurifiedDir);

			final ArtifactMatcher artifactMatcher = new ArtifactMatcher(
					createArtifactComparator(), 0.2);

			final MatchPrinter matchPrinter = new MatchPrinter();

			for (final Artifact obscurifiedArtifact : obscurifiedArtifacts) {
				final Match m = artifactMatcher.findMatchingArtifacts(
						obscurifiedArtifact, clearTextArtifacts);

				matchPrinter.printMatch(System.out, m);
			}

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}

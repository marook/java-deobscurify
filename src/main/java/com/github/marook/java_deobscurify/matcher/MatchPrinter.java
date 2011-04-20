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

import java.io.PrintStream;
import java.text.NumberFormat;

import com.github.marook.java_deobscurify.matcher.Match.ArtifactDistance;

public class MatchPrinter {

	private final NumberFormat propabilityFormat = NumberFormat
			.getPercentInstance();

	public void printMatch(final PrintStream out, final Match m) {
		out.print(m.getObscurifiedArtifact().getName());
		out.print(":\n");

		for (final ArtifactDistance d : m.getClearTextArtifacts()) {
			out.print(" ");
			out.print(propabilityFormat.format(1 - d.getClearToObscurifiedDistance()));
			out.print(" ");
			out.print(d.getArtifact().getName());
			out.print("\n");
		}
	}

}

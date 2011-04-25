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

package com.github.marook.java_deobscurify.util;

public final class Strings {

	public static final String DEFAULT_SEPARATOR = ", ";

	private Strings() {
		// this method should only contain static members
		throw new IllegalStateException();
	}

	public static <T> String cat(final Iterable<T> ts) {
		return cat(ts, DEFAULT_SEPARATOR);
	}

	public static <T> String cat(final Iterable<T> ts, final String separator) {
		final StringBuilder sb = new StringBuilder();

		boolean first = true;
		for (final T t : ts) {
			if (first) {
				first = false;
			} else {
				sb.append(separator);
			}

			sb.append(t);
		}

		return sb.toString();
	}

}

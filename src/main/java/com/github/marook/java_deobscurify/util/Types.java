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

import java.util.HashSet;
import java.util.Set;

public final class Types {

	private static final Set<String> BUILT_IN_TYPES = new HashSet<String>();

	static {
		BUILT_IN_TYPES.add("byte");
		BUILT_IN_TYPES.add("char");
		BUILT_IN_TYPES.add("short");
		BUILT_IN_TYPES.add("int");
		BUILT_IN_TYPES.add("long");
		BUILT_IN_TYPES.add("float");
		BUILT_IN_TYPES.add("double");
		BUILT_IN_TYPES.add("void");
	}

	private Types() {
		// this class should only contain static members
		throw new IllegalStateException();
	}

	public static boolean isAtomicType(final String typeName) {
		final String rawType;
		if (typeName.endsWith("[]")) {
			rawType = typeName.substring(0, typeName.length() - "[]".length());
		} else {
			rawType = typeName;
		}

		return BUILT_IN_TYPES.contains(rawType);
	}

}

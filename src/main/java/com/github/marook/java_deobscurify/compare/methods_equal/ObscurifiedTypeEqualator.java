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

package com.github.marook.java_deobscurify.compare.methods_equal;

import java.util.HashSet;
import java.util.Set;

import com.github.marook.java_deobscurify.model.Type;
import com.github.marook.java_deobscurify.util.Equalator;
import com.github.marook.java_deobscurify.util.Types;

public class ObscurifiedTypeEqualator implements Equalator<Type> {

	private final Set<String> jseTypeNames = new HashSet<String>();

	private final Set<String> notJseTypeNames = new HashSet<String>();

	private boolean isJSEType(final Type t) {
		final String typeName = t.getName();

		if (Types.isAtomicType(typeName)) {
			return true;
		}

		if (jseTypeNames.contains(typeName)) {
			return true;
		}

		if (notJseTypeNames.contains(typeName)) {
			return false;
		}

		try {
			Thread.currentThread().getContextClassLoader().loadClass(typeName);

			jseTypeNames.add(typeName);

			return true;
		} catch (final ClassNotFoundException e) {
			notJseTypeNames.add(typeName);

			return false;
		}
	}

	@Override
	public boolean equalTo(final Type o1, final Type o2) {
		final boolean o1Jse = isJSEType(o1);
		final boolean o2Jse = isJSEType(o2);

		if (!o1Jse && !o2Jse) {
			return true;
		}

		return o1.equals(o2);
	}

}

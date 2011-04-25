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

package com.github.marook.java_deobscurify.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

import com.github.marook.java_deobscurify.util.Types;

public class TypeFactory {

	private final String currentPackage;

	private final Set<String> imports;

	private static void validateImports(final Collection<String> imports) {
		for (final String i : imports) {
			if (i.trim().endsWith("*")) {
				throw new IllegalArgumentException(
						"Can't handle wildcard imports like " + i);
			}
		}
	}

	public TypeFactory(final String currentPackage, final Set<String> imports) {
		if (currentPackage == null) {
			throw new IllegalArgumentException();
		}
		validateImports(imports);

		this.currentPackage = currentPackage;
		this.imports = Collections.unmodifiableSet(imports);
	}

	private String toRegEx(final String s) {
		return "\\Q" + s + "\\E";
	}

	private String getImport(final String typeName) {
		// TODO escape typeName
		final Pattern p = Pattern.compile("(^|(.+[.]))" + toRegEx(typeName)
				+ "$");

		for (final String i : imports) {
			if (!p.matcher(i).matches()) {
				continue;
			}

			return i;
		}

		return null;
	}

	private boolean isFullyQualifiedName(final String typeName) {
		return typeName.contains(".");
	}

	public Type getType(final String typeName) {
		if (isFullyQualifiedName(typeName)) {
			return new Type(typeName);
		}

		if (Types.isAtomicType(typeName)) {
			// TODO store built in types and do not regenerate them
			return new Type(typeName);
		}

		final String importType = getImport(typeName);
		if (importType != null) {
			return new Type(importType);
		}

		if (currentPackage.isEmpty()) {
			return new Type(typeName);
		}

		return new Type(currentPackage + "." + typeName);
	}

	public Type getType(final japa.parser.ast.type.Type type) {
		return getType(type.toString());
	}

}

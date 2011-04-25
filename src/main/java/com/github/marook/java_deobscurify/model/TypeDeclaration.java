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

import java.util.Collections;
import java.util.List;

public class TypeDeclaration {

	private final String name;

	private final List<MethodDeclaration> methods;

	public TypeDeclaration(final String name,
			final List<MethodDeclaration> methods) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.methods = Collections.unmodifiableList(methods);
	}

	public String getName() {
		return name;
	}

	public List<MethodDeclaration> getMethods() {
		return methods;
	}

	@Override
	public String toString() {
		return name;
	}

}

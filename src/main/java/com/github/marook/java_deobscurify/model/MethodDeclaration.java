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

import com.github.marook.java_deobscurify.util.Strings;

public class MethodDeclaration {

	private final Visibility visibility;

	private final String name;

	private final Type returnType;

	private final List<Parameter> parameters;

	public MethodDeclaration(final Visibility visibility, final String name,
			final Type returnType, final List<Parameter> parameters) {
		if (visibility == null) {
			throw new IllegalArgumentException();
		}
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (returnType == null) {
			throw new IllegalArgumentException();
		}

		this.visibility = visibility;
		this.name = name;
		this.returnType = returnType;
		this.parameters = Collections.unmodifiableList(parameters);
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public String getName() {
		return name;
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return String.valueOf(returnType) + " " + name + "("
				+ Strings.cat(parameters) + ")";
	}

}

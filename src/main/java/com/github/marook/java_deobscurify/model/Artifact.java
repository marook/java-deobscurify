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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Artifact {

	private final String name;

	private final Set<String> imports;

	private final List<TypeDeclaration> typeDeclarations;

	public Artifact(final String name, final Set<String> imports,
			final List<TypeDeclaration> typeDeclarations) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.imports = Collections.unmodifiableSet(imports);
		this.typeDeclarations = Collections.unmodifiableList(typeDeclarations);
	}

	public String getName() {
		return name;
	}

	public Set<String> getImports() {
		return imports;
	}

	public List<TypeDeclaration> getTypeDeclarations() {
		return typeDeclarations;
	}

	public TypeDeclaration getTypeDeclaration(final String name) {
		for (final TypeDeclaration td : getTypeDeclarations()) {
			if (!name.equals(td.getName())) {
				continue;
			}

			return td;
		}

		return null;
	}

	public Collection<MethodDeclaration> getAllMethods() {
		final List<TypeDeclaration> typeDeclarations = getTypeDeclarations();

		final Collection<MethodDeclaration> methods = new ArrayList<MethodDeclaration>(
				typeDeclarations.size() * 16);

		for (final TypeDeclaration td : typeDeclarations) {
			methods.addAll(td.getMethods());
		}

		return methods;
	}

	@Override
	public String toString() {
		return name;
	}

}

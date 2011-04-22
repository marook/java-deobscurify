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

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class TypeFactoryTest {

	private static final String THIS_PACKAGE = "this.is.my.package";

	private static TypeFactory createTypeFactory(final String[] imports) {
		return new TypeFactory(THIS_PACKAGE, Arrays.asList(imports));
	}

	@Test
	public void resolveImportedTypesFromDefaultPackage() {
		final String myClassName = "MyClass";

		final TypeFactory factory = createTypeFactory(new String[] { myClassName });

		Assert.assertEquals(myClassName, factory.getType(myClassName).getName());
	}

	@Test
	public void resolveImportedTypesFromPackage() {
		final String myClassName = "MyClass";
		final String myClassFullyQualifiedName = "my.package." + myClassName;

		final TypeFactory factory = createTypeFactory(new String[] { myClassFullyQualifiedName });

		Assert.assertEquals(myClassFullyQualifiedName, factory.getType(myClassName).getName());
	}

	@Test
	public void resolveFullyQualifiedTypes() {
		final String myClassName = "my.package.MyClass";

		final TypeFactory factory = createTypeFactory(new String[] { myClassName });

		Assert.assertEquals(myClassName, factory.getType(myClassName).getName());
	}

	@Test
	public void resolveAtomicBuiltInTypes() {
		final String myType = "int";

		final TypeFactory factory = createTypeFactory(new String[] { myType });

		Assert.assertEquals(myType, factory.getType(myType).getName());
	}

	@Test
	public void resolvePackageVisibleTypes() {
		final String myType = "MyClass";

		final TypeFactory factory = createTypeFactory(new String[] {});

		Assert.assertEquals(THIS_PACKAGE + "." + myType, factory
				.getType(myType).getName());
	}

	@Test(expected = Exception.class)
	public void failOnWildcardImports() {
		// wildcard imports are not yet supported and should fail
		createTypeFactory(new String[]{ "java.lang.*" });
	}

}

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ArtifactFactoryMethodsTest extends AbstractArtifactFactoryTest {

	private void assertMethod(final TypeDeclaration td, final int methodIndex,
			final Visibility methodVisibility, final String methodName) {
		final MethodDeclaration md = td.getMethods().get(methodIndex);

		Assert.assertEquals(methodName, md.getName());
		Assert.assertEquals(methodVisibility, md.getVisibility());
	}

	private TypeDeclaration getClassWithMethods() throws IOException {
		final Artifact a = testFactory.createTestArtifact("ClassWithMethods");

		return a.getTypeDeclaration("ClassWithMethods");
	}

	@Test
	public void testMethodVisibility() throws IOException {
		final TypeDeclaration td = getClassWithMethods();

		assertMethod(td, 0, Visibility.PACKAGE, "packageVisible");
		assertMethod(td, 1, Visibility.PRIVATE, "privateVisible");
		assertMethod(td, 2, Visibility.PROTECTED, "protectedVisible");
		assertMethod(td, 3, Visibility.PUBLIC, "publicVisible");
	}

	@Test
	public void testMethodReturnType() throws IOException {
		final TypeDeclaration td = getClassWithMethods();

		Assert.assertEquals("my.test.PackageVisibleClass",
				td.getMethods().get(0).getReturnType().getName());
	}

	private static void assertEqualSrc(final String[] expected,
			final List<String> actual) {
		final List<String> actualTrimmed = new ArrayList<String>(actual.size());

		for (final String s : actual) {
			actualTrimmed.add(s.trim());
		}

		Assert.assertEquals(Arrays.asList(expected), actualTrimmed);
	}

	@Test
	public void testMethodImplSource() throws IOException {
		final TypeDeclaration td = getClassWithMethods();

		final String[] expectedSrc = new String[] { "{",
				"String s = \"hello world\";", "}" };
		assertEqualSrc(expectedSrc, td.getMethods().get(0).getImplSource());
	}
}

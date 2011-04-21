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

import junit.framework.Assert;

import org.junit.Test;

public class ArtifactFactoryMethodsTest extends AbstractArtifactFactoryTest {

	private void assertMethod(final TypeDeclaration td, final int methodIndex,
			final Visibility methodVisibility, final String methodName) {
		final MethodDeclaration md = td.getMethods().get(methodIndex);
		
		Assert.assertEquals(methodName, md.getName());
		Assert.assertEquals(methodVisibility, md.getVisibility());
	}

	@Test
	public void testMethodVisibility() throws IOException {
		final Artifact a = testFactory.createTestArtifact("ClassWithMethods");

		final TypeDeclaration td = a.getTypeDeclaration("ClassWithMethods");
		assertMethod(td, 0, Visibility.PACKAGE, "packageVisible");
		assertMethod(td, 1, Visibility.PRIVATE, "privateVisible");
		assertMethod(td, 2, Visibility.PROTECTED, "protectedVisible");
		assertMethod(td, 3, Visibility.PUBLIC, "publicVisible");
	}

}

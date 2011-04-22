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

public class ArtifactFactoryTypeDeclarationTest extends
		AbstractArtifactFactoryTest {
	
	@Test
	public void parseOneClassFromJavaFile() throws IOException{
		final Artifact a = testFactory.createTestArtifact("EmptyClass");
		
		Assert.assertEquals(1, a.getTypeDeclarations().size());
		Assert.assertEquals("EmptyClass", a.getTypeDeclarations().get(0).getName());
	}
	
	@Test
	public void parseTwoClassesFromJavaFile() throws IOException{
		final Artifact a = testFactory.createTestArtifact("TwoEmptyClasses");
		
		Assert.assertEquals(2, a.getTypeDeclarations().size());
		Assert.assertEquals("EmptyClass1", a.getTypeDeclarations().get(0).getName());
		Assert.assertEquals("EmptyClass2", a.getTypeDeclarations().get(1).getName());
	}

	@Test
	public void parseEnumFromJavaFile() throws IOException {
		final Artifact a = testFactory.createTestArtifact("EnumType");
		
		Assert.assertEquals(1, a.getTypeDeclarations().size());
		Assert.assertEquals("EnumType", a.getTypeDeclarations().get(0).getName());
	}

}

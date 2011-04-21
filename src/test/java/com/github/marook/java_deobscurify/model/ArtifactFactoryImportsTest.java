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
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class ArtifactFactoryImportsTest extends AbstractArtifactFactoryTest {

	@Test
	public void parseImportsFromInputStream() throws IOException {
		final Artifact a = testFactory.createTestArtifact("ImportsClass");

		final Set<String> imports = a.getImports();
		Assert.assertEquals(2, imports.size());
		Assert.assertTrue(imports.contains("java.util.List"));
		Assert.assertTrue(imports.contains("java.util.Date"));
	}

}

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

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;

public class ArtifactFactoryRecursiveDirectoryParseTest extends AbstractArtifactFactoryTest {

	@Test
	public void parseArtifactsFromDirectoryRecursive() throws IOException {
		final Collection<Artifact> artifacts = factory
				.parseArtifactsFromDirectory(new File(
						"src/test/resources/com/github/marook/java_deobscurify/model/dirParse"));

		Assert.assertEquals(1, artifacts.size());
		Assert.assertEquals("EmptyClass.java", artifacts.iterator().next().getName());
	}

}

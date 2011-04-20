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
package com.github.marook.java_deobscurify.test;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.ArtifactFactory;

public class DemoArtifactFactory {
	
	private final ArtifactFactory artifactFactory;
	
	private final String testArtifactBasePath;
	
	public DemoArtifactFactory(final ArtifactFactory artifactFactory, final String testArtifactBasePath){
		if(artifactFactory == null){
			throw new IllegalArgumentException();
		}
		if(testArtifactBasePath == null){
			throw new IllegalArgumentException();
		}
		
		this.artifactFactory = artifactFactory;
		this.testArtifactBasePath = testArtifactBasePath;
	}
	
	public String createTestClassName(final String testClassName){
		final String name = testArtifactBasePath + testClassName + ".java";
		
		return name;
	}
	
	public Artifact createArtifact(final String className) throws IOException{
		final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(className);
		
		Assert.assertNotNull("No such test class exists: " + className, in);
		
		final Artifact a;
		try{
			a = artifactFactory.createArtifact(className, in);
		}
		finally{
			in.close();
		}
		
		return a;
	}
	
	public Artifact createTestArtifact(final String testClassName) throws IOException {
		final String name = createTestClassName(testClassName);
		final Artifact a = createArtifact(name);
		
		return a;
	}
	

}

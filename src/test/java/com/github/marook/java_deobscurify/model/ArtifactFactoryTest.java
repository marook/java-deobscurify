package com.github.marook.java_deobscurify.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ArtifactFactoryTest {
	
	private ArtifactFactory factory;
	
	@Before
	public void setUpFactory(){
		factory = new ArtifactFactory();
	}
	
	private String createTestClassName(final String testClassName){
		final String name = "com/github/marook/java_deobscurify/model/" + testClassName + ".java";
		
		return name;
	}
	
	private Artifact createArtifact(final String className) throws IOException{
		final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(className);
		
		Assert.assertNotNull("No such test class exists: " + className, in);
		
		final Artifact a;
		try{
			a = factory.createArtifact(className, in);
		}
		finally{
			in.close();
		}
		
		return a;
	}
	
	private Artifact createTestArtifact(final String testClassName) throws IOException {
		final String name = createTestClassName(testClassName);
		final Artifact a = createArtifact(name);
		
		return a;
	}
	
	@Test
	public void instantiateArtifactFromInputStream() throws IOException{
		final String name = createTestClassName("EmptyClass");
		final Artifact a = createArtifact(name);
		
		Assert.assertEquals(name, a.getName());
	}
	
	@Test(expected = Exception.class)
	public void exceptionOnParsingBrokenClass() throws IOException {
		createTestArtifact("BrokenClass");
	}
	
	@Test
	public void parseImportsFromClass() throws IOException {
		final Artifact a = createTestArtifact("ImportsClass");
		
		final Set<String> imports = a.getImports();
		Assert.assertEquals(2, imports.size());
		Assert.assertTrue(imports.contains("java.util.List"));
		Assert.assertTrue(imports.contains("java.util.Date"));
	}
	
}

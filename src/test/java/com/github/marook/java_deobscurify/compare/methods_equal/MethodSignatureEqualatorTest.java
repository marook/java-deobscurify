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

package com.github.marook.java_deobscurify.compare.methods_equal;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.github.marook.java_deobscurify.model.MethodDeclaration;
import com.github.marook.java_deobscurify.model.Parameter;
import com.github.marook.java_deobscurify.model.Type;
import com.github.marook.java_deobscurify.model.Visibility;
import com.github.marook.java_deobscurify.util.Equalator;

public class MethodSignatureEqualatorTest {

	private Equalator<MethodDeclaration> equalator;

	@Before
	public void setUpEqualator() {
		equalator = new MethodSignatureEqualator();
	}

	@Test
	public void equalSignatureButDifferentName() {
		final Parameter[] parameters = new Parameter[] { new Parameter(
				new Type("java.util.Date")) };

		final MethodDeclaration mdThis = new MethodDeclaration(
				Visibility.PACKAGE, "thisName", new Type("void"),
				Arrays.asList(parameters));

		final MethodDeclaration mdThat = new MethodDeclaration(
				Visibility.PACKAGE, "thatName", new Type("void"),
				Arrays.asList(parameters));

		Assert.assertTrue(equalator.equalTo(mdThis, mdThat));
	}

	@Test
	public void notEqualSignatureBecauseOfReturnType() {
		final Parameter[] parameters = new Parameter[] { new Parameter(
				new Type("java.util.Date")) };

		final MethodDeclaration mdThis = new MethodDeclaration(
				Visibility.PACKAGE, "name", new Type("void"),
				Arrays.asList(parameters));

		final MethodDeclaration mdThat = new MethodDeclaration(
				Visibility.PACKAGE, "name", new Type("int"),
				Arrays.asList(parameters));

		Assert.assertFalse(equalator.equalTo(mdThis, mdThat));
	}

	@Test
	public void notEqualSignatureBecauseOfParameterType() {
		final MethodDeclaration mdThis = new MethodDeclaration(
				Visibility.PACKAGE, "name", new Type("void"),
				Arrays.asList(new Parameter[] { new Parameter(new Type(
						"java.util.Date")) }));

		final MethodDeclaration mdThat = new MethodDeclaration(
				Visibility.PACKAGE, "name", new Type("void"),
				Arrays.asList(new Parameter[] { new Parameter(new Type(
						"java.lang.String")) }));

		Assert.assertFalse(equalator.equalTo(mdThis, mdThat));
	}
}

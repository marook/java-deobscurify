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

package com.github.marook.java_deobscurify.util;

import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import junit.framework.Assert;

public class XCollectionsContainsTest {
	
	@Test
	public void emptyCollectionsNotContainsObject(){
		final Collection<String> c = Collections.emptySet();
		
		Assert.assertFalse(XCollections.contains(c, "x", new Equalator<String>() {
			@Override
			public boolean equalTo(final String o1, final String o2) {
				return true;
			}
		}));
	}
	
	@Test
	public void collectionContainsObject(){
		final Collection<String> c = Collections.singleton("y");
		
		Assert.assertTrue(XCollections.contains(c, "x", new Equalator<String>() {
			@Override
			public boolean equalTo(final String o1, final String o2) {
				return true;
			}
		}));
	}
	
	@Test
	public void collectionNotContainsObject(){
		final Collection<String> c = Collections.singleton("x");
		
		Assert.assertFalse(XCollections.contains(c, "x", new Equalator<String>() {
			@Override
			public boolean equalTo(final String o1, final String o2) {
				return false;
			}
		}));
	}

}

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
import java.util.Iterator;

public class XCollections {

	private XCollections() {
		// this class should only contain static members
		throw new IllegalStateException();
	}

	public static <T> boolean contains(final Collection<T> c, final T o,
			final Equalator<T> e) {
		for (final T t : c) {
			if (!e.equalTo(t, o)) {
				continue;
			}

			return true;
		}

		return false;
	}

	public static <T> boolean retainAll(final Collection<T> c1,
			final Collection<T> c2, final Equalator<T> e) {
		boolean modified = false;
		
		for(final Iterator<T> it = c1.iterator(); it.hasNext();){
			final T o = it.next();
			
			if(contains(c2, o, e)){
				continue;
			}
			
			it.remove();
			modified = true;
		}
		
		return modified;
	}

}

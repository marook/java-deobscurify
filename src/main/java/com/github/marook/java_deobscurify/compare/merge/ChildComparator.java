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

package com.github.marook.java_deobscurify.compare.merge;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;

public class ChildComparator {
	
	private final ArtifactComparator comparator;
	
	private final double weight;
	
	public ChildComparator(final ArtifactComparator comparator, final double weight){
		if(comparator == null){
			throw new IllegalArgumentException();
		}
		
		this.comparator = comparator;
		this.weight = weight;
	}
	
	public ArtifactComparator getComparator() {
		return comparator;
	}
	
	public double getWeight() {
		return weight;
	}

}

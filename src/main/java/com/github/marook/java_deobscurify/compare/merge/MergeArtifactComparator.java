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

import java.util.Collection;

import com.github.marook.java_deobscurify.compare.ArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.DistanceValidator;

public class MergeArtifactComparator implements ArtifactComparator {
	
	private final Collection<ChildComparator> children;
	
	private final double weightSum;
	
	private static double calcWeightSum(final Collection<ChildComparator> children){
		double sum = 0.0d;
		
		for(final ChildComparator c : children){
			sum += c.getWeight();
		}
		
		return sum;
	}
	
	public MergeArtifactComparator(final Collection<ChildComparator> children) {
		if(children.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		this.children = children;
		this.weightSum = calcWeightSum(children);
	}

	@Override
	public double getDistance(final Artifact from, final Artifact to) {
		double distanceSum = 0.0;
		
		for(final ChildComparator c : children){
			final double distance = c.getComparator().getDistance(from, to);
			
			DistanceValidator.validateDistance(distance);
			
			distanceSum += distance * c.getWeight();
		}
		
		return distanceSum / weightSum;
	}

}

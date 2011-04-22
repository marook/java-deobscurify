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

import java.util.ArrayList;
import java.util.Collection;

import com.github.marook.java_deobscurify.compare.count.AbstractCountArtifactComparator;
import com.github.marook.java_deobscurify.model.Artifact;
import com.github.marook.java_deobscurify.model.MethodDeclaration;
import com.github.marook.java_deobscurify.model.TypeDeclaration;
import com.github.marook.java_deobscurify.util.XCollections;

public class MethodsEqualArtifactComparator extends
		AbstractCountArtifactComparator {
	
	@Override
	protected int getNumberOfElements(final Artifact a) {
		int methods = 0;
		
		for(final TypeDeclaration td : a.getTypeDeclarations()){
			methods += td.getMethods().size();
		}
		
		return methods;
	}
	
	private Collection<MethodDeclaration> getMethods(final Artifact a){
		final Collection<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
		
		for(final TypeDeclaration td : a.getTypeDeclarations()){
			methods.addAll(td.getMethods());
		}
		
		return methods;
	}
	
	@Override
	protected int getNumberOfEqualElements(final Artifact a1, final Artifact a2) {
		final Collection<MethodDeclaration> a1Methods = getMethods(a1); 
		final Collection<MethodDeclaration> a2Methods = getMethods(a2);
		
		XCollections.retainAll(a1Methods, a2Methods, new MethodSignatureEqualator());
		
		return a1Methods.size();
	}

}

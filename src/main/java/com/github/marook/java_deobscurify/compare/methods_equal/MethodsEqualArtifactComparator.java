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

		for (final TypeDeclaration td : a.getTypeDeclarations()) {
			methods += td.getMethods().size();
		}

		return methods;
	}

	@Override
	protected int getNumberOfEqualElements(final Artifact a1, final Artifact a2) {
		final Collection<MethodDeclaration> a1Methods = a1.getAllMethods();
		final Collection<MethodDeclaration> a2Methods = a2.getAllMethods();

		XCollections.retainAll(a1Methods, a2Methods,
				new MethodSignatureEqualator());

		return Math.min(a1Methods.size(), a2Methods.size());
	}

}

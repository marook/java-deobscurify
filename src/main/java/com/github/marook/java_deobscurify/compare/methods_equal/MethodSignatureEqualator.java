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

import com.github.marook.java_deobscurify.model.MethodDeclaration;
import com.github.marook.java_deobscurify.util.Equalator;

public class MethodSignatureEqualator implements Equalator<MethodDeclaration> {

	@Override
	public boolean equalTo(final MethodDeclaration o1,
			final MethodDeclaration o2) {
		if(!o1.getReturnType().equals(o2.getReturnType())){
			return false;
		}
		
		return o1.getParameters().equals(o2.getParameters()); 
	}

}

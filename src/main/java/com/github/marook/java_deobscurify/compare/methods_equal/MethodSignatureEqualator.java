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

import java.util.List;

import com.github.marook.java_deobscurify.model.MethodDeclaration;
import com.github.marook.java_deobscurify.model.Parameter;
import com.github.marook.java_deobscurify.model.Type;
import com.github.marook.java_deobscurify.util.Equalator;

public class MethodSignatureEqualator implements Equalator<MethodDeclaration> {

	private final Equalator<Type> typeEqualator = new ObfuscationTypeEqualator();

	private boolean isParameterTypesEqual(final MethodDeclaration m1,
			final MethodDeclaration m2) {
		final List<Parameter> ps1 = m1.getParameters();
		final List<Parameter> ps2 = m2.getParameters();

		final int l1 = ps1.size();
		final int l2 = ps2.size();

		if (l1 != l2) {
			return false;
		}

		for (int i = 0; i < l1; ++i) {
			final Parameter p1 = ps1.get(i);
			final Parameter p2 = ps2.get(i);

			if (!typeEqualator.equalTo(p1.getType(), p2.getType())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean equalTo(final MethodDeclaration o1,
			final MethodDeclaration o2) {
		if (!typeEqualator.equalTo(o1.getReturnType(), o2.getReturnType())) {
			return false;
		}

		return isParameterTypesEqual(o1, o2);
	}

}

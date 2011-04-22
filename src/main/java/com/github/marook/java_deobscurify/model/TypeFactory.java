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

package com.github.marook.java_deobscurify.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class TypeFactory {
	
	private static final Set<String> BUILT_IN_TYPES = new HashSet<String>();
	
	static {
		BUILT_IN_TYPES.add("byte");
		BUILT_IN_TYPES.add("char");
		BUILT_IN_TYPES.add("short");
		BUILT_IN_TYPES.add("int");
		BUILT_IN_TYPES.add("long");
		BUILT_IN_TYPES.add("float");
		BUILT_IN_TYPES.add("double");
		BUILT_IN_TYPES.add("void");
	}

	private final String currentPackage;
	
	private final List<String> imports;

	private static void validateImports(final List<String> imports) {
		for (final String i : imports) {
			if (i.trim().endsWith("*")) {
				throw new IllegalArgumentException(
						"Can't handle wildcard imports like " + i);
			}
		}
	}

	public TypeFactory(final String currentPackage, final List<String> imports) {
		if(currentPackage == null){
			throw new IllegalArgumentException();
		}
		validateImports(imports);

		this.currentPackage = currentPackage;
		this.imports = Collections.unmodifiableList(imports);
	}
	
	private String getImport(final String typeName){
		// TODO escape typeName
		final Pattern p = Pattern.compile("(^|(.+[.]))" + typeName + "$");
		
		for(final String i : imports){
			if(!p.matcher(i).matches()){
				continue;
			}
			
			return i;
		}
		
		return null;
	}
	
	private boolean isFullyQualifiedName(final String typeName){
		return typeName.contains(".");
	}

	public Type getType(final String typeName) {
		if(isFullyQualifiedName(typeName)){
			return new Type(typeName);
		}
		
		if(BUILT_IN_TYPES.contains(typeName)){
			// TODO store built in types and do not regenerate them
			return new Type(typeName);
		}
		
		final String importType = getImport(typeName);
		if(importType != null){
			return new Type(importType);
		}
		
		if(currentPackage.isEmpty()){
			return new Type(typeName);
		}
		
		return new Type(currentPackage + "." + typeName);
	}

}

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
import java.util.Set;

public class Artifact {
	
	private final String name;
	
	private final Set<String> imports;
	
	public Artifact(final String name, final Set<String> imports){
		if(name == null){
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		this.imports = Collections.unmodifiableSet(imports);
	}
	
	public String getName(){
		return name;
	}
	
	public Set<String> getImports(){
		return imports;
	}
	
}

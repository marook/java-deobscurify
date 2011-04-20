package com.github.marook.java_deobscurify.compare;

import com.github.marook.java_deobscurify.model.Artifact;

public interface ArtifactComparator {
	
	/**
	 * @return Returns 0.0 when the two Artifacts are identical. Returns 1.0
	 * when the two Artifacts are completely different.
	 */
	double getDistance(Artifact from, Artifact to);

}

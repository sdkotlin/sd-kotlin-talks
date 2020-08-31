package org.sdkotlin.intro.java._22_nullsafety;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NullSafetyInJava {

	public static final String MAYBE_NULL = null;

	@NotNull
	public static final String NOT_NULL = "";

	@Nullable
	public static final String NULL = null;

	@NotNull
	public static final String SURPRISE = null;
}

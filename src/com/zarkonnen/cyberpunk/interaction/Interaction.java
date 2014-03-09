package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;

public interface Interaction<T> {
	public Person actor();
	public T target();
	public String description();
	public boolean enabled();
	public String run();
}

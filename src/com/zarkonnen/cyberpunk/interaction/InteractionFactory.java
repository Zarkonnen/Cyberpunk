package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import java.util.List;

public interface InteractionFactory<T, I extends Interaction<T>> {
	public List<I> make(Person actor, T t);
}

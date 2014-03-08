package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public interface TileInteractionFactory<T extends TileInteraction> {
	public List<T> make(Person actor, Tile t);
}

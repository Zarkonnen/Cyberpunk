package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;

public interface TileInteraction {
	public Person actor();
	public Tile target();
	public String description();
	public boolean enabled();
	public String run();
}

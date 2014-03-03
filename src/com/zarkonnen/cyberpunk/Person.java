package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public class Person implements Serializable {
	private Tile location;
	
    public Tile location() {
		return location;
	}
	
	public void moveBy(Direction d) {
		if (location.passable(d)) {
			moveTo(location.in(d));
		}
	}
	
	public void moveTo(Tile location) {
		this.location = location;
	}

	public Person(Tile location) {
		this.location = location;
	}
	
	
}

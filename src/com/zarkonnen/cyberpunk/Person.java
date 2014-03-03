package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public class Person implements Serializable {
	private Tile location;
	
    public Tile location() {
		return location;
	}
	
	public void moveBy(int dx, int dy, int dz) {
		moveTo(location.map.at(location.x + dx, location.y + dy, location.z + dz));
	}
	
	public void moveTo(Tile location) {
		this.location = location;
	}

	public Person(Tile location) {
		this.location = location;
	}
	
	
}

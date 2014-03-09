package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;

public class Person implements Serializable {
	private Tile location;
	public String message;
	public int health = 100;
	public int money = 0;
	
	public final EnumMap<Skill, Integer> skills = new EnumMap<Skill, Integer>(Skill.class);
	public final ArrayList<Item> inventory = new ArrayList<Item>();
	
	public boolean test(Skill sk, int vs) {
		return location.map.test(100 + skills.get(sk) - vs);
	}

	public Person(Tile location) {
		this.location = location;
		for (Skill sk : Skill.values()) {
			skills.put(sk, 0);
		}
	}
	
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
}

package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;

public class Person implements Serializable, HasName {
	private Tile location;
	public String message;
	public int hunger = 0;
	public int exhaustion = restedPoint();
	public int reputation = 100;
	public int minDealRep = 50;
	public int health = 100;
	public int money = 0;
	public Tile home;
	public Tile workplace;
	public int stunned;
	public boolean dead = false;
	public boolean isPlayer;
	
	private final EnumMap<Skill, Integer> skills = new EnumMap<Skill, Integer>(Skill.class);
	public final ArrayList<Item> inventory = new ArrayList<Item>();
	public final ArrayList<Item> implants = new ArrayList<Item>();
	public final ArrayList<Item> drugsTaken = new ArrayList<Item>();
	public final ArrayList<Item> drugsLingering = new ArrayList<Item>();
	
	public Person(Tile location) {
		this.location = location;
		for (Skill sk : Skill.values()) {
			skills.put(sk, 0);
		}
	}
	
	public boolean unconscious() {
		return health <= 0 || stunned > 0 || exhaustion >= 100;
	}
	
	// qqDPS
	public boolean willBuyForSelf(Item it) {
		return true;
	}
	
	public boolean willForWork(Item it) {
		return true;
	}
	
	public boolean willSell(Item it) {
		return true;
	}
	
	@Override
	public String getName() {
		return "some person"; // qqDPS
	}
	
	public String description() {
		return "A nondescript humanoid blob.";
	}
	
	public int getSkill(Skill sk) {
		return skills.get(sk);
	}
	
	public boolean test(int vs, Skill... sks) {
		int skillSum = 0;
		for (Skill sk : sks) {
			skillSum += getSkill(sk);
		}
		return location.map.test(100 + skillSum - vs);
	}
	
	// qqDPS modify re: drugs
	public int restedPoint() {
		return 20;
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

	public boolean hasItem(ItemType itemType) {
		for (Item item : inventory) {
			if (item.type == itemType) { return true; }
		}
		return false;
	}
}

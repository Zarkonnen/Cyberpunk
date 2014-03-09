package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;

public class Person implements Serializable, HasName {
	public static final int BASE_EXHAUSTION = 20;
	public static final int EX_WIRED = 20; public static final double EX_WIRED_MULT = 1.2;
	public static final int EX_TIRED = 40; public static final double EX_TIRED_MULT = 0.9;
	public static final int EX_VERY_TIRED = 60; public static final double EX_VERY_TIRED_MULT = 0.8;
	public static final int EX_EXHAUSTED = 80; public static final double EX_EXHAUSTED_MULT = 0.6;
	public static final int H_BRUISED = 80; public static final double H_BRUISED_MULT = 1.0;
	public static final int H_INJURED = 60; public static final double H_INJURED_MULT = 0.9;
	public static final int H_BADLY_INJURED = 40; public static final double H_BADLY_INJURED_MULT = 0.8;
	public static final int H_VERY_BADLY_INJURED = 20; public static final double H_VERY_BADLY_INJURED_MULT = 0.6;
	public static final int H_HUNGRY = 50; public static final double H_HUNGRY_MULT = 0.9;
	public static final int H_VERY_HUNGRY = 75; public static final double H_VERY_HUNGRY_MULT = 0.8;
	
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
		int base = unconscious() ? 0 : skills.get(sk);
		int bonus = 0;
		for (ItemType t : ItemType.values()) {
			if (hasItem(t)) {
				bonus += t.skillBonuses.get(sk);
			}
		}
				
		if (sk == Skill.COUNTER_INTRUSION) {
			return (int) (base * getSkillMultiplier()) + bonus;
		} else {
			if (unconscious()) { return 0; }
			return (int) ((base + bonus) * getSkillMultiplier());
		}
	}
	
	public boolean test(int vs, Skill... sks) {
		int skillSum = 0;
		for (Skill sk : sks) {
			skillSum += getSkill(sk);
		}
		return location.map.test(100 + skillSum - vs);
	}
	
	public double getSkillMultiplier() {
		double mult = 1.0;
		int ex = getVisibleExhaustion();
		if (ex < EX_WIRED) {
			mult *= EX_WIRED_MULT;
		} else if (ex < EX_TIRED) {
			mult *= 1.0;
		} else if (ex < EX_VERY_TIRED) {
			mult *= EX_TIRED_MULT;
		} else if (ex < EX_EXHAUSTED) {
			mult *= EX_VERY_TIRED_MULT;
		} else {
			mult *= EX_EXHAUSTED_MULT;
		}
		if (health > H_BRUISED) {
			mult *= 1.0;
		} else if (health > H_INJURED) {
			mult *= H_BRUISED_MULT;
		} else if (health > H_BADLY_INJURED) {
			mult *= H_INJURED_MULT;
		} else if (health > H_VERY_BADLY_INJURED) {
			mult *= H_BADLY_INJURED_MULT;
		} else {
			mult *= H_VERY_BADLY_INJURED_MULT;
		}
		if (hunger < H_HUNGRY) {
			mult *= 1.0;
		} else if (hunger < H_VERY_HUNGRY) {
			mult *= H_HUNGRY_MULT;
		} else {
			mult *= H_VERY_HUNGRY_MULT;
		}
		
		return mult;
	}
	
	public int restedPoint() {
		int pt = BASE_EXHAUSTION;
		for (Item drug : drugsLingering) {
			pt += drug.type.drugAddictionExhaustionBaseModifier;
		}
		return pt;
	}
	
	public int getVisibleExhaustion() {
		int ex = exhaustion;
		for (Item drug : drugsTaken) {
			ex += drug.type.exhaustionModifier;
		}
		return Math.max(0, ex);
	}
	
	public void addExhaustion(int amt) {
		exhaustion += amt * exhaustionGainMultiplier();
	}
	
	public void removeExhaustion(int amt) {
		exhaustion = Math.max(restedPoint(), exhaustion - (int) (amt * exhaustionLossMultiplier()));
	}
	
	public double exhaustionLossMultiplier() {
		double mod = 0.0;
		for (Item drug : drugsTaken) {
			mod += drug.type.exhaustionLossModifier;
		}
		return mod + 1.0;
	}
	
	public double exhaustionGainMultiplier() {
		double mod = 0.0;
		for (Item drug : drugsTaken) {
			mod += drug.type.exhaustionGainModifier;
		}
		return mod + 1.0;
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

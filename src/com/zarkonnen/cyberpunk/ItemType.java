package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.EnumMap;
import static com.zarkonnen.catengine.util.Utils.*;
import static com.zarkonnen.cyberpunk.Skill.*;

public enum ItemType implements Serializable {
	RAM(
		"RAM Stick", /* name */ 
		"A stick of RAM.", /* description */ 
		50, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* weapon */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* drugExhaustionModifier */ 
		0, /* drugExhaustionGainModifier */ 
		0, /* drugExhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	);

	private ItemType(String name, String description, int value, boolean key, boolean data, boolean implant, boolean blackmailMaterial, int drone, int bug, int weapon, int medicine, int food, int stun, int drugExhaustionModifier, double drugExhaustionGainModifier, double drugExhaustionLossModifier, int drugDuration, int drugAddictionDuration, int drugAdditionExhaustionBaseModifier, EnumMap<Skill, Integer> skillBonuses) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.key = key;
		this.data = data;
		this.implant = implant;
		this.blackmailMaterial = blackmailMaterial;
		this.drone = drone;
		this.bug = bug;
		this.weapon = weapon;
		this.medicine = medicine;
		this.food = food;
		this.stun = stun;
		this.drugExhaustionModifier = drugExhaustionModifier;
		this.drugExhaustionGainModifier = drugExhaustionGainModifier;
		this.drugExhaustionLossModifier = drugExhaustionLossModifier;
		this.drugDuration = drugDuration;
		this.drugAddictionDuration = drugAddictionDuration;
		this.drugAdditionExhaustionBaseModifier = drugAdditionExhaustionBaseModifier;
		this.skillBonuses = new EnumMap<Skill, Integer>(Skill.class);
		for (Skill sk : Skill.values()) {
			if (skillBonuses.containsKey(sk)) {
				this.skillBonuses.put(sk, skillBonuses.get(sk));
			} else {
				this.skillBonuses.put(sk, 0);
			}
		}
	}
	
	public final String name;
	public final String description;
	public final int value;
	public final boolean key;
	public final boolean data;
	public final boolean implant;
	public final boolean blackmailMaterial;
	public final int drone;
	public final int bug;
	public final int weapon;
	public final int medicine;
	public final int food;
	public final int stun;
	public final int drugExhaustionModifier;
	public final double drugExhaustionGainModifier;
	public final double drugExhaustionLossModifier;
	public final int drugDuration;
	public final int drugAddictionDuration;
	public final int drugAdditionExhaustionBaseModifier;
	public final EnumMap<Skill, Integer> skillBonuses;
}

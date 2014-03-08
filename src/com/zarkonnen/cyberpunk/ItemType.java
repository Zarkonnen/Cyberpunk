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
	),
	AR_GOGGLE_VIRUS(
		"AR goggle virus", /* name */ 
		"Distract someone by screwing up their augmented view on the world.", /* description */ 
		150, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		50, /* weapon */ 
		0, /* medicine */ 
		0, /* food */ 
		50, /* stun */ 
		0, /* drugExhaustionModifier */ 
		0, /* drugExhaustionGainModifier */ 
		0, /* drugExhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		new EnumMap<Skill, Integer>(m(p(RUNNING_AWAY, 50)))/* skillBonuses */ 
	),
	GUARD_DRONE(
		"Guard drone", /* name */ 
		"A weaonised defense drone.", /* description */ 
		1000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		100, /* drone */ 
		0, /* bug */ 
		90, /* weapon */ 
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
	),
	REMOTE_WIRETAP(
		"Remote wiretap", /* name */ 
		"Device to record remote data exchange and communication.", /* description */ 
		300, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		100, /* bug */ 
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
	),
	WIRETAP(
		"Wiretap", /* name */ 
		"Device to record local data exchange and communication.", /* description */ 
		150, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		100, /* bug */ 
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
	),
	ENDURANCE_UPGRADE(
		"Endurance upgrade", /* name */ 
		"Bioactive mod that increases your endurance.", /* description */ 
		300, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
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
	),
	MEDICAL_HELPER_IMPLANT(
		"Medical helper implant", /* name */ 
		"An implant to support your surgical skills.", /* description */ 
		450, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
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
		new EnumMap<Skill, Integer>(m(p(GRINDING, 70)))/* skillBonuses */ 
	),
	BLACKMAIL_MATERIAL(
		"Blackmail material", /* name */ 
		"Evidence that someone has been up to something they really shouldn't have.", /* description */ 
		1500, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		true, /* blackmailMaterial */ 
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
	),
	GRINDER_TOOLS(
		"Grinder tools", /* name */ 
		"Equipment for serious body modifications.", /* description */ 
		200, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* weapon */ 
		50, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* drugExhaustionModifier */ 
		0, /* drugExhaustionGainModifier */ 
		0, /* drugExhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		new EnumMap<Skill, Integer>(m(p(GRINDING, 60)))/* skillBonuses */ 
	),
	ART(
		"Art", /* name */ 
		"Is it art? Well, there are art collectors who'd kill for it.", /* description */ 
		2000, /* value */ 
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
	),
	JEWELLERY(
		"Jewellery", /* name */ 
		"Expensive adornments.", /* description */ 
		700, /* value */ 
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
	),
	DESIGNER_CLOTHES(
		"Designer clothes", /* name */ 
		"Fashionable, ugly clothing.", /* description */ 
		250, /* value */ 
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
	),
	GENETIC_CODE(
		"Genetic code", /* name */ 
		"The valuable results of extensive research.", /* description */ 
		1500, /* value */ 
		false, /* key */ 
		true, /* data */ 
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
	),
	OPTICAL_CAMO(
		"Optical camo", /* name */ 
		".", /* description */ 
		300, /* value */ 
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
		new EnumMap<Skill, Integer>(m(p(HIDING, 80)))/* skillBonuses */ 
	),
	BUG(
		"Bug", /* name */ 
		"Device to capture nearby images and sounds.", /* description */ 
		150, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		100, /* bug */ 
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
	),
	MIRRORSHADES(
		"Mirrorshades", /* name */ 
		"Cover your eyes like a real hacker.", /* description */ 
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
		new EnumMap<Skill, Integer>(m(p(FORCE_OF_PERSONALITY, 20)))/* skillBonuses */ 
	),
	SHARES(
		"Shares", /* name */ 
		".", /* description */ 
		2000, /* value */ 
		false, /* key */ 
		true, /* data */ 
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

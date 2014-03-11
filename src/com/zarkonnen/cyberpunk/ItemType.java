package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.EnumMap;
import static com.zarkonnen.catengine.util.Utils.*;
import static com.zarkonnen.cyberpunk.Skill.*;
import java.util.EnumSet;

public enum ItemType implements Serializable {
	RAM(
		"RAM Stick", /* name */ 
		"An old stick of RAM for a non-wearable computer.", /* description */ 
		50, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	MACHINE_PARTS(
		"Machine Parts", /* name */ 
		"Wires, motors, sensors. Someone skilled could put this together into something useful.", /* description */ 
		20, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	CPUS(
		"CPUs", /* name */ 
		"State of the art processing cores.", /* description */ 
		100, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	AR_GOGGLES(
		"AR Goggles", /* name */ 
		"NuvaTron 900 Augmented Reality Goggles. What everyone is wearing these days.", /* description */ 
		200, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 10)))/* skillBonuses */ 
	),
	FANCY_AR_GOGGLES(
		"Expert AR Goggles", /* name */ 
		"Clunky but powerful Katana XL augmented reality goggles.", /* description */ 
		800, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		3000, /* maxAge */
		"Your fancy AR goggles finally broke.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 25), p(FORCE_OF_PERSONALITY, -15)))/* skillBonuses */ 
	),
	SHARP_SUIT(
		"Sharp Suit", /* name */ 
		"This outfit screams power and money.", /* description */ 
		1200, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		4000, /* maxAge */
		"Your suit is no longer looking so sharp.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FORCE_OF_PERSONALITY, 10)))/* skillBonuses */ 
	),
	VALUABLE_DATA(
		"Valuable Data", /* name */ 
		"Rows and rows of numbers. You don't care what they are, only what someone might pay you for them.", /* description */ 
		500, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1000, /* maxAge */
		"That data you were carrying around? No longer relevant.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 0)))/* skillBonuses */ 
	),
	BOOTLEG_ENTERTAINMENT(
		"Bootleg Entertainment", /* name */ 
		"This week's movies.", /* description */ 
		5, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1000, /* maxAge */
		"You delete some old movies.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 0)))/* skillBonuses */ 
	),
	AMATEUR_SEX_VID(
		"Amateur Sex Vid", /* name */ 
		"Most people keep on their glasses to have sex. The resulting videos sometimes go... astray.", /* description */ 
		15, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 0)))/* skillBonuses */ 
	),
	SECURITY_KEY(
		"Security Keycard", /* name */ 
		"This opens a door somewhere.", /* description */ 
		100, /* value */ 
		true, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	PASSWORD(
		"Password", /* name */ 
		"The usual unimaginative sequence of letters and numbers. It opens a door somewhere.", /* description */ 
		100, /* value */ 
		true, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	EXPERIMENTAL_UPPERS(
		"Brainhancers", /* name */ 
		"Experimental mind-enhancing drugs.", /* description */ 
		200, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		-30, /* exhaustionModifier */ 
		0.5, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		20, /* drugDuration */ 
		200, /* drugAddictionDuration */ 
		3, /* drugAdditionExhaustionBaseModifier */ 
		2000, /* maxAge */
		"You throw away some pills that have gone weird and crumbly.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	EXPERIMENTAL_DOWNERS(
		"Relaxo-Serum", /* name */ 
		"Experimental anti-stress meds.", /* description */ 
		200, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		1, /* exhaustionLossModifier */ 
		50, /* drugDuration */ 
		500, /* drugAddictionDuration */ 
		5, /* drugAdditionExhaustionBaseModifier */ 
		2000, /* maxAge */
		"You throw away some pills that have gone weird and crumbly.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	CHEMICALS(
		"Precursors", /* name */ 
		"Chemicals needed for drug production.", /* description */ 
		5, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	UPPERS(
		"Uppers", /* name */ 
		"These pills make your mind go faster, at a cost.", /* description */ 
		30, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		-20, /* exhaustionModifier */ 
		1, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		20, /* drugDuration */ 
		500, /* drugAddictionDuration */ 
		5, /* drugAdditionExhaustionBaseModifier */ 
		10000, /* maxAge */
		"You throw away some pills that have gone weird and crumbly.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	DOWNERS(
		"Downers", /* name */ 
		"You have to relax somehow, right.", /* description */ 
		30, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0.3, /* exhaustionLossModifier */ 
		50, /* drugDuration */ 
		1000, /* drugAddictionDuration */ 
		5, /* drugAdditionExhaustionBaseModifier */ 
		10000, /* maxAge */
		"You throw away some pills that have gone weird and crumbly.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	STRENGTH_ENHANCEMENT(
		"Strength Enhancement", /* name */ 
		"Artificial spidersilk muscles interwoven with your real ones.", /* description */ 
		3000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 20), /*p(RUNNING_AWAY, 20), */p(BREAKING_AND_ENTERING, 30)))/* skillBonuses */ 
	),
	MULTIWAVE_EYES(
		"Multiwave Eyes", /* name */ 
		"These ocular implants let you cycle through a whole bunch of different ways of seeing the world, all of them useful.", /* description */ 
		3000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 20), p(OBSERVATION, 30), p(GRINDING, 10)))/* skillBonuses */ 
	),
	ANGLE_GRINDER(
		"Angle Grinder", /* name */ 
		"Cuts through most things like butter. A bit noisier than a butter knife, though.", /* description */ 
		60, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 10), p(BREAKING_AND_ENTERING, 20)))/* skillBonuses */ 
	),
	LOCKPICKS(
		"Lockpicks", /* name */ 
		"A decent set of lockpicks.", /* description */ 
		30, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(BREAKING_AND_ENTERING, 30)))/* skillBonuses */ 
	),
	NANO_PICKS(
		"Nano-picks", /* name */ 
		"This goo can slither into the most complex electronic locks and persuade them to open.", /* description */ 
		2000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(BREAKING_AND_ENTERING, 60)))/* skillBonuses */ 
	),
	CRYPTOGRAPHIC_COPROCESSOR(
		"Cryptographic Coprocessor", /* name */ 
		"Being able to do cryptography in your head is pretty handy.", /* description */ 
		1500, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		20, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 20)))/* skillBonuses */ 
	),
	QUANTUM_CORE(
		"Quantum Core", /* name */ 
		"Calculate everything at once. In your head. Try not to get dizzy.", /* description */ 
		20000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		90, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 60)))/* skillBonuses */ 
	),
	TRANQ_DART(
		"Tranq Dart", /* name */ 
		"A discreet single tranquilizer dart, spring-operated. Hope your aim is good.", /* description */ 
		50, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		30, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	TRANQ_BUG(
		"Tranq Bug", /* name */ 
		"Buzzes up to the target of your choice and puts them to sleep. A favorite.", /* description */ 
		400, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		90, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	SHIV(
		"Shiv", /* name */ 
		"It's sharp. Stick it in someone to make them bleed.", /* description */ 
		1, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 10)))/* skillBonuses */ 
	),
	SWITCHBLADE(
		"Switchblade", /* name */ 
		"Prized as much for its classic air of menace as for its blade.", /* description */ 
		10, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 20), p(FORCE_OF_PERSONALITY, 5)))/* skillBonuses */ 
	),
	SPEAR(
		"Spear", /* name */ 
		"A broom handle, a kitchen knife, and some ingenuity make an unsubtle weapon.", /* description */ 
		5, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 20), p(FORCE_OF_PERSONALITY, -20)))/* skillBonuses */ 
	),
	PISTOL(
		"Pistol", /* name */ 
		"The classic \"self-defence\" weapon, or so the manufacturers claim.", /* description */ 
		200, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 35)))/* skillBonuses */ 
	),
	ASSAULT_RIFLE(
		"Assault Rifle", /* name */ 
		"For when you need a lot of people dead.", /* description */ 
		400, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 50)))/* skillBonuses */ 
	),
	MEDICAL_SUPPLIES(
		"Medical Supplies", /* name */ 
		"Pills, bandages, disinfectant, syringes. Medical stuff. Puts you back together.", /* description */ 
		80, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		10, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		10000, /* maxAge */
		"You throw away some old medical supplies that have gone bad.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	SNACKS(
		"Snacks", /* name */ 
		"Various crinkly packets of unhealthy food.", /* description */ 
		3, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		10, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		5000, /* maxAge */
		"You throw away some moldy snacks you were still carrying around for some reason.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	VEGETABLES(
		"Vegetables", /* name */ 
		"Fresh food from the farm.", /* description */ 
		10, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		20, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		500, /* maxAge */
		"You throw away some rotten vegetables.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	RATIONS(
		"Rations", /* name */ 
		"Military rations. No idea how these ended up here.", /* description */ 
		20, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		35, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	ROOTKIT(
		"Rootkit", /* name */ 
		"Your standard tool for getting into where you shouldn't.", /* description */ 
		100, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		2000, /* maxAge */
		"You delete an utterly outdated rootkit.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 20)))/* skillBonuses */ 
	),
	ADVANCED_ROOTKIT(
		"Advanced Rootkit", /* name */ 
		"Put together by the Shining Tiger Hacker Army.", /* description */ 
		500, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		2000, /* maxAge */
		"Your once so advanced rootkit is no longer cutting edge.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 40)))/* skillBonuses */ 
	),
	INTRUSION_EXPERT_SYSTEM(
		"Intrusion Expert System", /* name */ 
		"If there is a way into a computer, this will probably find it. Don't call it an AI though, it doesn't like that.", /* description */ 
		5000, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1500, /* maxAge */
		"Things move rapidly in the computer world. Your intrusion expert system is no longer worth the bytes it's written in.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(HACKING, 70)))/* skillBonuses */ 
	),
	ANTIVIRUS_SOFTWARE(
		"Antivirus Software", /* name */ 
		"Does a decent job at maintaining basic security. Ignore the popups.", /* description */ 
		20, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1000, /* maxAge */
		"Well, looks like your antivirus is out of date again.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(COUNTER_INTRUSION, 15)))/* skillBonuses */ 
	),
	OPERATING_SYSTEM_HARDENER(
		"OS Hardener", /* name */ 
		"Gives your operating system a once-over to harden it against intrusion.", /* description */ 
		200, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		4000, /* maxAge */
		"That OS hardener you were using? Not state of the art anymore.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(COUNTER_INTRUSION, 35)))/* skillBonuses */ 
	),
	COUNTER_INTRUSION_SYSTEM(
		"Counter-Intrusion System", /* name */ 
		"A Black Ice brand active counter-intrusion system. Industry standard.", /* description */ 
		2000, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		3000, /* maxAge */
		"You realize that your fancy counter-intrusion system is completely outdated by now.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(COUNTER_INTRUSION, 60)))/* skillBonuses */ 
	),
	AR_GOGGLE_VIRUS(
		"AR Goggle Virus", /* name */ 
		"Distract someone by screwing up their augmented view on the world.", /* description */ 
		800, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1000, /* maxAge */
		"Well, looks like they patched that vulnerability. Time for that Goggle virus to go to the trash.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(/*p(RUNNING_AWAY, 40)*/p(FIGHTING, 20)))/* skillBonuses */ 
	),
	GUARD_DRONE(
		"Guard Drone", /* name */ 
		"A weaponised defense drone.", /* description */ 
		1000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		60, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	REMOTE_WIRETAP(
		"Remote Wiretap", /* name */ 
		"Device to record remote data exchange and communication.", /* description */ 
		300, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		60, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		500, /* maxAge */
		"The antenna in your remote wiretap is busted. You throw it away.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	WIRETAP(
		"Wiretap", /* name */ 
		"Device to record local data exchange and communication.", /* description */ 
		100, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		25, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		10000, /* maxAge */
		"Some wiring broke in your wiretap. You trash it.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	ENDURANCE_UPGRADE(
		"Endurance Upgrade", /* name */ 
		"Bioactive mod that increases your endurance.", /* description */ 
		3000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		-0.4, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	MEDICAL_HELPER_IMPLANT(
		"Medical Helper Implant", /* name */ 
		"An implant to support your surgical skills.", /* description */ 
		4500, /* value */ 
		false, /* key */ 
		false, /* data */ 
		true, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		1.5, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(GRINDING, 50)))/* skillBonuses */ 
	),
	BLACKMAIL_MATERIAL(
		"Blackmail Material", /* name */ 
		"Evidence that someone has been up to something they really shouldn't have.", /* description */ 
		1500, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		true, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		8000, /* maxAge */
		"OK, by this point no one cares about that old blackmail material you were keeping around. You delete it.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	GRINDER_TOOLS(
		"Grinder Tools", /* name */ 
		"Equipment for serious body modifications.", /* description */ 
		500, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(GRINDING, 60)))/* skillBonuses */ 
	),
	ART(
		"Art", /* name */ 
		"Is it art? Well, there are art collectors who'd kill for it.", /* description */ 
		800, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
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
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	DESIGNER_CLOTHES(
		"Designer Clothes", /* name */ 
		"Fashionable, ugly clothing.", /* description */ 
		150, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1000, /* maxAge */
		"Looks like your clothes aren't fashionable anymore. Happens constantly.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	GENETIC_CODE(
		"Genetic Code", /* name */ 
		"The valuable results of extensive research.", /* description */ 
		700, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		10000, /* maxAge */
		"You realize that that genetic data you were carrying around is by now no longer interesting. You delete it.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	),
	OPTICAL_CAMO(
		"Optical Camo", /* name */ 
		"It's literally an invisibility cloak.", /* description */ 
		5000, /* value */ 
		false, /* key */ 
		false, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		0, /* maxAge */
		null, /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FIGHTING, 10)/*, p(HIDING, 60)*/))/* skillBonuses */ 
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
		30, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		3000, /* maxAge */
		"Some wiring broke in this bug. You bin it.", /* disintegrationMsg */
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
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		2000, /* maxAge */
		"Damn! Your mirrorshades broke!", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(FORCE_OF_PERSONALITY, 20)))/* skillBonuses */ 
	),
	SHARES(
		"Shares", /* name */ 
		"Own a piece of the global corporocracy. Coprolithocracy? Not sure. Valuable, anyway,", /* description */ 
		2000, /* value */ 
		false, /* key */ 
		true, /* data */ 
		false, /* implant */ 
		false, /* blackmailMaterial */ 
		0, /* drone */ 
		0, /* bug */ 
		0, /* decrypt */ 
		0, /* medicine */ 
		0, /* food */ 
		0, /* stun */ 
		0, /* exhaustionModifier */ 
		0, /* exhaustionGainModifier */ 
		0, /* exhaustionLossModifier */ 
		0, /* drugDuration */ 
		0, /* drugAddictionDuration */ 
		0, /* drugAdditionExhaustionBaseModifier */ 
		1000, /* maxAge */
		"One quick glance at the stock market confirms that those shares you'd been holding on are now worthless.", /* disintegrationMsg */
		new EnumMap<Skill, Integer>(m(p(SCAVENGING, 0)))/* skillBonuses */ 
	);

	private ItemType(String name, String description, int value, boolean key, boolean data, boolean implant, boolean blackmailMaterial, int drone, int bug, int decrypt, int medicine, int food, int stun, int exhaustionModifier, double exhaustionGainModifier, double exhaustionLossModifier, int drugDuration, int drugAddictionDuration, int drugAddictionExhaustionBaseModifier, int maxAge, String disintegrateMsg, EnumMap<Skill, Integer> skillBonuses) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.key = key;
		this.data = data;
		this.implant = implant;
		this.blackmailMaterial = blackmailMaterial;
		this.drone = drone;
		this.bug = bug;
		this.decrypt = decrypt;
		this.medicine = medicine;
		this.food = food;
		this.stun = stun;
		this.exhaustionModifier = exhaustionModifier;
		this.exhaustionGainModifier = exhaustionGainModifier;
		this.exhaustionLossModifier = exhaustionLossModifier;
		this.drugDuration = drugDuration;
		this.drugAddictionDuration = drugAddictionDuration;
		this.drugAddictionExhaustionBaseModifier = drugAddictionExhaustionBaseModifier;
		this.maxAge = maxAge;
		this.disintegrateMsg = disintegrateMsg;
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
	public final int decrypt;
	public final int medicine;
	public final int food;
	public final int stun;
	public final int exhaustionModifier;
	public final double exhaustionGainModifier;
	public final double exhaustionLossModifier;
	public final int drugDuration;
	public final int drugAddictionDuration;
	public final int drugAddictionExhaustionBaseModifier;
	public final int maxAge;
	public final String disintegrateMsg;
	public final EnumMap<Skill, Integer> skillBonuses;
	
	public static final EnumSet<ItemType> HARDWARE = EnumSet.of(ItemType.ANGLE_GRINDER, ItemType.AR_GOGGLES, ItemType.ASSAULT_RIFLE, ItemType.CPUS, ItemType.CRYPTOGRAPHIC_COPROCESSOR, ItemType.FANCY_AR_GOGGLES, ItemType.GUARD_DRONE, ItemType.LOCKPICKS, ItemType.MACHINE_PARTS, ItemType.NANO_PICKS, ItemType.OPTICAL_CAMO, ItemType.PISTOL, ItemType.PISTOL, ItemType.RAM, ItemType.REMOTE_WIRETAP, ItemType.SWITCHBLADE, ItemType.TRANQ_BUG, ItemType.TRANQ_DART, ItemType.WIRETAP);
	public static final EnumSet<ItemType> HACKER_KIT = EnumSet.of(ADVANCED_ROOTKIT, ANTIVIRUS_SOFTWARE, AR_GOGGLES, AR_GOGGLE_VIRUS, BUG, COUNTER_INTRUSION_SYSTEM, CRYPTOGRAPHIC_COPROCESSOR, FANCY_AR_GOGGLES, GRINDER_TOOLS, MIRRORSHADES, MULTIWAVE_EYES, PASSWORD, QUANTUM_CORE, REMOTE_WIRETAP, ROOTKIT, SECURITY_KEY, WIRETAP);

}

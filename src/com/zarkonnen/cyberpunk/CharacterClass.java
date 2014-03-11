package com.zarkonnen.cyberpunk;

import java.util.Random;

public enum CharacterClass {
	HACKER("Hacker", "You know machines, if not much else.") {
		@Override
		public void install(Person p, Random r) {
			p.setSkill(Skill.HACKING, 50);
			p.setSkill(Skill.COUNTER_INTRUSION, 50);
			p.setSkill(Skill.GRINDING, 10);
			p.setSkill(Skill.SCAVENGING, 20);
			p.setSkill(Skill.OBSERVATION, 20);
			
			p.inventory.add(new Item(ItemType.AR_GOGGLES));
		}
	},
	SCAVENGER("Scavenger", "Finding things is your business. Who cares who those things belonged to? They're yours now.") {
		@Override
		public void install(Person p, Random r) {
			p.setSkill(Skill.HACKING, 10);
			p.setSkill(Skill.BREAKING_AND_ENTERING, 30);
			p.setSkill(Skill.SCAVENGING, 50);
			p.setSkill(Skill.OBSERVATION, 30);			
		}
	},
	GRINDER("Grinder", "Augments and implants. Improve yourself!") {
		@Override
		public void install(Person p, Random r) {
			p.setSkill(Skill.HACKING, 10);
			p.setSkill(Skill.GRINDING, 60);
			p.setSkill(Skill.SCAVENGING, 20);
			p.setSkill(Skill.FIGHTING, 10);
			
			p.inventory.add(new Item(ItemType.GRINDER_TOOLS));
		}
	},
	SOCIAL_ENGINEER("Social Engineer", "You can be very... convincing if needed.") {
		@Override
		public void install(Person p, Random r) {
			p.setSkill(Skill.HACKING, 30);
			p.setSkill(Skill.FORCE_OF_PERSONALITY, 50);
			p.setSkill(Skill.OBSERVATION, 10);
			
			p.inventory.add(new Item(ItemType.AR_GOGGLES));
		}
	},
	SECURITY_SPECIALIST("Security Specialist", "Keeping things safe can mean a lot of things.") {
		@Override
		public void install(Person p, Random r) {
			p.setSkill(Skill.COUNTER_INTRUSION, 20);
			p.setSkill(Skill.OBSERVATION, 40);
			p.setSkill(Skill.FIGHTING, 20);
			p.setSkill(Skill.BREAKING_AND_ENTERING, 20);
			p.setSkill(Skill.SCAVENGING, 10);
			
			p.inventory.add(new Item(ItemType.AR_GOGGLES));
		}
	},
	BRUISER("Bruiser", "Handy with a knife.") {
		@Override
		public void install(Person p, Random r) {
			p.setSkill(Skill.FORCE_OF_PERSONALITY, 20);
			p.setSkill(Skill.FIGHTING, 50);
			p.setSkill(Skill.GRINDING, 10);
			p.setSkill(Skill.BREAKING_AND_ENTERING, 20);
			p.setSkill(Skill.SCAVENGING, 10);
			
			p.inventory.add(new Item(ItemType.SWITCHBLADE));
		}
	};
		
	public final String name;
	public final String desc;
	
	public abstract void install(Person p, Random r);

	private CharacterClass(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
}

package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Names;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Attack;
import com.zarkonnen.cyberpunk.interaction.BreakIn;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.BuyFoodAtRestaurantOrBar;
import com.zarkonnen.cyberpunk.interaction.BuyFromOutsideWorld;
import com.zarkonnen.cyberpunk.interaction.BuySupply;
import com.zarkonnen.cyberpunk.interaction.Datatrawl;
import com.zarkonnen.cyberpunk.interaction.DeployGadget;
import com.zarkonnen.cyberpunk.interaction.DoWork;
import com.zarkonnen.cyberpunk.interaction.Eat;
import com.zarkonnen.cyberpunk.interaction.Gogglehack;
import com.zarkonnen.cyberpunk.interaction.HarvestGadget;
import com.zarkonnen.cyberpunk.interaction.HarvestImplants;
import com.zarkonnen.cyberpunk.interaction.HealAtClinic;
import com.zarkonnen.cyberpunk.interaction.Loot;
import com.zarkonnen.cyberpunk.interaction.MoveToHome;
import com.zarkonnen.cyberpunk.interaction.MoveToMapEdge;
import com.zarkonnen.cyberpunk.interaction.MoveToSupplier;
import com.zarkonnen.cyberpunk.interaction.MoveToType;
import com.zarkonnen.cyberpunk.interaction.MoveToWork;
import com.zarkonnen.cyberpunk.interaction.Mug;
import com.zarkonnen.cyberpunk.interaction.Rest;
import com.zarkonnen.cyberpunk.interaction.Scavenge;
import com.zarkonnen.cyberpunk.interaction.SellDataToOutsideWorld;
import com.zarkonnen.cyberpunk.interaction.SellToBusiness;
import com.zarkonnen.cyberpunk.interaction.SellToOutsideWorld;
import com.zarkonnen.cyberpunk.interaction.SellToPerson;
import com.zarkonnen.cyberpunk.interaction.Unlock;
import com.zarkonnen.cyberpunk.interaction.Lock;
import com.zarkonnen.cyberpunk.interaction.Stun;
import com.zarkonnen.cyberpunk.interaction.Wander;
import java.util.EnumSet;
import java.util.Random;

public enum Job {
	PROSTITUTE("prostitute", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(12, 23);
			p.behave(DoWork.class).between(12, 23);
			p.behave(Lock.class).between(23, 24);
			p.behave(Lock.class).between(0, 1);
			p.behave(MoveToHome.class).between(0, 9);
			p.behave(MoveToWork.class).between(11, 22);
			p.behave(Rest.class).between(0, 9);
			
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 30, r);
			p.setApproximateSkill(Skill.OBSERVATION, 10, r);
		}
	},
	DRUG_DEALER("dealer", EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.UPPERS, ItemType.DOWNERS, ItemType.EXPERIMENTAL_UPPERS, ItemType.EXPERIMENTAL_DOWNERS), EnumSet.of(ItemType.UPPERS, ItemType.DOWNERS)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(0, 10);
			p.behave(Rest.class).between(0, 9);
			p.behave(MoveToWork.class).hasnt(ItemType.UPPERS, ItemType.DOWNERS, ItemType.EXPERIMENTAL_UPPERS, ItemType.EXPERIMENTAL_DOWNERS);
			p.behave(BuySupply.class).item(ItemType.EXPERIMENTAL_DOWNERS);
			p.behave(BuySupply.class).item(ItemType.EXPERIMENTAL_UPPERS);
			p.behave(BuySupply.class).item(ItemType.DOWNERS);
			p.behave(BuySupply.class).item(ItemType.UPPERS);
			p.behave(Wander.class).between(11, 22);
			
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 10, r);
			p.setApproximateSkill(Skill.OBSERVATION, 20, r);
		}
	},
	FOOD_MERCHANT("food merchant", EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SNACKS, ItemType.VEGETABLES), EnumSet.of(ItemType.VEGETABLES, ItemType.SNACKS)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 4);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 3);
			p.behave(MoveToWork.class).between(3, 8);
			p.behave(BuySupply.class).item(ItemType.VEGETABLES);
			p.behave(MoveToType.class).moveTo(TileType.MARKET).between(8, 19).has(ItemType.VEGETABLES);
			
			p.setApproximateSkill(Skill.FIGHTING, 10, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	TRADER("trader", SellToOutsideWorld.SELLABLES, BuyFromOutsideWorld.BUYABLES, EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(21, 24);
			p.behave(MoveToHome.class).between(0, 3);
			p.behave(Rest.class).between(21, 24);
			p.behave(Rest.class).between(0, 3);
			p.behave(MoveToMapEdge.class).between(3, 12);
			p.behave(SellToOutsideWorld.class);
			p.behave(BuyFromOutsideWorld.class).item(ItemType.CHEMICALS).between(3, 12).hasnt(ItemType.CHEMICALS);
			p.behave(BuyFromOutsideWorld.class).between(4, 12);
			p.behave(MoveToType.class).moveTo(TileType.DOCK).between(12, 20);
			
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 30, r);
		}
	},
	HARDWARE_MERCHANT("hardware merchant", ItemType.HARDWARE, ItemType.HARDWARE, EnumSet.of(ItemType.MACHINE_PARTS, ItemType.AR_GOGGLES)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(20, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(Rest.class).between(20, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(Lock.class).between(15, 16);
			p.behave(MoveToWork.class).between(7, 15);
			p.behave(Unlock.class).between(9, 15);
			p.behave(DoWork.class).between(7, 16);
			p.behave(MoveToSupplier.class).between(16, 19);
			for (ItemType h : ItemType.HARDWARE) {
				p.behave(BuySupply.class).item(h);
				p.behave(SellToPerson.class).item(h);
			}
			
			p.setApproximateSkill(Skill.FIGHTING, 10, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
			p.setApproximateSkill(Skill.HACKING, 20, r);
		}
	},
	GRINDER("grinder", EnumSet.of(ItemType.MEDICAL_SUPPLIES, ItemType.GRINDER_TOOLS, ItemType.ENDURANCE_UPGRADE, ItemType.MEDICAL_HELPER_IMPLANT, ItemType.STRENGTH_ENHANCEMENT, ItemType.MULTIWAVE_EYES), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.GRINDER_TOOLS, ItemType.AR_GOGGLES)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.HACKING, 10, r);
			p.setApproximateSkill(Skill.GRINDING, 50, r);
		}
	},
	V_FARM_WORKER("farm worker", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			p.behave(BuySupply.class).food().hungry(75);
			p.behave(Unlock.class).between(6, 18);
			p.behave(DoWork.class).between(6, 18);
			p.behave(Lock.class).between(18, 20);
			p.behave(MoveToHome.class).between(21, 24);
			p.behave(MoveToHome.class).between(0, 5);
			p.behave(MoveToWork.class).between(5, 17);
			p.behave(Rest.class).between(21, 24);
			p.behave(MoveToHome.class).between(0, 5);
		}
	},
	V_FARM_BOSS("farm boss", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			p.behave(BuySupply.class).food().hungry(75);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	SWEATSHOP_WORKER("sweatshop worker", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(6, 18);
			p.behave(DoWork.class).between(6, 18);
			p.behave(Lock.class).between(18, 20);
			p.behave(MoveToHome.class).between(21, 24);
			p.behave(MoveToHome.class).between(0, 5);
			p.behave(MoveToWork.class).between(5, 17);
			p.behave(Rest.class).between(21, 24);
			p.behave(Rest.class).between(0, 5);
		}
	},
	SWEATSHOP_BOSS("sweatshop boss", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	PROGRAMMER("programmer", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(DoWork.class).between(9, 17);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.HACKING, 30, r);
			p.setApproximateSkill(Skill.OBSERVATION, 10, r);
		}
	},
	PROGRAMMER_BOSS("programmer boss", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	VAGRANT("vagrant", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SHIV)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Wander.class).between(10, 20);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.SCAVENGING, 30, r);
		}
	},
	KID("kid", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SNACKS)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(20, 24);
			p.behave(MoveToHome.class).between(0, 10);
			p.behave(Rest.class).between(20, 24);
			p.behave(Rest.class).between(0, 10);
			p.behave(Wander.class).between(10, 20);			
		}
	},
	MUGGER("mugger", EnumSet.of(ItemType.TRANQ_BUG, ItemType.TRANQ_DART), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(3, 13);
			p.behave(Rest.class).between(3, 13);
			p.behave(Stun.class).between(22, 24).unobservedExceptVictim();
			p.behave(Stun.class).between(0, 3).unobservedExceptVictim();
			p.behave(Mug.class).between(22, 24).unobservedExceptVictim();
			p.behave(Mug.class).between(0, 3).unobservedExceptVictim();
			p.behave(Loot.class).unobservedExceptVictim();
			p.behave(Buy.class).item(ItemType.TRANQ_BUG);
			p.behave(Buy.class).item(ItemType.SWITCHBLADE).hasnt(ItemType.SWITCHBLADE);
			p.behave(Buy.class).item(ItemType.PISTOL).hasnt(ItemType.PISTOL);
			p.behave(SellToPerson.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV, ItemType.TRANQ_BUG, ItemType.TRANQ_DART);
			p.behave(SellToBusiness.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV, ItemType.TRANQ_BUG, ItemType.TRANQ_DART);
			p.behave(Wander.class).between(20, 24);
			p.behave(Wander.class).between(0, 3);
			
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 10, r);
		}
	},
	HACKER("hacker", ItemType.HACKER_KIT, EnumSet.of(ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD, ItemType.VALUABLE_DATA), EnumSet.of(ItemType.AR_GOGGLES)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(SellToPerson.class).item(ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD, ItemType.VALUABLE_DATA);
			p.behave(MoveToHome.class).between(5, 15);
			p.behave(Rest.class).between(5, 15);
			p.behave(MoveToType.class).moveTo(TileType.BACK_ROOM).between(15, 20);
			p.behave(Datatrawl.class).between(0, 5).unobserved();
			//p.behave(Gogglehack.class).between(0, 5).unobservedExceptVictim(); Perf. Problems.
			p.behave(DeployGadget.class).between(0, 5).unobserved();
			p.behave(HarvestGadget.class).between(0, 5).unobserved();
			for (ItemType h : ItemType.HACKER_KIT) {
				p.behave(Buy.class).item(h).hasnt(h);
			}
			p.behave(Wander.class).between(0, 5);
			p.behave(Wander.class).between(15, 20);
			
			p.setApproximateSkill(Skill.HACKING, 60, r);
			p.setApproximateSkill(Skill.COUNTER_INTRUSION, 50, r);
			p.setApproximateSkill(Skill.OBSERVATION, 30, r);
			p.setApproximateSkill(Skill.SCAVENGING, 20, r);
		}
	},
	DATA_FENCE("data fence", EnumSet.of(ItemType.GENETIC_CODE, ItemType.VALUABLE_DATA, ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SHARP_SUIT)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(SellDataToOutsideWorld.class);
			p.behave(Buy.class).item(ItemType.GENETIC_CODE, ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD, ItemType.VALUABLE_DATA);
			p.behave(MoveToHome.class).between(0, 12);
			p.behave(Rest.class).between(0, 12);
			p.behave(MoveToWork.class).between(15, 22);
			
			p.setApproximateSkill(Skill.HACKING, 20, r);
			p.setApproximateSkill(Skill.COUNTER_INTRUSION, 50, r);
			p.setApproximateSkill(Skill.OBSERVATION, 30, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 30, r);
		}
	},
	THIEF("thief", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(6, 15);
			p.behave(Rest.class).between(6, 15);
			p.behave(Scavenge.class).between(0, 6).unobserved();
			p.behave(BreakIn.class).between(0, 6).unobserved();
			p.behave(Buy.class).item(ItemType.LOCKPICKS).hasnt(ItemType.LOCKPICKS);
			p.behave(Buy.class).item(ItemType.ANGLE_GRINDER).hasnt(ItemType.ANGLE_GRINDER);
			p.behave(Buy.class).item(ItemType.NANO_PICKS).hasnt(ItemType.NANO_PICKS);
			p.behave(SellToPerson.class).isntItem(ItemType.LOCKPICKS, ItemType.ANGLE_GRINDER, ItemType.NANO_PICKS);
			p.behave(SellToBusiness.class).isntItem(ItemType.LOCKPICKS, ItemType.ANGLE_GRINDER, ItemType.NANO_PICKS);
			p.behave(Wander.class).between(22, 24);
			p.behave(Wander.class).between(0, 6);
			
			p.setApproximateSkill(Skill.BREAKING_AND_ENTERING, 60, r);
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.OBSERVATION, 20, r);
			p.setApproximateSkill(Skill.SCAVENGING, 50, r);
		}
	},
	GANGSTER("gangster", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToHome.class).between(2, 11);
			p.behave(Rest.class).between(2, 11);
			p.behave(MoveToType.class).moveTo(TileType.GANG_HIDEOUT).between(23, 24);
			p.behave(MoveToType.class).moveTo(TileType.GANG_HIDEOUT).between(0, 2);
			p.behave(Stun.class).unobservedExceptVictim();
			p.behave(Attack.class).unobservedExceptVictim();
			p.behave(Loot.class).unobservedExceptVictim();
			p.behave(HarvestImplants.class).unobservedExceptVictim();
			p.behave(Buy.class).item(ItemType.SWITCHBLADE).hasnt(ItemType.SWITCHBLADE);
			p.behave(Buy.class).item(ItemType.PISTOL).hasnt(ItemType.PISTOL);
			p.behave(Buy.class).item(ItemType.ASSAULT_RIFLE).hasnt(ItemType.ASSAULT_RIFLE);
			p.behave(Buy.class).item(ItemType.SHARP_SUIT).hasnt(ItemType.SHARP_SUIT);
			p.behave(Buy.class).item(ItemType.GRINDER_TOOLS).hasnt(ItemType.GRINDER_TOOLS);
			p.behave(Buy.class).item(ItemType.TRANQ_BUG).hasnt(ItemType.TRANQ_BUG);
			p.behave(Buy.class).item(ItemType.TRANQ_DART).hasnt(ItemType.TRANQ_DART);
			p.behave(SellToPerson.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV, ItemType.TRANQ_BUG, ItemType.TRANQ_DART);
			p.behave(SellToBusiness.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV, ItemType.TRANQ_BUG, ItemType.TRANQ_DART);
			p.behave(Wander.class).between(11, 23);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 30, r);
			p.setApproximateSkill(Skill.FIGHTING, 50, r);
			p.setApproximateSkill(Skill.OBSERVATION, 20, r);
			p.setApproximateSkill(Skill.SCAVENGING, 20, r);
		}
	},
	PLUTOCRAT("plutocrat", EnumSet.of(ItemType.UPPERS, ItemType.EXPERIMENTAL_UPPERS, ItemType.EXPERIMENTAL_DOWNERS), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.AR_GOGGLES, ItemType.SHARP_SUIT)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(10, 12);
			p.behave(DoWork.class).between(10, 12);
			p.behave(Lock.class).between(12, 14);
			p.behave(Unlock.class).between(14, 17);
			p.behave(DoWork.class).between(14, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(1, 8);
			p.behave(MoveToWork.class).between(9, 12);
			p.behave(MoveToWork.class).between(14, 16);
			p.behave(Rest.class).between(1, 8);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 40, r);
		}
	},
	GENETICIST("geneticist", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.GRINDING, 20, r);
			p.setApproximateSkill(Skill.HACKING, 20, r);
		}
	},
	GENETICIST_BOSS("chief geneticist", EnumSet.of(ItemType.GENETIC_CODE), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.GRINDING, 10, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	PA("personal assistant", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.AR_GOGGLES, ItemType.DESIGNER_CLOTHES)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(9, 17);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			
			p.setApproximateSkill(Skill.OBSERVATION, 10, r);
		}
	},
	DRUG_COOKER("drug cooker", EnumSet.of(ItemType.CHEMICALS), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.CHEMICALS)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(DoWork.class).between(8, 11);
			p.behave(Lock.class).between(8, 11);
			p.behave(MoveToType.class).moveTo(TileType.DOCK).between(11, 17);
			p.behave(Buy.class).item(ItemType.CHEMICALS);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 11);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 5);
			
			p.setApproximateSkill(Skill.GRINDING, 10, r);
			p.setApproximateSkill(Skill.SCAVENGING, 20, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	GAMBLING_PARLOUR_OPERATOR("gambling parlour operator", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(17, 23);
			p.behave(DoWork.class).between(17, 23);
			p.behave(Lock.class).between(23, 24);
			p.behave(Lock.class).between(0, 1);
			p.behave(MoveToHome.class).between(24, 9);
			p.behave(MoveToWork.class).between(16, 22);
			p.behave(Rest.class).between(0, 9);
			
			p.setApproximateSkill(Skill.FIGHTING, 20, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 30, r);
		}
	},
	BARTENDER("bartender", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Unlock.class).between(17, 23);
			p.behave(DoWork.class).between(17, 23);
			p.behave(Lock.class).between(23, 24);
			p.behave(Lock.class).between(0, 1);
			p.behave(MoveToHome.class).between(24, 9);
			p.behave(MoveToWork.class).between(16, 22);
			p.behave(Rest.class).between(0, 9);
			
			p.setApproximateSkill(Skill.FIGHTING, 10, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	},
	GUN_DEALER("gun dealer", EnumSet.of(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART), EnumSet.of(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART), EnumSet.of(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART).between(6, 9);
			p.behave(Unlock.class).between(9, 16);
			p.behave(DoWork.class).between(9, 17);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 6);
			p.behave(SellToPerson.class).item(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART);
			
			p.setApproximateSkill(Skill.FIGHTING, 30, r);
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 10, r);
		}
	},
	CLOTHES_DEALER("clothes dealer", EnumSet.of(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES), EnumSet.of(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES), EnumSet.of(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Lock.class).between(16, 19);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES).between(6, 9);
			p.behave(Unlock.class).between(9, 16);
			p.behave(DoWork.class).between(9, 17);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(SellToPerson.class).item(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 10, r);
		}
	},
	JEWELLERY_DEALER("jewellery dealer", EnumSet.of(ItemType.JEWELLERY), EnumSet.of(ItemType.JEWELLERY), EnumSet.of(ItemType.JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Lock.class).between(16, 19);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.JEWELLERY).between(6, 9);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(SellToPerson.class).item(ItemType.JEWELLERY);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 10, r);
		}
	},
	SECURITY_GUARD("security guard", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.PISTOL, ItemType.SHARP_SUIT)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(MoveToWork.class).between(6, 20);
			p.behave(MoveToHome.class).between(0, 6);
			p.behave(Rest.class).between(0, 6);
			p.behave(Attack.class).targetDisliked(60);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 10, r);
			p.setApproximateSkill(Skill.FIGHTING, 10, r);
		}
	},
	ART_DEALER("art dealer", EnumSet.of(ItemType.ART), EnumSet.of(ItemType.ART), EnumSet.of(ItemType.ART, ItemType.DESIGNER_CLOTHES)) {
		@Override
		public void install(Person p, Random r) {
			addBasicNeeds(p);
			addBuyFood(p);
			p.behave(Lock.class).between(16, 19);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.ART).between(6, 9);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(SellToPerson.class).item(ItemType.ART);
			
			p.setApproximateSkill(Skill.FORCE_OF_PERSONALITY, 20, r);
		}
	};
	
	public final String jobName;
	public final EnumSet<ItemType> buys;
	public final EnumSet<ItemType> sells;
	public final EnumSet<ItemType> equipment;

	private Job(String jobName, EnumSet<ItemType> buys, EnumSet<ItemType> sells, EnumSet<ItemType> equipment) {
		this.jobName = jobName;
		this.buys = buys;
		this.sells = sells;
		this.equipment = equipment;
	}
	
	public void doInstall(Person p, Random r) {
		p.jobTitle = jobName;
		p.buyForWork = buys;
		p.buyForSelf = buys;
		p.sell = sells;
		install(p, r);
		for (ItemType t : equipment) {
			p.inventory.add(new Item(t));
		}
		p.name = Names.random(r);
		p.minDealRep = 10 + r.nextInt(60);
		p.reputation = 80 + r.nextInt(20);
		p.hunger = r.nextInt(60);
		p.exhaustion = r.nextInt(30);
		p.health = 80 + r.nextInt(20);
	}

	public abstract void install(Person p, Random r);
	
	public static void addBuyFood(Person p) {
		p.behave(Lock.class).hungry(50).hasMoney(10);
		p.behave(MoveToType.class).moveTo(TileType.MARKET).hungry(50).hasMoney(10).between(7, 18);
		p.behave(Buy.class).food().hungry(25);
		p.behave(MoveToType.class).moveTo(TileType.BAR).hungry(50).hasMoney(10).between(18, 22);
		p.behave(BuyFoodAtRestaurantOrBar.class).hungry(40);
	}
	
	public static void addBasicNeeds(Person p) {
		p.behave(Eat.class).hungry(25);
		p.behave(HealAtClinic.class).injured(70);
		p.behave(MoveToType.class).moveTo(TileType.CLINIC).injured(70).hasMoney(200);
		p.behave(Rest.class).exhausted(80);
	}
}

package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Attack;
import com.zarkonnen.cyberpunk.interaction.BreakIn;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.BuyFromOutsideWorld;
import com.zarkonnen.cyberpunk.interaction.BuySupply;
import com.zarkonnen.cyberpunk.interaction.Datatrawl;
import com.zarkonnen.cyberpunk.interaction.DeployGadget;
import com.zarkonnen.cyberpunk.interaction.DoWork;
import com.zarkonnen.cyberpunk.interaction.Eat;
import com.zarkonnen.cyberpunk.interaction.Gogglehack;
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
import com.zarkonnen.cyberpunk.interaction.Wander;
import java.util.EnumSet;
import java.util.concurrent.locks.Lock;

public enum Job {
	PROSTITUTE("prostitute", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(12, 23);
			p.behave(DoWork.class).between(12, 23);
			p.behave(Lock.class).between(23, 24);
			p.behave(Lock.class).between(0, 1);
			p.behave(MoveToHome.class).between(0, 9);
			p.behave(MoveToWork.class).between(11, 22);
			p.behave(Rest.class).between(0, 9);
		}
	},
	DRUG_DEALER("dealer", EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.UPPERS, ItemType.DOWNERS, ItemType.EXPERIMENTAL_UPPERS, ItemType.EXPERIMENTAL_DOWNERS), EnumSet.of(ItemType.UPPERS, ItemType.DOWNERS)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(0, 10);
			p.behave(Rest.class).between(0, 9);
			p.behave(MoveToWork.class).hasnt(ItemType.UPPERS, ItemType.DOWNERS, ItemType.EXPERIMENTAL_UPPERS, ItemType.EXPERIMENTAL_DOWNERS);
			p.behave(BuySupply.class).item(ItemType.EXPERIMENTAL_DOWNERS);
			p.behave(BuySupply.class).item(ItemType.EXPERIMENTAL_UPPERS);
			p.behave(BuySupply.class).item(ItemType.DOWNERS);
			p.behave(BuySupply.class).item(ItemType.UPPERS);
			p.behave(Wander.class).between(11, 22);
		}
	},
	FOOD_MERCHANT("food merchant", EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SNACKS, ItemType.VEGETABLES), EnumSet.of(ItemType.VEGETABLES, ItemType.SNACKS)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 4);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 4);
			p.behave(MoveToWork.class).between(4, 6);
			p.behave(BuySupply.class).item(ItemType.VEGETABLES);
			p.behave(MoveToType.class).moveTo(TileType.MARKET).between(6, 19);
		}
	},
	TRADER("trader", SellToOutsideWorld.SELLABLES, BuyFromOutsideWorld.BUYABLES, EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(20, 24);
			p.behave(MoveToHome.class).between(0, 3);
			p.behave(Rest.class).between(20, 24);
			p.behave(Rest.class).between(0, 3);
			p.behave(MoveToMapEdge.class).between(3, 10);
			p.behave(SellToOutsideWorld.class);
			p.behave(BuyFromOutsideWorld.class).item(ItemType.CHEMICALS).between(8, 9);
			p.behave(BuyFromOutsideWorld.class).item(ItemType.MACHINE_PARTS).between(9, 10);
			p.behave(BuyFromOutsideWorld.class);
			p.behave(MoveToType.class).moveTo(TileType.DOCK).between(12, 19);
		}
	},
	HARDWARE_MERCHANT("hardware merchant", ItemType.HARDWARE, ItemType.HARDWARE, EnumSet.of(ItemType.MACHINE_PARTS)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
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
		}
	},
	GRINDER("grinder", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.GRINDER_TOOLS)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	V_FARM_WORKER("vertical farm worker", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
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
	V_FARM_BOSS("vertical farm boss", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	SWEATSHOP_WORKER("sweatshop worker", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
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
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	PROGRAMMER("programmer", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	PROGRAMMER_BOSS("programmer boss", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	VAGRANT("vagrant", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Wander.class).between(10, 20);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	KID("kid", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SNACKS)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(20, 24);
			p.behave(MoveToHome.class).between(0, 10);
			p.behave(Rest.class).between(20, 24);
			p.behave(Rest.class).between(0, 10);
			p.behave(Wander.class).between(10, 20);
		}
	},
	MUGGER("mugger", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(3, 13);
			p.behave(Rest.class).between(3, 13);
			p.behave(Mug.class).between(22, 24).unobservedExceptVictim();
			p.behave(Mug.class).between(0, 3).unobservedExceptVictim();
			p.behave(Buy.class).item(ItemType.SWITCHBLADE).hasnt(ItemType.SWITCHBLADE);
			p.behave(Buy.class).item(ItemType.PISTOL).hasnt(ItemType.PISTOL);
			p.behave(SellToPerson.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV);
			p.behave(SellToBusiness.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV);
			p.behave(Wander.class).between(20, 24);
			p.behave(Wander.class).between(0, 3);

		}
	},
	HACKER("hacker", ItemType.HACKER_KIT, EnumSet.of(ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD, ItemType.VALUABLE_DATA), EnumSet.of(ItemType.AR_GOGGLES)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(SellToPerson.class).item(ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD, ItemType.VALUABLE_DATA);
			p.behave(MoveToHome.class).between(5, 15);
			p.behave(Rest.class).between(5, 15);
			p.behave(MoveToType.class).moveTo(TileType.BACK_ROOM).between(15, 20);
			p.behave(Datatrawl.class).between(0, 5).unobserved();
			p.behave(Gogglehack.class).between(0, 5).unobservedExceptVictim();
			p.behave(DeployGadget.class).between(0, 5).unobserved();
			p.behave(DeployGadget.class).between(0, 5).unobserved();
			for (ItemType h : ItemType.HACKER_KIT) {
				p.behave(Buy.class).item(h).hasnt(h);
			}
			p.behave(Wander.class).between(0, 5);
			p.behave(Wander.class).between(15, 20);
		}
	},
	DATA_FENCE("data fence", EnumSet.of(ItemType.VALUABLE_DATA, ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SHARP_SUIT)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(SellDataToOutsideWorld.class);
			p.behave(Buy.class).item(ItemType.BLACKMAIL_MATERIAL, ItemType.PASSWORD, ItemType.VALUABLE_DATA);
			p.behave(MoveToHome.class).between(0, 12);
			p.behave(Rest.class).between(0, 12);
			p.behave(MoveToType.class).moveTo(TileType.BACK_ROOM).between(15, 22);
		}
	},
	THIEF("thief", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
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
		}
	},
	GANGSTER("gangster", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SWITCHBLADE)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToHome.class).between(2, 11);
			p.behave(Rest.class).between(2, 11);
			p.behave(MoveToType.class).moveTo(TileType.GANG_HIDEOUT).between(23, 24);
			p.behave(MoveToType.class).moveTo(TileType.GANG_HIDEOUT).between(0, 2);
			p.behave(Attack.class).unobservedExceptVictim();
			p.behave(Loot.class).unobservedExceptVictim();
			p.behave(HarvestImplants.class).unobservedExceptVictim();
			p.behave(Buy.class).item(ItemType.SWITCHBLADE).hasnt(ItemType.SWITCHBLADE);
			p.behave(Buy.class).item(ItemType.PISTOL).hasnt(ItemType.PISTOL);
			p.behave(Buy.class).item(ItemType.ASSAULT_RIFLE).hasnt(ItemType.ASSAULT_RIFLE);
			p.behave(Buy.class).item(ItemType.SHARP_SUIT).hasnt(ItemType.SHARP_SUIT);
			p.behave(Buy.class).item(ItemType.GRINDER_TOOLS).hasnt(ItemType.GRINDER_TOOLS);
			p.behave(SellToPerson.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV);
			p.behave(SellToBusiness.class).isntItem(ItemType.SWITCHBLADE, ItemType.PISTOL, ItemType.SHARP_SUIT, ItemType.SHIV);
			p.behave(Wander.class).between(11, 23);
		}
	},
	PLUTOCRAT("plutocrat", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
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
		}
	},
	GENETICIST("geneticist", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	GENETICIST_BOSS("chief geneticist", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	PA("personal assistant", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
		}
	},
	DRUG_COOKER("drug cooker", EnumSet.of(ItemType.CHEMICALS), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(7, 12);
			p.behave(DoWork.class).between(7, 12);
			p.behave(Unlock.class).between(12, 17);
			p.behave(DoWork.class).between(12, 17);
			p.behave(Lock.class).between(12, 14);
			p.behave(MoveToType.class).moveTo(TileType.DOCK).between(14, 17);
			p.behave(Buy.class).item(ItemType.CHEMICALS);
			p.behave(Buy.class).item(ItemType.CHEMICALS);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 17);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 5);
		}
	},
	GAMBLING_PARLOUR_OPERATOR("gambling parlour operator", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(Unlock.class).between(17, 23);
			p.behave(DoWork.class).between(17, 23);
			p.behave(Lock.class).between(23, 24);
			p.behave(Lock.class).between(0, 1);
			p.behave(MoveToHome.class).between(24, 9);
			p.behave(MoveToWork.class).between(16, 22);
			p.behave(Rest.class).between(0, 9);
		}
	},
	GUN_DEALER("gun dealer", EnumSet.of(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART), EnumSet.of(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART), EnumSet.of(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART).between(6, 9);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 6);
			p.behave(SellToPerson.class).item(ItemType.ASSAULT_RIFLE, ItemType.PISTOL, ItemType.TRANQ_DART);
		}
	},
	CLOTHES_DEALER("clothes dealer", EnumSet.of(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES), EnumSet.of(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES), EnumSet.of(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES).between(6, 9);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(SellToPerson.class).item(ItemType.DESIGNER_CLOTHES, ItemType.SHARP_SUIT, ItemType.MIRRORSHADES);
		}
	},
	JEWELLERY_DEALER("jewellery dealer", EnumSet.of(ItemType.JEWELLERY), EnumSet.of(ItemType.JEWELLERY), EnumSet.of(ItemType.JEWELLERY)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.JEWELLERY).between(6, 9);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(SellToPerson.class).item(ItemType.JEWELLERY);
		}
	},
	ART_DEALER("art dealer", EnumSet.of(ItemType.ART), EnumSet.of(ItemType.ART), EnumSet.of(ItemType.ART)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(MoveToSupplier.class).between(6, 9);
			p.behave(BuySupply.class).item(ItemType.ART).between(6, 9);
			p.behave(Unlock.class).between(9, 17);
			p.behave(DoWork.class).between(9, 17);
			p.behave(Lock.class).between(17, 19);
			p.behave(MoveToHome.class).between(22, 24);
			p.behave(MoveToHome.class).between(0, 7);
			p.behave(MoveToWork.class).between(8, 16);
			p.behave(Rest.class).between(22, 24);
			p.behave(Rest.class).between(0, 7);
			p.behave(SellToPerson.class).item(ItemType.ART);
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
	
	public void doInstall(Person p) {
		p.description += jobName;
		p.buyForWork = buys;
		p.buyForSelf = buys;
		p.sell = sells;
		install(p);
	}

	public abstract void install(Person p);
	
	public static void addBasicNeeds(Person p) {
		p.behave(HealAtClinic.class).injured(70);
		p.behave(MoveToType.class).moveTo(TileType.CLINIC).injured(70);
		p.behave(Eat.class).hungry(75);
		p.behave(MoveToType.class).moveTo(TileType.MARKET).hungry(75);
		p.behave(Buy.class).food().hungry(75);
		p.behave(Rest.class).exhausted(80);
	}
}

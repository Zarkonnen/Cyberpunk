package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.BuySupply;
import com.zarkonnen.cyberpunk.interaction.DoWork;
import com.zarkonnen.cyberpunk.interaction.Eat;
import com.zarkonnen.cyberpunk.interaction.HealAtClinic;
import com.zarkonnen.cyberpunk.interaction.MoveToHome;
import com.zarkonnen.cyberpunk.interaction.MoveToType;
import com.zarkonnen.cyberpunk.interaction.MoveToWork;
import com.zarkonnen.cyberpunk.interaction.Rest;
import com.zarkonnen.cyberpunk.interaction.Wander;
import com.zarkonnen.cyberpunk.interaction.Unlock;
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
	FOOD_MERCHANT("food merchant", EnumSet.noneOf(ItemType.class), EnumSet.of(ItemType.SNACKS, ItemType.VEGETABLES), EnumSet.of(ItemType.UPPERS, ItemType.DOWNERS)) {
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

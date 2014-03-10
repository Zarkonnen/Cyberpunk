package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.DoWork;
import com.zarkonnen.cyberpunk.interaction.Eat;
import com.zarkonnen.cyberpunk.interaction.HealAtClinic;
import com.zarkonnen.cyberpunk.interaction.MoveToHome;
import com.zarkonnen.cyberpunk.interaction.MoveToType;
import com.zarkonnen.cyberpunk.interaction.MoveToWork;
import com.zarkonnen.cyberpunk.interaction.Rest;
import java.util.EnumSet;

public enum Job {
	PROSTITUTE("prostitute", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p) {
			addBasicNeeds(p);
			p.behave(DoWork.class).between(12, 23);
			p.behave(MoveToHome.class).between(0, 9);
			p.behave(MoveToWork.class).between(11, 22);
			p.behave(Rest.class).between(0, 9);
		}
	};
	
	public final String jobName;
	public final EnumSet<ItemType> buys;
	public final EnumSet<ItemType> sells;

	private Job(String jobName, EnumSet<ItemType> buys, EnumSet<ItemType> sells) {
		this.jobName = jobName;
		this.buys = buys;
		this.sells = sells;
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

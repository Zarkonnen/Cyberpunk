package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.ItemType;
import static com.zarkonnen.cyberpunk.ItemType.*;
import static com.zarkonnen.cyberpunk.TileType.*;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.MoveToType;
import com.zarkonnen.cyberpunk.interaction.TakeDrug;
import java.util.EnumSet;
import java.util.Random;

public enum Detail {
	UPPER_ADDICT(" They have a nervous look about them.", EnumSet.of(UPPERS, EXPERIMENTAL_UPPERS), EnumSet.noneOf(ItemType.class), EnumSet.of(UPPERS)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(TakeDrug.class).sober();
			p.behave(MoveToType.class).moveTo(DRUG_DEN);
			p.behave(Buy.class).item(UPPERS, EXPERIMENTAL_UPPERS);
		}
	};

	private Detail(String description, EnumSet<ItemType> buys, EnumSet<ItemType> sells, EnumSet<ItemType> equipment) {
		this.description = description;
		this.buys = buys;
		this.sells = sells;
		this.equipment = equipment;
	}
		
	public final String description;
	public final EnumSet<ItemType> buys;
	public final EnumSet<ItemType> sells;
	public final EnumSet<ItemType> equipment;
	
	public abstract void install(Person p, Random r);
}

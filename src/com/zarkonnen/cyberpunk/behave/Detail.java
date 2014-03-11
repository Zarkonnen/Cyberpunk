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
	},
	SHAVEN(" Their hair and eyebrows have been completely razored off.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	SCARRED(" A gnarled trail of scar tissue crosses their face and disappears under their jacket.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	PIERCED(" You can see several piercings on their face and body.", EnumSet.of(JEWELLERY), EnumSet.noneOf(ItemType.class), EnumSet.of(JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Buy.class).item(JEWELLERY);
		}
	},
	MODDED(" They have extensive body modifications.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	FASHIONABLE(" They're wearing the very latest in up-to-the-nanosecond fashion.", EnumSet.of(DESIGNER_CLOTHES), EnumSet.noneOf(ItemType.class), EnumSet.of(DESIGNER_CLOTHES)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Buy.class).item(DESIGNER_CLOTHES);
		}
	},
	BLINGY(" You wonder how they can stand under the weight of all that jewellery.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	FILTHY(" They are covered in a greasy layer of filth.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	SHIFTY(" They have a shifty look to them.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	LEFT_EYED(" Beneath their AR goggles, their right eye is missing. The wound looks fresh.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	RIGHT_EYED(" Their left eye is covered by a leather patch.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	LUDDITE(" They aren't wearing any AR goggles.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	BARFLY(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(MoveToType.class).moveTo(BAR);
		}
	},
	STAY_AT_HOME(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(MoveToHome.class);
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

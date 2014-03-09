package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class BuyFromOutsideWorld extends AbstractInteraction<Tile> {
	public static final EnumSet<ItemType> BUYABLES = EnumSet.of(
		ItemType.ANGLE_GRINDER,
		ItemType.ANTIVIRUS_SOFTWARE,
		ItemType.ART,
		ItemType.AR_GOGGLES,
		ItemType.ASSAULT_RIFLE,
		ItemType.BOOTLEG_ENTERTAINMENT,
		ItemType.COUNTER_INTRUSION_SYSTEM,
		ItemType.CRYPTOGRAPHIC_COPROCESSOR,
		ItemType.DESIGNER_CLOTHES,
		ItemType.ENDURANCE_UPGRADE,
		ItemType.FANCY_AR_GOGGLES,
		ItemType.JEWELLERY,
		ItemType.MEDICAL_HELPER_IMPLANT,
		ItemType.MEDICAL_SUPPLIES,
		ItemType.GRINDER_TOOLS,
		ItemType.GUARD_DRONE,
		ItemType.OPTICAL_CAMO,
		ItemType.PISTOL,
		ItemType.QUANTUM_CORE,
		ItemType.RATIONS,
		ItemType.SNACKS,
		ItemType.SWITCHBLADE
	);
	
	public final ItemType good;

	public BuyFromOutsideWorld(Person actor, Tile target, ItemType good) {
		super(actor, target);
		this.good = good;
	}
	
	@Override
	public String getName() {
		return "Import " + good.name;
	}

	@Override
	public String disabledReason() {
		if (actor().money < good.value) {
			return "You can't afford it.";
		}
		return null;
	}

	@Override
	public String description() {
		return "Import from outside the map.";
	}

	@Override
	public String run() {
		actor().inventory.add(new Item(good));
		actor().money -= good.value;
		return "You buy: " + good.name;
	}
	
	public static class F implements InteractionFactory<Tile, BuyFromOutsideWorld> {
		@Override
		public List<BuyFromOutsideWorld> make(Person actor, Tile t) {
			if (actor.isPlayer) { return Collections.emptyList(); }
			if (!t.atMapEdge()) { return Collections.emptyList(); }
			ArrayList<BuyFromOutsideWorld> l = new ArrayList<BuyFromOutsideWorld>();
			for (ItemType good : BUYABLES) {
				l.add(new BuyFromOutsideWorld(actor, t, good));
			}
			return l;
		}
	}
}

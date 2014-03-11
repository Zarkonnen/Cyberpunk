package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class SellToOutsideWorld extends AbstractInteraction<Tile> implements ItemInteraction {
	public static final double MULT = 1.1;
	
	public static final EnumSet<ItemType> SELLABLES = EnumSet.of(
		ItemType.AMATEUR_SEX_VID,
		ItemType.ART,
		ItemType.DESIGNER_CLOTHES,
		ItemType.DOWNERS,
		ItemType.UPPERS,
		ItemType.EXPERIMENTAL_DOWNERS,
		ItemType.EXPERIMENTAL_UPPERS,
		ItemType.GENETIC_CODE,
		ItemType.VEGETABLES,
		ItemType.VALUABLE_DATA
	);
	
	public final Item item;

	public SellToOutsideWorld(Person actor, Tile target, Item item) {
		super(actor, target);
		this.item = item;
	}
	
	@Override
	public String getName() {
		return "Export " + item.getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Export to outside the map.";
	}

	@Override
	public String run() {
		exhaust(3);
		actor().inventory.remove(item);
		actor().money += item.type.value * MULT;
		return "Sell: " + item.getName();
	}

	@Override
	public Item getItem() {
		return item;
	}
	
	public static class F implements InteractionFactory<Tile, SellToOutsideWorld> {
		@Override
		public List<SellToOutsideWorld> make(Person actor, Tile t) {
			if (actor.isPlayer) { return Collections.emptyList(); }
			if (!t.atMapEdge()) { return Collections.emptyList(); }
			ArrayList<SellToOutsideWorld> l = new ArrayList<SellToOutsideWorld>();
			for (Item it : actor.inventory) {
				if (SELLABLES.contains(it.type)) {
					l.add(new SellToOutsideWorld(actor, t, it));
				}
			}
			return l;
		}
	}
}

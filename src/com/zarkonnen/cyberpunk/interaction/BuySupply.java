package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuySupply extends AbstractInteraction<Tile> implements ItemInteraction {
	public static final double COST_MULT = 0.5;
	
	public final Tile.HiddenItem item;

	public BuySupply(Tile.HiddenItem item, Person actor, Tile target) {
		super(actor, target);
		this.item = item;
	}

	@Override
	public String getName() {
		return "Buy cheap " + item.item.getName();
	}

	@Override
	public String disabledReason() {
		if (actor().money < item.item.type.value * COST_MULT) {
			return "You can't afford it.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Your partners will sell you cheap " + item.item.getName() + ".";
	}

	@Override
	public String run() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Item getItem() {
		return item.item;
	}
	
	public static final class F implements InteractionFactory<Tile, BuySupply> {
		@Override
		public List<BuySupply> make(Person actor, Tile t) {
			if (actor.workplace == t) {
				ArrayList<BuySupply> l = new ArrayList<BuySupply>();
				for (Tile.HiddenItem item : t.hiddenItems) {
					if (!item.item.type.data) {
						l.add(new BuySupply(item, actor, t));
					}
				}
				return l;
			} else {
				return Collections.emptyList();
			}
		}
	}
}

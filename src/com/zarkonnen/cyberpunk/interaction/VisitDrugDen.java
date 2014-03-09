package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Person.Drug;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;

public class VisitDrugDen extends AbstractInteraction<Tile> {
	public static final int COST = ItemType.DOWNERS.value;

	public VisitDrugDen(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Visit Drug Den";
	}

	@Override
	public String disabledReason() {
		if (actor().money < COST) {
			return "You can't afford it.";
		}
		return null;
	}

	@Override
	public String description() {
		return "Put something into you to help you relax.";
	}

	@Override
	public String run() {
		actor().money -= COST;
		actor().drugsTaken.add(new Drug(new Item(ItemType.DOWNERS)));
		return "The drugs are smooth and relaxing, as always.";
	}
	
	public static class F extends TileTypeFactory {
		public F() {
			super(TileType.DRUG_DEN);
		}
		
		@Override
		public Interaction<Tile> get(Person actor, Tile t) {
			return new VisitDrugDen(actor, t);
		}
	}
}

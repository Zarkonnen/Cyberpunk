package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;

public class BuyFoodAtRestaurantOrBar extends AbstractInteraction<Tile> {
	public static final int FOOD_COST = 10;
	public static final int FOOD_AMOUNT = 20;

	public BuyFoodAtRestaurantOrBar(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Get something to eat ($" + FOOD_COST + ")";
	}

	@Override
	public String disabledReason() {
		if (!target().inBusiness()) { return "The place is closed."; }
		if (actor().hunger <= 0) {
			return "You're not hungry.";
		}
		if (actor().money < FOOD_COST) {
			return "You can't afford it.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "If you don't look too closely, the food here is almost tasty.";
	}

	@Override
	public String run() {
		actor().hunger = Math.max(0, actor().hunger - FOOD_AMOUNT);
		actor().money -= FOOD_COST;
		return "You slurp down your meal as quickly as you can. It fills the hole.";
	}
	
	public static class F extends TileTypeFactory {
		public F() {
			super(TileType.BAR);
		}

		@Override
		public Interaction<Tile> get(Person actor, Tile t) {
			return new BuyFoodAtRestaurantOrBar(actor, t);
		}
	}
}

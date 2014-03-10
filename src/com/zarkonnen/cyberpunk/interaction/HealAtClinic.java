
package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;

public class HealAtClinic extends AbstractInteraction<Tile> {
	public static final int HEAL_COST = 200;
	public static final int HEAL_AMOUNT = 20;
	
	public HealAtClinic(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Seek medical attention ($" + HEAL_COST + ")";
	}

	@Override
	public String disabledReason() {
		if (!target().inBusiness()) { return "The clinic is closed."; }
		if (actor().health >= 100) {
			return "You don't need a doctor.";
		}
		if (actor().money < HEAL_COST) {
			return "You can't afford it.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "This clinic can patch you back up, for a price.";
	}

	@Override
	public String run() {
		actor().removeExhaustion(3);
		actor().health = Math.min(100, actor().health + HEAL_AMOUNT);
		actor().money -= HEAL_COST;
		return "The doctor's services are hurried but efficient. You feel better already.";
	}
	
	public static class F extends TileTypeFactory {
		public F() {
			super(TileType.CLINIC);
		}
		
		@Override
		public Interaction<Tile> get(Person actor, Tile t) {
			return new HealAtClinic(actor, t);
		}
	}
}

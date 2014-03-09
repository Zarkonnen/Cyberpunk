package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;

public class Gamble extends AbstractInteraction<Tile> {
	public static final int WAGER = 10;
	public static final int PAYOUT = 100;
	public static final int PAYOUT_P = 5;

	public Gamble(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Gamble";
	}

	@Override
	public String disabledReason() {
		if (actor().money < WAGER) {
			return "You can't afford the wager.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Wager some money on the spinning wheel. Maybe you get lucky.";
	}

	@Override
	public String run() {
		actor().money -= WAGER;
		if (test(PAYOUT_P)) {
			actor().money += PAYOUT;
			return "You won $100! Today is your lucky day! You should try the wheel again!";
		} else {
			return "Well, that didn't work. Another spin, maybe?";
		}
	}
	
	public static class F extends TileTypeFactory {
		public F() {
			super(TileType.GAMBLING_PARLOUR);
		}
		
		@Override
		public Interaction<Tile> get(Person actor, Tile t) {
			return new Gamble(actor, t);
		}
	}
}

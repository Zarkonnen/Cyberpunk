package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class Rest extends AbstractInteraction<Tile> {
	public static final int HOME_REST_AMT = 10;
	public static final int ELSEWHERE_REST_AMT = 5;
	
	public Rest(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Rest";
	}

	@Override
	public String disabledReason() {
		if (actor().exhaustion <= actor().restedPoint()) {
			return "This is as relaxed as you're going to get right now.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		if (target() == actor().home) {
			return "Have a lie down. You're at home.";
		} else {
			return "Sit down, maybe close your eyes for a moment.";
		}
	}

	@Override
	public String run() {
		if (target() == actor().home) {
			actor().removeExhaustion(HOME_REST_AMT);
			return null;//"You make a clearing in the electronic junk and kip for a little while. It's nice to be home.";
		} else {
			actor().removeExhaustion(ELSEWHERE_REST_AMT);
			return null;//"You sit down and rest your eyes for a moment.";
		}
	}
	
	public static class F implements InteractionFactory<Tile, Rest> {
		public static final EnumSet<TileType> ELIGIBLE = EnumSet.of(TileType.BAR, TileType.BROTHEL, TileType.DRUG_DEN, TileType.GAMBLING_PARLOUR);
		@Override
		public List<Rest> make(Person actor, Tile t) {
			if (t == actor.home || ELIGIBLE.contains(t.type)) {
				return Collections.singletonList(new Rest(actor, t));
			} else {
				return Collections.emptyList();
			}
		}
	}
}

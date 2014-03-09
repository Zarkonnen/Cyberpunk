package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.TileType;
import java.util.Collections;
import java.util.List;

public class VisitBrothel extends AbstractInteraction<Person> {
	public static final int COST = 20;
	public static final int EXHAUSTION_DECREASE = 15;

	public VisitBrothel(Person actor, Person target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Pay " + target().getName() + " a visit, for $" + COST;
	}

	@Override
	public String disabledReason() {
		if (actor().money < COST) {
			return "You can't afford a brothel visit.";
		}
		return null;
	}

	@Override
	public String description() {
		return "A visit to the brothel might be... refreshing?";
	}

	@Override
	public String run() {
		actor().exhaustion = Math.max(actor().restedPoint(), actor().exhaustion - EXHAUSTION_DECREASE);
		return "You have A Good Time.";
	}
	
	public static class F implements InteractionFactory<Person, VisitBrothel> {
		@Override
		public List<VisitBrothel> make(Person actor, Person t) {
			if (t.location() == t.workplace && t.workplace.type == TileType.BROTHEL) {
				return Collections.singletonList(new VisitBrothel(actor, t));
			} else {
				return Collections.emptyList();
			}
		}
	}
}

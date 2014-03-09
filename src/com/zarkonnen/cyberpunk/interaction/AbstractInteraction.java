package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.Random;

public abstract class AbstractInteraction<T> implements Interaction<T> {
	private final Person actor;
	private final T target;

	public AbstractInteraction(Person actor, T target) {
		this.actor = actor;
		this.target = target;
	}
	
	public int decreaseRep(int decrease) {
		int pop = 0;
		for (Person p : actor.location().people()) {
			if (p != actor && !p.unconscious()) { pop++; }
		}
		if (pop > 0) {
			actor.reputation -= decrease * pop;
		}
		return pop;
	}
	
	public void exhaust(int amt) {
		actor.exhaustion += amt;
	}
	
	public Random r() {
		return actor.location().map.r;
	}
	
	public boolean test(int vs, Skill... sks) {
		return actor.test(vs, sks);
	}
	
	public boolean test(int successChance) {
		return actor.location().map.test(successChance);
	}

	@Override
	public Person actor() {
		return actor;
	}

	@Override
	public T target() {
		return target;
	}

	@Override
	public String name() {
		String dr = disabledReason();
		if (dr != null) {
			return getName() + "\n" +  dr;
		} else {
			return getName();
		}
	}
	
	public abstract String getName();
	public abstract String disabledReason();

	@Override
	public boolean enabled() {
		return disabledReason() == null;
	}
}

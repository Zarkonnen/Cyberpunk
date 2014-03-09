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
	
	public Random r() {
		return actor.location().map.r;
	}
	
	public boolean test(Skill sk, int vs) {
		return actor.test(sk, vs);
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
	public String description() {
		String dr = disabledReason();
		if (dr != null) {
			return desc() + "\n" +  dr;
		} else {
			return desc();
		}
	}
	
	public abstract String desc();
	public abstract String disabledReason();

	@Override
	public boolean enabled() {
		return disabledReason() == null;
	}
}

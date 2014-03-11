package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import java.util.Collections;
import java.util.List;

public class Murder extends AbstractInteraction<Person> {
	public Murder(Person actor, Person target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Murder " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Kill them while they're down.";
	}

	@Override
	public String run() {
		exhaust(4);
		target().dead = true;
		if (decreaseRep(30) > 0) {
			return "Well, they're dead. Unfortunately, there are witnesses.";
		} else {
			return "Well, they're dead. Good no-one saw that. You think.";
		}
	}
	
	public static final class F implements InteractionFactory<Person, Murder> {
		@Override
		public List<Murder> make(Person actor, Person t) {
			if (!t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new Murder(actor, t));
		}
	}
}

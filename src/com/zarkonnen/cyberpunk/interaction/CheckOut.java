package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import java.util.Collections;
import java.util.List;

public class CheckOut extends AbstractInteraction<Person> {
	public CheckOut(Person actor, Person target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Check out " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String description() {
		return "Give them an appraising look-over.";
	}

	@Override
	public String run() {
		String desc = target().description();
		actor().reputation--;
		StringList items = new StringList();
		for (Item item : target().inventory) {
			if (!item.type.data && test(50, Skill.OBSERVATION)) {
				items.add(item);
			}
		}
		if (!items.isEmpty()) {
			desc += " On them: " + items;
		}
		return desc;
	}
	
	public static final class F implements InteractionFactory<Person, CheckOut> {
		@Override
		public List<CheckOut> make(Person actor, Person t) {
			return Collections.singletonList(new CheckOut(actor, t));
		}
	}
}

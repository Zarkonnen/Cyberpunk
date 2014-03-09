package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Datajack extends AbstractInteraction<Person> {
	public Datajack(Person actor, Person target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Datajack " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Plug into their wearables while they're unconscious and trawl for data.";
	}

	@Override
	public String run() {
		StringList things = new StringList();
		for (Iterator<Item> it = target().inventory.iterator(); it.hasNext();) {
			Item item = it.next();
			if (item.type.data && test(target().getSkill(Skill.COUNTER_INTRUSION), Skill.HACKING)) {
				actor().inventory.add(item);
				things.add(item);
				it.remove();
			}
		}
		
		if (things.isEmpty()) {
			decreaseRep(2);
			return "You look for interesting data, but find none.";
		} else {
			decreaseRep(10);
			return "You find " + things + ".";
		}
	}
	
	public static final class F implements InteractionFactory<Person, Datajack> {
		@Override
		public List<Datajack> make(Person actor, Person t) {
			if (!t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new Datajack(actor, t));
		}
	}
}

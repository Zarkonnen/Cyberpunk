package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Gogglehack extends AbstractInteraction<Person> {
	public static final int MALUS = 40;
	public static final int DETECT_MALUS = 60;
	
	public Gogglehack(Person actor, Person target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Gogglehack " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Hack into their AR system and look for valuable data.";
	}

	@Override
	public String run() {
		exhaust(6);
		StringList things = new StringList();
		for (Iterator<Item> it = target().inventory.iterator(); it.hasNext();) {
			Item item = it.next();
			if (item.type.data && test(target().getSkill(Skill.COUNTER_INTRUSION) + MALUS, Skill.HACKING)) {
				actor().inventory.add(item);
				things.add(item);
			}
		}
		
		if (test(target().getSkill(Skill.COUNTER_INTRUSION) + DETECT_MALUS, Skill.HACKING)) {
			if (things.isEmpty()) {
				actor().reputation -= 10;
				return "You try to get into the AR overlay, but " + target().getName() + " notices your intrusion and shuts you out.";
			} else {
				actor().reputation -= 10;
				return "You find " + things + ". You're pretty certain " + target().getName() + " noticed your intrusion, though.";
			}
		} else {
			if (things.isEmpty()) {
				return "You look for interesting data, but find none. At least your victim seems not to have noticed your intrusion.";
			} else {
				return "You find " + things + ". Your download goes unnoticed.";
			}
		}
	}
	
	public static final class F implements InteractionFactory<Person, Gogglehack> {
		@Override
		public List<Gogglehack> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new Gogglehack(actor, t));
		}
	}
}

package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.Collections;
import java.util.List;

public class FirstAid extends AbstractInteraction<Item> implements ItemInteraction {
	public static final int VS = 30;
	
	public FirstAid(Person actor, Item target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Perform first aid with" + target().getName();
	}

	@Override
	public String disabledReason() {
		if (actor().health == 100) {
			return "You're not injured.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Patch yourself back together.";
	}

	@Override
	public String run() {
		exhaust(4);
		actor().inventory.remove(target());
		if (test(VS, Skill.GRINDING)) {
			actor().health = Math.min(100, actor().health + target().type.medicine);
			return "You use the " + target().getName() + " to patch yourself back up.";
		} else {
			return "The " + target().getName() + " goes to waste as you realise you're not sure how to do this first aid thing again.";
		}
	}

	@Override
	public Item getItem() {
		return target();
	}
	
	public static class F implements InteractionFactory<Item, FirstAid> {
		@Override
		public List<FirstAid> make(Person actor, Item t) {
			if (t.type.medicine == 0) { return Collections.emptyList(); }
			return Collections.singletonList(new FirstAid(actor, t));
		}
	}
}

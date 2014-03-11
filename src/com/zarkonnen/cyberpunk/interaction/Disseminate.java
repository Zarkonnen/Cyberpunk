package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.Collections;
import java.util.List;

public class Disseminate extends AbstractInteraction<Item> implements ItemInteraction {
	public static final int REP_DECREASE = 25;
	
	public Disseminate(Person actor, Item target) {
		super(actor, target);
	}
	
	
	@Override
	public String getName() {
		return "Disseminate " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Disseminate embarassing information about " + target().blackmailFor.getName() + ", hurting their reputation.";
	}

	@Override
	public String run() {
		exhaust(3);
		actor().inventory.remove(target());
		target().blackmailFor.reputation -= REP_DECREASE;
		actor().setSkill(Skill.FORCE_OF_PERSONALITY, actor().getSkill(Skill.FORCE_OF_PERSONALITY) + 3);
		return "You upload the information to the 'net and grin as you watch the reputation of " + target().blackmailFor.getName() + " crumble.";
	}

	@Override
	public Item getItem() {
		return target();
	}
	
	public static class F implements InteractionFactory<Item, Disseminate> {
		@Override
		public List<Disseminate> make(Person actor, Item t) {
			if (!t.type.blackmailMaterial || t.blackmailFor == null) { return Collections.emptyList(); }
			return Collections.singletonList(new Disseminate(actor, t));
		}
	}
}

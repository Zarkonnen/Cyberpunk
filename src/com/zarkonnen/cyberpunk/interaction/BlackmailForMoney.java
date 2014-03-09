package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BlackmailForMoney extends AbstractInteraction<Person> {
	public static final int BONUS = 50;

	public BlackmailForMoney(Person actor, Person target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Blackmail " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Swap incriminating evidence for money.";
	}

	@Override
	public String run() {
		if (actor().getSkill(Skill.FORCE_OF_PERSONALITY) + BONUS > target().getSkill(Skill.FORCE_OF_PERSONALITY)) {
			actor().money += target().money;
			target().money = 0;
			for (Iterator<Item> it = actor().inventory.iterator(); it.hasNext();) {
				if (it.next().blackmailFor == target()) {
					it.remove();
				}
			}
			return "You draw " + target().getName() + " aside and show them the goods. It takes only a little convincing to have them hand over all of their money. In exchange, you make a show of deleting the incriminating evidence.";
		} else {
			return "You try to convince " + target().getName() + " that bad things will happen if you don't get paid, but they laugh you off.";
		}
	}
	
	public static class F implements InteractionFactory<Person, BlackmailForMoney> {
		@Override
		public List<BlackmailForMoney> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			for (Item it : actor.inventory) {
				if (it.blackmailFor == t) {
					return Collections.singletonList(new BlackmailForMoney(actor, t));
				}
			}
			return Collections.emptyList();
		}
	}
}

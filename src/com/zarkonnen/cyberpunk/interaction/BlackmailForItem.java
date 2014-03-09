package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BlackmailForItem extends AbstractInteraction<Person> {
	public static final int BONUS = 30;

	public BlackmailForItem(Person actor, Person target, Item item) {
		super(actor, target);
		this.item = item;
	}
	
	public final Item item;
	
	@Override
	public String getName() {
		return "Blackmail " + target().getName() + " for " + item.getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String description() {
		return "Get them to hand over the " + item.getName() + ".";
	}

	@Override
	public String run() {
		if (actor().getSkill(Skill.FORCE_OF_PERSONALITY) + BONUS > target().getSkill(Skill.FORCE_OF_PERSONALITY)) {
			actor().inventory.add(item);
			if (!item.type.data) {
				target().inventory.remove(item);
			}
			for (Iterator<Item> it = actor().inventory.iterator(); it.hasNext();) {
				if (it.next().blackmailFor == target()) {
					it.remove();
				}
			}
			return "You draw " + target().getName() + " aside and show them the goods. It takes only a little convincing to have them hand over the " + item.getName() + ". In exchange, you make a show of deleting the incriminating evidence.";
		} else {
			return "You try to convince " + target().getName() + " that bad things will happen if you don't get that " + item.getName() + ", but they laugh you off.";
		}
	}
	
	public static class F implements InteractionFactory<Person, BlackmailForItem> {
		@Override
		public List<BlackmailForItem> make(Person actor, Person t) {
			for (Item it : actor.inventory) {
				if (it.blackmailFor == t) {
					ArrayList<BlackmailForItem> l = new ArrayList<BlackmailForItem>();
					for (Item it2 : t.inventory) {
						l.add(new BlackmailForItem(actor, t, it2));
					}
					return l;
				}
			}
			return Collections.emptyList();
		}
	}
}

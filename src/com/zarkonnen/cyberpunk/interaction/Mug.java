package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Mug extends AbstractInteraction<Person> {
	public static final int BONUS = 20;
	
	public Mug(Person actor, Person target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Mug " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Threaten them bodily harm if they don't hand over all their things.";
	}

	@Override
	public String run() {
		exhaust(4);
		if (test(target().getSkill(Skill.FIGHTING) + target().getSkill(Skill.FORCE_OF_PERSONALITY) + BONUS, Skill.FIGHTING, Skill.FORCE_OF_PERSONALITY)) {
			decreaseRep(15);
			StringList stolen = new StringList();
			for (Iterator<Item> it = target().inventory.iterator(); it.hasNext();) {
				Item item = it.next();
				if (!item.type.data) {
					actor().inventory.add(item);
					it.remove();
					stolen.add(item);
				}
			}
			int money = target().money;
			actor().money += target().money;
			target().money = 0;
			target().messages.add("You are mugged by " + actor().getName() + ".");
			if (stolen.isEmpty()) {
				return "You corner your victim and quickly relieve them of $" + money + ".";
			} else {
				return "You corner your victim and quickly relieve them of $" + money + ", and: " + stolen + ".";
			}
			
		} else {
			decreaseRep(5);
			target().messages.add(actor().getName() + " tries to mug you, but you walk away, laughing off their threats.");
			return "They laugh at your feeble threats and walk on.";
		}
	}
	
	public static final class F implements InteractionFactory<Person, Mug> {
		@Override
		public List<Mug> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new Mug(actor, t));
		}
	}
}

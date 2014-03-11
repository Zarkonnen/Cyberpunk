package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Attack extends AbstractInteraction<Person> {
	public static final int BONUS = 20;
	
	public Attack(Person actor, Person target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Attack " + target().getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Inflict bodily harm upon them.";
	}

	@Override
	public String run() {
		exhaust(10);
		int result = actor().getSkill(Skill.FIGHTING) + BONUS - target().getSkill(Skill.FIGHTING) + r().nextInt(100) - 50;
				
		if (result > 0) {
			target().health -= result;
			decreaseRep(20 * target().reputation / 100);
			if (target().unconscious()) {
				target().messages.add("You are attacked and knocked out by " + actor().getName() + ".");
				return "You attack " + target().getName() + " and knock them out.";
			} else {
				target().messages.add("You are attacked and wounded by " + actor().getName() + ".");
				return "You attack " + target().getName() + " and wound them.";
			}
		} else {
			actor().health += result;
			decreaseRep(15 * target().reputation / 100);
			if (actor().unconscious()) {
				target().messages.add(actor().getName() + " attempts to attack you, but you fend them off, knocking them out in the process.");
				return target().getName() + " manages to get the better of you and knocks you out.";
			} else {
				target().messages.add(actor().getName() + " attempts to attack you, but you fend them off.");
				return "Your attempted attack just leaves you injured.";
			}
		}
	}
	
	public static final class F implements InteractionFactory<Person, Attack> {
		@Override
		public List<Attack> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new Attack(actor, t));
		}
	}
}

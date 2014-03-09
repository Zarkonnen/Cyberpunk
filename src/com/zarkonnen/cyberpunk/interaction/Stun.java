package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Stun extends AbstractInteraction<Person> implements ItemInteraction {
	public final Item stunner;

	public Stun(Item stunner, Person actor, Person target) {
		super(actor, target);
		this.stunner = stunner;
	}
	
	@Override
	public String getName() {
		return "Stun " + target().getName() + " with " + stunner.getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Attempt to knock them out with the " + stunner.getName() + ".";
	}

	@Override
	public String run() {
		exhaust(3);
		actor().inventory.remove(stunner);
		if (test(stunner.type.stun)) {
			target().stunned = 5;
			decreaseRep(20);
			return "The " + stunner.getName() + " hits its mark, and " + target().getName() + " drops, unconscious for a little while.";
		} else {
			if (r().nextBoolean()) {
				decreaseRep(5);
			}
			return "The " + stunner.getName() + " misses its mark. You hope no one noticed.";
		}
	}

	@Override
	public Item getItem() {
		return stunner;
	}
	
	public static final class F implements InteractionFactory<Person, Stun> {
		@Override
		public List<Stun> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			ArrayList<Stun> l = new ArrayList<Stun>();
			for (Item item : actor.inventory) {
				if (item.type.stun > 0) {
					l.add(new Stun(item, actor, t));
				}
			}
			return l;
		}
	}
}

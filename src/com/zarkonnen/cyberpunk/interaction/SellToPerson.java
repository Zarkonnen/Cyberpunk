package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SellToPerson extends AbstractInteraction<Person> implements ItemInteraction {
	public final Item item;

	public SellToPerson(Item item, Person actor, Person target) {
		super(actor, target);
		this.item = item;
	}
	
	@Override
	public String getName() {
		return "Sell " + item.getName() + " ($" + item.type.value + ")";
	}

	@Override
	public String disabledReason() {
		if (target().money < item.type.value) {
			return "They can't afford it.";
		}
		if (actor().reputation < target().minDealRep) {
			return "They refuse to do business with you.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Sell " + item.getName() + " to " + target().getName() + " for $" + item.type.value;
	}

	@Override
	public String run() {
		exhaust(1);
		actor().money += item.type.value;
		target().money -= item.type.value;
		target().inventory.add(item);
		if (!item.type.data || item.type == ItemType.VALUABLE_DATA || item.type == ItemType.GENETIC_CODE || item.type == ItemType.BLACKMAIL_MATERIAL) {
			actor().inventory.remove(item);
		}
		return null;//"You sell the " + item.getName() + ".";
	}

	@Override
	public Item getItem() {
		return item;
	}
	
	public static class F implements InteractionFactory<Person, SellToPerson> {
		@Override
		public List<SellToPerson> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			ArrayList<SellToPerson> l = new ArrayList<SellToPerson>();
			for (Item item : actor.inventory) {
				if (t.willBuyForSelf(item)) {
					l.add(new SellToPerson(item, actor, t));
				}
			}
			return l;
		}
	}
}

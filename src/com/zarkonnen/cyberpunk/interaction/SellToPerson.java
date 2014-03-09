package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import java.util.ArrayList;
import java.util.List;

public class SellToPerson extends AbstractInteraction<Person> implements ItemInteraction {
	public final Item item;

	public SellToPerson(Item item, Person actor, Person target) {
		super(actor, target);
		this.item = item;
	}
	
	@Override
	public String getName() {
		return "Sell " + item.getName() + " to " + target().getName() + " for $" + item.type.value;
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String description() {
		return "Engage in commercial transactions.";
	}

	@Override
	public String run() {
		actor().money += item.type.value;
		target().money -= item.type.value;
		target().inventory.add(item);
		if (!item.type.data) {
			actor().inventory.remove(item);
		}
		return "You sell the " + item.getName() + ".";
	}

	@Override
	public Item getItem() {
		return item;
	}
	
	public static class F implements InteractionFactory<Person, SellToPerson> {
		@Override
		public List<SellToPerson> make(Person actor, Person t) {
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

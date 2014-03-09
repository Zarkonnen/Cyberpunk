package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SellToBusiness extends AbstractInteraction<Person> implements ItemInteraction {
	public final Item item;

	public SellToBusiness(Item item, Person actor, Person target) {
		super(actor, target);
		this.item = item;
	}
	
	@Override
	public String getName() {
		return "Sell " + item.getName() + " to the " + target().workplace.getName() + " for $" + item.type.value;
	}

	@Override
	public String disabledReason() {
		if (actor().reputation < target().minDealRep) {
			return "They refuse to do business with you.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Engage in commercial transactions.";
	}

	@Override
	public String run() {
		actor().money += item.type.value;
		target().workplace.hiddenItems.add(new Tile.HiddenItem(r().nextInt(50), item));
		if (!item.type.data) {
			actor().inventory.remove(item);
		}
		return "You sell the " + item.getName() + ".";
	}

	@Override
	public Item getItem() {
		return item;
	}
	
	public static class F implements InteractionFactory<Person, SellToBusiness> {
		@Override
		public List<SellToBusiness> make(Person actor, Person t) {
			if (t.unconscious()) { return Collections.emptyList(); }
			if (t.location() != t.workplace) { return Collections.emptyList(); }
			ArrayList<SellToBusiness> l = new ArrayList<SellToBusiness>();
			for (Item item : actor.inventory) {
				if (t.willForWork(item)) {
					l.add(new SellToBusiness(item, actor, t));
				}
			}
			return l;
		}
	}
}

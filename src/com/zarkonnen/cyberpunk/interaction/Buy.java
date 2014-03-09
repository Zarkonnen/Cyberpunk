package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public class Buy extends AbstractInteraction<Person> {
	public final Item item;

	public Buy(Item item, Person actor, Person target) {
		super(actor, target);
		this.item = item;
	}
	
	@Override
	public String getName() {
		return "Buy " + item.getName() + " from " + target().getName() + " for $" + item.type.value;
	}

	@Override
	public String disabledReason() {
		if (actor().money < item.type.value) {
			return "You can't afford it.";
		}
		return null;
	}

	@Override
	public String description() {
		return "Engage in commerce!";
	}

	@Override
	public String run() {
		actor().inventory.add(item);
		actor().money -= item.type.value;
		if (!item.type.data) {
			if (target().inventory.contains(item)) {
				target().inventory.remove(item);
				target().money += item.type.value;
			} else {
				target().location().inventory.remove(item);
				for (Tile.HiddenItem hi : target().location().hiddenItems) {
					if (hi.item == item) {
						target().location().hiddenItems.remove(hi);
						break;
					}
				}
			}
		}
		return "You buy the " + item.getName() + ".";
	}
	
	public static class F implements InteractionFactory<Person, Buy> {
		@Override
		public List<Buy> make(Person actor, Person t) {
			ArrayList<Buy> l = new ArrayList<Buy>();
			for (Item item : t.inventory) {
				if (t.willSell(item)) {
					l.add(new Buy(item, actor, t));
				}
			}
			if (t.workplace == t.location()) {
				for (Item item : t.location().inventory) {
					if (t.willSell(item)) {
						l.add(new Buy(item, actor, t));
					}
				}
				for (Tile.HiddenItem item : t.location().hiddenItems) {
					if (t.willSell(item.item)) {
						l.add(new Buy(item.item, actor, t));
					}
				}
			}
			return l;
		}
	}
}

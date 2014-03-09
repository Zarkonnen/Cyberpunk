package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Iterator;

public class HarvestBug extends AbstractInteraction<Tile> {
	public HarvestBug(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Harvest bugs.";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String description() {
		return "Pick up your bugs and see if they've caught anything juicy.";
	}

	@Override
	public String run() {
		StringList found = new StringList();
		for (Iterator<Tile.Gadget> it = target().gadgets.iterator(); it.hasNext();) {
			Tile.Gadget gadget = it.next();
			if (test(Skill.OBSERVATION, gadget.hidingScore) && gadget.item.type.bug > 0 && gadget.owner == actor()) {
				actor().inventory.add(gadget.item);
				found.add(gadget.item);
				it.remove();
			}
		}
		return found.isEmpty() ? "There are no bugs here." : "You found " + found + ".";
	}
	
}

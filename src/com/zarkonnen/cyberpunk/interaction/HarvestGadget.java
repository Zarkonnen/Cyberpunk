package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HarvestGadget extends AbstractInteraction<Tile> {
	public HarvestGadget(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Harvest gadgets.";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String description() {
		return "Collect your drones, and see if your bugs and wiretaps have caught anything juicy.";
	}

	@Override
	public String run() {
		StringList found = new StringList();
		for (Iterator<Tile.Gadget> it = target().gadgets.iterator(); it.hasNext();) {
			Tile.Gadget gadget = it.next();
			if (gadget.owner == actor()) {
				actor().inventory.add(gadget.item);
				found.add(gadget.item);
				it.remove();
			}
		}
		return found.isEmpty() ? "There are no gadgets here." : "You found " + found + ".";
	}
	
	public static class F implements InteractionFactory<Tile, HarvestGadget> {

		@Override
		public List<HarvestGadget> make(Person actor, Tile t) {
			return Collections.singletonList(new HarvestGadget(actor, t));
		}
		
	}
}

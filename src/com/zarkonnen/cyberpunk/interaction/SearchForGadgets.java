package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SearchForGadgets extends AbstractInteraction<Tile> {
	public SearchForGadgets(Person actor, Tile target) {
		super(actor, target);
	}
		
	@Override
	public String getName() {
		return "Search for gadgets.";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String description() {
		return "Sweep for bugs, wiretaps and drones -- leaving your own, of course.";
	}

	@Override
	public String run() {
		StringList found = new StringList();
		for (Iterator<Tile.Gadget> it = target().gadgets.iterator(); it.hasNext();) {
			Tile.Gadget gadget = it.next();
			if (test(Skill.OBSERVATION, gadget.hidingScore) && gadget.owner != actor()) {
				actor().inventory.add(gadget.item);
				found.add(gadget.item);
				it.remove();
			}
		}
		return found.isEmpty() ? "You found nothing." : "You found " + found + ".";
	}
	
	public static class F implements InteractionFactory<Tile, SearchForGadgets> {
		@Override
		public List<SearchForGadgets> make(Person actor, Tile t) {
			return Collections.singletonList(new SearchForGadgets(actor, t));
		}
		
	}	
}
package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Scavenge extends AbstractInteraction<Tile> {
	public Scavenge(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Scavenge";
	}
	
	@Override
	public String getDescription() {
		return "Look around for anything of value.";
	}

	@Override
	public String disabledReason() {
		if (locked()) { return "The door is locked."; }
		return null;
	}

	@Override
	public String run() {
		exhaust(8);
		decreaseRep(8);
		StringList found = new StringList();
		for (Iterator<Tile.HiddenItem> it = target().hiddenItems.iterator(); it.hasNext();) {
			Tile.HiddenItem hiddenItem = it.next();
			if (actor().getSkill(Skill.SCAVENGING) > hiddenItem.hidingScore && !hiddenItem.item.type.data) {
				actor().inventory.add(hiddenItem.item);
				found.add(hiddenItem.item);
				it.remove();
			}
		}
		
		return found.isEmpty() ? "You find nothing." : "You find: " + found + ".";
	}
	
	public static class F implements InteractionFactory<Tile, Scavenge> {
		@Override
		public List<Scavenge> make(Person actor, Tile t) {
			return Collections.singletonList(new Scavenge(actor, t));
		}
	}
}
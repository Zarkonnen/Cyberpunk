package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Scavenge extends AbstractTileInteraction {
	public Scavenge(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String desc() {
		return "Scavenge";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String run() {
		StringList found = new StringList();
		for (Iterator<Tile.HiddenItem> it = target().hiddenItems.iterator(); it.hasNext();) {
			Tile.HiddenItem hiddenItem = it.next();
			if (test(Skill.SCAVENGING, hiddenItem.hidingScore)) {
				actor().inventory.add(hiddenItem.item);
				found.add(hiddenItem.item);
				it.remove();
			}
		}
		
		return found.isEmpty() ? "You find nothing." : "You find: " + found + ".";
	}
	
	public static class F implements TileInteractionFactory<Scavenge> {
		@Override
		public List<Scavenge> make(Person actor, Tile t) {
			return Collections.singletonList(new Scavenge(actor, t));
		}
	}
}
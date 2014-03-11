package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Datatrawl extends AbstractInteraction<Tile> {
	public Datatrawl(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Datatrawl";
	}
	
	@Override
	public String getDescription() {
		return "Scan the local network for valuable data.";
	}

	@Override
	public String disabledReason() {
		if (locked()) { return "The door is locked."; }
		return null;
	}

	@Override
	public String run() {
		exhaust(2);
		decreaseRep(8);
		StringList found = new StringList();
		for (Iterator<Tile.HiddenItem> it = target().hiddenItems.iterator(); it.hasNext();) {
			Tile.HiddenItem hiddenItem = it.next();
			if (actor().getSkill(Skill.HACKING) > hiddenItem.hidingScore && hiddenItem.item.type.data) {
				if (hiddenItem.item.type == ItemType.VALUABLE_DATA || hiddenItem.item.type == ItemType.PASSWORD || hiddenItem.item.type == ItemType.BLACKMAIL_MATERIAL || hiddenItem.item.type == ItemType.BOOTLEG_ENTERTAINMENT || hiddenItem.item.type == ItemType.AMATEUR_SEX_VID) {
					if (!actor().inventory.contains(hiddenItem.item)) {
						actor().inventory.add(hiddenItem.item);
						found.add(hiddenItem.item);
					}
				} else if (!actor().hasItem(hiddenItem.item.type)) {
					actor().inventory.add(hiddenItem.item);
					found.add(hiddenItem.item);
				}
			}
		}
		
		return found.isEmpty() ? "You find nothing." : "You find: " + found + ".";
	}
	
	public static class F implements InteractionFactory<Tile, Datatrawl> {
		@Override
		public List<Datatrawl> make(Person actor, Tile t) {
			return Collections.singletonList(new Datatrawl(actor, t));
		}
	}
}
package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveImplant extends AbstractInteraction<Item> implements ItemInteraction {
	public static final int INJURY_VS = 40;
	public static final int HARVEST_VS = 30;

	
	public RemoveImplant(Person actor, Item target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "DIY implant removal.";
	}

	@Override
	public String disabledReason() {
		if (!actor().hasItem(ItemType.GRINDER_TOOLS)) {
			return "You need grinder tools to do this.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Use your grinding skills to just pull that implant out.";
	}

	@Override
	public String run() {
		if(actor().test(INJURY_VS, Skill.GRINDING)) {
			if (actor().test(HARVEST_VS, Skill.GRINDING)) {
				actor().inventory.add(target());
				actor().implants.remove(target());
				return "Success! You stitch yourself up and wipe off the " + target().getName() + ".";
			} else {
				actor().implants.remove(target());
				return "The " + target().getName() + " comes out easily enough, but its delicate connectors are irreparably broken. At least you're all right.";
			}
		} else {
			if (actor().test(HARVEST_VS, Skill.GRINDING)) {
				actor().inventory.add(target());
				actor().implants.remove(target());
				actor().health -= 30;
				return "The " + target().getName() + " is undamaged, but something doesn't feel right. You've really messed yourself up this time.";
			} else {
				actor().implants.remove(target());
				actor().health -= 30;
				return "CRACK! That's not a sound you want to hear. The " + target().getName() + " comes out in pieces -- or most of it does. You're badly hurt.";
			}
		}
	}

	@Override
	public Item getItem() {
		return target();
	}
	
	public static class F implements InteractionFactory<Item, RemoveImplant> {
		@Override
		public List<RemoveImplant> make(Person actor, Item t) {
			if (!actor.implants.contains(t)) {
				return Collections.emptyList();
			}
			ArrayList<RemoveImplant> l = new ArrayList<RemoveImplant>();
			l.add(new RemoveImplant(actor, t));
			return l;
		}
		
	}
	
}

package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImplantImplant extends AbstractInteraction<Item> implements ItemInteraction {
	public static final int INJURY_VS = 40;
	public static final int SUCCESS_VS = 30;
	
	public ImplantImplant(Person actor, Item target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "DIY implant insertion.";
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
		return "Insert that implant and hook it up yourself.";
	}

	@Override
	public String run() {
		if(actor().test(INJURY_VS, Skill.GRINDING)) {
			if (actor().test(SUCCESS_VS, Skill.GRINDING)) {
				actor().inventory.remove(target());
				actor().implants.add(target());
				return "Success! The " + target().getName() + " is functional and you feel great.";
			} else {
				actor().inventory.remove(target());
				return "Your tool slips and the connectors of the " + target().getName() + " get yanked out. It's broken for good, but at least you're all right.";
			}
		} else {
			if (actor().test(SUCCESS_VS, Skill.GRINDING)) {
				actor().inventory.remove(target());
				actor().implants.add(target());
				actor().health -= 30;
				return "You feel a shooting pain. The " + target().getName() + " is working flawlessly, but you're not doing so well.";
			} else {
				actor().inventory.remove(target());
				actor().health -= 30;
				return "CRUNCH. One false move and the " + target().getName() + " is expensive trash, sticking through a hole in your skin. You'd better go to a real doctor.";
			}
		}
	}

	@Override
	public Item getItem() {
		return target();
	}
	
	public static class F implements InteractionFactory<Item, ImplantImplant> {
		@Override
		public List<ImplantImplant> make(Person actor, Item t) {
			if (!actor.implants.contains(t)) {
				return Collections.emptyList();
			}
			ArrayList<ImplantImplant> l = new ArrayList<ImplantImplant>();
			l.add(new ImplantImplant(actor, t));
			return l;
		}
	}
	
}

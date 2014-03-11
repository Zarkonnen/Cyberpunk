package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.StringList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HarvestImplants extends AbstractInteraction<Person> {
	public static final int HARVEST_VS = 40;
	public static final int SURVIVE_VS = 70;
	
	public HarvestImplants(Person actor, Person target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Harvest implants from " + target().getName();
	}

	@Override
	public String disabledReason() {
		if (target().implants.isEmpty()) {
			return target().getName() + " has no implants.";
		}
		if (!actor().hasItem(ItemType.GRINDER_TOOLS)) {
			return "You need grinder tools to do this.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Some quick and bloody surgery will remove the valuable implants in this unconscious body.";
	}

	@Override
	public String run() {
		exhaust(7);
		StringList things = new StringList();
		for (Iterator<Item> it = target().implants.iterator(); it.hasNext();) {
			Item item = it.next();
			if (test(HARVEST_VS, Skill.GRINDING)) {
				actor().inventory.add(item);
				things.add(item);
			}
			it.remove();
		}
		
		if (test(SURVIVE_VS, Skill.GRINDING)) {
			decreaseRep(20);
			if (things.isEmpty()) {
				return "Your attempts at removal break the implants beyond repair. At least the \"patient\" survived.";
			} else {
				return "You remove " + things + " from your victim's body. They even survive the process.";
			}
		} else {
			decreaseRep(60);
			if (things.isEmpty()) {
				return "You hack away at your victim, but only succeed in killing them. Their implants break from your inexpert removal attempts.";
			} else {
				return "You remove " + things + " from your victim's body. They do not survive the process.";
			}
		}
	}
	
	public static final class F implements InteractionFactory<Person, HarvestImplants> {
		@Override
		public List<HarvestImplants> make(Person actor, Person t) {
			if (!t.unconscious()) { return Collections.emptyList(); }
			return Collections.singletonList(new HarvestImplants(actor, t));
		}
	}
}

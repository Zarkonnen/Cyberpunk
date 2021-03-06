package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public class DeployGadget extends AbstractInteraction<Tile> implements ItemInteraction {
	public final Item gadget;
	
	public DeployGadget(Person actor, Tile target, Item gadget) {
		super(actor, target);
		this.gadget = gadget;
	}

	@Override
	public String getName() {
		return "Deploy " + gadget.getName();
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Install a " + gadget.getName() + " to keep tabs on things.";
	}

	@Override
	public String run() {
		exhaust(2);
		actor().inventory.remove(gadget);
		target().gadgets.add(new Tile.Gadget(r().nextInt(50), actor(), gadget));
		return "You smoothly deploy the " + gadget.getName() + ".";
	}

	@Override
	public Item getItem() {
		return gadget;
	}
	
	public static class F implements InteractionFactory<Tile, DeployGadget> {
		@Override
		public List<DeployGadget> make(Person actor, Tile t) {
			ArrayList<DeployGadget> l = new ArrayList<DeployGadget>();
			for (Item it : actor.inventory) {
				if (it.type.bug > 0 || it.type.drone > 0) {
					l.add(new DeployGadget(actor, t, it));
				}
			}
			return l;
		}
	}
}

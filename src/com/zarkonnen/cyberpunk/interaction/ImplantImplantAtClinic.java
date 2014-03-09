package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import java.util.ArrayList;
import java.util.List;

public class ImplantImplantAtClinic extends AbstractInteraction<Tile> implements ItemInteraction {
	public final Item implant;
	public static final int IMPLANTATION_COST = 300;
	
	public ImplantImplantAtClinic(Person actor, Tile target, Item implant) {
		super(actor, target);
		this.implant = implant;
	}

	@Override
	public String getName() {
		return "Have an implant put in ($" + IMPLANTATION_COST + ").";
	}

	@Override
	public String disabledReason() {
		if (actor().money < IMPLANTATION_COST) {
			return "You can't afford it.";
		}
		for (Item it : actor().inventory) {
			if (it.type.implant) {
				return null;
			}
		}
		return "You haven't got anything to be implanted.";
	}

	@Override
	public String getDescription() {
		return "The doctors here know what they're doing. You hope.";
	}

	@Override
	public String run() {
		exhaust(5);
		actor().money -= IMPLANTATION_COST;
		actor().inventory.remove(implant);
		actor().implants.add(implant);
		return "The surgery is quick, and the " + implant.getName() +" is fully functional.";
	}

	@Override
	public Item getItem() {
		return implant;
	}
	
	public static class F implements InteractionFactory<Tile, ImplantImplantAtClinic> {
		@Override
		public List<ImplantImplantAtClinic> make(Person actor, Tile t) {
			ArrayList<ImplantImplantAtClinic> l = new ArrayList<ImplantImplantAtClinic>();
			if (t.type == TileType.CLINIC) {
				for (Item it : actor.inventory) {
					if (it.type.implant) {
						l.add(new ImplantImplantAtClinic(actor, t, it));
					}
				}
			}
			return l;
		}
		
	}
}

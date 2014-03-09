package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import java.util.ArrayList;
import java.util.List;

public class RemoveImplantAtClinic extends AbstractInteraction<Tile> implements ItemInteraction {
	public final Item implant;
	public static final int REMOVAL_COST = 300;
	
	public RemoveImplantAtClinic(Person actor, Tile target, Item implant) {
		super(actor, target);
		this.implant = implant;
	}

	@Override
	public String getName() {
		return "Have an implant removed. ($" + REMOVAL_COST + "):";
	}

	@Override
	public String disabledReason() {
		if (actor().money < REMOVAL_COST) {
			return "You can't afford it.";
		}
		if (!actor().implants.isEmpty()) {
			return null;
		}
		return "You haven't got any implants to be removed.";
	}

	@Override
	public String getDescription() {
		return "Better let the doctor take it out than yank it out yourself.";
	}

	@Override
	public String run() {
		exhaust(5);
		actor().money -= REMOVAL_COST;
		actor().implants.remove(implant);
		actor().inventory.add(implant);
		return "The surgery is quick, and they hand you the " + implant.getName() +" in a medifoil envelope.";
	}

	@Override
	public Item getItem() {
		return implant;
	}
	
	public static class F implements InteractionFactory<Tile, RemoveImplantAtClinic> {
		@Override
		public List<RemoveImplantAtClinic> make(Person actor, Tile t) {
			ArrayList<RemoveImplantAtClinic> l = new ArrayList<RemoveImplantAtClinic>();
			if (t.type == TileType.CLINIC) {
				for (Item it : actor.implants) {
					l.add(new RemoveImplantAtClinic(actor, t, it));
				}
			}
			return l;
		}
		
	}
	
}

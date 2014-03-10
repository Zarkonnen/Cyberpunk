package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class MoveToSupplier extends AbstractInteraction<Tile> {
	public MoveToSupplier(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Move towards your supplier.";
	}

	@Override
	public String disabledReason() {
		if (actor().workplace == null) {
			return "You have no supplier.";
		}
		if (actor().location().towardsTile(actor().supplier) == null) {
			return "No path to supplier.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Off to the supplier you go.";
	}

	@Override
	public String run() {
		actor().moveTo(actor().location().towardsTile(actor().supplier));
		return "You move towards your supplier.";
	}
	
	public static class F implements InteractionFactory<Tile, MoveToSupplier> {
		@Override
		public List<MoveToSupplier> make(Person actor, Tile t) {
			if (actor.supplier == null) { return Collections.emptyList(); }
			if (actor.isPlayer) { return Collections.emptyList(); }
			return Collections.singletonList(new MoveToSupplier(actor, t));
		}
	}
}

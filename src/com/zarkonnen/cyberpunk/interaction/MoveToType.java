package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveToType extends AbstractInteraction<Tile> {
	public MoveToType(TileType tileType, Person actor, Tile target) {
		super(actor, target);
		this.tileType = tileType;
	}
	
	public final TileType tileType;
	
	@Override
	public String getName() {
		return "Move towards a " + tileType.name() + ".";
	}

	@Override
	public String disabledReason() {
		if (actor().location().towardsTileType(tileType) == null) {
			return "No path to a " + tileType.name() + ".";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Off you go.";
	}

	@Override
	public String run() {
		actor().moveTo(actor().location().towardsTileType(tileType));
		return "You move towards a " + tileType.name() + ".";
	}
	
	public static class F implements InteractionFactory<Tile, MoveToType> {
		@Override
		public List<MoveToType> make(Person actor, Tile t) {
			if (actor.isPlayer) { return Collections.emptyList(); }
			ArrayList<MoveToType> l = new ArrayList<MoveToType>();
			for (TileType tt : TileType.values()) {
				l.add(new MoveToType(tt, actor, t));
			}
			return l;
		}
	}
}

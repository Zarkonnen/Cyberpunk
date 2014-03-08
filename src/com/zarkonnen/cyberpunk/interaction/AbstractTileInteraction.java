package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Random;

public abstract class AbstractTileInteraction implements TileInteraction {
	private final Person actor;
	private final Tile target;

	public AbstractTileInteraction(Person actor, Tile target) {
		this.actor = actor;
		this.target = target;
	}
	
	public Random r() {
		return target.map.r;
	}
	
	public boolean test(Skill sk, int vs) {
		return actor.test(sk, vs);
	}
	
	public boolean test(int successChance) {
		return target.map.test(successChance);
	}

	@Override
	public Person actor() {
		return actor;
	}

	@Override
	public Tile target() {
		return target;
	}

	@Override
	public String description() {
		String dr = disabledReason();
		if (dr != null) {
			return "[999999]" + desc() + "\n" +  dr;
		} else {
			return desc();
		}
	}
	
	public abstract String desc();
	public abstract String disabledReason();

	@Override
	public boolean enabled() {
		return disabledReason() == null;
	}
}

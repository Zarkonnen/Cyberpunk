package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public final class Factories {
	private Factories() {}
	public static final List<InteractionFactory<Tile, ?>> TILE_I_FACTORIES = new ArrayList<InteractionFactory<Tile, ?>>();
	static {
		TILE_I_FACTORIES.add(new Scavenge.F());
		TILE_I_FACTORIES.add(new HealAtClinic.F());
	}
	
	public static final List<InteractionFactory<Person, ?>> PERSON_I_FACTORIES = new ArrayList<InteractionFactory<Person, ?>>();
	static {
		
	}
	
	public static List<Interaction<Tile>> make(Person actor, Tile target) {
		ArrayList<Interaction<Tile>> l = new ArrayList<Interaction<Tile>>();
		for (InteractionFactory<Tile, ?> f : TILE_I_FACTORIES) {
			l.addAll(f.make(actor, target));
		}
		return l;
	}
	
	public static List<Interaction<Person>> make(Person actor, Person target) {
		ArrayList<Interaction<Person>> l = new ArrayList<Interaction<Person>>();
		for (InteractionFactory<Person, ?> f : PERSON_I_FACTORIES) {
			l.addAll(f.make(actor, target));
		}
		return l;
	}
}

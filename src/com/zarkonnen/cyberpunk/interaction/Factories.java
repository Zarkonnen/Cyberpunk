package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public final class Factories {
	private Factories() {}
	public static final List<InteractionFactory<Tile, ?>> TILE_I_FACTORIES = new ArrayList<InteractionFactory<Tile, ?>>();
	static {
		TILE_I_FACTORIES.add(new MoveToHome.F());
		TILE_I_FACTORIES.add(new MoveToWork.F());
		TILE_I_FACTORIES.add(new MoveToType.F());
		TILE_I_FACTORIES.add(new Scavenge.F());
		TILE_I_FACTORIES.add(new HealAtClinic.F());
		TILE_I_FACTORIES.add(new Gamble.F());
		TILE_I_FACTORIES.add(new VisitDrugDen.F());
		TILE_I_FACTORIES.add(new Rest.F());
		TILE_I_FACTORIES.add(new BuyFromOutsideWorld.F());
		TILE_I_FACTORIES.add(new SellToOutsideWorld.F());
		TILE_I_FACTORIES.add(new DoWork.F());
		TILE_I_FACTORIES.add(new BuyFoodAtRestaurantOrBar.F());
		TILE_I_FACTORIES.add(new SearchForGadgets.F());
		TILE_I_FACTORIES.add(new HarvestGadget.F());
		TILE_I_FACTORIES.add(new DeployGadget.F());
		TILE_I_FACTORIES.add(new Datatrawl.F());
		TILE_I_FACTORIES.add(new ImplantImplantAtClinic.F());
		TILE_I_FACTORIES.add(new RemoveImplantAtClinic.F());
	}
	
	public static final List<InteractionFactory<Person, ?>> PERSON_I_FACTORIES = new ArrayList<InteractionFactory<Person, ?>>();
	static {
		PERSON_I_FACTORIES.add(new VisitBrothel.F());
		PERSON_I_FACTORIES.add(new BlackmailForMoney.F());
		PERSON_I_FACTORIES.add(new BlackmailForItem.F());
		PERSON_I_FACTORIES.add(new Buy.F());
		PERSON_I_FACTORIES.add(new SellToPerson.F());
		PERSON_I_FACTORIES.add(new SellToBusiness.F());
		PERSON_I_FACTORIES.add(new CheckOut.F());
		PERSON_I_FACTORIES.add(new Gogglehack.F());
		PERSON_I_FACTORIES.add(new Mug.F());
		PERSON_I_FACTORIES.add(new Attack.F());
		PERSON_I_FACTORIES.add(new Stun.F());
		PERSON_I_FACTORIES.add(new Loot.F());
		PERSON_I_FACTORIES.add(new Datajack.F());
		PERSON_I_FACTORIES.add(new HarvestImplants.F());
		PERSON_I_FACTORIES.add(new Murder.F());
	}
	
	public static final List<InteractionFactory<Item, ?>> ITEM_I_FACTORIES = new ArrayList<InteractionFactory<Item, ?>>();
	static {
		ITEM_I_FACTORIES.add(new RemoveImplant.F());
		ITEM_I_FACTORIES.add(new ImplantImplant.F());
		ITEM_I_FACTORIES.add(new Eat.F());
		ITEM_I_FACTORIES.add(new FirstAid.F());
		ITEM_I_FACTORIES.add(new Disseminate.F());
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
	
	public static List<Interaction<Item>> make(Person actor, Item target) {
		ArrayList<Interaction<Item>> l = new ArrayList<Interaction<Item>>();
		for (InteractionFactory<Item, ?> f : ITEM_I_FACTORIES) {
			l.addAll(f.make(actor, target));
		}
		return l;
	}
}

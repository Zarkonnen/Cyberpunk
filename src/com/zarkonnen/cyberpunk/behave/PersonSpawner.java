package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import static com.zarkonnen.cyberpunk.TileType.*;
import static com.zarkonnen.cyberpunk.behave.Job.*;
import com.zarkonnen.cyberpunk.WorldMap;

public enum PersonSpawner {
	PROSTITUTES(2, BROTHEL, PROSTITUTE, BROTHEL, null, 80),
	DEALERS(2, DRUG_LAB, DRUG_DEALER, SLUM_BARGE, DRUG_LAB, 200),
	V_FOOD(2, V_FARM, FOOD_MERCHANT, ROOFTOP_SLUM, V_FARM, 100),
	H_FOOD(2, ROOFTOP_FARM, FOOD_MERCHANT, ROOFTOP_SLUM, ROOFTOP_FARM, 50),
	TRADE(4, DOCK, TRADER, SLUM_BARGE, null, 500),
	HWM(1, HARDWARE_SHOP, HARDWARE_MERCHANT, APARTMENT, DOCK, 500),
	GRI(2, CLINIC, GRINDER, APARTMENT, DOCK, 400),
	VFW(4, V_FARM, V_FARM_WORKER, ROOFTOP_SLUM, null, 20),
	HFW(3, ROOFTOP_FARM, V_FARM_WORKER, ROOFTOP_SLUM, null, 20),
	VFB(1, V_FARM, V_FARM_BOSS, APARTMENT, null, 300),
	SSW(5, SWEATSHOP, SWEATSHOP_WORKER, SLUM_BARGE, null, 10),
	SSB(1, SWEATSHOP, SWEATSHOP_BOSS, APARTMENT, null, 500),
	PROG(3, OFFICE, PROGRAMMER, APARTMENT, null, 200),
	PROG_SR(1, SERVER_ROOM, PROGRAMMER, APARTMENT, null, 100),
	PROGB(1, OFFICE, PROGRAMMER, PENTHOUSE, null, 1500),
	VAG(1, CORRIDOR, VAGRANT, CORRIDOR, null, 5),
	AKID(1, APARTMENT, KID, APARTMENT, null, 20),
	SKID(2, ROOFTOP_SLUM, KID, ROOFTOP_SLUM, null, 5),
	BKID(2, SLUM_BARGE, KID, SLUM_BARGE, null, 0),
	MUG(1, SLUM_BARGE, MUGGER, SLUM_BARGE, null, 30),
	HACK(1, APARTMENT, HACKER, APARTMENT, null, 100),
	FENCE(1, BACK_ROOM, DATA_FENCE, PENTHOUSE, null, 2000),
	THI(1, SLUM_BARGE, THIEF, SLUM_BARGE, null, 50),
	GANG(1, GANG_HIDEOUT, GANGSTER, ROOFTOP_SLUM, null, 200),
	PLUTO(1, OFFICE, PLUTOCRAT, PENTHOUSE, null, 5000),
	GEN(2, GENETICS_LAB, GENETICIST, APARTMENT, null, 300),
	GENB(1, GENETICS_LAB, GENETICIST_BOSS, PENTHOUSE, null, 1200),
	PERSAS(1, PENTHOUSE, PA, APARTMENT, null, 200),
	COOK(2, DRUG_LAB, DRUG_COOKER, APARTMENT, DOCK, 400),
	COOKD(1, DRUG_DEN, DRUG_COOKER, APARTMENT, DOCK, 400),
	GAMB(2, GAMBLING_PARLOUR, GAMBLING_PARLOUR_OPERATOR, APARTMENT, null, 300),
	BART(2, BAR, BARTENDER, ROOFTOP_SLUM, null, 60),
	GUND(1, GUN_SHOP, GUN_DEALER, APARTMENT, DOCK, 300),
	CLOD(2, CLOTHES_SHOP, CLOTHES_DEALER, APARTMENT, SWEATSHOP, 200),
	JEWD(1, JEWELLERY_SHOP, JEWELLERY_DEALER, APARTMENT, DOCK, 500),
	ARTD(1, ART_GALLERY, ART_DEALER, APARTMENT, DOCK, 800);
	public final int quantity;
	public final TileType work;
	public final Job job;
	public final TileType home;
	public final TileType supply;
	public final int cash;

	private PersonSpawner(int quantity, TileType work, Job job, TileType home, TileType supply, int cash) {
		this.quantity = quantity;
		this.work = work;
		this.job = job;
		this.home = home;
		this.supply = supply;
		this.cash = cash;
	}
	
	public void install(WorldMap wm) {
		for (Tile workT : wm.tilesOfType(work)) {
			for (int i = 0; i < quantity; i++) {
				Tile homeT;
				if (work == home) {
					homeT = workT;
				} else {
					homeT = wm.randomOfType(home);
				}
				Tile supplyT = null;
				if (supply != null) {
					supplyT = wm.randomOfType(supply);
				}
				if (homeT == null) {
					homeT = workT;
				}
				Person p = new Person(homeT);
				p.home = homeT;
				p.workplace = workT;
				p.supplier = supplyT;
				job.doInstall(p, wm.r);
				p.money = (int) ((wm.r.nextDouble() + 0.5) * cash);
				if (work.locked > 0) {
					Item key;
					if (wm.r.nextBoolean()) {
						key = new Item(ItemType.PASSWORD);
					} else {
						key = new Item(ItemType.SECURITY_KEY);
					}
					key.keyFor = workT;
					p.inventory.add(key);
				}
				//System.out.println("Adding: " + p.description());
				wm.addPerson(p);
			}
		}
	}
	
	public static void installAll(WorldMap wm) {
		for (PersonSpawner sp : PersonSpawner.values()) {
			sp.install(wm);
		}
	}
}

package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.TileType;
import java.util.EnumMap;
import java.util.EnumSet;
import static com.zarkonnen.catengine.util.Utils.*;
import com.zarkonnen.cyberpunk.Item;
import static com.zarkonnen.cyberpunk.ItemType.*;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import static com.zarkonnen.cyberpunk.TileType.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DoWork extends AbstractInteraction<Tile> {
	public static final EnumMap<TileType, EnumSet<ItemType>> OUTPUTS = new EnumMap<TileType, EnumSet<ItemType>>(m(
		p(CLINIC, EnumSet.of(ENDURANCE_UPGRADE, GRINDER_TOOLS, MEDICAL_HELPER_IMPLANT, STRENGTH_ENHANCEMENT, MULTIWAVE_EYES)),
		p(HARDWARE_SHOP, EnumSet.of(BUG, LOCKPICKS, WIRETAP, TRANQ_BUG, TRANQ_DART, AR_GOGGLES)),
		p(BROTHEL, EnumSet.of(AMATEUR_SEX_VID)),
		p(DRUG_LAB, EnumSet.of(UPPERS, DOWNERS, EXPERIMENTAL_UPPERS, EXPERIMENTAL_DOWNERS)),
		p(GENETICS_LAB, EnumSet.of(GENETIC_CODE)),
		p(OFFICE, EnumSet.of(ADVANCED_ROOTKIT, ANTIVIRUS_SOFTWARE, AR_GOGGLES, AR_GOGGLE_VIRUS, COUNTER_INTRUSION_SYSTEM, CRYPTOGRAPHIC_COPROCESSOR, FANCY_AR_GOGGLES, INTRUSION_EXPERT_SYSTEM, OPERATING_SYSTEM_HARDENER, QUANTUM_CORE, VALUABLE_DATA)),
		p(PENTHOUSE, EnumSet.of(VALUABLE_DATA, SHARES)),
		p(SLUM_BARGE, EnumSet.of(SHIV, SPEAR)),
		p(ROOFTOP_FARM, EnumSet.of(VEGETABLES)),
		p(ROOFTOP_SLUM, EnumSet.of(SHIV, SPEAR)),
		p(V_FARM, EnumSet.of(VEGETABLES)),
		p(SWEATSHOP, EnumSet.of(DESIGNER_CLOTHES, SHARP_SUIT))
	));
	
	public static final EnumMap<ItemType, Integer> OUTPUT_P = new EnumMap<ItemType, Integer>(m(
		p(ENDURANCE_UPGRADE, 100),
		p(GRINDER_TOOLS, 200),
		p(MEDICAL_HELPER_IMPLANT, 100),
		p(STRENGTH_ENHANCEMENT, 100),
		p(MULTIWAVE_EYES, 10),
		p(EXPERIMENTAL_UPPERS, 300),
		p(EXPERIMENTAL_DOWNERS, 300),
		p(ADVANCED_ROOTKIT, 2),
		p(ANTIVIRUS_SOFTWARE, 5),
		p(AR_GOGGLES, 100),
		p(AR_GOGGLE_VIRUS, 2),
		p(COUNTER_INTRUSION_SYSTEM, 2),
		p(CRYPTOGRAPHIC_COPROCESSOR, 3),
		p(FANCY_AR_GOGGLES, 10),
		p(INTRUSION_EXPERT_SYSTEM, 1),
		p(OPERATING_SYSTEM_HARDENER, 5),
		p(QUANTUM_CORE, 1),
		p(VALUABLE_DATA, 10),
		p(SHARES, 10),
		p(GENETIC_CODE, 10),
		p(SHARP_SUIT, 5),
		p(DESIGNER_CLOTHES, 20),
		p(AMATEUR_SEX_VID, 5),
		p(VEGETABLES, 80)
	));
	
	public static final EnumMap<TileType, EnumSet<ItemType>> INPUTS = new EnumMap<TileType, EnumSet<ItemType>>(m(
		p(CLINIC, EnumSet.of(MEDICAL_SUPPLIES, MACHINE_PARTS)),
		p(HARDWARE_SHOP, EnumSet.of(RAM, CPUS, MACHINE_PARTS)),
		p(DRUG_LAB, EnumSet.of(CHEMICALS)),
		p(PENTHOUSE, EnumSet.of(UPPERS, EXPERIMENTAL_UPPERS))
	));
	
	public static final EnumSet<TileType> FREELANCERS = EnumSet.of(PENTHOUSE, SLUM_BARGE, ROOFTOP_SLUM);

	public DoWork(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Work at " + target().getName();
	}

	@Override
	public String disabledReason() {
		if (INPUTS.containsKey(target().type)) {
			EnumSet<ItemType> inputs = INPUTS.get(target().type);
			for (Item it : target().inventory) {
				if (inputs.contains(it.type)) {
					return null;
				}
			}
			for (Item it : actor().inventory) {
				if (inputs.contains(it.type)) {
					return null;
				}
			}
			
			return "You need raw materials to work.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Busy busy worker bees.";
	}
	
	private void consume() {
		if (INPUTS.containsKey(target().type)) {
			EnumSet<ItemType> inputs = INPUTS.get(target().type);
			for (Iterator<Item> it = target().inventory.iterator(); it.hasNext();) {
				if (inputs.contains(it.next().type)) {
					it.remove();
					return;
				}
			}
			for (Iterator<Item> it = actor().inventory.iterator(); it.hasNext();) {
				if (inputs.contains(it.next().type)) {
					it.remove();
					return;
				}
			}
		}
	}
	
	private Item produce() {
		if (!OUTPUTS.containsKey(target().type)) { return null; }
		int roll = r().nextInt(OUTPUTS.get(target().type).size());
		for (ItemType t : OUTPUTS.get(target().type)) {
			if (roll <= 0) {
				if (!OUTPUT_P.containsKey(t) || r().nextInt(1000) < OUTPUT_P.get(t)) {
					return new Item(t);
				} else {
					return null;
				}
			}
			roll--;
		}
		throw new RuntimeException("Item generation messup.");
	}

	@Override
	public String run() {
		exhaust(1);
		consume();
		Item product = produce();
		if (product == null) {
			return "You work.";
		}
		if (FREELANCERS.contains(target().type)) {
			actor().inventory.add(product);
		} else {
			actor().money += product.type.value / 8;
			int hiding = Math.min(100, 30 + r().nextInt(60) + product.type.value / 20);
			target().hiddenItems.add(new Tile.HiddenItem(hiding, product));
		}
		/*if (target().type == TileType.BROTHEL) {
			for (Person p : target().people()) {
				if (p.workplace != target() && r().nextInt(5) == 0) {
					Item tape = new Item(ItemType.BLACKMAIL_MATERIAL);
					tape.blackmailFor = p;
					target().hiddenItems.add(new Tile.HiddenItem(30 + r().nextInt(30), tape));
				}
			}
		}*/
		return "You produce a " + product.getName() + ".";
	}
	
	public static class F implements InteractionFactory<Tile, DoWork> {
		@Override
		public List<DoWork> make(Person actor, Tile t) {
			if (!actor.isPlayer && actor.workplace == t && OUTPUTS.containsKey(t.type)) {
				return Collections.singletonList(new DoWork(actor, t));
			} else {
				return Collections.emptyList();
			}
		}
	}
}

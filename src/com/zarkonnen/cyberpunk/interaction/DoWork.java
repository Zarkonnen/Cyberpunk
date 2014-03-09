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
		p(CLINIC, EnumSet.of(ENDURANCE_UPGRADE, GRINDER_TOOLS, MEDICAL_HELPER_IMPLANT, STRENGTH_ENHANCEMENT)),
		p(HARDWARE_SHOP, EnumSet.of(BUG, LOCKPICKS, WIRETAP, TRANQ_BUG, TRANQ_DART)),
		p(BROTHEL, EnumSet.of(AMATEUR_SEX_VID)),
		p(DRUG_LAB, EnumSet.of(UPPERS, DOWNERS, EXPERIMENTAL_UPPERS, EXPERIMENTAL_DOWNERS)),
		p(GENETICS_LAB, EnumSet.of(GENETIC_CODE)),
		p(OFFICE, EnumSet.of(ADVANCED_ROOTKIT, ANTIVIRUS_SOFTWARE, AR_GOGGLES, AR_GOGGLE_VIRUS, COUNTER_INTRUSION_SYSTEM, CRYPTOGRAPHIC_COPROCESSOR, FANCY_AR_GOGGLES, INTRUSION_EXPERT_SYSTEM, MULTIWAVE_EYES, OPERATING_SYSTEM_HARDENER, QUANTUM_CORE, VALUABLE_DATA)),
		p(PENTHOUSE, EnumSet.of(VALUABLE_DATA, SHARES)),
		p(SLUM_BARGE, EnumSet.of(SHIV, SPEAR)),
		p(ROOFTOP_FARM, EnumSet.of(VEGETABLES)),
		p(ROOFTOP_SLUM, EnumSet.of(SHIV, SPEAR)),
		p(V_FARM, EnumSet.of(VEGETABLES)),
		p(SWEATSHOP, EnumSet.of(DESIGNER_CLOTHES, SHARP_SUIT))
	));
	
	public static final EnumMap<TileType, EnumSet<ItemType>> INPUTS = new EnumMap<TileType, EnumSet<ItemType>>(m(
		p(CLINIC, EnumSet.of(MEDICAL_SUPPLIES)),
		p(HARDWARE_SHOP, EnumSet.of(RAM, CPUS)),
		p(DRUG_LAB, EnumSet.of(CHEMICALS)),
		p(PENTHOUSE, EnumSet.of(UPPERS, EXPERIMENTAL_UPPERS))
	));
	
	public static final EnumSet<TileType> FREELANCERS = EnumSet.of(PENTHOUSE, SLUM_BARGE, ROOFTOP_FARM, V_FARM, ROOFTOP_SLUM);

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
	public String description() {
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
		int roll = r().nextInt(OUTPUTS.get(target().type).size());
		for (ItemType t : OUTPUTS.get(target().type)) {
			if (roll <= 0) {
				return new Item(t);
			}
			roll--;
		}
		throw new RuntimeException("Item generation messup.");
	}

	@Override
	public String run() {
		consume();
		Item product = produce();
		if (FREELANCERS.contains(target().type)) {
			actor().inventory.add(product);
		} else {
			actor().money += product.type.value / 8;
			target().hiddenItems.add(new Tile.HiddenItem(r().nextInt(50), product));
		}
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

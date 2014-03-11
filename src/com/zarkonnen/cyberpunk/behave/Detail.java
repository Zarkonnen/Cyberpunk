package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.Item;
import com.zarkonnen.cyberpunk.ItemType;
import static com.zarkonnen.cyberpunk.ItemType.*;
import com.zarkonnen.cyberpunk.Names;
import static com.zarkonnen.cyberpunk.TileType.*;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Adultery;
import com.zarkonnen.cyberpunk.interaction.Attack;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.Gamble;
import com.zarkonnen.cyberpunk.interaction.ImplantImplant;
import com.zarkonnen.cyberpunk.interaction.ImplantImplantAtClinic;
import com.zarkonnen.cyberpunk.interaction.MoveToHome;
import com.zarkonnen.cyberpunk.interaction.MoveToType;
import com.zarkonnen.cyberpunk.interaction.Murder;
import com.zarkonnen.cyberpunk.interaction.StealFromEmployers;
import com.zarkonnen.cyberpunk.interaction.TakeDrug;
import com.zarkonnen.cyberpunk.interaction.VisitBrothel;
import com.zarkonnen.cyberpunk.interaction.VisitDrugDen;
import com.zarkonnen.cyberpunk.interaction.WatchHorriblePorn;
import java.util.EnumSet;
import java.util.Random;

public enum Detail {
	UPPER_ADDICT("They have a nervous look about them.", EnumSet.of(UPPERS, EXPERIMENTAL_UPPERS), EnumSet.noneOf(ItemType.class), EnumSet.of(UPPERS)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(TakeDrug.class).sober();
			p.behave(MoveToType.class).moveTo(DRUG_DEN);
			p.behave(Buy.class).item(UPPERS, EXPERIMENTAL_UPPERS);
		}
	},
	DOWNER_ADDICT("They have a tired look about them.", EnumSet.of(DOWNERS, EXPERIMENTAL_DOWNERS), EnumSet.noneOf(ItemType.class), EnumSet.of(DOWNERS)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(TakeDrug.class).sober();
			p.behave(MoveToType.class).moveTo(DRUG_DEN);
			p.behave(VisitDrugDen.class);
			p.behave(Buy.class).item(DOWNERS, EXPERIMENTAL_DOWNERS);
		}
	},
	GAMBLER(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Gamble.class);
			p.behave(MoveToType.class).moveTo(GAMBLING_PARLOUR);
		}
	},
	WHORER(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(VisitBrothel.class);
			p.behave(MoveToType.class).moveTo(BROTHEL);
		}
	},
	IMPLANTED("They appear to be fond of augmentations.", EnumSet.of(ENDURANCE_UPGRADE, GRINDER_TOOLS, MEDICAL_HELPER_IMPLANT, STRENGTH_ENHANCEMENT), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Buy.class).item(ENDURANCE_UPGRADE, GRINDER_TOOLS, MEDICAL_HELPER_IMPLANT, STRENGTH_ENHANCEMENT, MULTIWAVE_EYES);
			p.behave(MoveToType.class).moveTo(CLINIC);
			p.behave(ImplantImplantAtClinic.class);
			p.behave(ImplantImplant.class);
			p.addApproximateSkill(Skill.GRINDING, 20, r);
			ItemType[] is = { ENDURANCE_UPGRADE, MEDICAL_HELPER_IMPLANT, STRENGTH_ENHANCEMENT, MULTIWAVE_EYES };
			Item implant = new Item(is[r.nextInt(is.length)]);
			p.implants.add(implant);
			p.health -= 20;
		}
	},
	ARMED("Something about the way they walk suggests they're armed.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(PISTOL)) {
		@Override
		public void install(Person p, Random r) {
			p.addApproximateSkill(Skill.FIGHTING, 15, r);
		}
	},
	SKINNY("Thin as a rake.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.addApproximateSkill(Skill.FIGHTING, -10, r);
		}
	},
	MUSCULAR("Bulging muscles.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.addApproximateSkill(Skill.FIGHTING, 10, r);
		}
	},
	IMPOSING("Something about their gaze... A definite presence.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.addApproximateSkill(Skill.FORCE_OF_PERSONALITY, 25, r);
		}
	},
	SHY("Very shy, it seems.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.addApproximateSkill(Skill.FORCE_OF_PERSONALITY, -20, r);
		}
	},
	SHAVEN("Their hair and eyebrows have been completely razored off.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	TATTOOED("Tattoos cover most of their body.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	SCARRED("A gnarled trail of scar tissue crosses their face and disappears under their jacket.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	PIERCED("You can see several piercings on their face and body.", EnumSet.of(JEWELLERY), EnumSet.noneOf(ItemType.class), EnumSet.of(JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Buy.class).item(JEWELLERY);
		}
	},
	MODDED("They have extensive body modifications.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	FASHIONABLE("They're wearing the very latest in up-to-the-nanosecond fashion.", EnumSet.of(DESIGNER_CLOTHES), EnumSet.noneOf(ItemType.class), EnumSet.of(DESIGNER_CLOTHES)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Buy.class).item(DESIGNER_CLOTHES);
		}
	},
	BLINGY("You wonder how they can stand under the weight of all that jewellery.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	FILTHY("They are covered in a greasy layer of filth.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(JEWELLERY)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	SHIFTY("They have a shifty look to them.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	LEFT_EYED("Beneath their AR goggles, their right eye is missing. The wound looks fresh.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	RIGHT_EYED("Their left eye is covered by a leather patch.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	MURDERER("The way they glance back at you makes you shiver.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(SWITCHBLADE)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Murder.class).unobservedExceptVictim();
			p.behave(Attack.class).unobservedExceptVictim();
			p.addApproximateSkill(Skill.FIGHTING, 5, r);
		}
	},
	VIGILANTE("They seem to be looking for someone.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.of(PISTOL)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Attack.class).targetDisliked(30).unobservedExceptVictim();
			p.addApproximateSkill(Skill.FIGHTING, 5, r);
		}
	},
	ADULTERY(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(Adultery.class);
		}
	},
	PORN(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(WatchHorriblePorn.class);
		}
	},
	STEAL(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(StealFromEmployers.class);
		}
	},
	LUDDITE("They aren't wearing any AR goggles.", EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
		}
	},
	BARFLY(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(MoveToType.class).moveTo(BAR);
		}
	},
	STAY_AT_HOME(null, EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class), EnumSet.noneOf(ItemType.class)) {
		@Override
		public void install(Person p, Random r) {
			p.behave(MoveToHome.class);
		}
	};

	private Detail(String description, EnumSet<ItemType> buys, EnumSet<ItemType> sells, EnumSet<ItemType> equipment) {
		this.description = description;
		this.buys = buys;
		this.sells = sells;
		this.equipment = equipment;
	}
		
	public final String description;
	public final EnumSet<ItemType> buys;
	public final EnumSet<ItemType> sells;
	public final EnumSet<ItemType> equipment;
	
	public abstract void install(Person p, Random r);
	
	public void doInstall(Person p, Random r) {
		if (description != null) {
			p.description += " " + description;
		}
		p.buyForSelf = EnumSet.copyOf(p.buyForSelf);
		p.buyForSelf.addAll(buys);
		p.sell = EnumSet.copyOf(p.sell);
		p.sell.addAll(sells);
		install(p, r);
		for (ItemType t : equipment) {
			p.inventory.add(new Item(t));
		}
	}
}

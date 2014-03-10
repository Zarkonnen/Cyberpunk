package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.ItemType;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.interaction.Interaction;
import com.zarkonnen.cyberpunk.interaction.ItemInteraction;
import com.zarkonnen.cyberpunk.interaction.MoveToType;
import java.util.ArrayList;

public class InteractionBehavior {
	public final ArrayList<Condition> conditions = new ArrayList<Condition>();

	public boolean enabled(Interaction interaction) {
		for (Condition c : conditions) {
			if (!c.check(interaction)) { return false; }
		}
		return interaction.enabled();
	}
	
	public static InteractionBehavior behave(Class iType) {
		InteractionBehavior ib = new InteractionBehavior();
		ib.conditions.add(new IType(iType));
		return ib;
	}
	
	public static interface Condition {
		public boolean check(Interaction interaction);
	}
	
	public static class IType implements Condition {
		public final Class type;

		public IType(Class type) {
			this.type = type;
		}

		@Override
		public boolean check(Interaction interaction) {
			return interaction.getClass().equals(type);
		}
	}
	
	public InteractionBehavior item(ItemType type) {
		conditions.add(new IItemType(type));
		return this;
	}
	
	public static class IItemType implements Condition {
		public final ItemType type;

		public IItemType(ItemType type) {
			this.type = type;
		}

		@Override
		public boolean check(Interaction interaction) {
			if (!(interaction instanceof ItemInteraction)) { return false; }
			return ((ItemInteraction) interaction).getItem().type == type;
		}
	}
	
	public InteractionBehavior food() {
		conditions.add(new Food());
		return this;
	}
	
	public static class Food implements Condition {
		@Override
		public boolean check(Interaction interaction) {
			if (!(interaction instanceof ItemInteraction)) { return false; }
			return ((ItemInteraction) interaction).getItem().type.food > 0;
		}
	}
	
	public InteractionBehavior moveTo(TileType type) {
		conditions.add(new MoveTo(type));
		return this;
	}
	
	public static class MoveTo implements Condition {
		public final TileType type;

		public MoveTo(TileType type) {
			this.type = type;
		}

		@Override
		public boolean check(Interaction interaction) {
			if (!(interaction instanceof MoveToType)) { return false; }
			return ((MoveToType) interaction).tileType == type;
		}
	}
	
	public InteractionBehavior hungry(int hunger) {
		conditions.add(new Hungry(hunger));
		return this;
	}
	
	public static class Hungry implements Condition {
		public final int hunger;

		public Hungry(int hunger) {
			this.hunger = hunger;
		}

		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().hunger >= hunger;
		}
	}
	
	public InteractionBehavior injured(int health) {
		conditions.add(new Injured(health));
		return this;
	}
	
	public static class Injured implements Condition {
		public final int health;

		public Injured(int health) {
			this.health = health;
		}

		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().health <= health;
		}
	}
	
	public InteractionBehavior exhausted(int exhaustion) {
		conditions.add(new Exhausted(exhaustion));
		return this;
	}
	
	public static class Exhausted implements Condition {
		public final int exhaustion;

		public Exhausted(int exhaustion) {
			this.exhaustion = exhaustion;
		}

		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().getVisibleExhaustion() <= exhaustion;
		}
	}
	
	public InteractionBehavior poor(int money) {
		conditions.add(new Poor(money));
		return this;
	}
	
	public static class Poor implements Condition {
		public final int money;

		public Poor(int money) {
			this.money = money;
		}
		
		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().money < money;
		}
	}
	
	public InteractionBehavior has(ItemType... anyOf) {
		conditions.add(new Carrying(anyOf));
		return this;
	}
	
	public InteractionBehavior hasnt(ItemType... anyOf) {
		conditions.add(new Not(new Carrying(anyOf)));
		return this;
	}
	
	public static class Carrying implements Condition {
		public final ItemType[] types;

		public Carrying(ItemType... anyOf) {
			this.types = anyOf;
		}

		@Override
		public boolean check(Interaction interaction) {
			for (ItemType t : types) {
				if (interaction.actor().hasItem(t)) { return true; }
			}
			return false;
		}
	}
	
	public InteractionBehavior sober() {
		conditions.add(new OnDrugs(false));
		return this;
	}
	
	public InteractionBehavior high() {
		conditions.add(new OnDrugs(true));
		return this;
	}
	
	public static class OnDrugs implements Condition {
		public final boolean onDrugs;

		public OnDrugs(boolean onDrugs) {
			this.onDrugs = onDrugs;
		}

		
		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().drugsTaken.isEmpty() != onDrugs;
		}
	}
	
	public InteractionBehavior between(int fromHour, int toHour) {
		conditions.add(new TimeBetween(fromHour, toHour));
		return this;
	}
	
	public static class TimeBetween implements Condition {
		public final int fromHour, toHour;

		public TimeBetween(int fromHour, int toHour) {
			this.fromHour = fromHour;
			this.toHour = toHour;
		}
		
		@Override
		public boolean check(Interaction interaction) {
			int hr = interaction.actor().location().map.hour();
			return hr >= fromHour && hr <= toHour;
		}
	}
	
	public InteractionBehavior unobserved() {
		conditions.add(new Unobserved());
		return this;
	}
	
	public static class Unobserved implements Condition {
		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().location().people().size() < 2;
		}
	}
	
	public InteractionBehavior unobservedExceptVictim() {
		conditions.add(new UnobservedExceptVictim());
		return this;
	}
	
	public static class UnobservedExceptVictim implements Condition {
		@Override
		public boolean check(Interaction interaction) {
			return interaction.actor().location().people().size() < 3;
		}
	}
	
	public static class Not implements Condition {
		public final Condition c;

		public Not(Condition c) {
			this.c = c;
		}

		@Override
		public boolean check(Interaction interaction) {
			return !c.check(interaction);
		}
	}
	
	public static class Or implements Condition {
		public final Condition[] cs;

		public Or(Condition... cs) {
			this.cs = cs;
		}

		@Override
		public boolean check(Interaction interaction) {
			for (Condition c : cs) {
				if (c.check(interaction)) { return true; }
			}
			return false;
		}
	}
	
	public static class And implements Condition {
		public final Condition[] cs;

		public And(Condition... cs) {
			this.cs = cs;
		}

		@Override
		public boolean check(Interaction interaction) {
			for (Condition c : cs) {
				if (!c.check(interaction)) { return false; }
			}
			return true;
		}
	}
}

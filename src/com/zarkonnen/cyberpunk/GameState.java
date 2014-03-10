package com.zarkonnen.cyberpunk;

import com.zarkonnen.cyberpunk.interaction.Interaction;
import java.io.Serializable;

public class GameState implements Serializable {
	public final WorldMap map;
	public final Person player;

	public GameState(WorldMap map, Person player) {
		this.map = map;
		this.player = player;
	}
	
	public GameState(long seed, int xS, int yS, int zS) {
		this.map = new WorldMap(seed, xS, yS, zS);
		this.player = this.map.people().get(0);
		player.isPlayer = true;
		for (Skill sk : Skill.values()) {
			player.setSkill(sk, 40);
		}
	}

	public void playerAction(Interaction<?> interaction) {
		String msg = interaction.run();
		player.location().observe(interaction);
		if (msg != null) {
			player.messages.add(msg);
		}
		map.tick();
	}
}

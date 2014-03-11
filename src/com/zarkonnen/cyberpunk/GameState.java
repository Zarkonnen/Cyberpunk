package com.zarkonnen.cyberpunk;

import com.zarkonnen.cyberpunk.interaction.Interaction;
import java.io.Serializable;

public class GameState implements Serializable {
	public final WorldMap map;
	public Person player;

	public GameState(WorldMap map, Person player) {
		this.map = map;
		this.player = player;
	}
	
	public GameState(long seed, int xS, int yS, int zS) {
		this.map = new WorldMap(seed, xS, yS, zS);
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

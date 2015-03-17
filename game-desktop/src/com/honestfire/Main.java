package com.honestfire;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = game.TITLE;
		cfg.width = game.V_WIDTH * game.SCALE;
		cfg.height = game.V_HEIGHT * game.SCALE;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new game(), cfg);
	}
}

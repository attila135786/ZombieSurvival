package com.epgaming.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.epgaming.game.ZombieSurvivalGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie Survival";
		config.width = 1400;
		config.height = 800;

		new LwjglApplication(new ZombieSurvivalGame(), config);
	}
}

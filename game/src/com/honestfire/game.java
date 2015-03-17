package com.honestfire;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class game extends Game {
	
	public Preferences prefs;
	public static String TITLE = "Test gry";
	public static int V_WIDTH = 320;
	public static int V_HEIGHT = 240;
	public static int SCALE = 2;
	
	
	@Override
	public void create() {		
        
		prefs = Gdx.app.getPreferences("config.cfg");
		prefs.flush();
		
        this.setScreen(new mainMenuScreen(this));	// First Screen to switch when app loaded
		
	}

	@Override
	public void render() {
		
		super.render();
		
	}
	
	@Override
	public void dispose() {
		
	}
}

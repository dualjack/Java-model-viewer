package com.honestfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class mainMenuScreen implements Screen {

	final game game;
	
	private BitmapFont fontButtons;
	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private TextButton buttonPlay, buttonExit;
	private Label heading;
	
	public mainMenuScreen(final game gameObject){
		
		game = gameObject; // save game object in variable --- !!!
		
	}
	
	@Override
	public void show() {
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);	// Change input processor to stage
		
		fontButtons = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);	// font setup
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// -- Styl przycisków
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("darkButton.up");
		textButtonStyle.down = skin.getDrawable("darkButton.down");
		textButtonStyle.overFontColor = Color.ORANGE;
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = fontButtons;
		textButtonStyle.fontColor = Color.WHITE;
		
		// -- Inicjalizacja przycisków
		
		buttonPlay = new TextButton("Play", textButtonStyle);
		buttonPlay.pad(10,30,10,30);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.input.setInputProcessor(null); // Wy³¹cz Input Procesor
				game.setScreen(new modelRenderTest());
			}
		});
		
		buttonExit = new TextButton("Exit", textButtonStyle);
		buttonExit.pad(10,30,10,30);
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();	// Zamknij aplikacjê
			}
		});
		
		// -- Styl tytu³u
		
		LabelStyle headingStyle = new LabelStyle(fontButtons,Color.GRAY);
		
		// -- Inicjowanie tytu³u
		
		heading = new Label("Biegnij Maleñka ;)", headingStyle);
		
		
		// -- Komponowanie tabeli
		
		table.add(heading);
		table.row().padTop(40);
		table.add(buttonPlay);
		table.row().padTop(20);
		table.add(buttonExit);
		table.debug();
		stage.addActor(table);
		
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        ///Table.drawDebug(stage);
        
        stage.act(delta);
        stage.draw();
        
	}

	@Override
	public void resize(int width, int height) {
		
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		
		fontButtons.dispose();
		atlas.dispose();
		stage.dispose();
		skin.dispose();
		
	}

}

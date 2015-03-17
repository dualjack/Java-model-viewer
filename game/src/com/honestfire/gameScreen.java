package com.honestfire;

import static com.honestfire.handlers.B2Dvars.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class gameScreen implements Screen {
	
	OrthographicCamera camera;
	SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer b2dr;
	
	@Override
	public void show() {
		
		// - Box2D
		
		world = new World(new Vector2(0,-10),true);		// create world
		b2dr = new Box2DDebugRenderer();
		
		// create platfrom
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 /PPM, 120 /PPM);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 /PPM, 5 /PPM);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		
		// create falling box
		
		bdef.position.set(160 /PPM, 200 /PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		shape.setAsBox(5 /PPM, 5 /PPM);
		fdef.shape = shape;
		body.createFixture(fdef);
		
		
		// - camera
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.V_WIDTH /PPM, game.V_HEIGHT /PPM);
		
		// - batch
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			
			//
		
		batch.end();

	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        generalUpdate(delta);	//	update od game logic
        
        b2dr.render(world, camera.combined);
        
	}
	
	public void generalUpdate(float delta){
		
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());		// FPS count
		
		camera.update();
        world.step(delta, 6, 2);
		
	}
	
	@Override
	public void resize(int width, int height) {

	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

		
		
	}

}

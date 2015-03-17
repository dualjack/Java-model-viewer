package com.honestfire;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.UBJsonReader;

public class modelRenderTest implements Screen {

	private PerspectiveCamera camera;
    private Model model;
    private ModelInstance modelInstance;
    private Environment environment;
    private AnimationController controller;
    private ModelBatch modelBatch;
    public CameraInputController camController;
	
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		
		// Create camera sized to screens width/height with Field of View of 75 degrees
        camera = new PerspectiveCamera(
                75,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        
        // Move the camera 5 units back along the z-axis and look at the origin
        camera.position.set(0f,0f,7f);
        camera.lookAt(0f,0f,0f);
        
        // Near and Far (plane) represent the minimum and maximum ranges of the camera in, um, units
        camera.near = 1f; 
        camera.far = 300.0f;
        
        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);
        
        modelBatch = new ModelBatch();
        
        // Model loader needs a binary json reader to decode
        UBJsonReader jsonReader = new UBJsonReader();
        // Create a model loader passing in our json reader
        G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
        // Now load the model by name
        // Note, the model (g3db file ) and textures need to be added to the assets folder of the Android proj
        model = modelLoader.loadModel(Gdx.files.getFileHandle("model2.g3db", FileType.Internal));
        // Now create an instance.  Instance holds the positioning data, etc of an instance of your model
        modelInstance = new ModelInstance(model);
        
        //fbx-conv is supposed to perform this rotation for you... it doesnt seem to
        modelInstance.transform.rotate(1, 0, 0, -90);
        //move the model down a bit on the screen ( in a z-up world, down is -z ).
        modelInstance.transform.translate(0, 0, -2);

        // Finally we want some light, or we wont see our color.  The environment gets passed in during
        // the rendering process.  Create one, then create an Ambient ( non-positioned, non-directional ) light.
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1, 1, 1, 1));
        
        // You use an AnimationController to um, control animations.  Each control is tied to the model instance
        controller = new AnimationController(modelInstance);  
        // Pick the current animation by name
        controller.setAnimation("Walk1",1, new AnimationListener(){

            @Override
            public void onEnd(AnimationDesc animation) {
                // this will be called when the current animation is done. 
                // queue up another animation called "balloon". 
                // Passing a negative to loop count loops forever.  1f for speed is normal speed.
                controller.queue("Run1_hold_low",-1,1f,null,0f);
            }

            @Override
            public void onLoop(AnimationDesc animation) {
                
            }
            
        });

	}

	@Override
	public void render(float delta) {
		// You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        // For some flavor, lets spin our camera around the Y axis by 1 degree each time render is called
        //camera.rotateAround(Vector3.Zero, new Vector3(0,1,0),1f);
        // When you change the camera details, you need to call update();
        // Also note, you need to call update() at least once.
        camController.update();
        camera.update();
        
        controller.update(delta);

        modelBatch.begin(camera);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();
        

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
		// TODO Auto-generated method stub

	}

}

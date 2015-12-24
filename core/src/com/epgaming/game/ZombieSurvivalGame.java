package com.epgaming.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epgaming.game.screens.FirstGameScreen;
import com.epgaming.game.screens.ZombieSurvivalGameScreen;


public class ZombieSurvivalGame extends Game {
	FPSLogger fpsLogger = new FPSLogger();
	OrthographicCamera camera;
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	boolean debugCollision = true;
	boolean debugFPS = true;

	public SpriteBatch getBatch(){
		return batch;
	}

	public OrthographicCamera getCamera(){
		return camera;
	}

	@Override
	public void create() {
		Assets.initializeAssets();
		batch = new SpriteBatch();
		if(debugCollision){
			shapeRenderer = new ShapeRenderer();
			shapeRenderer.setAutoShapeType(true);
		}

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1400,800);

		// TODO: let the screen controller worry about which screen to set based on screen id
		FirstGameScreen firstGameScreen = new FirstGameScreen(this);
		this.setScreen(firstGameScreen);


		/*try {
//			ServerNetworkTest serverNetworkTest = new ServerNetworkTest();
			ClientNetworkTest clientNetworkTest = new ClientNetworkTest();
		} catch (Exception e) {

		}*/

	}

	@Override
	public void render() {
		if(debugFPS) fpsLogger.log();
		glClear();
		batch.begin();
		super.render();
		batch.end();

		if(debugCollision) {
			debugCollision();
		}
	}

	private void glClear() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void debugCollision(){
		if(this.screen == null){
			return;
		}

		ZombieSurvivalGameScreen screen = (ZombieSurvivalGameScreen) this.screen;
		shapeRenderer.begin();
		screen.debugCollision(shapeRenderer);
		shapeRenderer.end();
	}
}

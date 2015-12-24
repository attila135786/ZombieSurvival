package com.epgaming.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.epgaming.game.PlayerController;

public class ZombieSurvivalGameActor extends Actor {

	private static int MOVEMENT_DISTANCE = 150;

	protected TextureRegion region;
	protected Animation animation;
	private Polygon collisionPolygon;
	private Polygon rectangle;
	private Intersector intersector;
	private PlayerController controller;

	public ZombieSurvivalGameActor(TextureRegion region) {
		this.region = region;
		animation = new Animation(0.5f, region);
		initializeCollisionDetection();
	}

	public void setController(PlayerController controller){
		this.controller = controller;
	}

	// sprite actual height = 39
	// sprite actual width = 52
	// space at bottom = 6
	// space at top = 9
	private void initializeCollisionDetection() {
		float[] collisionVertices = { 13f, 10f, 50f, 10f, 42f, 34f, 14f, 40f };
		collisionPolygon = new Polygon(collisionVertices);

		collisionPolygon.setOrigin(getCurrentFrame().getRegionWidth()/2, getCurrentFrame().getRegionHeight()/2);

		collisionPolygon.setPosition(500, 500);
		float[] rectangleVertices = { 700f, 700f, 800, 700, 800, 800, 700, 800 };
		rectangle = new Polygon(rectangleVertices);

		intersector = new Intersector();

	}

	@Override
	public void act(float delta) {
		controller.handleInput();
		handleInput(delta);
		updateSprite();
	}

	private void updateSprite() {
		this.setPosition(collisionPolygon.getX(), collisionPolygon.getY());
	}

	// TODO: do this in an input controller or something of that nature
	private void handleInput(float delta) {

		float newX;
		float newY;
		if (controller.getInputMap().get("A")) {
			newX = collisionPolygon.getX() - MOVEMENT_DISTANCE * delta;
			checkCollision(newX, collisionPolygon.getY(), collisionPolygon.getRotation());
		}

		if (controller.getInputMap().get("D")) {
			newX = (collisionPolygon.getX() + MOVEMENT_DISTANCE * delta);
			checkCollision(newX, collisionPolygon.getY(), collisionPolygon.getRotation());
		}

		if (controller.getInputMap().get("W")) {
			newY = collisionPolygon.getY() + MOVEMENT_DISTANCE * delta;
			checkCollision(collisionPolygon.getX(), newY, collisionPolygon.getRotation());
		}

		if (controller.getInputMap().get("S")) {
			newY = collisionPolygon.getY() - MOVEMENT_DISTANCE * delta;
			checkCollision(collisionPolygon.getX(), newY, collisionPolygon.getRotation());
		}

		Vector2 mousePosition = controller.getMousePosition();
		Vector2 playerPosition = new Vector2(this.getX() + getCurrentFrame().getRegionWidth()/2, this.getY() + getCurrentFrame().getRegionHeight()/2);
		Vector2 dirVector = mousePosition.sub(playerPosition);
		// TODO: this probably doesn't need to be done after major refactor that is coming
		float newRotation =  dirVector.angle();
		checkCollision(collisionPolygon.getX(), collisionPolygon.getY(), newRotation);
	}

	private boolean checkCollision(float x, float y, float angle) {
		float oldX = collisionPolygon.getX();
		float oldY = collisionPolygon.getY();
		float oldAngle = collisionPolygon.getRotation();

		collisionPolygon.setPosition(x, y);
		collisionPolygon.setRotation(angle);
		if(!intersector.overlapConvexPolygons(collisionPolygon, rectangle)){
			return false;
		}

		collisionPolygon.setPosition(oldX, oldY);
		collisionPolygon.setRotation(oldAngle);
		return true;
	}


	@Override
	public void draw(Batch batch, float parentAlpha) {
		TextureRegion currentFrame = getCurrentFrame();
		float regionWidth = currentFrame.getRegionWidth();
		float regionHeight = currentFrame.getRegionHeight();

		batch.draw(currentFrame, this.getX(), this.getY(), regionWidth/2, regionHeight/2, regionWidth, regionHeight, 1, 1, collisionPolygon.getRotation());
	}

	// TODO: put this in a custom Sprite class or something
	public TextureRegion getCurrentFrame() {
		if(Gdx.graphics.getDeltaTime() < 0){
			return animation.getKeyFrame(1);
		}
		return animation.getKeyFrame(Gdx.graphics.getDeltaTime());
	}


	public void drawCollision(ShapeRenderer shapeRenderer) {
		// TODO: place this stuff in a config file at some point
		shapeRenderer.polygon(collisionPolygon.getTransformedVertices());
		shapeRenderer.polygon(rectangle.getTransformedVertices());
	}
}

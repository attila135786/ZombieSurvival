package com.epgaming.game.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epgaming.game.Assets;
import com.epgaming.game.PlayerController;
import com.epgaming.game.ZombieSurvivalGame;
import com.epgaming.game.actors.ZombieSurvivalGameActor;

public class FirstGameScreen extends ZombieSurvivalGameScreen {

	// TODO: remove this player, this is just for testing
	ZombieSurvivalGameActor player = new ZombieSurvivalGameActor(Assets.handgunSpriteTextureRegion);
	PlayerController playerController;
	public FirstGameScreen(ZombieSurvivalGame game) {
		super(game);
		playerController = new PlayerController(game.getCamera());
		player.setController(playerController);
	}

	// TODO: eventually change this to get players
	public ZombieSurvivalGameActor getPlayer(){
		return player;
	}

	// TODO: eventually change this to set players
	public void setActor(ZombieSurvivalGameActor actor){
		this.player = actor;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta){
		super.render(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	public void update(float delta){
		player.act(delta);
	}


	public void draw(SpriteBatch batch){
		if(batch == null){
			return;
		}
		player.draw(batch, 1);
	}

	@Override
	public void debugCollision(ShapeRenderer shapeRenderer) {
		player.drawCollision(shapeRenderer);
	}
}

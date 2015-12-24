package com.epgaming.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epgaming.game.ZombieSurvivalGame;

public abstract class ZombieSurvivalGameScreen implements Screen {

	protected ZombieSurvivalGame game;

	public ZombieSurvivalGameScreen(ZombieSurvivalGame game) {
		this.game = game;
	}

	abstract public void update(float delta);

	abstract public void draw(SpriteBatch batch);

	abstract public void debugCollision(ShapeRenderer shapeRenderer);

	@Override
	public void render(float delta) {
		update(delta);
		draw(game.getBatch());
	}

	@Override
	abstract public void show();

	@Override
	abstract public void resize(int width, int height);

	@Override
	abstract public void pause();

	@Override
	abstract public void resume();

	@Override
	abstract public void hide();

	@Override
	abstract public void dispose();
}

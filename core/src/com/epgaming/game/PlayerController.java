package com.epgaming.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

public class PlayerController {

	OrthographicCamera camera;
	private Map<String, Boolean> keys;
	private Vector2 mousePosition;
	public PlayerController(OrthographicCamera camera){
		this.camera = camera;
		keys = new HashMap<String, Boolean>();
		mousePosition = new Vector2();
		// register keys here
		keys.put("A", false);
		keys.put("D", false);
		keys.put("W", false);
		keys.put("S", false);
	}



	// TODO: combine these functions and return a data transfer object instead, that way we just call handleInput and we are done
	// TODO: also, loop through the keys and change them to enum so this check isn't several if statements
	public void handleInput(){
		// TODO: I don't really like this, revisit this late
		for (String value: keys.keySet()){
			keys.put(value, false);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			keys.put("A", true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			keys.put("D", true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			keys.put("W", true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			keys.put("S", true);
		}

		updateMousePosition();

	}

	private void updateMousePosition() {
		Vector3 newMousePosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

		newMousePosition = camera.unproject(newMousePosition);
		mousePosition.set(newMousePosition.x, newMousePosition.y);
	}

	public Map<String, Boolean> getInputMap(){
		return keys;
	}

	public Vector2 getMousePosition(){
		return mousePosition;
	}
}

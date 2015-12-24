package com.epgaming.game.testing.tests;

import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by BeastProgrammer on 12/9/2015.
 */
public class Vector2Tests {
	@Test
	public void firstTest(){
		Vector2 v = new Vector2();
		Assert.assertTrue(v != null);
	}


	public void directionVectorTest(){
		Vector2 playerPosition = new Vector2(0f, 0f);
		Vector2 mousePosition = new Vector2(3f, 3f);

		Vector2 directionVector = mousePosition.sub(playerPosition);

		Assert.assertEquals(2f, directionVector.x);
		Assert.assertEquals(2f, directionVector.y);
	}
}

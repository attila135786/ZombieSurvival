package com.epgaming.game;

import com.epgaming.game.screens.ZombieSurvivalGameScreen;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ScreenController {

	// TODO: finish this up, I think this is the right direction
	private	Map<Integer, ZombieSurvivalGameScreen> zombieSurvivalGameScreenMap;
	public ScreenController(Collection<ZombieSurvivalGameScreen> zombieSurvivalGameScreens) {
		Map<Integer, ZombieSurvivalGameScreen> zombieSurvivalGameScreenMap = new HashMap<Integer, ZombieSurvivalGameScreen>();


	}

	public ZombieSurvivalGameScreen getScreenById(int id){
		if(!zombieSurvivalGameScreenMap.containsKey(id)){
			return null;
		}
		return zombieSurvivalGameScreenMap.get(id);
	}
}

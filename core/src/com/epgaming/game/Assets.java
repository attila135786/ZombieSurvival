package com.epgaming.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static Texture testSpriteTexture;
	public static TextureRegion testSpriteRegion;

	public static Texture handgunSpriteTexture;
	public static TextureRegion handgunSpriteTextureRegion;

	public static void initializeAssets(){
		initializeTextures();
		initializeTextureRegions();
	}

	private static void initializeTextureRegions() {
		testSpriteRegion = new TextureRegion(testSpriteTexture, 0, 0, 256, 256);
		handgunSpriteTextureRegion = new TextureRegion(handgunSpriteTexture, 0,0,63,54);
//		handgunSpriteTextureRegion = new TextureRegion(handgunSpriteTexture, 9,8,60-9,45-6);
	}

	private static void initializeTextures() {
		testSpriteTexture = new Texture("badlogic.jpg");
		handgunSpriteTexture = new Texture("handgun.png");
	}

	public static void dispose(){
		testSpriteTexture.dispose();
		handgunSpriteTexture.dispose();
	}
}

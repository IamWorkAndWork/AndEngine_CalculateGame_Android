package com.example.calculategame;

import android.R.animator;

import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.gl.Animation;
import com.badlogic.androidgames.framework.gl.Font;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Assets {
	public static Texture items;
	public static Font font;
	
	public static Texture numberButtonTexture;
	public static TextureRegion numberButtonTextureRegion;
	public static Texture numberButtonTexture2;
	public static TextureRegion numberButtonTextureRegion2;
	public static Animation numberButtonAnimation;
	public static Animation numberButtonAnimation2;
	
	public static Texture operandButtonTexture1;
	public static TextureRegion operandButtonRegion1;
	public static Texture operandButtonTexture2;
	public static TextureRegion operandButtonRegion2;
	public static Animation numberOperandAnim1;
	public static Animation numberOperandAnim2;	
	
	public static Texture myFontTexture;
	public static Font myFont;
	
	public static Texture LaserPlayerTexture;
	public static Animation LaserPlayerTextureAnimation;
	public static Texture LaserPlayerTexture2;
	public static Animation LaserPlayerTextureAnimation2;
	
	public static Texture AttackTexture;
	public static TextureRegion AttackTexturereRegion;
	public static Texture AttackTexture2;
	public static TextureRegion AttackTexturereRegion2;
	
	public static Texture BackgroundTexture;
	public static TextureRegion BackgroundTextureRegion;
	public static Texture FoodTexture;
	public static TextureRegion FoodTextureRegion;
	public static Animation FoodAnimation;
	
	public static Texture MonsterBearTexture;
	public static Animation MonsterBearTextureRegion;
	public static Texture MonsterBirdTexture;
	public static Animation MonsterBirdAnim;
	public static Texture MonsterStoneTexture;
	public static Animation MonsterStoneAnim;

	public static Texture hpTexture;
	public static Animation hpRegion;
	
	public static Texture explodeTexture;
	public static Animation explodeTextureAnim;
	
	public static Texture windTexture;
	public static Animation windAnim;
	
	public static Texture windIconTexture;
	public static TextureRegion windIconTextureRegion;
	

	public static Music music;

	public static void load(GLGame game) {
	
		items = new Texture(game, "items.png");
//		font = new Font(items, 224/2, 0, 16, 16/2, 20/2);
		font = new Font(items, 224, 0, 16, 16, 20);
		
		numberButtonTexture = new Texture(game,"numafter.png");
		numberButtonTextureRegion = new TextureRegion(numberButtonTexture, 0, 0, 250, 34);
		numberButtonTexture2 = new Texture(game,"numbefore.png");
		numberButtonTextureRegion2 = new TextureRegion(numberButtonTexture, 0, 0, 250, 34);
		
		operandButtonTexture1 = new Texture(game,"operand.png");
		operandButtonRegion1 = new TextureRegion(operandButtonTexture1, 0, 0, 520, 189);
		operandButtonTexture2 = new Texture(game,"operand2.png");
		operandButtonRegion2 = new TextureRegion(operandButtonTexture2, 0, 0, 520, 189);
		
		windIconTexture = new  Texture(game, "windicon.png");
		windIconTextureRegion = new TextureRegion(windIconTexture, 0, 0, 190, 163);
		
		myFontTexture = new Texture(game,"number.png");
		myFont = new Font(myFontTexture,0,0,4,16,16);
		
		BackgroundTexture= new Texture(game,"background.png");
		BackgroundTextureRegion = new TextureRegion(BackgroundTexture, 0, 0, 320, 480);
	
		FoodTexture = new Texture(game,"kn_bandt3.png");
		FoodTextureRegion = new TextureRegion(FoodTexture, 64*0, 48*2, 64, 48);
		
		LaserPlayerTexture = new Texture(game,"laser.png");
		LaserPlayerTextureAnimation = new Animation(0.2f, new TextureRegion(LaserPlayerTexture, 32*0, 32, 32, 32),
				new TextureRegion(LaserPlayerTexture, 32*1, 32, 32, 32),new TextureRegion(LaserPlayerTexture, 32*2, 32, 32, 32),
				new TextureRegion(LaserPlayerTexture, 32*3, 32, 32, 32));
		LaserPlayerTexture2 = new Texture(game,"redlaser.png");
		LaserPlayerTextureAnimation2 = new Animation(0.2f, new TextureRegion(LaserPlayerTexture2, 32*0, 32, 32, 32),
				new TextureRegion(LaserPlayerTexture, 32*1, 32, 32, 32),new TextureRegion(LaserPlayerTexture, 32*2, 32, 32, 32),
				new TextureRegion(LaserPlayerTexture, 32*3, 32, 32, 32));
		
		AttackTexture = new Texture(game,"weapon00.png");
		AttackTexture2 = new Texture(game,"weapon02.png");
		AttackTexturereRegion = new TextureRegion(AttackTexture, 0, 0, 24, 24);
		AttackTexturereRegion2 = new TextureRegion(AttackTexture2, 0, 0, 24, 24);
	
		MonsterBearTexture = new Texture(game,"bear.png");
		MonsterBearTextureRegion = new Animation(0.2f,new TextureRegion(MonsterBearTexture, 56*0, 56*1, 56, 56),
				new TextureRegion(MonsterBearTexture, 56*1, 56*1, 56, 56),
				new TextureRegion(MonsterBearTexture, 56*2, 56*1, 56, 56),
				new TextureRegion(MonsterBearTexture, 56*3, 56*1, 56, 56));
		
		MonsterBirdTexture = new Texture(game,"AG_C.PNG");
		MonsterBirdAnim = new Animation(0.2f, new TextureRegion(MonsterBirdTexture, 64*0, 64, 64 , 64),
				new TextureRegion(MonsterBirdTexture, 64*1, 64, 64 , 64),new TextureRegion(MonsterBirdTexture, 64*2, 64, 64 , 64),
				new TextureRegion(MonsterBirdTexture, 64*3, 64, 64 , 64));
		
		MonsterStoneTexture = new Texture(game,"067-Goblin01.png");
		MonsterStoneAnim = new Animation(0.3f, new TextureRegion(MonsterStoneTexture, 32*0, 48, 32 , 48),
				new TextureRegion(MonsterStoneTexture, 32*1, 48, 32 , 48),new TextureRegion(MonsterStoneTexture, 32*2, 48, 32 , 48),
				new TextureRegion(MonsterStoneTexture, 32*3, 48, 32 , 48));
		
		numberButtonAnimation = new Animation(0.2f, new TextureRegion(numberButtonTexture, 25*0, 0, 25, 34),
				new TextureRegion(numberButtonTexture, 25*1, 0, 25, 34),new TextureRegion(numberButtonTexture, 25*2, 0, 25, 34),
				new TextureRegion(numberButtonTexture, 25*3, 0, 25, 34),new TextureRegion(numberButtonTexture, 25*4, 0, 25, 34),
				new TextureRegion(numberButtonTexture, 25*5, 0, 25, 34),new TextureRegion(numberButtonTexture, 25*6, 0, 25, 34),
				new TextureRegion(numberButtonTexture, 25*7, 0, 25, 34),new TextureRegion(numberButtonTexture, 25*8, 0, 25, 34),
				new TextureRegion(numberButtonTexture, 25*9, 0, 25, 34));
		numberButtonAnimation2 = new Animation(0.2f, new TextureRegion(numberButtonTexture2, 25*0, 0, 25, 34),
				new TextureRegion(numberButtonTexture2, 25*1, 0, 25, 34),new TextureRegion(numberButtonTexture2, 25*2, 0, 25, 34),
				new TextureRegion(numberButtonTexture2, 25*3, 0, 25, 34),new TextureRegion(numberButtonTexture2, 25*4, 0, 25, 34),
				new TextureRegion(numberButtonTexture2, 25*5, 0, 25, 34),new TextureRegion(numberButtonTexture2, 25*6, 0, 25, 34),
				new TextureRegion(numberButtonTexture2, 25*7, 0, 25, 34),new TextureRegion(numberButtonTexture2, 25*8, 0, 25, 34),
				new TextureRegion(numberButtonTexture2, 25*9, 0, 25, 34));
		
		numberOperandAnim1 = new Animation(0.2f, new TextureRegion(operandButtonTexture1, 0, 0, 520/4, 189),
				new TextureRegion(operandButtonTexture1, 520/4*1, 0, 520/4, 189),new TextureRegion(operandButtonTexture1, 520/4*2, 0, 520/4, 189)
				,new TextureRegion(operandButtonTexture1, 520/4*3, 0, 520/4, 189));
		numberOperandAnim2 = new Animation(0.2f, new TextureRegion(operandButtonTexture2, 0, 0, 520/4, 189),
				new TextureRegion(operandButtonTexture2, 520/4*1, 0, 520/4, 189),new TextureRegion(operandButtonTexture2, 520/4*2, 0, 520/4, 189)
				,new TextureRegion(operandButtonTexture2, 520/4*3, 0, 520/4, 189));
		
		FoodAnimation = new Animation(0.2f, new TextureRegion(FoodTexture, 64*0, 48*2, 64,48),
				new TextureRegion(FoodTexture, 64*1, 48*2, 64,48),new TextureRegion(FoodTexture, 64*2, 48*2, 64,48),
				new TextureRegion(FoodTexture, 64*3, 48*2, 64,48));
		
		explodeTexture = new Texture(game,"explode2.png");
//		explodeTextureAnim = new Animation(0.2f, new TextureRegion(explodeTexture, 260*0, 0,260, 240),
//				new TextureRegion(explodeTexture, 260*1, 0,260, 240),new TextureRegion(explodeTexture, 260*2, 0,260, 240),
//				new TextureRegion(explodeTexture, 260*3, 0,260, 240),new TextureRegion(explodeTexture, 260*4, 0,260, 240),
//				new TextureRegion(explodeTexture, 260*5, 0,260, 240),new TextureRegion(explodeTexture, 260*6, 0,260, 240),
//				new TextureRegion(explodeTexture, 260*7, 0,260, 240),new TextureRegion(explodeTexture, 260*8, 0,260, 240));
		explodeTextureAnim = new Animation(0.1f, new TextureRegion(explodeTexture, 192*0, 0,192, 192),
				new TextureRegion(explodeTexture, 192*1, 0,192, 192),new TextureRegion(explodeTexture, 192*2, 0,192, 192),
				new TextureRegion(explodeTexture, 192*3, 0,192, 192),new TextureRegion(explodeTexture, 192*4, 0,192, 192));
		
		
		windTexture = new Texture(game, "Wind2.png");
		windAnim = new Animation(0.1f, new TextureRegion(windTexture, 192*0, 0, 192, 192),new TextureRegion(windTexture, 192*1, 0, 192, 192),
				new TextureRegion(windTexture, 192*2, 0, 192, 192),new TextureRegion(windTexture, 192*3, 0, 192, 192),new TextureRegion(windTexture, 192*4, 0, 192, 192));
		
		hpTexture = new Texture(game,"hp.png");
		hpRegion = new Animation(0.1f, new TextureRegion(hpTexture, 0, 0, 192, 32),new TextureRegion(hpTexture, 0, 32, 192, 32));
	}
	

	
	
	public static void reload() {
		FoodTexture.reload();
	}

}

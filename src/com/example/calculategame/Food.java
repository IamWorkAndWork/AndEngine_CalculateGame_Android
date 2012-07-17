package com.example.calculategame;

import com.badlogic.androidgames.framework.DynamicGameObject;
import com.badlogic.androidgames.framework.GameObject;

public class Food extends DynamicGameObject {

	public static float FOOD_WIDTH=1f;
	public static float FOOD_HEIGHT=1f;
	
	public static int HOLD=0;
	public static int ATTACK=1;
	
	int state;
	float stateTime;
	
	public Food(float x, float y) {
		super(x, y, FOOD_WIDTH, FOOD_HEIGHT);
		state=HOLD;
	}
	
	public void update(float deltaTime){
		bounds.lowerLeft.set(position);
//		stateTime+=deltaTime;
	}
	
	

}

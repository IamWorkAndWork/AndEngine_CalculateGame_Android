package com.example.calculategame;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class ExplodeEffect extends DynamicGameObject {

	public static final float WIDTH=1.5f;
	public static final float HEIGHT=1.5f;
	
	public static int DEFAULT_EFFECT=0;
	public static int WIND_EFFECT=1;
	
	float stateTime;
	int state;
	
	public ExplodeEffect(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		stateTime=0;
		state = DEFAULT_EFFECT;
	}
	
	public void update(float deltaTime){
		stateTime+=deltaTime;
	}

}

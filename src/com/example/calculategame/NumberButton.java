package com.example.calculategame;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class NumberButton extends DynamicGameObject {

	public static final float WIDTH=20.0f;
	public static final float HEIGHT=26.0f;
	
	public static final int NOCLICK=0;
	public static final int CLICK=1;
	
	float stateTime=0;
	int state;
	
	public NumberButton(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		stateTime =0;
		state=NOCLICK;
	}

	public void update(float deltaTime){
		bounds.lowerLeft.set(position);
		
		if (position.x<0 && World.GAME_PLAY==1) {
			position.x=WorldRenderer.FRUSTUM_WIDTH+2;
		}
//		stateTime+=deltaTime;
	}
}

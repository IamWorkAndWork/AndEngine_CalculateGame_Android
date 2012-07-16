package com.example.calculategame;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class WindStromEffect extends DynamicGameObject {

	public static final float WIDTH=4f;
	public static final float HEIGHT=15f;
	
	public static int DEFAULT_EFFECT=0;
	public static int WIND_EFFECT=1;
	public static int REMOVE=5;
	
	float stateTime;
	int state;
	
	public WindStromEffect(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		stateTime=0;
		state = DEFAULT_EFFECT;
		velocity.x=3f;
	}
	
	public void update(float deltaTime){
		stateTime+=deltaTime;
		position.add(velocity.x*deltaTime,velocity.y*deltaTime);
		bounds.lowerLeft.set(position.x-3,position.y-4);
		if (position.x>World.WORLD_WIDTH-1) {
			velocity.x=-3f;
//			state=REMOVE;
		}
		if (position.x<0) {
			state=REMOVE;
		}
	}

}

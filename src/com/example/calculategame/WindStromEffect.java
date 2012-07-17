package com.example.calculategame;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class WindStromEffect extends DynamicGameObject {

	public static final float WIDTH=4f;
	public static final float HEIGHT=15f;
	public static final float explode3WIDTH=1f;
	public static final float explode3HEIGHT=1f;
	
	
	public static int DEFAULT_EFFECT=0;
	public static int WIND_EFFECT=1;
	public static int FIRE_EFFECT=2;
	public static int REMOVE=5;
	
	float stateTime;
	int state;
	
	public WindStromEffect(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		stateTime=0;
		state = WIND_EFFECT;
		velocity.x=3f;
	}
	
	public void update(float deltaTime){
		stateTime+=deltaTime;
		if (state==WIND_EFFECT) {
			position.add(velocity.x*deltaTime,velocity.y*deltaTime);
			bounds.lowerLeft.set(position.x-3,position.y-4);
			if (position.x>World.WORLD_WIDTH-1) {
				velocity.x=-3f;
//				state=REMOVE;
			}
			if (position.x<0) {
				state=REMOVE;
			}
		}
		if (state==FIRE_EFFECT) {
			bounds.width=1f;
			bounds.height=1f;
			bounds.lowerLeft.set(position.x-0.1f,position.y);
		}
	}

}

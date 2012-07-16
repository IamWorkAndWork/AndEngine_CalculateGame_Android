package com.example.calculategame;

import android.util.Log;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class LaserPlayer extends DynamicGameObject {

	public static final float WIDTH=0.6f;
	public static final float HEIGHT=0.9f;
	
	public static float destX,destY;
	
	float stateTime;
	int state;
	int color;
	
	public LaserPlayer(float x, float y, float width, float height) {
		super(x, y, width, height);
		velocity.x=8f;
		color =  (int)(Math.random()*2)+1;
	}
	
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime,velocity.y);
		bounds.lowerLeft.set(position.x-0.4f,position.y);
		stateTime+=deltaTime;
	}
	
	public void AttackDest(float deltaTime,float destX,float destY){
//		this.destX=destX;
//		this.destY=destY;
//		Log.d("location", position.x+" : "+position.y+" = "+destX+" : "+destY);
		if (position.y!=destY) {
			if (position.y>=destY) {
				velocity.y=(5.0f*deltaTime)*-1;
	//			position.x+=3;
			}
			if (position.y<=destY){
				velocity.y=(5.0f*deltaTime);
	//			position.x-=3;			
			}
		}
		else if (position.y==destY) {
			velocity.y=0;
		}
			
		
			if (position.x>=destX) {
				velocity.x=-5f;
			}
			if (position.x<=destX) {
//				velocity.x=(5.0f*deltaTime)*-1;
			}
		
	}

}

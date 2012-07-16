package com.example.calculategame;

import android.util.Log;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class Monster_01 extends DynamicGameObject {

	public static float WIDTH=0.8f;
	public static float HEIGHT=1.3f;
	public static float FWIDTH=0.2f;
	public static float FHEIGHT=0.3f;
	public static final float MOVE_VELOCITY=20;
	public static final int STATE_DEATH=0;
	public static final int STATE_ALIVE=1;
	public static final int STATE_HIT=2;
	public static final int STATE_BOSS=3;
	public static final int STATE_DEATHALL=4;
	int randomMonster;
	
	int state;
	float stateTime;
	public String setText;
	public String setAnswer;
	

	
	public Monster_01(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		state = STATE_ALIVE;
		stateTime=0;
		setText="";
		setAnswer="";
		randomMonster = (int)(Math.random()*3)+1;
		if (randomMonster==1) {
			velocity.add(-0.4f,0);
		}
		if (randomMonster==2) {
			velocity.add(-0.5f,0);
		}
		if (randomMonster==3) {
			velocity.add(-0.3f,0);
		}
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime,velocity.y*deltaTime);
		bounds.lowerLeft.set(position.x,position.y);
//		bounds.lowerLeft.set(position).sub(bounds.width, bounds.height);
		stateTime+=deltaTime;
		
		if (position.x<0 && World.GAME_PLAY==1) {
			position.x=WorldRenderer.FRUSTUM_WIDTH+2;
		}
		
		if (World.GAME_PLAY==2 && position.x<0) {
			state=STATE_DEATH;
		}
		
		if (World.GAME_PLAY==2 &&  !setAnswer.equals("-10") ) {
			state=STATE_BOSS;
		}
		
		if ( World.GAME_PLAY==2 && state==STATE_BOSS) {
			if (position.x<0) {
				state=STATE_DEATHALL;
			}
		}
	}

}

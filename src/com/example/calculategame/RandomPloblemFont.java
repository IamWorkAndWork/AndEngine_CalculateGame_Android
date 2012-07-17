package com.example.calculategame;

import android.text.NoCopySpan.Concrete;

import com.badlogic.androidgames.framework.GameObject;

public class RandomPloblemFont extends GameObject {

	public static float WIDTH=0.2f;
	public static float HEIGHT=0.3f;
	public static int REMOVE=2;
	public String setText;
	public String setAnswer;
	public int LEVEL=0;
	int state;
	
	public RandomPloblemFont(float x, float y, String setText,String setAnswer) {
		super(x, y, WIDTH, HEIGHT);
//		LEVEL=level;
//		String operand="";
//		int num1=0,num2;
//		int ranCase = (int)(Math.random()*4)+1;
////		while (true) {
////			num1 = (int)(Math.random()*(10+(LEVEL*3)))+1;
////			num2 = (int)(Math.random()*(10+(LEVEL*3)))+1;
////			if (num1%num2==0 && num1>=num2 && num2>0) {
////				break;
////			}
////			else  {
////				continue;
////			}
////		}
//		
//		switch (ranCase) {
//		case 1:
//			operand="+";
//			setAnswer=""+(num1+num2);
//			break;
//		case 2:
//			operand="-";			
//			setAnswer=""+(num1-num2);
//			break;
//		case 3:
//			operand="x";
//			setAnswer=""+(num1*num2);
//			break;
//		case 4:
//			operand="/";
//			setAnswer=""+(num1/num2);
//			break;
//		default:
//			operand="+";
//			break;
//		}
//		setText= num1+""+operand+""+num2;
		this.setText = setText;
		this.setAnswer = setAnswer;
	}
	
	
	public void update(float deltaTime){
		bounds.lowerLeft.set(position);
		
		if (position.x<0 && World.GAME_PLAY==1) {
			position.x=WorldRenderer.FRUSTUM_WIDTH+2;
		}
		
		if (World.GAME_PLAY==2 && position.x<0) {
			state=REMOVE;
		}
//		stateTime+=deltaTime;
	}
	
}



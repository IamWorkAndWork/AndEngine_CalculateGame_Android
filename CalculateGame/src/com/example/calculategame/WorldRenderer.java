package com.example.calculategame;

import javax.microedition.khronos.opengles.GL10;

import android.animation.Keyframe;
import android.util.Log;

import com.badlogic.androidgames.framework.gl.Animation;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class WorldRenderer {

	
	static final float FRUSTUM_WIDTH=10;
	static final float FRUSTUM_HEIGHT=15;
	GLGraphics glGraphics;
	World world;
	Camera2D cam;
	SpriteBatcher batcher;
	
	
	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
		this.glGraphics = glGraphics;
		this.world = world;
		this.cam = new Camera2D(glGraphics,FRUSTUM_WIDTH,FRUSTUM_HEIGHT);
		this.batcher = batcher;
	}

	public void render() {
		cam.setViewportAndMatrices();
		renderBackground();
		renderOjects();
	}

	private void renderOjects() {
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
//		renderNumberButton();
//		renderFood();
//		cam.zoom=2;
		renderMonster_01();
		renderNumber();
		renderLaserPlayer();
		renderExplodeEffect();
		renderWindStromEffect();
		
		gl.glDisable(GL10.GL_BLEND);
	}



	private void renderWindStromEffect() {
		int len = world.windStromEffect.size();
		if (len>0) {
			batcher.beginBatch(Assets.windTexture);
			for (int i = 0; i < len; i++) {
				WindStromEffect obj = world.windStromEffect.get(i);
				TextureRegion keyFrame = Assets.windAnim.getKeyFrame(obj.stateTime, Animation.ANIMATION_LOOPING);
				batcher.drawSprite(obj.position.x, obj.position.y, obj.bounds.width, obj.bounds.height, keyFrame);
			}
			batcher.endBatch();
		}
	}

	private void renderExplodeEffect() {
		int len = world.explodeEffect.size();
		if (len>0) {
			batcher.beginBatch(Assets.explodeTexture);
			for (int i = 0; i < len; i++) {
				ExplodeEffect explode = world.explodeEffect.get(i);
				TextureRegion keyFrame = Assets.explodeTextureAnim.getKeyFrame(explode.stateTime, Animation.ANIMATION_NONLOOPING);
				batcher.drawSprite(explode.position.x, explode.position.y, explode.WIDTH, explode.HEIGHT, keyFrame);
				if (explode.stateTime>0.6f) {
					world.explodeEffect.remove(explode);

				}
				len = world.explodeEffect.size();
			}
			batcher.endBatch();
		}
	}

	private void renderLaserPlayer() {
		int len = world.laserPlayer.size();
		if (len>0) {
			for (int i = 0; i < len; i++) {
				LaserPlayer laser = world.laserPlayer.get(i);
				if (laser.color==1) {
					batcher.beginBatch(Assets.LaserPlayerTexture);
					TextureRegion Keyframe = Assets.LaserPlayerTextureAnimation.getKeyFrame(laser.stateTime, Animation.ANIMATION_LOOPING);
					batcher.drawSprite(laser.position.x, laser.position.y, laser.WIDTH, laser.HEIGHT, Keyframe);
					batcher.endBatch();
				}
				else{
					batcher.beginBatch(Assets.LaserPlayerTexture2);
					TextureRegion Keyframe = Assets.LaserPlayerTextureAnimation2.getKeyFrame(laser.stateTime, Animation.ANIMATION_LOOPING);
					batcher.drawSprite(laser.position.x, laser.position.y, laser.WIDTH, laser.HEIGHT, Keyframe);
					batcher.endBatch();					
				}
//				Log.d("size", "w = "+laser.WIDTH+" : "+laser.HEIGHT);
			}
		}
	}

	private void renderNumberButton() {
		int len = GameScreen.NumberButtonList.size();
		batcher.beginBatch(Assets.numberButtonTexture);
		float x=0.0f;
		for (int i = 0; i < len; i++) {
			NumberButton number = GameScreen.NumberButtonList.get(i);
			TextureRegion keyFrame = Assets.numberButtonAnimation.getKeyFrame(x, Animation.ANIMATION_LOOPING);
			batcher.drawSprite(number.position.x, number.position.y, number.WIDTH, number.HEIGHT, keyFrame);
			x+=0.2f;
		}
		batcher.endBatch();
	}

	float up;//
	private void renderNumber() {
		if (World.GAME_PLAY==1) {
//			batcher.beginBatch(Assets.items);
//			String showProblem="1+3";
//			Assets.font.drawText(batcher, showProblem, world.monster_01.get(2).position.x-showProblem.length()*0.08f, world.monster_01.get(2).position.y+0.65f,0.2f,0.3f);
//			batcher.endBatch();
			int len = world.monster_01.size();
			if (len>0) {
				batcher.beginBatch(Assets.items);
				for (int i = 0; i < len; i++) {
					up=0.65f;
					//RandomPloblemFont ran = world.randomProblem.get(i);
					if (world.monster_01.get(i).randomMonster==2) {
						up=0.87f;
					}
					Assets.font.drawText(batcher, world.randomProblem.get(i).setText, world.monster_01.get(i).position.x-world.randomProblem.get(i).setText.length()*0.06f, world.monster_01.get(i).position.y+up,world.randomProblem.get(i).WIDTH,world.randomProblem.get(i).HEIGHT);
					len = world.monster_01.size();
				}
				batcher.endBatch();
			}
		}
		if (world.GAME_PLAY==2) {
			int len = world.monster_01.size();
			if (len>0) {
				for (int i = 0; i < len; i++) {
					up=0.65f;
					Monster_01 monster = world.monster_01.get(i);
					if (monster.randomMonster==2) {
						up=0.87f;
					}
					batcher.beginBatch(Assets.items);
					Assets.font.drawText(batcher, monster.setText, monster.position.x-world.randomProblem.get(i).setText.length()*0.06f, monster.position.y+up,monster.FWIDTH,monster.FHEIGHT);
					batcher.endBatch();
					len = world.monster_01.size();
				}
			}
		}
	}

	private void renderMonster_01() {
		int len = world.monster_01.size();
		if (len>0) {
			for (int i = 0; i < len; i++) {	
				Monster_01 monster = world.monster_01.get(i);
				if (monster.randomMonster==1 ) {
					batcher.beginBatch(Assets.MonsterBearTexture);
					TextureRegion keyFrame = Assets.MonsterBearTextureRegion.getKeyFrame(monster.stateTime, Animation.ANIMATION_LOOPING);
					batcher.drawSprite(monster.position.x, monster.position.y, monster.WIDTH, monster.HEIGHT, keyFrame);
					batcher.endBatch();
				}
				if (monster.randomMonster==2) {
					batcher.beginBatch(Assets.MonsterBirdTexture);
					TextureRegion keyFrame = Assets.MonsterBirdAnim.getKeyFrame(monster.stateTime, Animation.ANIMATION_LOOPING);
					batcher.drawSprite(monster.position.x, monster.position.y, monster.WIDTH, monster.HEIGHT, keyFrame);
					batcher.endBatch();					
				}
				if (monster.randomMonster==3) {
					batcher.beginBatch(Assets.MonsterStoneTexture);
					TextureRegion keyFrame = Assets.MonsterStoneAnim.getKeyFrame(monster.stateTime, Animation.ANIMATION_LOOPING);
					batcher.drawSprite(monster.position.x, monster.position.y, monster.WIDTH, monster.HEIGHT, keyFrame);
					batcher.endBatch();					
				}
			}
		}
	}

	private void renderFood() {
		Food food = world.food;
		batcher.beginBatch(Assets.FoodTexture);
		TextureRegion keyFrame = Assets.FoodAnimation.getKeyFrame(food.stateTime, Animation.ANIMATION_LOOPING);
		batcher.drawSprite(food.position.x, food.position.y, 1f, 1.3f, keyFrame);
		batcher.endBatch();		
		if (food.stateTime>1) {
			food.state=food.HOLD;
		}
	}
	

	private void renderBackground() {
		batcher.beginBatch(Assets.BackgroundTexture);
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTUM_WIDTH, FRUSTUM_HEIGHT, Assets.BackgroundTextureRegion);
		batcher.endBatch();
	}

}

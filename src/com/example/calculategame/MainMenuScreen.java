package com.example.calculategame;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Camera;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;


public class MainMenuScreen extends GLScreen {

	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle helpBounds;
	Rectangle playBounds;
	Rectangle scoreBounds;
	Rectangle soundBounds;
	Rectangle game1bounds;
	Rectangle game2bounds;
	Vector2 touchPoint;
	
	
	AttackButton testSoundBound = new AttackButton(160, 240);
	AttackButton myMove = new AttackButton(160 , 200 + 18);
	String text="Click in: ";
	
	boolean isClick=false;
	
	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics, 320, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		scoreBounds = new Rectangle(105, 144, 108, 34);
		playBounds = new Rectangle(115, 195, 90, 42);
		helpBounds = new Rectangle(110,90,100,37);
		touchPoint = new Vector2();
		soundBounds = new Rectangle(0, 0, 64, 64);
		game1bounds = new Rectangle(135, 245, 42, 40);
		game2bounds = new Rectangle(135, 180, 42, 40);
		
		myMove.bounds.width=300;
		myMove.bounds.height=36;
//		Settings.load(game.getFileIO());
	}
	
	boolean isSelect=false;
	boolean isSelectGame=false;
	@Override
	public void update(float deltaTime) {
		try {

			List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
			game.getInput().getKeyEvents();
			
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				touchPoint.set(event.x,event.y);
				guiCam.touchToWorld(touchPoint);
				
				if (event.type == TouchEvent.TOUCH_UP) {
					isClick=false;
				}
				if (event.type == TouchEvent.TOUCH_DOWN) {
					if (isSelect) {
						isSelectGame=true;
					}
					if (OverlapTester.pointInRectangle(playBounds,touchPoint)) {
//						isClick=true;
						isSelect=true;
						Assets.playSound(Assets.clickSound);
						
					}
					if (OverlapTester.pointInRectangle(scoreBounds,touchPoint)) {
						isClick=true;
						Assets.playSound(Assets.clickSound);
						game.setScreen(new HighscoreScreen(game));
					}
					if (OverlapTester.pointInRectangle(helpBounds,touchPoint)) {
						game.setScreen(new HelpScreen(game));
						Assets.playSound(Assets.clickSound);
						isClick=true;
					}
	                if(OverlapTester.pointInRectangle(soundBounds, touchPoint)) {
	                	isClick=true;
	                	Assets.playSound(Assets.clickSound);
	                	Settings.soundEnabled = !Settings.soundEnabled;
	                    if(Settings.soundEnabled) 
	                        Assets.music1.play();
	                    else
	                        Assets.music1.pause();
	                }
	                
	                if (OverlapTester.pointInRectangle(game1bounds, touchPoint) && isSelectGame ) {
						Assets.playSound(Assets.clickSound);
						game.setScreen(new GameScreen(game,1));
					}
	                if (OverlapTester.pointInRectangle(game2bounds, touchPoint) && isSelectGame) {
						Assets.playSound(Assets.clickSound);					
						game.setScreen(new GameScreen(game,2));
					}
				}
			}
		} catch (Exception e) {
			Log.d("Error", "error = "+e);
		}
	}

	@Override
	public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        
        gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Assets.BackgroundTexture);
		batcher.drawSprite(160,240, guiCam.frustumWidth, guiCam.frustumHeight, Assets.BackgroundTextureRegion);
		batcher.endBatch();
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);               

        batcher.beginBatch(			Assets.items);
        batcher.drawSprite(32f, 32, 64, 64, Settings.soundEnabled?Assets.soundOn:Assets.soundOff);
//        Assets.font.drawText(batcher, "dest : "+touchPoint.x+" : "+touchPoint.y, 160-20, 450,7,8);
        batcher.endBatch();
        
        if (isSelect) {
			batcher.beginBatch(Assets.items);
			Assets.font.drawText(batcher, "Game1", 160-20, 260,8,15);
			Assets.font.drawText(batcher, "Game2", 160-20, 200,8,15);
			batcher.endBatch();
		}
        else{
        	if (isClick) {
        		batcher.beginBatch(Assets.test1);
        		batcher.drawSprite(160, 240, 140, 360, Assets.test1Region);
        		batcher.endBatch();
        	}
        	else{
        		batcher.beginBatch(Assets.test1);
        		batcher.drawSprite(160, 240, 110, 350 ,Assets.test1Region);
        		batcher.endBatch();
        	}
        }

        
        gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}

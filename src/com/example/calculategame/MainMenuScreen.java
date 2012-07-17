package com.example.calculategame;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Camera;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;

public class MainMenuScreen extends GLScreen {

	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle Settings;
	Vector2 touchPoint;
	
	
	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(glGraphics, 320, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(0,0,64,64);
		Settings = new Rectangle(0,0,64,64);
		touchPoint = new Vector2();
		
//		Settings.load(game.getFileIO());
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x,event.y);
			guiCam.touchToWorld(touchPoint);
			
			if (event.type == TouchEvent.TOUCH_UP) {
				
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
//		batcher.beginBatch(Assets.items);
		
		
		
//		batcher.endBatch();
		
		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

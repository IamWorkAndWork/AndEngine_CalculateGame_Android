package com.example.calculategame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import android.util.Log;

import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Vector2;

public class World {

	public static final float WORLD_WIDTH=10;
	public static final float WORLD_HEIGHT=15*20;
	public static final int WORLD_STATE_RUNNING=0;
	public static final int WORLD_STATE_NEXT_LEVEL=1;
	public static final int WORLD_STATE_GAME_OVER=2;
	public static final Vector2 gravity = new Vector2(0,-12);
	
	public final ArrayList<Monster_01> monster_01;
	public final ArrayList<LaserPlayer> laserPlayer;
	public final ArrayList<RandomPloblemFont> randomProblem;
	public final ArrayList<ExplodeEffect> explodeEffect;
	public final ArrayList<WindStromEffect> windStromEffect;
//	public final ArrayList<NumberButton> NumberButtonList;
	public final Food food;

	public int score;
	public int state;
	public int LEVEL=0;
	public static int GAME_PLAY = 1 ;
	RandomNoDuplicate ranNotDup;
	
	RandomGame2 game2;
	public static HashMap<String, String> keepQuestionGame2;
	boolean isGame2FirstTime=true;
	
	public World() {
		this.monster_01 = new ArrayList<Monster_01>();
		this.food = new Food(0.6f, 7.5f);
		laserPlayer = new ArrayList<LaserPlayer>();
		randomProblem = new ArrayList<RandomPloblemFont>();
		explodeEffect = new ArrayList<ExplodeEffect>();
		windStromEffect = new ArrayList<WindStromEffect>();
		ranNotDup = new RandomNoDuplicate();
		game2 = new RandomGame2();
//		keepQuestionGame2 = new HashMap<String, String>();
//		this.NumberButtonList = new ArrayList<NumberButton>();
		
		generateObjects();
		this.score=0;
		this.state = WORLD_STATE_RUNNING;
	}

	int count=0;
	int numMonster=2;
//	TreeSet<String> checkDup = new TreeSet();
	HashMap<String, String> chkDup = new HashMap<String, String>();
	private void generateObjects() {
		LEVEL++;
		if (count%2==0) {
			numMonster++;
			System.out.println(GAME_PLAY+" = gameplay");
		}
		if (GAME_PLAY==2 && isGame2FirstTime) {
			numMonster=4;
			isGame2FirstTime=false;
		}
//		int monsterY=2;
		int x;
		for (int i = 0; i < numMonster; i++) {
			x = (int)(Math.random()*(2+LEVEL))+10;
			Monster_01 monster = new Monster_01(x,(float)Math.random()*(WORLD_HEIGHT/24)+1f );
			monster_01.add(monster);
//			monsterY+=2;
		}
		System.out.println();
		if (GAME_PLAY==1) {
			generateProblem(numMonster);
		}
		if (GAME_PLAY==2) {
			generateProblem2(numMonster);	
		}
		count++;
	}
	

	private void generateProblem2(int numMonster) {
			game2.generate(numMonster);
			int i=0,j=0;
//			System.out.println("game2 = "+game2.Question.size()+"");
			int len = monster_01.size();

				for (i=j; i <len ; i++) {
					Monster_01 monster = monster_01.get(i);
					RandomPloblemFont ranMonsert = new RandomPloblemFont(monster.position.x, monster.position.y+0.65f,game2.Question.get(i),game2.Answer.get(i));
					randomProblem.add(ranMonsert);
					monster.setAnswer=game2.Answer.get(i);
					monster.setText=game2.Question.get(i);
					len =  monster_01.size();
					System.out.println(monster.state+" = "+i);
				}
				game2.Question.clear();
				game2.Answer.clear();
				
	}


	private void generateProblem(int numMonster) {
		//Generate Problem
		String textProblem="",ansProblem;
		for (int i = 0; ; i++) {
			if (ranNotDup.StringKey.size()==numMonster) {
				break;
			}
			textProblem = ranNotDup.getNumber(1);
			if (ranNotDup.StringKey.size()!=numMonster) {
				continue;
			}
		}
//		System.out.println(ranNotDup.StringKey.size()+"\n"+ranNotDup.StringKey.toString());
		String data="";
		int i=0;
		while ((data = ranNotDup.StringKey.pollFirst())!=null) {
			String sp[] = data.split("=");
			textProblem=sp[0];
			ansProblem=sp[1];
			RandomPloblemFont ran = new RandomPloblemFont(monster_01.get(i).position.x, monster_01.get(i).position.y+0.65f,textProblem,ansProblem);
			randomProblem.add(ran);
			i++;
//			System.out.println(i+" : "+textProblem+" = "+ansProblem);
		}
		//end generareProblem		
	}


	public void update(float deltaTime, float accelX) {
		if (monster_01.size()==0) {
			generateObjects();
		}
		updateMonster_01(deltaTime);
		updateRandomNumberMonster(deltaTime);
		updateFood(deltaTime);
		updateLaser(deltaTime);
		updateExplodeEffect(deltaTime);
		updateWindStromEffect(deltaTime);
		
		checkCollide();
		
	}



	private void updateRandomNumberMonster(float deltaTime) {
		int len = randomProblem.size();
		if (len>0) {
			for (int i = 0; i < len; i++) {
				RandomPloblemFont ran = randomProblem.get(i);
				ran.update(deltaTime);
				if (ran.state==ran.REMOVE) {
					randomProblem.remove(ran);
				}
				len = randomProblem.size();	
			}
		}
	}


	private void checkCollide() {
		collideLaserVSMonster();
		collideWindVSMonster();
	}
	
	
	private void updateExplodeEffect(float deltaTime) {
		int len =explodeEffect.size();
		if (len>0) {
			for (int i = 0; i < len; i++) {
				ExplodeEffect explode = explodeEffect.get(i);
				explode.update(deltaTime);
			}
		}
	}
	
	
	private void updateWindStromEffect(float deltaTime) {
		int len = windStromEffect.size();
		if (len>0) {
			for (int i = 0; i < len; i++) {
				WindStromEffect obj = windStromEffect.get(i);
				obj.update(deltaTime);
				if (obj.state==obj.REMOVE) {
					windStromEffect.remove(obj);
				}
				len = windStromEffect.size();
			}
		}
	}
	
	
	String DestinationString="";
	public void CreateLaserPlayer(String keepMyAnser){
		
		int len = monster_01.size();
		for (int i = 0; i < len; i++) {
			if (GAME_PLAY==1) {
				RandomPloblemFont ran = randomProblem.get(i);
				if (keepMyAnser.equals(ran.setAnswer)) {
					DestinationString = randomProblem.get(i).setText+"="+randomProblem.get(i).setAnswer;
					LaserPlayer laser = new LaserPlayer(-2f, monster_01.get(i).position.y, 1f, 1f);
					laserPlayer.add(laser);
					break;
				}
			}
			if (GAME_PLAY==2) {
				Monster_01 monster = monster_01.get(i);
				if (keepMyAnser.equals(monster.setAnswer)) {
					DestinationString = monster.setText+"="+monster.setAnswer;
					LaserPlayer laser = new LaserPlayer(-2f, monster.position.y, 1f, 1f);
					laserPlayer.add(laser);
					len = monster_01.size();
//					break;
				}
			}
		}
	}

	private void collideLaserVSMonster() {
		int len = monster_01.size();
		int len2 = laserPlayer.size();
		for (int i = 0; i < len; i++) {
			Monster_01 monster = monster_01.get(i);
			RandomPloblemFont ran = randomProblem.get(i);
			for (int j = 0; j < len2; j++) {
				LaserPlayer laser = laserPlayer.get(j);
				if (OverlapTester.overlapRectangles(laser.bounds, monster.bounds)) {
					createExplode(monster.position.x,monster.position.y);

					laserPlayer.remove(laser);
					monster_01.remove(monster);
					randomProblem.remove(ran);
					
					if (GAME_PLAY==2) {
						for (int k = 0; k < monster_01.size(); k++) {
								createExplode(monster_01.get(k).position.x,monster_01.get(k).position.y);						
						}
						monster_01.removeAll(monster_01);
						randomProblem.removeAll(randomProblem);
					}
					len = monster_01.size();
					len2 = laserPlayer.size();
				}
			}
		}
	}
	
	
	private void collideWindVSMonster() {
		int len = windStromEffect.size();
		int len2 = monster_01.size();
		for (int i = 0; i < len; i++) {
			WindStromEffect obj = windStromEffect.get(i);
			for (int j = 0; j < len2; j++) {
				Monster_01 monster = monster_01.get(j);
				RandomPloblemFont ran = randomProblem.get(j);
				if (OverlapTester.overlapRectangles(obj.bounds, monster.bounds)) {
					createExplode(monster.position.x,monster.position.y);
					monster_01.remove(monster);
					randomProblem.remove(ran);
					if (monster.state == monster.STATE_BOSS) {
						monster_01.removeAll(monster_01);
						randomProblem.removeAll(randomProblem);
					}
					len = windStromEffect.size();
					len2 = monster_01.size();
				}
			}
		}
	}

	private void createExplode(float x, float y) {
		ExplodeEffect explode = new ExplodeEffect(x, y);
		explodeEffect.add(explode);
	}
	
	public void createWindStromEffect(float x, float y) {
		WindStromEffect windstrom = new WindStromEffect(x/32f, y/32f);
		windStromEffect.add(windstrom);
	}


	private void updateLaser(float deltaTime) {
		int len = laserPlayer.size();
		if (len>0) {
			for (int i = 0; i < len; i++) {
				LaserPlayer laser = laserPlayer.get(i);
//				laser.AttackDest(deltaTime, monster_01.get(target).position.x, monster_01.get(target).position.y);
				laser.update(deltaTime);
//				if (laser.position.x>WORLD_WIDTH) {
//					laserPlayer.remove(laser);
//				}
				len = laserPlayer.size();
			}
		}
	}

	private void updateFood(float deltaTime) {
		food.update(deltaTime);
		if (food.state ==Food.HOLD) {
			food.stateTime=0;
		}
		if (food.state==food.ATTACK) {
			food.stateTime+=deltaTime;
		}
		
	}
	
	

	private void updateMonster_01(float deltaTime) {
		int len = monster_01.size();
		for (int i = 0; i < len; i++) {
			Monster_01 monster = monster_01.get(i);
			monster.update(deltaTime);
			if (monster.state==monster.STATE_DEATH) {
				monster_01.remove(monster);
			}
			if (monster.state==monster.STATE_DEATHALL) {
				monster_01.removeAll(monster_01);
				randomProblem.removeAll(randomProblem);
			}
			len = monster_01.size();
		}
	}




}

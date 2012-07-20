package com.example.calculategame;

import java.util.ArrayList;
import java.util.TreeSet;

public class RandomGame2 {

//	World world;
	String base,baseAns;
	ArrayList<String> Question = new ArrayList<String>();
	ArrayList<String> Answer = new ArrayList<String>();
	
	
	public void generate(int numMonster){
		int num1,num2;
		int ans=0;
		String operand="";
//		for (int i = 0; i < 100; i++) {
			int wcase = (int)(Math.random()*4)+1;
//			System.out.print("case = "+wcase);
			while (true) {
				num1 = (int)(Math.random()*(8+numMonster))+0;
				num2 = (int)(Math.random()*(8+numMonster))+0;
				switch (wcase) {
				case 1:
					operand="+";
					ans=num1+num2;
					break;
				case 2:
					if (num1<num2) {
						continue;
					}
					operand="-";			
					ans=num1-num2;
					break;
				case 3:
					if (num1>12 || num2 >12) {
						continue;
					}
					operand="x";
					ans=num1*num2;
					break;
				case 4:
					if (num2==0|| num1==0 || num1<num2  ||(num1%num2)!=0  ) {
						continue;
					}
					operand="/";
					ans=num1/num2;
					break;
				default:
					break;
				}
				break;
			}
			base = num1+operand+num2;
			baseAns=""+ans;
//			System.out.println(base+"="+ans);
			generateNewQuestion(ans,numMonster);

//				randomShowQuestion();
//			System.out.println("\n");
//			Question.clear();
//		}

	}

	public void randomShowQuestion() {
		int ran=0;
		for (int i = 0; i < 4; i++) {
			ran = (int)(Math.random()*Question.size())+0;
//			System.out.print(Question.get(ran)+"   ");
			Question.remove(ran);
		}
	}

	public void generateNewQuestion(int ans, int numMonster) {
		int num1 = (int)(Math.random()*ans)+0;
		int wcace =0;// 2;//(int)(Math.random()*2)+1;
		int num2=0;
		String op="",ques="";
		if (num1>=(ans/2)) {
			num2=ans+num1;
			op="-";
			ques=num2+op+num1;
			wcace=1;
		}
		else {
			op="+";
			num2=ans-num1;
			ques=num1+op+num2;
			wcace=2;
		}
		
//		System.out.println(ques+"   ");
		Question.add(ques);
		Answer.add(""+ques);
		
		for (int i = 0; i < numMonster; i++) {
			if (wcace==1) {
				if (i==0) num1+=1;
				if (i==1) { num1+=1; num2+=1;}
				if (i==2) { num1+=1; }//num2+=1;
				else{
					num1+=1;
					num2+=1;
				}
				ques=num2+op+num1;
			}
			else{
				if (i==0) num1+=1;
				if (i==1) { num2-=1; num2-=1;}
				if (i==2) { num1-=1; num2-=1 ;}
				else{
					num1+=1;
					num2+=1;
				}
				ques=num1+op+num2;
			}
			Answer.add("-10");
//			System.out.println(ques+"   ");
			Question.add(ques);
		}
	}


//	public static void main(String[] args) {
//		new RandomGame2();
//	}

}

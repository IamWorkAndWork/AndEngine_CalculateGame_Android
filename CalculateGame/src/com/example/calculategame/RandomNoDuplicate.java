package com.example.calculategame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


public class RandomNoDuplicate {
	
	public TreeSet<String> StringKey = new TreeSet<String>();
	public TreeSet<String> StringValue = new TreeSet<String>();
	String textProblem="",ansProblem;

//	String Answer="";
	public String getNumber(int LEVEL){
		String operand="",setAnswer="",setText="";
		int num1=0,num2=0;
		int ranCase=0;// = (int)(Math.random()*4)+1;
//		for (int i = 0; i < monster_01.size(); i++) {
			while (true) {
				num1 = (int)(Math.random()*(10+(LEVEL*3)))+0;
				num2 = (int)(Math.random()*(10+(LEVEL*3)))+0;
					ranCase = (int)(Math.random()*4)+1;
					switch (ranCase) {
					case 1:
						operand="+";
						setAnswer=""+(num1+num2);
						break;
					case 2:
						if (num1<num2) {
							continue;
						}
						operand="-";			
						setAnswer=""+(num1-num2);
						break;
					case 3:
						operand="x";
						setAnswer=""+(num1*num2);
						break;
					case 4:
						if (num2==0 || (num1%num2)!=0 || num1<num2) continue;
						operand="/";
						setAnswer=""+(num1/num2);
						break;
					default:
						operand="+";
						setAnswer=""+(num1+num2);
						break;
					}
					
					setText=num1+""+operand+""+num2;
//					Answer = setAnswer;
					if (StringValue.add(setAnswer)) {
						StringKey.add(setText+"="+setAnswer);
					}
					break;
			}
			
			return setText;
//			System.out.println("setText = "+setText);
	}
	
}

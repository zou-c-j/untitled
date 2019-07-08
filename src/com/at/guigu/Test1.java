package com.at.guigu;

public class Test1{
	public static void main(String[] args){

		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().totalMemory());


	/*	try{
			Robot ro = new Robot();
			for (int i=0;i<10000;i++){
			int x = (int)(Math.random()*1024);
			int y = (int)(Math.random()*768);
			ro.mouseMove(x,y);
		}
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}
}


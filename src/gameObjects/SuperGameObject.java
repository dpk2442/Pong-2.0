package gameObjects;

import java.awt.*;

import pong.Pong;

public class SuperGameObject {
	int xPos;
	int yPos;
	int height;
	int width;
	Pong parent;
	
	int MoveConst = 8;
	
	public static int AIMoveConst = 3;
	static int BallMoveConst = 6;
	public static long SleepConst = 30;
	
	public SuperGameObject(Pong parent, int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.parent = parent;
	}
	
	public void draw(Graphics g, PongBall ball) {}
}

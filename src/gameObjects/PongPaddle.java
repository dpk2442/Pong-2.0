package gameObjects;

import java.awt.*;

import pong.Pong;

public class PongPaddle extends SuperGameObject {
	
	boolean isAI;
	
	public PongPaddle(Pong parent, int xPos, int yPos, int width, int height, boolean isAI) {
		super(parent, xPos, yPos, width, height);
		this.isAI = isAI;
		
		if (this.isAI) MoveConst = AIMoveConst;
	}

	public void draw(Graphics g, PongBall ball) {
		g.fillRect(xPos, yPos, width, height);
		
		if (isAI && !parent.isPaused()) processAI(ball);
	}
	
	public void moveUp() {
		yPos -= MoveConst;
		if (yPos <= 0) {
			yPos = 0;
		}
	}
	
	public void moveDown() {
		yPos += MoveConst;
		if (yPos >= (500 - height)) {
			yPos = 500 - height;
		}
	}
	
	public void moveMouse(int y) {
		if (parent.isPaused()) return;
		// Jumps Straight to mouse
		if (!((y - height / 2 <= 0) || (y + height /2 >= 500))) {
			yPos = y - height / 2;
		}
		
		// Is meant to glide to mouse
//		if (y - height / 2 < yPos) moveUp();
//		else if (y - height / 2 > yPos) moveDown();
	}
	
	public void processAI(PongBall ball) {
		if (ball.xincrement < 0) {
			MoveConst = AIMoveConst;
			if (ball.yPos <= (yPos + 10)) moveUp();
			if ((ball.yPos + ball.height) >= (yPos + height + 10)) moveDown();
		}
	}
	
}

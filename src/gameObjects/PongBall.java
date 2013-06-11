package gameObjects;

import helpers.SoundEffect;

import java.awt.*;

import pong.Pong;

public class PongBall extends SuperGameObject {

	PongPaddle player1;
	PongPaddle player2;
	ScoreKeeper score;
	
	public PongBall(Pong parent, int xPos, int yPos, int width, int height, PongPaddle player1, PongPaddle player2, ScoreKeeper score) {
		super(parent, xPos, yPos, width, height);
		
		this.player1 = player1;
		this.player2 = player2;
		this.score = score;
	}
	
	public void draw(Graphics g) {
		g.fillRect(xPos, yPos, width, height);
		
		if (!parent.isPaused()) processMove();
	}
	
	int xincrement = BallMoveConst;
	int yincrement = 0;
	
	public void processMove() {
		xPos += xincrement;
		yPos += yincrement;
		
		// Player 1 collision
		if ((xPos + width) >= player1.xPos && (xPos + width) < (player1.xPos + SuperGameObject.BallMoveConst)) {
			if ((yPos + height) >= player1.yPos && yPos <= (player1.yPos + player1.height)) {
				xincrement *= -1;
				if ((yPos + height) < (player1.yPos + (player1.height / 2))) {
					if ((yPos + height) < (player1.yPos + (player1.height / 4))) yincrement = -4;
					else yincrement = -2;
				} else if (yPos > (player1.yPos + (player1.height / 2))) {
					if ((yPos + height) > (player1.yPos + ((player1.height / 4)*3))) yincrement = 4;
					else yincrement = 2;
				}
				SoundEffect.BOUNCE.play();
			}
		}
		
		// Player 2 collision
		if (xPos <= (player2.xPos + player2.width) && xPos > (player2.xPos + player2.width - SuperGameObject.BallMoveConst)) {
			if ((yPos + height) >= player2.yPos && yPos <= (player2.yPos + player2.height)) {
				xincrement *= -1;
				if ((yPos + height) < (player2.yPos + (player2.height / 2))) {
					if ((yPos + height) < (player2.yPos + (player2.height / 4))) yincrement = -4;
					else yincrement = -2;
				} else if (yPos > (player2.yPos + (player2.height / 2))) {
					if ((yPos + height) > (player2.yPos + ((player2.height / 4)*3))) yincrement = 4;
					else yincrement = 2;
				}
				SoundEffect.BOUNCE.play();
			}
		}
		
		// Bottom wall collision
		if ((yPos + height) >= 500) {
			yincrement *= -1;
			SoundEffect.BOUNCE.play();
		}
		
		// Top wall collision
		if ((yPos) <= 0) {
			yincrement *= -1;
			SoundEffect.BOUNCE.play();
		}
		
		// Gone off right
		if (xPos >= 720) {
			xPos = 360;
			yPos = 230;
			xincrement = BallMoveConst;
			yincrement = 0;
			score.addPlayer2();
			SoundEffect.SCORE.play();
		}

		// Gone off left
		if (xPos <= -20) {
			xPos = 320;
			yPos = 230;
			xincrement = BallMoveConst * -1;
			yincrement = 0;
			score.addPlayer1();
			SoundEffect.SCORE.play();
		}
		
	}

}

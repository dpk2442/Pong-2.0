package gameObjects;

import gui.GuiScreenEndGame;

import java.awt.*;

import pong.Pong;

public class ScoreKeeper {
	private int player1Score = 0;
	private int player2Score = 0;
	
	public static int endScore = 2;
	
	private Pong pong;
	
	public ScoreKeeper(Pong pong) {
		this.pong = pong;
	}
	
	public void addPlayer1() {
		player1Score++;
		checkEndGame();
	}
	
	public void addPlayer2() {
		player2Score++;
		checkEndGame();
	}
	
	public void checkEndGame() {
		if (player1Score == ScoreKeeper.endScore) {
			pong.gameOver();
			pong.showMenu(new GuiScreenEndGame(pong, 1));
		} else if (player2Score == ScoreKeeper.endScore) {
			pong.gameOver();
			pong.showMenu(new GuiScreenEndGame(pong, 2));
		}
	}
	
	public void draw(Graphics g) {
		g.drawString("" + player1Score, 645, 30);
		g.drawString("" + player2Score, 50, 30);
	}
}

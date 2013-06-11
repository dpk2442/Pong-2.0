package pong;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import gameObjects.*;
import gui.*;
import helpers.*;

public class Pong extends JFrame implements KeyListener, MouseListener, MouseMotionListener, WindowFocusListener {

	int i = 0;

	PongPaddle player1 = new PongPaddle(this, 660, 180, 20, 70, false);
	PongPaddle player2 = new PongPaddle(this, 20, 180, 20, 70, true);

	ScoreKeeper score = new ScoreKeeper(this);

	PongBall ball = new PongBall(this, 360, 230, 20, 20, player1, player2, score);

	private Dimension gameSize;

	boolean gameStarted = false;
	private boolean paused = true;
	private boolean showMenu = false;

	GuiScreen currentGuiScreen;
	GuiScreen lastGuiScreen;

	public Pong() {

		SoundEffect.values();

		setSize(706, 530);

		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);

		setBackground(Color.black);

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addWindowFocusListener(this);

		createBufferStrategy(1);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		gameSize = new Dimension(700, 500);

		showMenu(new GuiScreenMainMenu(this));

		new Thread(new Repaint(this)).start();
	}

	public void paint(Graphics g) {

		BufferStrategy bufferStrategy = this.getBufferStrategy();
		Graphics bufferGraphics = bufferStrategy.getDrawGraphics();

		bufferGraphics.translate(2, 25);

		bufferGraphics.clearRect(-50,-50,760,560);

		if (gameStarted) {

			bufferGraphics.setColor(Color.green);

			bufferGraphics.drawRect(0,0,700,500);

			player1.draw(bufferGraphics, null);
			player2.draw(bufferGraphics, ball);

			score.draw(bufferGraphics);

			ball.draw(bufferGraphics);

			bufferGraphics.drawLine(350, 0, 350, 500);
		}

		if (showMenu) {

			if (gameStarted) {
				Color oldColor = bufferGraphics.getColor();
				Color color = new Color(Color.gray.getRed(), Color.gray.getGreen(), Color.gray.getBlue(), 100);
				bufferGraphics.setColor(color);
				bufferGraphics.fillRect(-2, -25, 750, 550);
				bufferGraphics.setColor(oldColor);
			}

			currentGuiScreen.draw(bufferGraphics, mouseX - 2, mouseY - 25);
		}

		if (!bufferStrategy.contentsLost())
			bufferStrategy.show();
	}

	public void showMenu(GuiScreen menu) {
		this.showMenu = true;
		this.lastGuiScreen = this.currentGuiScreen;
		this.currentGuiScreen = menu;
	}

	public void hideMenu() {
		this.showMenu = false;
		this.currentGuiScreen = null;
	}

	public void showLastMenu() {
		if (this.lastGuiScreen == null) return;
		this.showMenu(this.lastGuiScreen);
		this.lastGuiScreen = null;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
		if (paused) {
			showMenu(new GuiScreenPauseMenu(this));
		} else {
			hideMenu();
		}
	}

	public void startGame() {
		this.hideMenu();
		paused = false;
		gameStarted = true;
	}

	public void endGame() {
		paused = true;
		gameStarted = false;

		player1 = new PongPaddle(this, 660, 180, 20, 70, false);
		player2 = new PongPaddle(this, 20, 180, 20, 70, true);

		score = new ScoreKeeper(this);

		ball = new PongBall(this, 360, 230, 20, 20, player1, player2, score);
	}

	public void gameOver() {
		paused = true;
	}

	public Dimension getGameSize() {
		return gameSize;
	}

	int keyHit;
	boolean processBool = false;
	ProcessTheKeys processTheKeys = new ProcessTheKeys();
	Thread processInputThread = null;

	public void keyPressed(KeyEvent e) {
		keyHit = e.getKeyCode();
		processBool = true;
		if (processInputThread == null) processInputThread = new Thread(processTheKeys);
		try {
			processInputThread.start();
		} catch (IllegalThreadStateException tse) {}
	}
	public void keyReleased(KeyEvent e) {
		processBool = false;
		processInputThread = null;
	}

	class ProcessTheKeys implements Runnable {
		public void run() {
			boolean runOnce = false;
			while (processBool) {
				if (keyHit == 38) {
					player1.moveUp();
				} else if (keyHit == 40) {
					player1.moveDown();
				} else if (keyHit == KeyEvent.VK_ESCAPE) {
					if (!runOnce && gameStarted) setPaused(!isPaused());
				}

				runOnce = true;
				try { Thread.sleep(10); } catch (InterruptedException e) {}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (currentGuiScreen != null) {
			currentGuiScreen.buttonClicked(e);
		}
	}

	private int mouseX;
	private int mouseY;

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (gameStarted) {
			player1.moveMouse(e.getY());
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}


	public void mousePressed(MouseEvent e) {
		if (currentGuiScreen != null) {
			currentGuiScreen.sliderPressed(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
		if (currentGuiScreen != null) {
			currentGuiScreen.sliderReleased(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (currentGuiScreen != null) {
			currentGuiScreen.sliderDragged(e);
		}
	}

	public static void main(String[] args) {
		new Pong();
	}

	public void windowGainedFocus(WindowEvent e) {}

	public void windowLostFocus(WindowEvent e) {
		if (!showMenu) this.setPaused(true);
	}

}

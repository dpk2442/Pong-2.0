package gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import pong.Pong;

public class GuiScreenEndGame extends GuiScreen {

	private int winner;
	
	public GuiScreenEndGame(Pong pong, int winner) {
		super(pong);
		
		this.winner = winner;
		
		int screenWidth = (int) this.pong.getGameSize().getWidth();
		int screenHeight = (int) this.pong.getGameSize().getHeight();
		int buttonX = screenWidth/2 - 125;

		buttons.add(new GuiButton(0, new Point(buttonX, screenHeight/2), null, "Play Again", this));
		buttons.add(new GuiButton(1, new Point(buttonX, screenHeight/2 + 50), null, "Return to Menu", this));
		buttons.add(new GuiButton(2, new Point(buttonX, screenHeight/2 + 100), null, "Exit", this));
	}

	@Override
	public void buttonActionPerformed(GuiButton btn) {
		if (btn.getId() == 0) {
			pong.endGame();
			pong.startGame();
		} else if (btn.getId() == 1) {
			pong.endGame();
			pong.showMenu(new GuiScreenMainMenu(pong));
		} else if (btn.getId() == 2) {
			System.exit(0);
		}
	}

	@Override
	public void draw(Graphics screenGraphics, int mouseX, int mouseY) {
		int screenWidth = (int) this.pong.getGameSize().getWidth();
		int screenHeight = (int) this.pong.getGameSize().getHeight();
		
		Font oldFont = screenGraphics.getFont();
		
		String line1 = (winner == 1) ? "Congratulations! :D" : "Sorry :(";
		String line2 = (winner == 1) ? "You won!" : "Game Over";
		
		screenGraphics.setFont(new Font(oldFont.getName(), oldFont.getStyle(), 50));
		FontMetrics fm = screenGraphics.getFontMetrics();
		screenGraphics.drawString(line1, screenWidth/2 - fm.stringWidth(line1)/2, screenHeight/4 - 50);

		screenGraphics.setFont(new Font(oldFont.getName(), oldFont.getStyle(), 40));
		fm = screenGraphics.getFontMetrics();
		screenGraphics.drawString(line2, screenWidth/2 - fm.stringWidth(line2)/2, screenHeight/4);
		
		screenGraphics.setFont(oldFont);
		
		this.drawButtons(screenGraphics, mouseX, mouseY);
	}

	@Override
	public void sliderDragged(GuiSlider slider) {}

}

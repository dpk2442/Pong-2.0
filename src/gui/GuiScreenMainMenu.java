package gui;

import java.awt.*;

import pong.Pong;

public class GuiScreenMainMenu extends GuiScreen {

	public GuiScreenMainMenu(Pong pong) {
		super(pong);
		
		int screenWidth = (int) this.pong.getGameSize().getWidth();
		int screenHeight = (int) this.pong.getGameSize().getHeight();
		int buttonX = screenWidth/2 - 125;

		buttons.add(new GuiButton(0, new Point(buttonX, screenHeight/2 + 50), null, "Start Game", this));
		buttons.add(new GuiButton(1, new Point(buttonX, screenHeight/2 + 100), null, "Options", this));
		buttons.add(new GuiButton(2, new Point(buttonX, screenHeight/2 + 150), null, "Exit", this));
	}

	@Override
	public void draw(Graphics screenGraphics, int mouseX, int mouseY) {
		
		int screenWidth = (int) this.pong.getGameSize().getWidth();
		int screenHeight = (int) this.pong.getGameSize().getHeight();
		
		Image logoImg = getImage("/images/pong_logo.png");
		screenGraphics.drawImage(logoImg, screenWidth/2 - logoImg.getWidth(null)/2, screenHeight/2 - logoImg.getHeight(null), null);
		
		this.drawButtons(screenGraphics, mouseX, mouseY);
	}

	@Override
	public void buttonActionPerformed(GuiButton btn) {
		if (btn.getId() == 0) {
			pong.startGame();
		} else if (btn.getId() == 1) {
			pong.showMenu(new GuiScreenOptions(pong));
		} else if (btn.getId() == 2) {
			System.exit(0);
		}
	}

	@Override
	public void sliderDragged(GuiSlider slider) {}

}

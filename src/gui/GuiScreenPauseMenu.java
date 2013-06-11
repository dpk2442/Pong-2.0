package gui;

import java.awt.*;

import pong.Pong;

public class GuiScreenPauseMenu extends GuiScreen {

	public GuiScreenPauseMenu(Pong pong) {
		super(pong);

		int numButtons = 4;
		int screenWidth = (int) pong.getGameSize().getWidth();
		int screenHeight = (int) pong.getGameSize().getHeight();
		int buttonX = screenWidth/2 - 125;
		int firstButtonY = screenHeight/2 - (numButtons * 50) / 2;

		buttons.add(new GuiButton(0, new Point(buttonX, firstButtonY), null, "Options", this));
		buttons.add(new GuiButton(1, new Point(buttonX, firstButtonY + 100), null, "Return to game", this));
		buttons.add(new GuiButton(2, new Point(buttonX, firstButtonY + 150), null, "Quit to Menu", this));
	}
	
	@Override
	public void draw(Graphics screenGraphics, int mouseX, int mouseY) {
		this.drawButtons(screenGraphics, mouseX, mouseY);
	}

	@Override
	public void buttonActionPerformed(GuiButton btn) {
		if (btn.getId() == 0) {
			pong.showMenu(new GuiScreenOptions(pong));
		} else if (btn.getId() == 1) {
			pong.setPaused(false);
		} else if (btn.getId() == 2) {
			pong.endGame();
			pong.showMenu(new GuiScreenMainMenu(pong));
		}
	}

	@Override
	public void sliderDragged(GuiSlider slider) {}

}

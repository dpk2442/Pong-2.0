package gui;

import gameObjects.ScoreKeeper;
import gameObjects.SuperGameObject;
import helpers.SoundEffect;

import java.awt.Graphics;
import java.awt.Point;

import pong.Pong;

public class GuiScreenOptions extends GuiScreen {

	public GuiScreenOptions(Pong pong) {
		super(pong);

		int numButtons = 5;
		int screenWidth = (int) pong.getGameSize().getWidth();
		int screenHeight = (int) pong.getGameSize().getHeight();
		int buttonX = screenWidth/2 - 125;
		int firstButtonY = screenHeight/2 - (numButtons * 50) / 2;

		buttons.add(new GuiButton(0, new Point(buttonX, firstButtonY), null, "Play Until: " + ScoreKeeper.endScore, this));
		buttons.add(new GuiButton(1, new Point(buttonX, firstButtonY + 50), null, "AI Difficulty: ", this));
		buttons.add(new GuiButton(2, new Point(buttonX, firstButtonY + 200), null, "Back", this));
		
		sliders.add(new GuiSlider(0, new Point(buttonX, firstButtonY + 100), null, "Volume: <value>", this));
		sliders.get(0).setValue(SoundEffect.volume);
	}

	@Override
	public void buttonActionPerformed(GuiButton btn) {
		if (btn.getId() == 0) {
			ScoreKeeper.endScore = (ScoreKeeper.endScore == 15) ? 21 : 15;
		} else if (btn.getId() == 1) {
			SuperGameObject.AIMoveConst++;
			if (SuperGameObject.AIMoveConst == 5) SuperGameObject.AIMoveConst = 2;
		} else if (btn.getId() == 2) {
			pong.showLastMenu();
		}
	}

	@Override
	public void sliderDragged(GuiSlider slider) {
		if (slider.getId() == 0) {
			SoundEffect.volume = slider.getValue();
		}
	}

	@Override
	public void draw(Graphics screenGraphics, int mouseX, int mouseY) {
		

		String difficulty = "";
		switch (SuperGameObject.AIMoveConst) {
		case 2:
			difficulty = "Easy";
			break;
		case 3:
			difficulty = "Medium";
			break;
		case 4:
			difficulty = "Hard";
			break;
		}
		
		buttons.get(0).setText("Play Until: " + ScoreKeeper.endScore);
		buttons.get(1).setText("AI Difficulty: " + difficulty);
		this.drawButtons(screenGraphics, mouseX, mouseY);
		this.drawSliders(screenGraphics, mouseX, mouseY);
	}

}

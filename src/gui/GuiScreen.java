package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import pong.Pong;

public abstract class GuiScreen extends Gui {
	
	Pong pong;
	protected ArrayList<GuiButton> buttons;
	protected ArrayList<GuiSlider> sliders;
	
	public GuiScreen(Pong pong) {
		super(new Point(0, 0), null);
		this.setSize(pong.getSize());
		
		this.pong = pong;
		this.buttons = new ArrayList<GuiButton>();
		this.sliders = new ArrayList<GuiSlider>();
	}
	
	public void drawButtons(Graphics screenGraphics, int mouseX, int mouseY) {
		if (buttons.size() != 0) {
			// for each GuiButton button in buttons
			for (GuiButton button : buttons) {
				button.draw(screenGraphics, mouseX, mouseY);
			}
		}
	}
	
	public void drawSliders(Graphics screenGraphics, int mouseX, int mouseY) {
		if (sliders.size() != 0) {
			// for each GuiSlider slider in sliders
			for (GuiSlider slider : sliders) {
				slider.draw(screenGraphics, mouseX, mouseY);
			}
		}
	}

	public void buttonClicked(MouseEvent e) {
		if (buttons.size() != 0) {
			// for each GuiButton button in buttons
			for (GuiButton button : buttons) {
				button.clicked(e);
			}
		}
	}
	
	public void sliderPressed(MouseEvent e) {
		if (sliders.size() != 0) {
			// for each GuiSlider slider in sliders
			for (GuiSlider slider : sliders) {
				slider.pressed(e);
			}
		}
	}
	
	public void sliderReleased(MouseEvent e) {
		if (sliders.size() != 0) {
			// for each GuiSlider slider in sliders
			for (GuiSlider slider : sliders) {
				slider.released(e);
			}
		}
	}
	
	public void sliderDragged(MouseEvent e) {
		if (sliders.size() != 0) {
			// for each GuiSlider slider in sliders
			for (GuiSlider slider : sliders) {
				slider.dragged(e);
			}
		}
	}
	
	public abstract void buttonActionPerformed(GuiButton btn);
	
	public abstract void sliderDragged(GuiSlider slider);

}

package gui;

import helpers.SoundEffect;

import java.awt.*;
import java.awt.event.*;

public class GuiButton extends Gui {

	private String text;
	private GuiScreen parent;
	private int id;

	public GuiButton(int id, Point location, Dimension size, String text, GuiScreen parent) {
		super(location, size);

		this.id = id;

		this.text = text;
		this.parent = parent;

		if (size == null) {
			this.setSize(new Dimension(250, 40));
		}

		if (this.text == null) {
			this.text = "";
		}

	}

	@Override
	public void draw(Graphics screenGraphics, int mouseX, int mouseY) {
		int x = (int) this.getLocation().getX();
		int width = (int) this.getSize().getWidth();
		int y = (int) this.getLocation().getY();
		int height = (int) this.getSize().getHeight();

		boolean mouseIn = (mouseX > x && mouseX < (x + width)) && (mouseY > y && mouseY < (y + height));

		Color oldColor = screenGraphics.getColor();
		Font oldFont = screenGraphics.getFont();

		if (mouseIn) {
			screenGraphics.setColor(Color.green);
		} else {
			screenGraphics.setColor(Color.black);
		}
		screenGraphics.fillRect(x, y, width, height);
		screenGraphics.setColor(Color.green);
		screenGraphics.drawRect(x, y, width, height);
		screenGraphics.fillRect(x - 2, y - 2, 5, 5);
		screenGraphics.fillRect(x + width - 2, y - 2, 5, 5);
		screenGraphics.fillRect(x - 2, y + height - 2, 5, 5);
		screenGraphics.fillRect(x  + width - 2, y + height - 2, 5, 5);

		if (mouseIn) {
			screenGraphics.setColor(Color.black);
		} else {
			screenGraphics.setColor(Color.green);
		}

		int fontPointSize = 20;
		screenGraphics.setFont(new Font(oldFont.getName(), oldFont.getStyle(), fontPointSize));
		FontMetrics fm = screenGraphics.getFontMetrics();

		while (true) {
			if (fm.stringWidth(this.text) < width) break;
			fontPointSize--;
			screenGraphics.setFont(new Font(oldFont.getName(), oldFont.getStyle(), fontPointSize));
			fm = screenGraphics.getFontMetrics();
			if (fontPointSize < 5) break;
		}

		screenGraphics.drawString(this.text, x + width/2 - fm.stringWidth(this.text)/2, y + height/2 + fm.getAscent()/2 - fm.getDescent()/2);

		screenGraphics.setFont(oldFont);
		screenGraphics.setColor(oldColor);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void clicked(MouseEvent e) {
		int mouseX = e.getX() - 2;
		int mouseY = e.getY() - 25;
		int x = (int) this.getLocation().getX();
		int width = (int) this.getSize().getWidth();
		int y = (int) this.getLocation().getY();
		int height = (int) this.getSize().getHeight();
		if (mouseX > x && mouseX < (x + width)) {
			if (mouseY > y && mouseY < (y + height)) {
				parent.buttonActionPerformed(this);
				SoundEffect.CLICK.play(1.0);
			}
		}
	}

}

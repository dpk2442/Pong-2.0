package gui;

import java.awt.*;

public abstract class Gui {

	protected Point location;
	protected Dimension size;

	public Gui(Point location, Dimension size) {
		this.location = location;
		this.size = size;
	}

	public abstract void draw(Graphics screenGraphics, int mouseX, int mouseY);

	public Image getImage(String src) {
		Image img = null;
		try {
			img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(src));
		} catch (Exception e) {}
		return img;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

}

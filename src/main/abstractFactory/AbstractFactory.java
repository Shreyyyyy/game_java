package abstractFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class AbstractFactory {
	protected int screenWidth;
	protected int screenHeight;
	public static final int DEFAULT_WIDTH = 1920;
	public static final int DEFAULT_HEIGHT = 1080;
	protected Color colorDefault;
	protected Color colorForeground;
	
	/**
	 * Create a generic factory.
	 * @param screenWidth
	 * @param screenHeight
	 */
	public AbstractFactory(Dimension resolution) {
		screenWidth = (int) resolution.getWidth();
		screenHeight = (int) resolution.getHeight();
	}
	
	/**
	 * Set the default color.
	 * @param colorDefault the default color.
	 */
	public void setColorDefault(Color colorDefault) {
		this.colorDefault = colorDefault;
	}
	
	/**
	 * Sets the screen resolution taking a dimension as a parameter. (width, height)
	 * @param resolution the screen resolution.
	 */
	public void setScreenResolution(Dimension resolution) {
		screenWidth = (int) resolution.getWidth();
		screenHeight = (int) resolution.getHeight();
	}
	
	/**
	 * Returns the screen resolution as a dimension. (width, height)
	 * @return the screen resolution.
	 */
	public Dimension getScreenResolution() {
		return new Dimension(screenWidth, screenHeight);
	}
	
	/**
	 * Returns an image in its original resolution.
	 * @param path the image path.
	 * @return the image.
	 */
	protected ImageIcon getTheIcon(String path) {
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		return scale(icon);
	}
	
	/**
	 * It allows scaling an image based on the resolution passed by parameter. 
	 * Scale taking into account the original size of the image for a resolution of 1920 x 1080.
	 * for example if an image is for 1920 x 1080 in a resolution of 60 x 45 px, 
	 * and the actual resolution is 1280 x 720, the image is transformed into a 40 x 30 px.
	 * scaling is done correctly if the 16 9 aspect ratio is maintained.
	 * @param imageIcon the image to scale.
	 * @return the scaled image.
	 */
	protected ImageIcon scale(ImageIcon imageIcon) {
		double h = screenHeight;
		double scaleFactor = h / DEFAULT_HEIGHT;
		
		int width = (int) Math.round(imageIcon.getIconWidth() * scaleFactor);
		int height = (int) Math.round(imageIcon.getIconHeight() * scaleFactor);
		return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
	
	/**
	 * Return default color.
	 * @return default color.
	 */
	public abstract Color getColorDefault();	
	
	/**
	 * Return foreground color.
	 * @return foreground color.
	 */
	public abstract Color getColorForeground();	
	
	/**
	 * Returns a color similar to the default color.
	 * @return new Color.
	 */
	public abstract Color getDefaultMarkColor();
	
	/**
	 * Return the configuration icon.
	 * @return the configuration icon.
	 */
	public abstract Icon getConfiguration();
	
	/**
	 * Return the restart icon.
	 * @return the restart icon.
	 */
	public abstract Icon getRestart();
	
	/**
	 * Return the home icon.
	 * @return the home icon.
	 */
	public abstract Icon getHome();
	
	/**
	 * Return the exit icon.
	 * @return the exit icon.
	 */
	public abstract Icon getExit();
	
	/**
	 * Return a game icon associated to the name passed by parameter.
	 * @param name a game name.
	 * @return a game icon.
	 */
	public abstract Icon getGameIcon(String name);
	
	/**
	 * Return the keyboard0 icon. this keyboard contains the space.
	 * @return the keyboard0 icon.
	 */
	public abstract Icon getKeyboard0();
	
	/**
	 * Return the keyboard1 icon. this keyboard contains the keys: W, A, S and D.
	 * @return the keyboard1 icon.
	 */
	public abstract Icon getKeyboard1();
	
	/**
	 * Return the keyboard2 icon. this keyboard contains the keys: W, A, S, D and SPACE.
	 * @return the keyboard2 icon.
	 */
	public abstract Icon getKeyboard2();
	
	/**
	 * Return the keyboard3 icon. this keyboard contains the keys: W, and S.
	 * @return the keyboard3 icon.
	 */
	public abstract Icon getKeyboard3();
	
	/**
	 * Return the keyboard4 icon. this keyboard contains the keys: A, D, ande SPACE.
	 * @return the keyboard4 icon.
	 */
	public abstract Icon getKeyboard4();
	
	/**
	 * Return the keyboard5 icon. this keyboard contains the keys: 1, 2, 3, 4, 5, 6, 7, 8, 9 and 0.
	 * @return the keyboard5 icon.
	 */
	public abstract Icon getKeyboard5();
	
	/**
	 * Return the keyboard6 icon. this keyboard contains the keys: A, and D.
	 * @return the keyboard6 icon.
	 */
	public abstract Icon getKeyboard6();
	
	/**
	 * Return the keyboard7 icon. this keyboard contains the keys: A, S, and D.
	 * @return the keyboard6 icon.
	 */
	public abstract Icon getKeyboard7();
	
	/**
	 * Return the mouse icon.
	 * @return the mouse icon.
	 */
	public abstract Icon getMouse();	
	
	/**
	 * Return the empty icon.
	 * @return the empty icon.
	 */
	public abstract Icon getEmpty();
	
	/**
	 * Return the capsule icon.
	 * @return the capsule icon.
	 */
	public abstract Icon getCapsule();
	
	/**
	 * Return the triangle icon.
	 * @return the triangle icon.
	 */
	public abstract Icon getTriangle();
	
	/**
	 * Return the circle icon.
	 * @return the circle icon.
	 */
	public abstract Icon getCircle();
	
	/**
	 * Return the square icon.
	 * @return the square icon.
	 */
	public abstract Icon getSquare();

}

package Game.Images;

import javax.swing.*;
import java.awt.*;


/**
 * @author
 * BattleshipImageComponent
 * A class to create an image component
 */
public class BattleshipImageComponent extends JComponent {

    ImageIcon icon; // Current Icon
    String file;
    int width;
    int height;

    /**
     * Constructor of the PhotoComponentClass
     * 
     * @param fileName name of the file that stores the image
     */
    public BattleshipImageComponent(String fileName) {
        super();

        this.file = fileName;
        icon = new ImageIcon(fileName);

        // this.setPreferredSize(new Dimension(icon.getImage().getWidth(null), icon.getImage().getHeight(null)));
        this.setPreferredSize(new Dimension(this.getImageWidth(),this.getImageHeight()));
      }

    /**
     * Get the width of the image
     * @return width
     */
    public int getImageWidth() {
        return this.width;
    }

    /**
     * Get the height of the image
     * @return height
     */
    public int getImageHeight() {
        return this.height;
    }

    /**
     * Set the width of the image
     * @param w width
     */
    public void setImageWidth(int w) {
        this.width = w;
    }

    /**
     * Set the height of the component
     * @param h height
     */
    public void setImageHeight(int h) {
        this.height = h;
    }

    /**
     * Override the paint component to display the image
     * @param g graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image pic = icon.getImage();
        g.drawImage(pic, 0, 0, null); // Draw picture
    }
}

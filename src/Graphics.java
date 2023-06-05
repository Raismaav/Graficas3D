import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphics {
    private BufferedImage buffer;
    private JPanel canvas;
    private Color background;

    public Graphics(JPanel canvas, Color background) {
        this.canvas = canvas;
        this.buffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.background = background;

        for (int j = 0; j < buffer.getHeight() - 1; j++) {
            for (int i = 0; i <= buffer.getWidth() - 1; i++) {
                buffer.setRGB(i, j, background.getRGB());
            }
        }
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
    }

    public Graphics(JPanel canvas) {
        this.canvas = canvas;
        this.buffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.background = new Color(238, 238, 238);

        for (int j = 0; j < buffer.getHeight() - 1; j++) {
            for (int i = 0; i <= buffer.getWidth() - 1; i++) {
                buffer.setRGB(i, j, background.getRGB());
            }
        }
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
    }

    private void drawPixel(int x, int y, Color color) {
        if ((x >= 0 && x < buffer.getWidth()) && (y >= 0 && y < buffer.getHeight())) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    public void drawPoint(int x, int y, Color color) {
        drawPixel(x, y, color);
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
    }

    public void drawLine(int startX, int startY, int endX, int endY, Color color) {
        float x = 0, y = 0;
        if (startX < endX) {
            x = startX;
            y = startY;
        } else {
            int bufferX = endX, bufferY = endY;
            x = bufferX;
            y = bufferY;
            endX = startX;
            endY = startY;
            startX = bufferX;
            startY = bufferY;
        }
        float m = (endY - y) / (endX - x);

        if (m >= 1) {
            for (int j = startY; j <= endY; j++) {
                drawPixel(Math.round(x), j, color);
                x += 1 / m;
            }
        } else if (m >= -1){
            for (int i = startX; i <= endX; i++) {
                drawPixel(i, Math.round(y), color);
                y += m;
            }
        } else {
            for (int j = startY; j >= endY; j--) {
                drawPixel(Math.round(x), j, color);
                x -= 1 / m;
            }
        }
    }
    public void update() {
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
    }
}
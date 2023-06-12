import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphics {
    protected BufferedImage buffer;
    private JPanel canvas;
    private Color colorBackground = new Color(238, 238, 238);

    public Graphics(JPanel canvas, Color colorBackground) {
        this.canvas = canvas;
        this.buffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.colorBackground = colorBackground;

        for (int j = 0; j < buffer.getHeight() - 1; j++) {
            for (int i = 0; i <= buffer.getWidth() - 1; i++) {
                buffer.setRGB(i, j, colorBackground.getRGB());
            }
        }
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
    }

    public Graphics(JPanel canvas) {
        this.canvas = canvas;
        this.buffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int j = 0; j < buffer.getHeight() - 1; j++) {
            for (int i = 0; i <= buffer.getWidth() - 1; i++) {
                buffer.setRGB(i, j, colorBackground.getRGB());
            }
        }
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
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

    public void drawLine(int startX, int startY, int endX, int endY, Color color, BufferedImage auxBuffer) {
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
                drawPixel(Math.round(x), j, color, auxBuffer);
                x += 1 / m;
            }
        } else if (m >= -1){
            for (int i = startX; i <= endX; i++) {
                drawPixel(i, Math.round(y), color, auxBuffer);
                y += m;
            }
        } else {
            for (int j = startY; j >= endY; j--) {
                drawPixel(Math.round(x), j, color, auxBuffer);
                x -= 1 / m;
            }
        }
    }

    public void drawAuxbuffer(BufferedImage auxbuffer) {
        for (int j = 0; j < auxbuffer.getHeight(); j++) {
            for (int i = 0; i < auxbuffer.getWidth(); i++) {
                if (auxbuffer.getRGB(i, j) != 0)
                    buffer.setRGB(i, j, auxbuffer.getRGB(i, j));
            }
        }
    }

    public void update() {
        canvas.getGraphics().drawImage(buffer, 0, 0, canvas);
    }

    protected void drawPixel(int x, int y, Color color) {
        if ((x >= 0 && x < buffer.getWidth()) && (y >= 0 && y < buffer.getHeight())) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    protected void drawPixel(int x, int y, Color color, BufferedImage auxBuffer) {
        if ((x >= 0 && x < auxBuffer.getWidth()) && (y >= 0 && y < auxBuffer.getHeight())) {
            auxBuffer.setRGB(x, y, color.getRGB());
        }
    }

    protected void repaintBackground() {
        for (int j = 0; j < buffer.getHeight(); j++) {
            for (int i = 0; i < buffer.getWidth(); i++) {
                buffer.setRGB(i, j, colorBackground.getRGB());
            }
        }
    }

    protected void scanLineFill(int x, int y, int c, Color fill) {
        if(x < 0) {
            x = buffer.getWidth() - 1;
        } else if(x >= buffer.getWidth()) {
            x = 0;
        }

        if(y < 0) {
            y = buffer.getHeight() - 1;
        } else if(y >= buffer.getHeight()) {
            y = 0;
        }

        int pc =  buffer.getRGB(x,y);

        if(pc == c) {
            drawPixel(x, y, fill);
            scanLineFill(x + 1, y, c, fill);
            scanLineFill(x - 1, y, c, fill);
            scanLineFill(x, y - 1, c, fill);
            scanLineFill(x, y + 1, c, fill);
        }
    }
}
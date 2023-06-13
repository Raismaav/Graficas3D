import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphics3D extends Graphics {
    private BufferedImage bufferedFace;
    private Graphics2D graphics;
    private double[] projectionPlane = new double[] {10, 10, 50};
    private double[] cameraPosition, visionVector;
    public Graphics3D(JPanel canvas, Color background) {
        super(canvas, background);
        cameraPosition = new double[3];
        cameraPosition[0] = canvas.getWidth() / 2;
        cameraPosition[1] = canvas.getHeight() / 2;
        cameraPosition[2] = 1000;
        visionVector = new double[3];
        double[] vectorA = {
                0 - 0,
                0 - canvas.getHeight(),
                0 - 0};
        double[] vectorB = {
                canvas.getWidth() - 0,
                canvas.getHeight() - canvas.getHeight(),
                0 - 0};
        visionVector[0] = vectorA[1] * vectorB[2] - vectorA[2] * vectorB[1];
        visionVector[1] = vectorA[2] * vectorB[0] - vectorA[0] * vectorB[2];
        visionVector[2] = vectorA[0] * vectorB[1] - vectorA[1] * vectorB[0];
    }

    public Graphics3D(JPanel canvas) {
        super(canvas);
        cameraPosition = new double[3];
        cameraPosition[0] = canvas.getWidth() / 2;
        cameraPosition[1] = canvas.getHeight() / 2;
        cameraPosition[2] = 1000;
        visionVector = new double[3];
        double[] vectorA = {
                0 - 0,
                0 - canvas.getHeight(),
                0 - 0};
        double[] vectorB = {
                canvas.getWidth() - 0,
                canvas.getHeight() - canvas.getHeight(),
                0 - 0};
        visionVector[0] = vectorA[1] * vectorB[2] - vectorA[2] * vectorB[1];
        visionVector[1] = vectorA[2] * vectorB[0] - vectorA[0] * vectorB[2];
        visionVector[2] = vectorA[0] * vectorB[1] - vectorA[1] * vectorB[0];
    }

    public double[] getProjectionPlane() {
        return projectionPlane;
    }

    public void setProjectionPlane(double[] projectionPlane) {
        this.projectionPlane = projectionPlane;
    }

    public void obliqueProjection(Face[] faces, int[] facesZIndex) {
        repaintBackground();
        for (int face = 0; face < faces.length; face++) {
            int[][] figure = faces[facesZIndex[face]].getVertices();
            int[][] figureProjected = new int[figure.length][figure[0].length];
            for(int i = 0; i < figure[0].length; i++) {
                figureProjected[0][i] = (int) Math.round(figure[0][i] + projectionPlane[0] * (-figure[2][i] / projectionPlane[2]));
                figureProjected[1][i] = (int) Math.round(figure[1][i] + projectionPlane[1] * (-figure[2][i] / projectionPlane[2]));
            }
            drawFigureProjected(faces, facesZIndex[face], figureProjected);
        }
        update();
    }

    public void orthogonalProjection(Face[] faces, int[] facesZIndex) {
        repaintBackground();
        for (int face = 0; face < faces.length; face++) {
            int[][] figure = faces[facesZIndex[face]].getVertices();
            int[][] figureProjected = new int[figure.length][figure[0].length];
            for(int i = 0; i < figure[0].length; i++) {
                figureProjected[0][i] = figure[0][i];
                figureProjected[1][i] = figure[1][i];
            }
            drawFigureProjected(faces, facesZIndex[face], figureProjected);
        }
        update();
    }

    public void conicalProjection(Face[] faces, double[] facesZIndex) {
        repaintBackground();
        for (int face = faces.length - 1; face >= 0; face--) {
            int[][] figure = faces[(int) facesZIndex[face]].getVertices();
            int[][] figureProjected = new int[figure.length][figure[0].length];
            for(int i = 0; i < figure[0].length; i++) {
                figureProjected[0][i] = (int) Math.round(cameraPosition[0] + (figure[0][i] - cameraPosition[0]) * (-(cameraPosition[2] / (figure[2][i] - cameraPosition[2]))));
                figureProjected[1][i] = (int) Math.round(cameraPosition[1] + (figure[1][i] - cameraPosition[1]) * (-(cameraPosition[2] / (figure[2][i] - cameraPosition[2]))));
            }
            drawFigureProjected(faces, (int) facesZIndex[face], figureProjected);
        }
        update();
    }

    private void drawFigureProjected(Face[] faces, int face, int[][] figureProjected) {
        bufferedFace = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
        if (faces[face].isFaceFilled())
            fillFace(figureProjected, faces[face].getFill(), bufferedFace, true);
        drawLine(figureProjected[0][figureProjected[0].length - 1], figureProjected[1][figureProjected[0].length - 1], figureProjected[0][0], figureProjected[1][0], faces[face].getColor(), bufferedFace);
        for(int i = 1; i < figureProjected[0].length; i++) {
            drawLine(figureProjected[0][i - 1], figureProjected[1][i - 1], figureProjected[0][i], figureProjected[1][i], faces[face].getColor(), bufferedFace);
        }
        drawAuxbuffer(bufferedFace);
    }

    private boolean backFaceCulling(double[] plainVector) {
        double dotProduct = plainVector[0] * visionVector[0] + plainVector[1] * visionVector[1] + plainVector[2] * visionVector[2];
        double plainMagnitude = Math.sqrt(Math.pow(plainVector[0], 2) + Math.pow(plainVector[1], 2) + Math.pow(plainVector[2], 2));
        double visionMagnitude = Math.sqrt(Math.pow(visionVector[0], 2) + Math.pow(visionVector[1], 2) + Math.pow(visionVector[2], 2));
        double angle = Math.toDegrees(dotProduct / (plainMagnitude * visionMagnitude));
        if (angle > 0) {
            return true;
        }
        else
            return false;
    }

    private void fillFace(int[][] figureProjected, Color fill, BufferedImage auxBuffer, boolean g) {
        if(g) {
            graphics = (Graphics2D) auxBuffer.createGraphics();
            graphics.setColor(fill);
            graphics.fillPolygon(figureProjected[0], figureProjected[1], 4);
        }
    }

    private void fillFace(int[][] figureProjected, Color fill, BufferedImage auxBuffer) {
        int maxX = figureProjected[0][0];
        int minX = figureProjected[0][0];
        int maxY = figureProjected[1][0];
        int minY = figureProjected[1][0];

        for (int i = 0; i < figureProjected[0].length; i++) {
            if (maxX < figureProjected[0][i]) {
                maxX = figureProjected[0][i];
            }
            if (minX > figureProjected[0][i]) {
                minX = figureProjected[0][i];
            }
            if (maxY < figureProjected[1][i]) {
                maxY = figureProjected[1][i];
            }
            if (minY > figureProjected[1][i]) {
                minY = figureProjected[1][i];
            }
        }
        int x = (minX + maxX) / 2;
        int y = (minY + maxY) / 2;
        scanLineFill(x, y, auxBuffer.getRGB(x,y), fill, maxX, minX, maxY, minY, auxBuffer);
    }

    private void scanLineFill(int x, int y, int targetColor, Color fill,  int maxX, int minX, int maxY, int minY, BufferedImage auxBuffer) {
        if (fill.getRGB() == targetColor)
            return;
        if(x < 0 || y < 0) {
            return;
        }
        if(x > auxBuffer.getWidth() || y > auxBuffer.getHeight()) {
            return;
        }
        if(x < minX && x > maxX)
            return;
        if(y < minY && y > maxY)
            return;
        int pixelColor =  auxBuffer.getRGB(x,y);
        if(pixelColor == targetColor) {
            drawPixel(x, y, fill, auxBuffer);
            scanLineFill(x + 1, y, targetColor, fill, maxX, minX, maxY, minY, auxBuffer);
            scanLineFill(x - 1, y, targetColor, fill, maxX, minX, maxY, minY, auxBuffer);
            scanLineFill(x, y - 1, targetColor, fill, maxX, minX, maxY, minY, auxBuffer);
            scanLineFill(x, y + 1, targetColor, fill, maxX, minX, maxY, minY, auxBuffer);
        }
    }

    private void sortZIndex() {

    }
}

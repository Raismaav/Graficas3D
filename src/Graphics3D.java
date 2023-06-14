import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Graphics3D extends Graphics {
    private Graphics2D graphics;
    private BufferedImage bufferedFace;
    private double[] cameraPosition, visionVector;
    private Figures3D[] figures;
    private int[][] figuresZIndex;

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

    public void addFigure(Figures3D figure) {
        if (figures == null) {
            figures = new Figures3D[1];
            figuresZIndex = new int[2][1];
            figures[0] = figure;
            figuresZIndex[0][0] = figure.getZindex();
            figuresZIndex[1][0] = 0;
        } else {
            Figures3D[] newFigures = new  Figures3D[figures.length + 1];
            int[][] newZFiguresZIndex = new int[2][figures.length + 1];
            System.arraycopy(figures, 0, newFigures, 0, figures.length);
            newFigures[figures.length] = figure;
            System.arraycopy(figuresZIndex[0], 0, newZFiguresZIndex[0], 0, figuresZIndex[0].length);
            newZFiguresZIndex[0][figuresZIndex[0].length] = figure.getZindex();
            System.arraycopy(figuresZIndex[1], 0, newZFiguresZIndex[1], 0, figuresZIndex[1].length);
            newZFiguresZIndex[1][figuresZIndex[1].length] = figuresZIndex[1].length;
            figures = new Figures3D[newFigures.length];
            System.arraycopy(newFigures, 0, figures, 0, newFigures.length);
            figuresZIndex = new int[2][newZFiguresZIndex[0].length];
            System.arraycopy(newZFiguresZIndex[0], 0, figuresZIndex[0], 0, newZFiguresZIndex[0].length);
            System.arraycopy(newZFiguresZIndex[1], 0, figuresZIndex[1], 0, newZFiguresZIndex[1].length);
        }
    }

    public void obliqueProjection() {
        setFiguresZIndex();
        for (int figure = figures.length - 1; figure >= 0; figure--) {
            Face[] faces = figures[figuresZIndex[1][figure]].getFigure();
            int[] facesZIndex = figures[figuresZIndex[1][figure]].getFacesZIndex();
            for (int face = 0; face < faces.length; face++) {
                int[][] vertices = faces[facesZIndex[face]].getVertices();
                int[][] figureProjected = new int[vertices.length][vertices[0].length];
                for(int i = 0; i < vertices[0].length; i++) {
                    figureProjected[0][i] = (int) Math.round(vertices[0][i] + cameraPosition[0] * (-vertices[2][i] / cameraPosition[2]));
                    figureProjected[1][i] = (int) Math.round(vertices[1][i] + cameraPosition[1] * (-vertices[2][i] / cameraPosition[2]));
                }
                drawFigureProjected(faces, facesZIndex[face], figureProjected, false);
            }
        }
    }

    public void orthogonalProjection() {
        setFiguresZIndex();
        for (int figure = figures.length - 1; figure >= 0; figure--) {
            Face[] faces = figures[figuresZIndex[1][figure]].getFigure();
            int[] facesZIndex = figures[figuresZIndex[1][figure]].getFacesZIndex();
            for (int face = 0; face < faces.length; face++) {
                int[][] vertices = faces[facesZIndex[face]].getVertices();
                int[][] figureProjected = new int[vertices.length][vertices[0].length];
                for(int i = 0; i < vertices[0].length; i++) {
                }
                drawFigureProjected(faces,facesZIndex[face], figureProjected, false);
            }
        }
    }

    public void conicalProjection() {
        setFiguresZIndex();
        for (int figure = figures.length - 1; figure >= 0; figure--) {
            Face[] faces = figures[figuresZIndex[1][figure]].getFigure();
            int[] facesZIndex = figures[figuresZIndex[1][figure]].getFacesZIndex();
            for (int face = faces.length - 1; face >= 0; face--) {
                int[][] vertices = faces[facesZIndex[face]].getVertices();
                int[][] figureProjected = new int[vertices.length][vertices[0].length];
                for(int i = 0; i < vertices[0].length; i++) {
                    figureProjected[0][i] = (int) Math.round(cameraPosition[0] + (vertices[0][i] - cameraPosition[0]) * (-(cameraPosition[2] / (vertices[2][i] - cameraPosition[2]))));
                    figureProjected[1][i] = (int) Math.round(cameraPosition[1] + (vertices[1][i] - cameraPosition[1]) * (-(cameraPosition[2] / (vertices[2][i] - cameraPosition[2]))));
                }
                drawFigureProjected(faces,facesZIndex[face], figureProjected, false);
            }
        }
    }

    private void drawFigureProjected(Face[] faces, int face, int[][] figureProjected, boolean useBufferedFace) {
        if(useBufferedFace) {
            bufferedFace = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_ARGB);
            if (faces[face].isFaceFilled())
                fillFace(figureProjected, faces[face].getFill(), bufferedFace);
            drawLine(figureProjected[0][figureProjected[0].length - 1], figureProjected[1][figureProjected[0].length - 1], figureProjected[0][0], figureProjected[1][0], faces[face].getColor(), bufferedFace);
            for(int i = 1; i < figureProjected[0].length; i++) {
                drawLine(figureProjected[0][i - 1], figureProjected[1][i - 1], figureProjected[0][i], figureProjected[1][i], faces[face].getColor(), bufferedFace);
            }
            drawAuxbuffer(bufferedFace);
        } else {
            if (faces[face].isFaceFilled())
                fillFace(figureProjected, faces[face].getFill());
            drawLine(figureProjected[0][figureProjected[0].length - 1], figureProjected[1][figureProjected[0].length - 1], figureProjected[0][0], figureProjected[1][0], faces[face].getColor());
            for(int i = 1; i < figureProjected[0].length; i++) {
                drawLine(figureProjected[0][i - 1], figureProjected[1][i - 1], figureProjected[0][i], figureProjected[1][i], faces[face].getColor());
            }
        }
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

    private void fillFace(int[][] figureProjected, Color fill, BufferedImage auxBuffer) {
        graphics = (Graphics2D) auxBuffer.createGraphics();
        graphics.setColor(fill);
        graphics.fillPolygon(figureProjected[0], figureProjected[1], figureProjected[0].length);
    }

    private void fillFace(int[][] figureProjected, Color fill) {
        graphics = (Graphics2D) buffer.createGraphics();
        graphics.setColor(fill);
        graphics.fillPolygon(figureProjected[0], figureProjected[1], 4);
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

    private void setFiguresZIndex() {
        for (int figure = figures.length - 1; figure >= 0; figure--) {
            figuresZIndex[0][figure] = figures[figure].getZindex();
            figuresZIndex[1][figure] = figure;
        }
        MergeSort mergeSort = new MergeSort(figuresZIndex);
        mergeSort.sort(0, figuresZIndex[0].length - 1);
    }
}

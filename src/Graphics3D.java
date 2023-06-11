import javax.swing.*;
import java.awt.*;

public class Graphics3D extends Graphics {

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

    public void obliqueProjection(int[][] figure) {
        repaintBackground();
        int[][] figureProjected = new int[figure.length][figure[0].length];
        for(int i = 0; i < figure[0].length; i++) {
            figureProjected[0][i] = (int) Math.round(figure[0][i] + projectionPlane[0] * (-figure[2][i] / projectionPlane[2]));
            figureProjected[1][i] = (int) Math.round(figure[1][i] + projectionPlane[1] * (-figure[2][i] / projectionPlane[2]));
        }

        //A-B
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][1], figureProjected[1][1], Color.blue);
        //A-C
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][2], figureProjected[1][2], Color.blue);
        //A-E
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][4], figureProjected[1][4], Color.blue);
        //B-D
        drawLine(figureProjected[0][1], figureProjected[1][1], figureProjected[0][3], figureProjected[1][3], Color.blue);
        //B-F
        drawLine(figureProjected[0][1], figureProjected[1][1], figureProjected[0][5], figureProjected[1][5], Color.blue);
        //C-D
        drawLine(figureProjected[0][2], figureProjected[1][2], figureProjected[0][3], figureProjected[1][3], Color.blue);
        //C-G
        drawLine(figureProjected[0][2], figureProjected[1][2], figureProjected[0][6], figureProjected[1][6], Color.blue);
        //D-H
        drawLine(figureProjected[0][3], figureProjected[1][3], figureProjected[0][7], figureProjected[1][7], Color.blue);
        //E-F
        drawLine(figureProjected[0][4], figureProjected[1][4], figureProjected[0][5], figureProjected[1][5], Color.blue);
        //E-G
        drawLine(figureProjected[0][4], figureProjected[1][4], figureProjected[0][6], figureProjected[1][6], Color.blue);
        //F-H
        drawLine(figureProjected[0][5], figureProjected[1][5], figureProjected[0][7], figureProjected[1][7], Color.blue);
        //G-H
        drawLine(figureProjected[0][6], figureProjected[1][6], figureProjected[0][7], figureProjected[1][7], Color.blue);

        for(int i = 0; i < figureProjected[0].length; i++) {
            drawPoint(figureProjected[0][i], figureProjected[1][i], Color.red);
        }
    }

    public void obliqueProjection(Face[] faces) {
        repaintBackground();
        for (int face = 0; face < faces.length; face++) {
            if (backFaceCulling(faces[face].getPlainVector())) {
                int[][] figure = faces[face].getVertices();
                int[][] figureProjected = new int[figure.length][figure[0].length];
                for(int i = 0; i < figure[0].length; i++) {
                    figureProjected[0][i] = (int) Math.round(figure[0][i] + projectionPlane[0] * (-figure[2][i] / projectionPlane[2]));
                    figureProjected[1][i] = (int) Math.round(figure[1][i] + projectionPlane[1] * (-figure[2][i] / projectionPlane[2]));
                }
                drawLine(figureProjected[0][figureProjected[0].length - 1], figureProjected[1][figureProjected[0].length - 1], figureProjected[0][0], figureProjected[1][0], faces[face].getColor());
                for(int i = 1; i < figureProjected[0].length; i++) {
                    drawLine(figureProjected[0][i - 1], figureProjected[1][i - 1], figureProjected[0][i], figureProjected[1][i], faces[face].getColor());
                }
            }
        }
        update();
    }

    public void orthogonalProjection(int[][] figure) {
        repaintBackground();
        int[][] figureProjected = new int[figure.length][figure[0].length];
        for(int i = 0; i < figure[0].length; i++) {
            figureProjected[0][i] = figure[0][i];
            figureProjected[1][i] = figure[1][i];
        }

        //A-B
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][1], figureProjected[1][1], Color.blue);
        //A-C
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][2], figureProjected[1][2], Color.blue);
        //A-E
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][4], figureProjected[1][4], Color.blue);
        //B-D
        drawLine(figureProjected[0][1], figureProjected[1][1], figureProjected[0][3], figureProjected[1][3], Color.blue);
        //B-F
        drawLine(figureProjected[0][1], figureProjected[1][1], figureProjected[0][5], figureProjected[1][5], Color.blue);
        //C-D
        drawLine(figureProjected[0][2], figureProjected[1][2], figureProjected[0][3], figureProjected[1][3], Color.blue);
        //C-G
        drawLine(figureProjected[0][2], figureProjected[1][2], figureProjected[0][6], figureProjected[1][6], Color.blue);
        //D-H
        drawLine(figureProjected[0][3], figureProjected[1][3], figureProjected[0][7], figureProjected[1][7], Color.blue);
        //E-F
        drawLine(figureProjected[0][4], figureProjected[1][4], figureProjected[0][5], figureProjected[1][5], Color.blue);
        //E-G
        drawLine(figureProjected[0][4], figureProjected[1][4], figureProjected[0][6], figureProjected[1][6], Color.blue);
        //F-H
        drawLine(figureProjected[0][5], figureProjected[1][5], figureProjected[0][7], figureProjected[1][7], Color.blue);
        //G-H
        drawLine(figureProjected[0][6], figureProjected[1][6], figureProjected[0][7], figureProjected[1][7], Color.blue);

        for(int i = 0; i < figureProjected[0].length; i++) {
            drawPoint(figureProjected[0][i], figureProjected[1][i], Color.red);
        }
    }
    public void orthogonalProjection(Face[] faces) {
        repaintBackground();
        for (int face = 0; face < faces.length; face++) {
            if (backFaceCulling(faces[face].getPlainVector())) {
                faces[face].setColor(Color.black);
            } else {
                faces[face].setColor(Color.red);
            }
            int[][] figure = faces[face].getVertices();
            int[][] figureProjected = new int[figure.length][figure[0].length];
            for(int i = 0; i < figure[0].length; i++) {
                figureProjected[0][i] = figure[0][i];
                figureProjected[1][i] = figure[1][i];
            }
            drawLine(figureProjected[0][figureProjected[0].length - 1], figureProjected[1][figureProjected[0].length - 1], figureProjected[0][0], figureProjected[1][0], faces[face].getColor());
            for(int i = 1; i < figureProjected[0].length; i++) {
                drawLine(figureProjected[0][i - 1], figureProjected[1][i - 1], figureProjected[0][i], figureProjected[1][i], faces[face].getColor());
            }
        }
        update();
    }

    public void conicalProjection(int[][] figure) {
        repaintBackground();
        int[][] figureProjected = new int[figure.length][figure[0].length];
        for(int i = 0; i < figure[0].length; i++) {
            figureProjected[0][i] = (int) Math.round(cameraPosition[0] + (figure[0][i] - cameraPosition[0]) * (-(cameraPosition[2] / (figure[2][i] - cameraPosition[2]))));
            figureProjected[1][i] = (int) Math.round(cameraPosition[1] + (figure[1][i] - cameraPosition[1]) * (-(cameraPosition[2] / (figure[2][i] - cameraPosition[2]))));
        }

        //A-B
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][1], figureProjected[1][1], Color.blue);
        //A-C
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][2], figureProjected[1][2], Color.blue);
        //A-E
        drawLine(figureProjected[0][0], figureProjected[1][0], figureProjected[0][4], figureProjected[1][4], Color.blue);
        //B-D
        drawLine(figureProjected[0][1], figureProjected[1][1], figureProjected[0][3], figureProjected[1][3], Color.blue);
        //B-F
        drawLine(figureProjected[0][1], figureProjected[1][1], figureProjected[0][5], figureProjected[1][5], Color.blue);
        //C-D
        drawLine(figureProjected[0][2], figureProjected[1][2], figureProjected[0][3], figureProjected[1][3], Color.blue);
        //C-G
        drawLine(figureProjected[0][2], figureProjected[1][2], figureProjected[0][6], figureProjected[1][6], Color.blue);
        //D-H
        drawLine(figureProjected[0][3], figureProjected[1][3], figureProjected[0][7], figureProjected[1][7], Color.blue);
        //E-F
        drawLine(figureProjected[0][4], figureProjected[1][4], figureProjected[0][5], figureProjected[1][5], Color.blue);
        //E-G
        drawLine(figureProjected[0][4], figureProjected[1][4], figureProjected[0][6], figureProjected[1][6], Color.blue);
        //F-H
        drawLine(figureProjected[0][5], figureProjected[1][5], figureProjected[0][7], figureProjected[1][7], Color.blue);
        //G-H
        drawLine(figureProjected[0][6], figureProjected[1][6], figureProjected[0][7], figureProjected[1][7], Color.blue);

        for(int i = 0; i < figureProjected[0].length; i++) {
            drawPoint(figureProjected[0][i], figureProjected[1][i], Color.red);
        }
    }

    public void conicalProjection(Face[] faces) {
        repaintBackground();
        for (int face = 0; face < faces.length; face++) {
            if (backFaceCulling(faces[face].getPlainVector())) {
                faces[face].setColor(Color.black);
            } else {
                faces[face].setColor(Color.red);
            }
            int[][] figure = faces[face].getVertices();
            int[][] figureProjected = new int[figure.length][figure[0].length];
            for(int i = 0; i < figure[0].length; i++) {
                figureProjected[0][i] = (int) Math.round(cameraPosition[0] + (figure[0][i] - cameraPosition[0]) * (-(cameraPosition[2] / (figure[2][i] - cameraPosition[2]))));
                figureProjected[1][i] = (int) Math.round(cameraPosition[1] + (figure[1][i] - cameraPosition[1]) * (-(cameraPosition[2] / (figure[2][i] - cameraPosition[2]))));
            }
            drawLine(figureProjected[0][figureProjected[0].length - 1], figureProjected[1][figureProjected[0].length - 1], figureProjected[0][0], figureProjected[1][0], faces[face].getColor());
            for(int i = 1; i < figureProjected[0].length; i++) {
                drawLine(figureProjected[0][i - 1], figureProjected[1][i - 1], figureProjected[0][i], figureProjected[1][i], faces[face].getColor());
            }
        }
        update();
    }

    private boolean backFaceCulling(double[] plainVector) {
        double dotProduct = plainVector[0] * visionVector[0] + plainVector[1] * visionVector[1] + plainVector[2] * visionVector[2];
        double plainMagnitude = Math.sqrt(Math.pow(plainVector[0], 2) + Math.pow(plainVector[1], 2) + Math.pow(plainVector[2], 2));
        double visionMagnitude = Math.sqrt(Math.pow(visionVector[0], 2) + Math.pow(visionVector[1], 2) + Math.pow(visionVector[2], 2));
        double angle = Math.toDegrees(dotProduct / (plainMagnitude * visionMagnitude));
        if (angle > 0) {
            System.out.println(angle);
            return true;
        }
        else
            System.out.println(angle);
            return false;
    }
}

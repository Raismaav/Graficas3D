import javax.swing.*;
import java.awt.*;

public class Graphics3D extends Graphics {

    private double[] projectionPlane, cameraPosition;
    public Graphics3D(JPanel canvas, Color background) {
        super(canvas, background);
        cameraPosition = new double[3];
        cameraPosition[0] = canvas.getWidth() / 2;
        cameraPosition[1] = canvas.getHeight() / 2;
        cameraPosition[2] = 1000;
    }

    public Graphics3D(JPanel canvas) {
        super(canvas);
        cameraPosition = new double[3];
        cameraPosition[0] = canvas.getWidth() / 2;
        cameraPosition[1] = canvas.getHeight() / 2;
        cameraPosition[2] = 1000;
    }

    public double[] getProjectionPlane() {
        return projectionPlane;
    }

    public void setProjectionPlane(double[] projectionPlane) {
        this.projectionPlane = projectionPlane;
    }

    public void obliqueProjection(int[][] figure) {
        int[][] figureProjected = new int[figure.length][figure[0].length];
        for(int i = 0; i < figure[0].length; i++) {
            figureProjected[0][i] = (int) Math.round(figure[0][i] + projectionPlane[0] * (-figure[2][i] / projectionPlane[2]) + 250);
            figureProjected[1][i] = (int) Math.round(figure[1][i] + projectionPlane[1] * (-figure[2][i] / projectionPlane[2]) + 250);
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

    public void orthogonalProjection(int[][] figure) {
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

    public void conicalProjection(int[][] figure) {
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
}

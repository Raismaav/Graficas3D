import javax.swing.*;
import java.awt.*;

public class RubikCube extends Thread {
    private JPanel canvas;
    private Graphics3D graphics3D;

    private Figures3D core, centerW, centerY, centerR, centerO, centerG, centerB,
            aristWR, aristWO, aristWG, aristWB, aristRG, aristRB,
            aristOG, aristOB, aristYR, aristYO, aristYG, aristYB,
            cornerWRG, cornerWOG, cornerWRB, cornerWOB,
            cornerYRG, cornerYOG, cornerYRB, cornerYOB;

    private RubikFace white, yellow, red, orange, green, blue;

    public RubikCube(JPanel canvas) {
        this.canvas = canvas;
        graphics3D = new Graphics3D(canvas);
        setCube();
        setCubeFaces();
        setCubeFaces();
    }

    public void run() {
        green.firstSpin(-1);
        yellow.firstSpin(-1);
        red.firstSpin(-1);
        white.firstSpin(-1);
        orange.firstSpin(-1);
        blue.firstSpin(-1);
        while (true) {
            graphics3D.repaintBackground();
            graphics3D.conicalProjection();
            graphics3D.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void spinWhite() {
        white.spin(-1);
    }

    public void spinYellow() {
        yellow.spin(1);
    }

    public void spinRed() {
        red.spin(-1);
    }

    public void spinOrange() {
        orange.spin(1);
    }

    public void spinGreen() {
        green.spin(-1);
    }

    public void spinBlue() {
        blue.spin(1);
    }

    public void setXAxisRotationAngle(double angle) {
        core.setXAxisRotationAngle(angle, false);
    }

    public void setYAxisRotationAngle(double angle) {
        core.setYAxisRotationAngle(angle, false);
    }

    public void setZAxisRotationAngle(double angle) {
        core.setZAxisRotationAngle(angle, false);
    }

    private void setCube() {
        core = new Figures3D(graphics3D.getCanvas());
        core.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -400, 100);

        centerW = new Figures3D(graphics3D.getCanvas());
        centerW.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -500, 100);
        centerW.setPriorityAxis(Figures3D.Z_AXIS_PRIORITY);
        centerW.setAnchorFigure(core);
        centerW.setFillFace(Color.white, 0);

        centerY = new Figures3D(graphics3D.getCanvas());
        centerY.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -300, 100);
        centerY.setPriorityAxis(Figures3D.Z_AXIS_PRIORITY);
        centerY.setAnchorFigure(core);
        centerY.setFillFace(Color.yellow, 1);

        centerR = new Figures3D(graphics3D.getCanvas());
        centerR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -400, 100);
        centerR.setPriorityAxis(Figures3D.Y_AXIS_PRIORITY);
        centerR.setAnchorFigure(core);
        centerR.setFillFace(Color.red, 2);

        centerO = new Figures3D(graphics3D.getCanvas());
        centerO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -400, 100);
        centerO.setPriorityAxis(Figures3D.Y_AXIS_PRIORITY);
        centerO.setAnchorFigure(core);
        centerO.setFillFace(Color.orange, 3);

        centerG = new Figures3D(graphics3D.getCanvas());
        centerG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -400, 100);
        centerG.setPriorityAxis(Figures3D.X_AXIS_PRIORITY);
        centerG.setAnchorFigure(core);
        centerG.setFillFace(Color.green, 4);

        centerB = new Figures3D(graphics3D.getCanvas());
        centerB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -400, 100);
        centerB.setPriorityAxis(Figures3D.X_AXIS_PRIORITY);
        centerB.setAnchorFigure(core);
        centerB.setFillFace(Color.blue, 5);

        aristWR = new Figures3D(graphics3D.getCanvas());
        aristWR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -500, 100);
        aristWR.setFillFace(Color.white, 0);
        aristWR.setFillFace(Color.red, 2);

        aristWO = new Figures3D(graphics3D.getCanvas());
        aristWO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -500, 100);
        aristWO.setFillFace(Color.white, 0);
        aristWO.setFillFace(Color.orange, 3);

        aristWG = new Figures3D(graphics3D.getCanvas());
        aristWG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -500, 100);
        aristWG.setFillFace(Color.white, 0);
        aristWG.setFillFace(Color.green, 4);

        aristWB = new Figures3D(graphics3D.getCanvas());
        aristWB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -500, 100);
        aristWB.setFillFace(Color.white, 0);
        aristWB.setFillFace(Color.blue, 5);

        aristRG = new Figures3D(graphics3D.getCanvas());
        aristRG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) - 100, -400, 100);
        aristRG.setFillFace(Color.red, 2);
        aristRG.setFillFace(Color.green, 4);

        aristRB = new Figures3D(graphics3D.getCanvas());
        aristRB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - 100, -400, 100);
        aristRB.setFillFace(Color.red, 2);
        aristRB.setFillFace(Color.blue, 5);

        aristOG = new Figures3D(graphics3D.getCanvas());
        aristOG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) + 100, -400, 100);
        aristOG.setFillFace(Color.orange, 3);
        aristOG.setFillFace(Color.green, 4);

        aristOB = new Figures3D(graphics3D.getCanvas());
        aristOB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) + 100, -400, 100);
        aristOB.setFillFace(Color.orange, 3);
        aristOB.setFillFace(Color.blue, 5);

        aristYR = new Figures3D(graphics3D.getCanvas());
        aristYR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -300, 100);
        aristYR.setFillFace(Color.yellow, 1);
        aristYR.setFillFace(Color.red, 2);

        aristYO = new Figures3D(graphics3D.getCanvas());
        aristYO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -300, 100);
        aristYO.setFillFace(Color.yellow, 1);
        aristYO.setFillFace(Color.orange, 3);

        aristYG = new Figures3D(graphics3D.getCanvas());
        aristYG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -300, 100);
        aristYG.setFillFace(Color.yellow, 1);
        aristYG.setFillFace(Color.green, 4);

        aristYB = new Figures3D(graphics3D.getCanvas());
        aristYB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -300, 100);
        aristYB.setFillFace(Color.yellow, 1);
        aristYB.setFillFace(Color.blue, 5);

        cornerWRG = new Figures3D(graphics3D.getCanvas());
        cornerWRG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) - 100, -500, 100);
        cornerWRG.setFillFace(Color.white, 0);
        cornerWRG.setFillFace(Color.red, 2);
        cornerWRG.setFillFace(Color.green, 4);

        cornerWOG = new Figures3D(graphics3D.getCanvas());
        cornerWOG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) + 100, -500, 100);
        cornerWOG.setFillFace(Color.white, 0);
        cornerWOG.setFillFace(Color.orange, 3);
        cornerWOG.setFillFace(Color.green, 4);

        cornerWRB = new Figures3D(graphics3D.getCanvas());
        cornerWRB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - 100, -500, 100);
        cornerWRB.setFillFace(Color.white, 0);
        cornerWRB.setFillFace(Color.red, 2);
        cornerWRB.setFillFace(Color.blue, 5);

        cornerWOB = new Figures3D(graphics3D.getCanvas());
        cornerWOB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) + 100, -500, 100);
        cornerWOB.setFillFace(Color.white, 0);
        cornerWOB.setFillFace(Color.orange, 3);
        cornerWOB.setFillFace(Color.blue, 5);

        cornerYRG = new Figures3D(graphics3D.getCanvas());
        cornerYRG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) - 100, -300, 100);
        cornerYRG.setFillFace(Color.yellow, 1);
        cornerYRG.setFillFace(Color.red, 2);
        cornerYRG.setFillFace(Color.green, 4);

        cornerYOG = new Figures3D(graphics3D.getCanvas());
        cornerYOG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) + 100, -300, 100);
        cornerYOG.setFillFace(Color.yellow, 1);
        cornerYOG.setFillFace(Color.orange, 3);
        cornerYOG.setFillFace(Color.green, 4);

        cornerYRB = new Figures3D(graphics3D.getCanvas());
        cornerYRB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - 100, -300, 100);
        cornerYRB.setFillFace(Color.yellow, 1);
        cornerYRB.setFillFace(Color.red, 2);
        cornerYRB.setFillFace(Color.blue, 5);

        cornerYOB = new Figures3D(graphics3D.getCanvas());
        cornerYOB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) + 100, -300, 100);
        cornerYOB.setFillFace(Color.yellow, 1);
        cornerYOB.setFillFace(Color.orange, 3);
        cornerYOB.setFillFace(Color.blue, 5);

        graphics3D.addFigure(core);
        graphics3D.addFigure(centerW);
        graphics3D.addFigure(centerY);
        graphics3D.addFigure(centerR);
        graphics3D.addFigure(centerO);
        graphics3D.addFigure(centerG);
        graphics3D.addFigure(centerB);
        graphics3D.addFigure(aristWR);
        graphics3D.addFigure(aristWO);
        graphics3D.addFigure(aristWG);
        graphics3D.addFigure(aristWB);
        graphics3D.addFigure(aristRG);
        graphics3D.addFigure(aristRB);
        graphics3D.addFigure(aristOG);
        graphics3D.addFigure(aristOB);
        graphics3D.addFigure(aristYR);
        graphics3D.addFigure(aristYO);
        graphics3D.addFigure(aristYG);
        graphics3D.addFigure(aristYB);
        graphics3D.addFigure(cornerWRG);
        graphics3D.addFigure(cornerWOG);
        graphics3D.addFigure(cornerWRB);
        graphics3D.addFigure(cornerWOB);
        graphics3D.addFigure(cornerYRG);
        graphics3D.addFigure(cornerYOG);
        graphics3D.addFigure(cornerYRB);
        graphics3D.addFigure(cornerYOB);/**/
    }

    private void setCubeFaces() {
        yellow = new RubikFace(centerY);
        yellow.setUpNeighborFace(red, NeighborFace.DOWN, cornerYRG, aristYR, cornerYRB);
        yellow.setRightNeighborFace(blue, NeighborFace.LEFT, cornerYRB, aristYB, cornerYOB);
        yellow.setDownNeighborFace(orange, NeighborFace.UP, cornerYOG, aristYO, cornerYOB);
        yellow.setLeftNeighborFace(green, NeighborFace.RIGHT, cornerYRG, aristYG, cornerYOG);
        red = new RubikFace(centerR);
        red.setUpNeighborFace(white, NeighborFace.DOWN, cornerWRG, aristWR, cornerWRB);
        red.setRightNeighborFace(blue, NeighborFace.UP, cornerWRB, aristRB, cornerYRB);
        red.setDownNeighborFace(yellow, NeighborFace.UP, cornerYRG, aristYR, cornerYRB);
        red.setLeftNeighborFace(green, NeighborFace.UP, cornerWRG, aristRG, cornerYRG);
        white = new RubikFace(centerW);
        white.setUpNeighborFace(orange, NeighborFace.DOWN, cornerWOG, aristWO, cornerWOB);
        white.setRightNeighborFace(blue, NeighborFace.RIGHT, cornerWOB, aristWB, cornerWRB);
        white.setDownNeighborFace(red, NeighborFace.UP, cornerWRG, aristWR, cornerWRB);
        white.setLeftNeighborFace(green, NeighborFace.LEFT, cornerWOG, aristWG, cornerWRG);
        orange = new RubikFace(centerO);
        orange.setUpNeighborFace(yellow, NeighborFace.DOWN, cornerYOG, aristYO, cornerYOB);
        orange.setRightNeighborFace(blue, NeighborFace.DOWN, cornerYOB, aristOB, cornerWOB);
        orange.setDownNeighborFace(white, NeighborFace.UP, cornerWOG, aristWO, cornerWOB);
        orange.setLeftNeighborFace(green, NeighborFace.DOWN, cornerYOG, aristOG, cornerWOG);
        green = new RubikFace(centerG);
        green.setUpNeighborFace(red, NeighborFace.LEFT, cornerWRG, aristRG, cornerYRG);
        green.setRightNeighborFace(yellow, NeighborFace.LEFT, cornerYRG, aristYG, cornerYOG);
        green.setDownNeighborFace(orange, NeighborFace.LEFT, cornerWOG, aristOG, cornerYOG);
        green.setLeftNeighborFace(white, NeighborFace.LEFT, cornerWRG, aristWG, cornerWOG);
        blue = new RubikFace(centerB);
        blue.setUpNeighborFace(red, NeighborFace.RIGHT, cornerYRB, aristRB, cornerWRB);
        blue.setRightNeighborFace(white, NeighborFace.RIGHT, cornerWRB, aristWB, cornerWOB);
        blue.setDownNeighborFace(orange, NeighborFace.RIGHT, cornerYOB, aristOB, cornerWOB);
        blue.setLeftNeighborFace(yellow, NeighborFace.RIGHT, cornerYRB, aristYB, cornerYOB);
    }
}

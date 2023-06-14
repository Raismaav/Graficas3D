import javax.swing.*;
import java.awt.*;

public class RubikCube {
    private JPanel canvas;
    private Graphics3D graphics3D;

    private Figures3D core, centerW, centerY, centerR, centerO, centerG, centerB,
            aristWR, aristWO, aristWG, aristWB, aristRG, aristRB,
            aristOG, aristOB, aristYR, aristYO, aristYG, aristYB,
            cornerWRG, cornerWOG, cornerWRB, cornerWOB,
            cornerYRG, cornerYOG, cornerYRB, cornerYOB;

    public RubikCube(JPanel canvas) {
        this.canvas = canvas;
        graphics3D = new Graphics3D(canvas);
        //setCube();
        paint();
    }

    public void paint() {
        core = new Figures3D(graphics3D.getCanvas());
        core.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -400, 100);

        centerW = new Figures3D(graphics3D.getCanvas());
        centerW.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -500, 100);
        centerW.setFillFace(Color.white, 0);
        centerW.setAnchorFigure(core);

        centerR = new Figures3D(graphics3D.getCanvas());
        centerR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -400, 100);
        centerR.setFillFace(Color.red, 2);
        centerR.setAnchorFigure(core);

        graphics3D.addFigure(core);
        graphics3D.addFigure(centerW);
        graphics3D.addFigure(centerR);
        centerW.setPriorityAxis(Figures3D.Z_AXIS_PRIORITY);
        centerR.setPriorityAxis(Figures3D.Y_AXIS_PRIORITY);

        for (int i = 0; i < 1000; i++) {
            graphics3D.repaintBackground();
            graphics3D.conicalProjection();
            graphics3D.update();
            core.setXAxisRotationAngle(2, false);
            core.setYAxisRotationAngle(2, false);
            core.setYAxisRotationAngle(2, false);
            centerW.setZAxisRotationAngle(4, false);
            centerR.setYAxisRotationAngle(3, false);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setCube() {
        core = new Figures3D(graphics3D.getCanvas());
        core.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -400, 100);

        centerW = new Figures3D(graphics3D.getCanvas());
        centerW.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -500, 100);
        centerW.setFillFace(Color.white, 0);

        centerY = new Figures3D(graphics3D.getCanvas());
        centerY.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -300, 100);
        centerY.setFillFace(Color.yellow, 1);

        centerR = new Figures3D(graphics3D.getCanvas());
        centerR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -400, 100);
        centerR.setFillFace(Color.red, 2);

        centerO = new Figures3D(graphics3D.getCanvas());
        centerO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -400, 100);
        centerO.setFillFace(Color.orange, 3);

        centerG = new Figures3D(graphics3D.getCanvas());
        centerG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -400, 100);
        centerG.setFillFace(Color.green, 4);

        centerB = new Figures3D(graphics3D.getCanvas());
        centerB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -400, 100);
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
        graphics3D.addFigure(cornerYOB);
    }
}

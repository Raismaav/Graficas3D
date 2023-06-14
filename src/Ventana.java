import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame{
    private JPanel canvas;

    public Ventana() {
        setTitle("Graficos 3D");
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        canvas = new JPanel();
        canvas.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(canvas, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paint();
    }

    public void paint() {
        Graphics3D graphics3D = new Graphics3D(canvas);
        Figures3D core = new Figures3D(graphics3D.getCanvas());
        core.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -400, 100);

        Figures3D centerW = new Figures3D(graphics3D.getCanvas());
        centerW.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -500, 100);
        centerW.setFillFace(Color.white, 0);

        Figures3D centerY = new Figures3D(graphics3D.getCanvas());
        centerY.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -300, 100);
        centerY.setFillFace(Color.yellow, 1);

        Figures3D centerR = new Figures3D(graphics3D.getCanvas());
        centerR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -400, 100);
        centerR.setFillFace(Color.red, 2);

        Figures3D centerO = new Figures3D(graphics3D.getCanvas());
        centerO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -400, 100);
        centerO.setFillFace(Color.orange, 3);

        Figures3D centerG = new Figures3D(graphics3D.getCanvas());
        centerG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -400, 100);
        centerG.setFillFace(Color.green, 4);

        Figures3D centerB = new Figures3D(graphics3D.getCanvas());
        centerB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -400, 100);
        centerB.setFillFace(Color.blue, 5);

        Figures3D aristWR = new Figures3D(graphics3D.getCanvas());
        aristWR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -500, 100);
        aristWR.setFillFace(Color.white, 0);
        aristWR.setFillFace(Color.red, 2);

        Figures3D aristWO = new Figures3D(graphics3D.getCanvas());
        aristWO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -500, 100);
        aristWO.setFillFace(Color.white, 0);
        aristWO.setFillFace(Color.orange, 3);

        Figures3D aristWG = new Figures3D(graphics3D.getCanvas());
        aristWG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -500, 100);
        aristWG.setFillFace(Color.white, 0);
        aristWG.setFillFace(Color.green, 4);

        Figures3D aristWB = new Figures3D(graphics3D.getCanvas());
        aristWB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -500, 100);
        aristWB.setFillFace(Color.white, 0);
        aristWB.setFillFace(Color.blue, 5);

        Figures3D aristRG = new Figures3D(graphics3D.getCanvas());
        aristRG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) - 100, -400, 100);
        aristRG.setFillFace(Color.red, 2);
        aristRG.setFillFace(Color.green, 4);

        Figures3D aristRB = new Figures3D(graphics3D.getCanvas());
        aristRB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - 100, -400, 100);
        aristRB.setFillFace(Color.red, 2);
        aristRB.setFillFace(Color.blue, 5);

        Figures3D aristOG = new Figures3D(graphics3D.getCanvas());
        aristOG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) + 100, -400, 100);
        aristOG.setFillFace(Color.orange, 3);
        aristOG.setFillFace(Color.green, 4);

        Figures3D aristOB = new Figures3D(graphics3D.getCanvas());
        aristOB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) + 100, -400, 100);
        aristOB.setFillFace(Color.orange, 3);
        aristOB.setFillFace(Color.blue, 5);

        Figures3D aristYR = new Figures3D(graphics3D.getCanvas());
        aristYR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -300, 100);
        aristYR.setFillFace(Color.yellow, 1);
        aristYR.setFillFace(Color.red, 2);

        Figures3D aristYO = new Figures3D(graphics3D.getCanvas());
        aristYO.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 100, -300, 100);
        aristYO.setFillFace(Color.yellow, 1);
        aristYO.setFillFace(Color.orange, 3);

        Figures3D aristYG = new Figures3D(graphics3D.getCanvas());
        aristYG.createCube((canvas.getWidth() / 2) - 100, canvas.getHeight() / 2, -300, 100);
        aristYG.setFillFace(Color.yellow, 1);
        aristYG.setFillFace(Color.green, 4);

        Figures3D aristYB = new Figures3D(graphics3D.getCanvas());
        aristYB.createCube((canvas.getWidth() / 2) + 100, canvas.getHeight() / 2, -300, 100);
        aristYB.setFillFace(Color.yellow, 1);
        aristYB.setFillFace(Color.blue, 5);

        Figures3D cornerWRG = new Figures3D(graphics3D.getCanvas());
        cornerWRG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) - 100, -500, 100);
        cornerWRG.setFillFace(Color.white, 0);
        cornerWRG.setFillFace(Color.red, 2);
        cornerWRG.setFillFace(Color.green, 4);

        Figures3D cornerWOG = new Figures3D(graphics3D.getCanvas());
        cornerWOG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) + 100, -500, 100);
        cornerWOG.setFillFace(Color.white, 0);
        cornerWOG.setFillFace(Color.orange, 3);
        cornerWOG.setFillFace(Color.green, 4);

        Figures3D cornerWRB = new Figures3D(graphics3D.getCanvas());
        cornerWRB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - 100, -500, 100);
        cornerWRB.setFillFace(Color.white, 0);
        cornerWRB.setFillFace(Color.red, 2);
        cornerWRB.setFillFace(Color.blue, 5);

        Figures3D cornerWOB = new Figures3D(graphics3D.getCanvas());
        cornerWOB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) + 100, -500, 100);
        cornerWOB.setFillFace(Color.white, 0);
        cornerWOB.setFillFace(Color.orange, 3);
        cornerWOB.setFillFace(Color.blue, 5);

        Figures3D cornerYRG = new Figures3D(graphics3D.getCanvas());
        cornerYRG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) - 100, -300, 100);
        cornerYRG.setFillFace(Color.yellow, 1);
        cornerYRG.setFillFace(Color.red, 2);
        cornerYRG.setFillFace(Color.green, 4);

        Figures3D cornerYOG = new Figures3D(graphics3D.getCanvas());
        cornerYOG.createCube((canvas.getWidth() / 2) - 100, (canvas.getHeight() / 2) + 100, -300, 100);
        cornerYOG.setFillFace(Color.yellow, 1);
        cornerYOG.setFillFace(Color.orange, 3);
        cornerYOG.setFillFace(Color.green, 4);

        Figures3D cornerYRB = new Figures3D(graphics3D.getCanvas());
        cornerYRB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) - 100, -300, 100);
        cornerYRB.setFillFace(Color.yellow, 1);
        cornerYRB.setFillFace(Color.red, 2);
        cornerYRB.setFillFace(Color.blue, 5);

        Figures3D cornerYOB = new Figures3D(graphics3D.getCanvas());
        cornerYOB.createCube((canvas.getWidth() / 2) + 100, (canvas.getHeight() / 2) + 100, -300, 100);
        cornerYOB.setFillFace(Color.yellow, 1);
        cornerYOB.setFillFace(Color.orange, 3);
        cornerYOB.setFillFace(Color.blue, 5);

        core.addFigure(centerW);
        core.addFigure(centerY);
        core.addFigure(centerR);
        core.addFigure(centerO);
        core.addFigure(centerG);
        core.addFigure(centerB);
        core.addFigure(aristWR);
        core.addFigure(aristWO);
        core.addFigure(aristWG);
        core.addFigure(aristWB);
        core.addFigure(aristRG);
        core.addFigure(aristRB);
        core.addFigure(aristOG);
        core.addFigure(aristOB);
        core.addFigure(aristYR);
        core.addFigure(aristYO);
        core.addFigure(aristYG);
        core.addFigure(aristYB);
        core.addFigure(cornerWRG);
        core.addFigure(cornerWOG);
        core.addFigure(cornerWRB);
        core.addFigure(cornerWOB);
        core.addFigure(cornerYRG);
        core.addFigure(cornerYOG);
        core.addFigure(cornerYRB);
        core.addFigure(cornerYOB);
        graphics3D.addFigure(core);
        for (int i = 0; i < 1000; i++) {
            graphics3D.repaintBackground();
            graphics3D.conicalProjection();
            graphics3D.update();
            core.setXAxisRotationAngle(3);
            core.setYAxisRotationAngle(2);
            core.setZAxisRotationAngle(4);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

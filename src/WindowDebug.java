import javax.swing.*;
import java.awt.*;

public class WindowDebug extends JFrame {

    private JPanel canvas;
    private Graphics3D graphics3D;
    private Figures3D core, centerW, centerY, centerR, aristWR;

    public static void main(String[] args) {
        new WindowDebug();
    }

    public WindowDebug() {
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
        graphics3D = new Graphics3D(canvas);
        setCube();
        run();
    }

    public void run() {
        try {
            centerW.setAnchorFigure(core);
            centerY.setAnchorFigure(core);
            centerR.setAnchorFigure(core);
            aristWR.setAnchorFigure(centerR);
            for (int i = 0; i < 90; i++) {
                graphics3D.repaintBackground();
                graphics3D.conicalProjection();
                graphics3D.update();
                core.setXAxisRotationAngle(2, false);
                centerR.setYAxisRotationAngle(2, false);
                Thread.sleep(10);
            }
            aristWR.setAnchorFigure(centerY);
            for (int i = 0; i < 9000; i++) {
                graphics3D.repaintBackground();
                graphics3D.conicalProjection();
                graphics3D.update();
                core.setXAxisRotationAngle(2, false);
                centerY.setZAxisRotationAngle(2, false);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

        aristWR = new Figures3D(graphics3D.getCanvas());
        aristWR.createCube(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 100, -500, 100);
        aristWR.setFillFace(Color.white, 0);
        aristWR.setFillFace(Color.red, 2);

        graphics3D.addFigure(core);
        graphics3D.addFigure(centerW);
        graphics3D.addFigure(centerY);
        graphics3D.addFigure(centerR);
        graphics3D.addFigure(aristWR);/**/
    }
}

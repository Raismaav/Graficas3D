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
        Figures3D figura1 = new Figures3D(graphics3D.getCanvas());
        Figures3D figura2 = new Figures3D(graphics3D.getCanvas());
        Figures3D figura3 = new Figures3D(graphics3D.getCanvas());
        Figures3D figura4 = new Figures3D(graphics3D.getCanvas());
        figura1.createPrism(200, 200, -100, 100, 20, 56);
        figura1.setFillFace(Color.white, 0);
        figura1.setFillFace(Color.yellow, 1);
        figura1.setFillFace(Color.red, 2);
        figura1.setFillFace(Color.orange, 3);
        figura1.setFillFace(Color.green, 4);
        figura1.setFillFace(Color.blue, 5);
        figura1.setFigureFilled(true);
        figura2.createPlane(250, 200, -50, 24);
        figura3.createCube(100, 100, -400, 9);
        figura3.setXAxisRotationAngle(45);
        figura3.setYAxisRotationAngle(45);
        figura3.setZAxisRotationAngle(45);
        figura4.createCube(canvas.getWidth() / 2, canvas.getHeight() / 2, -400, 500);
        figura4.setFillFace(Color.white, 0);
        figura4.setFillFace(Color.yellow, 1);
        figura4.setFillFace(Color.red, 2);
        figura4.setFillFace(Color.orange, 3);
        figura4.setFillFace(Color.green, 4);
        figura4.setFillFace(Color.blue, 5);
        figura1.addFigure(figura2.getFigure());
        figura1.addFigure(figura3.getFigure());
        for (int i = 0; i < 1000; i++) {
            graphics3D.repaintBackground();
            graphics3D.conicalProjection(figura4.getFigure(), figura4.getFacesZIndex());
            graphics3D.conicalProjection(figura1.getFigure(), figura1.getFacesZIndex());
            graphics3D.update();
            figura1.setTranslations(-1, 0, 1);
            figura1.setXAxisRotationAngle(0);
            figura1.setYAxisRotationAngle(0);
            figura1.setZAxisRotationAngle(0);
            figura4.setXAxisRotationAngle(4);
            figura4.setYAxisRotationAngle(10);
            figura4.setZAxisRotationAngle(6);
            //figura1.setTranslations(1,0,-10);
            //figura1.setScale(1.01);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

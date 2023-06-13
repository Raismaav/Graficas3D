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
        figura1.createPrism(200, 200, -300, 100, 20, 56);
        figura1.setFillFace(Color.white, 0);
        figura1.setFillFace(Color.yellow, 1);
        figura1.setFillFace(Color.red, 2);
        figura1.setFillFace(Color.orange, 3);
        figura1.setFillFace(Color.green, 4);
        figura1.setFillFace(Color.blue, 5);
        figura1.setFigureFilled(true);
        for (int i = 0; i < 1000; i++) {
            graphics3D.conicalProjection(figura1.getFigure(), figura1.getFacesZIndex());
            figura1.setTranslations(2, 0, 0);
            figura1.setXAxisRotationAngle(2);
            figura1.setYAxisRotationAngle(3);
            figura1.setZAxisRotationAngle(4);
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

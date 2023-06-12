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
        Figures3D figura1 = new Figures3D();
        figura1.createCube(200, 200, -100, 100);
        figura1.setFigureFilled(true);
        Graphics3D graphics3D = new Graphics3D(canvas);
        for (int i = 0; i < 1000; i++) {
            graphics3D.conicalProjection(figura1.getFigure());
            figura1.setXAxisRotationAngle(2);
            figura1.setYAxisRotationAngle(0);
            figura1.setZAxisRotationAngle(0);
            //figura1.setTranslations(1,0,-10);
            //figura1.setScale(1.01);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

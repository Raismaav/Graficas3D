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
        double [] projectionPlane = new double[] {10, 10, 50};
        Figures3D figures = new Figures3D();
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.setProjectionPlane(projectionPlane);
        graphics.conicalProjection(figures.getCuboPrueba());
        graphics.conicalProjection(figures.getCuboPrueba2());
    }
}

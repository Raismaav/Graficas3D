import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame{
    private JPanel canvas;
    private RubikCube cube;

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
        cube = new RubikCube(canvas);
    }
}

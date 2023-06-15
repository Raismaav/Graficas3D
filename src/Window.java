import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame  implements KeyListener {
    private JPanel canvas;
    private RubikCube cube;
    private boolean isSpinned = false;

    public Window() {
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
        cube.start();
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //cube.setXAxisRotationAngle(-2);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //cube.setXAxisRotationAngle(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            cube.setYAxisRotationAngle(-2);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            cube.setYAxisRotationAngle(2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (!isSpinned) {
                isSpinned = true;
                cube.spinWhite();
                isSpinned = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            if (!isSpinned) {
                isSpinned = true;
                cube.spinYellow();
                isSpinned = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (!isSpinned) {
                isSpinned = true;
                cube.spinRed();
                isSpinned = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            if (!isSpinned) {
                isSpinned = true;
                cube.spinOrange();
                isSpinned = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            if (!isSpinned) {
                isSpinned = true;
                cube.spinGreen();
                isSpinned = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            if (!isSpinned) {
                isSpinned = true;
                cube.spinBlue();
                isSpinned = false;
            }
        }
    }
}

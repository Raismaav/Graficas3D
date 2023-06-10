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
        //practica1Obliuca();
        //practica2Ortogonal();
        //practica3UnPuntoDeFuga();
        //practica4DosPuntosDeFuga();
        //practica5TresPuntosDeFuga();
        //practica6PuntosDeFuga();
        practica7Rotacion();
        //practica8Traslacion();
        //practica9Escalacion();
        //practica10Relleno();
    }

    public void paint() {
        Figures3D figures = new Figures3D();
        Figures3D pruebas = new Figures3D(figures.getCuboPrueba());
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.conicalProjection(pruebas.getFigure());
        graphics.conicalProjection(figures.getCuboPrueba2());
    }

    public void practica1Obliuca() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        figures.setTranslations(100, -100, 0);
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.obliqueProjection(figures.getFigure());
    }

    public void practica2Ortogonal() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        figures.setTranslations(150, 200, 0);
        figures.setXAxisRotationAngle(45);
        figures.setYAxisRotationAngle(45);
        figures.setZAxisRotationAngle(45);
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.orthogonalProjection(figures.getFigure());
    }

    public void practica3UnPuntoDeFuga() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        figures.setTranslations(200, 200, 0);
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.conicalProjection(figures.getFigure());
    }

    public void practica4DosPuntosDeFuga() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        figures.setTranslations(200, 200, 0);
        figures.setYAxisRotationAngle(45);
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.conicalProjection(figures.getFigure());
    }

    public void practica5TresPuntosDeFuga() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        figures.setTranslations(200, 200, 0);
        figures.setXAxisRotationAngle(25);
        figures.setYAxisRotationAngle(45);
        figures.setZAxisRotationAngle(60);
        Graphics3D graphics = new Graphics3D(canvas);
        graphics.conicalProjection(figures.getFigure());
    }

    public void practica6PuntosDeFuga() {
        Figures3D figures1 = new Figures3D(Figures3D.getCuboPrueba());
        figures1.setTranslations(20, 20, 0);


        Figures3D figures2 = new Figures3D(Figures3D.getCuboPrueba());
        figures2.setTranslations(280, 300, 0);
        figures2.setYAxisRotationAngle(45);

        Figures3D figures3 = new Figures3D(Figures3D.getCuboPrueba());
        figures3.setTranslations(400, 150, 0);
        figures3.setXAxisRotationAngle(25);
        figures3.setYAxisRotationAngle(45);
        figures3.setZAxisRotationAngle(60);

        Graphics3D graphics = new Graphics3D(canvas);
        graphics.conicalProjection(figures1.getFigure());
        graphics.conicalProjection(figures2.getFigure());
        graphics.conicalProjection(figures3.getFigure());
    }

    public void practica7Rotacion() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        Graphics3D graphics = new Graphics3D(canvas);
        figures.setTranslations(200, 200, 0);
        for (int i = 0; i < 100; i++) {
            graphics.conicalProjection(figures.getFigure());
            figures.setXAxisRotationAngle(2);
            figures.setYAxisRotationAngle(2);
            figures.setZAxisRotationAngle(2);
            figures.setTranslations(1,0,-10);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void practica8Traslacion() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        Graphics3D graphics = new Graphics3D(canvas);
        for (int i = 0; i < 100; i++) {
            graphics.conicalProjection(figures.getFigure());
            figures.setTranslations(10,10,0);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void practica9Escalacion() {
        Figures3D figures = new Figures3D(Figures3D.getCuboPrueba());
        Graphics3D graphics = new Graphics3D(canvas);
        for (int i = 0; i < 100; i++) {
            graphics.conicalProjection(figures.getFigure());
            figures.setScale(1.01);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void practica10Relleno() {

    }
}

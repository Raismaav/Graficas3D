public class Figures3D {
    private int[][] figure;

    public Figures3D() {

    }

    public Figures3D(int[][] figure) {
        this.figure = figure;
    }

    public int[][] getFigure() {return figure;}

    public void setFigure(int[][] figure) {this.figure = figure;}

    public int[][] getCuboPrueba() {
        int[][] cuboImaginario = {
                {200, 0, 200, 0, 200, 0, 200, 0},
                {0, 0, 200, 200, 0, 0, 200, 200},
                {0, 0, 0, 0, -200, -200, -200, -200}};
        return cuboImaginario;
    }

    public int[][] getCuboPrueba2() {
        int[][] cuboImaginario = {
                {200, 100, 200, 100, 200, 100, 200, 100},
                {100, 100, 200, 200, 100, 100, 200, 200},
                {0, 0, 0, 0, -200, -200, -200, -200}};
        return cuboImaginario;
    }
}

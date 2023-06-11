import java.awt.*;
import java.util.Arrays;

public class Face {
    private int[][] vertices, originalvertices;

    private double[] plainVector;
    private Color color = Color.black;
    private Color fill = Color.blue;

    public Face(int points) {
        vertices = new int[4][points];
        originalvertices = new int[4][points];
        for (int i = 0; i < originalvertices[3].length; i++) {
            originalvertices[3][i] = 1;
            vertices[3][i] = 1;
        }
    }

    public int[][] getVertices() {
        int[][] returnVertices = new int[vertices.length][vertices[0].length];
        for (int j = 0; j < returnVertices.length; j++) {
            returnVertices[j] = Arrays.copyOf(vertices[j], returnVertices[j].length);
        }
        return returnVertices;
    }

    public void setVertices(int[][] vertices) {
        for (int j = 0; j < this.vertices.length; j++) {
            this.vertices[j] = Arrays.copyOf(vertices[j], this.vertices[j].length);
        }
        setPlainVector();
    }

    public int[][] getOriginalVertices() {
        int[][] returnVertices = new int[originalvertices.length][originalvertices[0].length];
        for (int j = 0; j < returnVertices.length; j++) {
            returnVertices[j] = Arrays.copyOf(originalvertices[j], returnVertices[j].length);
        }
        return returnVertices;
    }

    public void setOriginalVertices(int[][] vertices) {
        for (int j = 0; j < this.originalvertices.length; j++) {
            this.originalvertices[j] = Arrays.copyOf(vertices[j], this.originalvertices[j].length);
            this.vertices[j] = Arrays.copyOf(vertices[j], this.vertices[j].length);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public void setPoints(int point, int x, int y, int z) {
        originalvertices[0][point] = x;
        originalvertices[1][point] = y;
        originalvertices[2][point] = z;
        vertices[0][point] = x;
        vertices[1][point] = y;
        vertices[2][point] = z;
    }
    public int getPoints() {
        return originalvertices[0].length;
    }
    public double[] getPlainVector() {
        return Arrays.copyOf(plainVector, plainVector.length);
    }

    private void setPlainVector() {
        plainVector = new double[3];
        double[] vectorA = {
                vertices[0][0] - vertices[0][1],
                vertices[1][0] - vertices[1][1],
                vertices[2][0] - vertices[2][1]};
        double[] vectorB = {
                vertices[0][1] - vertices[0][2],
                vertices[1][1] - vertices[1][2],
                vertices[2][1] - vertices[2][2]};

        plainVector[0] = vectorA[1] * vectorB[2] - vectorA[2] * vectorB[1];
        plainVector[1] = vectorA[2] * vectorB[0] - vectorA[0] * vectorB[2];
        plainVector[2] = vectorA[0] * vectorB[1] - vectorA[1] * vectorB[0];
    }
}

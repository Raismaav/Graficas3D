import java.util.Arrays;

public class Figures3D {

    private int dx = 0, dy = 0, dz = 0;
    private double scale = 1;
    private int[][] figure, originalFigure;
    private double[] rotaionAngles = {0, 0, 0};

    public Figures3D() {

    }

    public Figures3D(int[][] figure) {
        this.figure = new int[figure.length][figure[0].length];
        this.originalFigure = new int[figure.length][figure[0].length];
        for (int j = 0; j < this.originalFigure.length; j++) {
            this.originalFigure[j] = Arrays.copyOf(figure[j], this.originalFigure[j].length);
        }
    }

    public int[][] getFigure() {
        int[][] returnFigure = new int[this.figure.length][figure[0].length];
        for (int j = 0; j < this.figure.length; j++) {
            this.figure[j] = Arrays.copyOf(this.originalFigure[j], this.figure[j].length);
        }
        xAxisRotation();
        yAxisRotation();
        zAxisRotation();
        translation();
        escalation();
        for (int j = 0; j < returnFigure.length; j++) {
            returnFigure[j] = Arrays.copyOf(this.figure[j], returnFigure[j].length);
        }
        return returnFigure;
    }

    public int[][] getOriginalFigure() {
        int[][] returnFigure = new int[this.originalFigure.length][this.originalFigure[0].length];
        for (int j = 0; j < returnFigure.length; j++) {
            returnFigure[j] = Arrays.copyOf(this.originalFigure[j], returnFigure[j].length);
        }
        return returnFigure;
    }

    public void setFigure(int[][] figure) {
        for (int j = 0; j < this.originalFigure.length; j++) {
            this.originalFigure[j] = Arrays.copyOf(figure[j], this.originalFigure[j].length);
        }
    }

    public void setXAxisRotationAngle(double angle) {
        this.rotaionAngles[0] += Math.toRadians(angle);
    }

    public void setYAxisRotationAngle(double angle) {
        this.rotaionAngles[1] +=  Math.toRadians(angle);
    }

    public void setZAxisRotationAngle(double angle) {
        this.rotaionAngles[2] +=  Math.toRadians(angle);
    }

    public void setTranslations(int dx, int dy, int dz) {
        this.dx += dx;
        this.dy += dy;
        this.dz += dz;
    }

    public void setScale(double scale) {
        this.scale *= scale;
    }

    public static int[][] getCuboPrueba() {
        int[][] cuboImaginario = {
                {200, 0, 200, 0, 200, 0, 200, 0},
                {0, 0, 200, 200, 0, 0, 200, 200},
                {0, 0, 0, 0, -200, -200, -200, -200},
                {1, 1, 1, 1, 1, 1, 1, 1}};
        return cuboImaginario;
    }

    public static int[][] getCuboPrueba2() {
        int[][] cuboImaginario = {
                {200, 100, 200, 100, 200, 100, 200, 100},
                {100, 100, 200, 200, 100, 100, 200, 200},
                {0, 0, 0, 0, -200, -200, -200, -200},
                {1, 1, 1, 1, 1, 1, 1, 1}};
        return cuboImaginario;
    }

    private void xAxisRotation() {
        int maxY = figure[1][0];
        int minY = figure[1][0];
        int maxZ = figure[2][0];
        int minZ = figure[2][0];

        for (int i = 0; i < figure[0].length; i++) {
            if (maxY < figure[1][i]) {
                maxY = figure[1][i];
            }
            if (minY > figure[1][i]) {
                minY = figure[1][i];
            }
            if (maxZ < figure[2][i]) {
                maxZ = figure[2][i];
            }
            if (minZ > figure[2][i]) {
                minZ = figure[2][i];
            }
        }

        for (int i = 0; i < figure[0].length; i++) {
            figure[1][i] -= (maxY + minY) / 2;
            figure[2][i] -= (maxZ + minZ) / 2;
        }

        double[][] identityMatrixX = {
                {1, 0, 0, 0},
                {0, Math.cos(rotaionAngles[0]), -Math.sin(rotaionAngles[0]), 0},
                {0, Math.sin(rotaionAngles[0]), Math.cos(rotaionAngles[0]), 0},
                {0, 0, 0, 1}};
        int[][] finalMatrix = new int[identityMatrixX.length][figure[0].length];

        for (int i = 0; i < identityMatrixX.length; i++) {
            for (int j = 0; j < figure[0].length; j++) {
                for (int k = 0; k < identityMatrixX[0].length; k++) {
                    finalMatrix[i][j] += Math.round(identityMatrixX[i][k] * figure[k][j]);
                }
            }
        }

        for (int j = 0; j < figure.length; j++) {
            figure[j] = Arrays.copyOf(finalMatrix[j], figure[j].length);
        }


        for (int i = 0; i < figure[0].length; i++) {
            figure[1][i] += (maxY + minY) / 2;
            figure[2][i] += (maxZ + minZ) / 2;
        }
    }

    private void yAxisRotation() {
        int maxX = figure[0][0];
        int minX = figure[0][0];
        int maxZ = figure[2][0];
        int minZ = figure[2][0];

        for (int i = 0; i < figure[0].length; i++) {
            if (maxX < figure[0][i]) {
                maxX = figure[0][i];
            }
            if (minX > figure[0][i]) {
                minX = figure[0][i];
            }
            if (maxZ < figure[2][i]) {
                maxZ = figure[2][i];
            }
            if (minZ > figure[2][i]) {
                minZ = figure[2][i];
            }
        }

        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] -= (maxX + minX) / 2;
            figure[2][i] -= (maxZ + minZ) / 2;
        }

        double[][] identityMatrixY = {
                {Math.cos(rotaionAngles[1]), 0, Math.sin(rotaionAngles[1]), 0},
                {0, 1, 0, 0},
                {-Math.sin(rotaionAngles[1]), 0, Math.cos(rotaionAngles[1]), 0, 0},
                {0, 0, 0, 1}};
        int[][] finalMatrix = new int[identityMatrixY.length][figure[0].length];

        for (int i = 0; i < identityMatrixY.length; i++) {
            for (int j = 0; j < figure[0].length; j++) {
                for (int k = 0; k < identityMatrixY[0].length; k++) {
                    finalMatrix[i][j] += Math.round(identityMatrixY[i][k] * figure[k][j]);
                }
            }
        }

        for (int j = 0; j < figure.length; j++) {
            figure[j] = Arrays.copyOf(finalMatrix[j], figure[j].length);
        }

        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] += (maxX + minX) / 2;
            figure[2][i] += (maxZ + minZ) / 2;
        }
    }

    private void zAxisRotation() {
        int maxX = figure[0][0];
        int minX = figure[0][0];
        int maxY = figure[1][0];
        int minY = figure[1][0];

        for (int i = 0; i < figure[0].length; i++) {
            if (maxX < figure[0][i]) {
                maxX = figure[0][i];
            }
            if (minX > figure[0][i]) {
                minX = figure[0][i];
            }
            if (maxY < figure[1][i]) {
                maxY = figure[1][i];
            }
            if (minY > figure[1][i]) {
                minY = figure[1][i];
            }
        }

        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] -= (maxX + minX) / 2;
            figure[1][i] -= (maxY + minY) / 2;
        }

        double[][] identityMatrixZ = {
                {Math.cos(rotaionAngles[2]), -Math.sin(rotaionAngles[2]), 0, 0},
                {Math.sin(rotaionAngles[2]), Math.cos(rotaionAngles[2]), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};
        int[][] finalMatrix = new int[identityMatrixZ.length][figure[0].length];

        for (int i = 0; i < identityMatrixZ.length; i++) {
            for (int j = 0; j < figure[0].length; j++) {
                for (int k = 0; k < identityMatrixZ[0].length; k++) {
                    finalMatrix[i][j] += Math.round(identityMatrixZ[i][k] * figure[k][j]);
                }
            }
        }

        for (int j = 0; j < figure.length; j++) {
            figure[j] = Arrays.copyOf(finalMatrix[j], figure[j].length);
        }

        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] += (maxX + minX) / 2;
            figure[1][i] += (maxY + minY) / 2;
        }
    }

    private void translation() {
        double[][] identityMatrix = {
                {1, 0, 0, dx},
                {0, 1, 0, dy},
                {0, 0, 1, dz},
                {0, 0, 0, 1}};
        int[][] finalMatrix = new int[identityMatrix.length][figure[0].length];

        for (int i = 0; i < identityMatrix.length; i++) {
            for (int j = 0; j < figure[0].length; j++) {
                for (int k = 0; k < identityMatrix[0].length; k++) {
                    finalMatrix[i][j] += Math.round(identityMatrix[i][k] * figure[k][j]);
                }
            }
        }

        for (int j = 0; j < figure.length; j++) {
            figure[j] = Arrays.copyOf(finalMatrix[j], figure[j].length);
        }
    }

    private void escalation() {
        double[][] identityMatrix = {
                {scale, 0, 0, 0},
                {0, scale, 0, 0},
                {0, 0, scale, 0},
                {0, 0, 0, 1}};
        int[][] finalMatrix = new int[identityMatrix.length][figure[0].length];

        for (int i = 0; i < identityMatrix.length; i++) {
            for (int j = 0; j < figure[0].length; j++) {
                for (int k = 0; k < identityMatrix[0].length; k++) {
                    finalMatrix[i][j] += Math.round(identityMatrix[i][k] * figure[k][j]);
                }
            }
        }

        for (int j = 0; j < figure.length; j++) {
            figure[j] = Arrays.copyOf(finalMatrix[j], figure[j].length);
        }
    }
}

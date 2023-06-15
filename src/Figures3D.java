import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Figures3D {
    public static final int X_AXIS_PRIORITY = 0, Y_AXIS_PRIORITY = 1, Z_AXIS_PRIORITY = 2;
    private int dx = 0, dy = 0, dz = 0, zindex = 0, priorityAxis = 0, priorityTranslationAxis = 0;
    private double scale = 1;
    private int[][] figure, originalFigure, facesZIndex;
    private Face[] faces;
    private int[] cameraPoint = {0,0,1000}, centerFigure = new int[3], centerAxialTranslation = new int[3];
    private double[] rotaionAngles = {0, 0, 0}, axialTranslationAngles = {0, 0, 0};
    private boolean figureFilled = true, anchored = false, anchoredInAnchorFigure = false;

    public Figures3D(JPanel canvas) {
        cameraPoint[0] = canvas.getWidth() / 2;
        cameraPoint[1] = canvas.getHeight() / 2;
    }

    public Face[] getFigure() {
        MergeSort mergeSort = new MergeSort(facesZIndex);
        for (int j = 0; j < this.figure.length; j++) {
            this.figure[j] = Arrays.copyOf(this.originalFigure[j], this.figure[j].length);
        }
        if (!anchoredInAnchorFigure)
            setCenterFigure();
        rotaion();
        axialTranslation();
        translation();
        escalation();
        copyToVertices();
        mergeSort.sort(0, facesZIndex[0].length - 1);
        zindex = facesZIndex[0][0];
        return Arrays.copyOf(faces, faces.length);
    }

    public int getPriorityAxis() {
        return priorityAxis;
    }

    public void setPriorityAxis(int priorityAxis) {
        this.priorityAxis = priorityAxis;
    }

    public void setXAxisRotationAngle(double angle, boolean isGlobal) {
        if (isGlobal)
            this.rotaionAngles[0] = Math.toRadians(angle);
        else
            this.rotaionAngles[0] += Math.toRadians(angle);
    }

    public void setYAxisRotationAngle(double angle, boolean isGlobal) {
        if (isGlobal)
            this.rotaionAngles[1] = Math.toRadians(angle);
        else
            this.rotaionAngles[1] += Math.toRadians(angle);
    }

    public void setZAxisRotationAngle(double angle, boolean isGlobal) {
        if (isGlobal)
            this.rotaionAngles[2] = Math.toRadians(angle);
        else
            this.rotaionAngles[2] += Math.toRadians(angle);
    }

    public void setTranslations(int dx, int dy, int dz) {
        this.dx += dx;
        this.dy += dy;
        this.dz += dz;
    }

    public void setScale(double scale) {
        this.scale *= scale;
    }

    public boolean isFigureFilled() {
        return figureFilled;
    }

    public void setFigureFilled(boolean figureFilled) {
        this.figureFilled = figureFilled;
        for (int face = 0; face < faces.length; face++) {
            faces[face].setFaceFilled(figureFilled);
        }
    }

    public int[] getFacesZIndex() {
        return Arrays.copyOf(facesZIndex[1], facesZIndex[1].length);
    }

    public int getZindex() {
        return zindex;
    }

    public void setColorsFace(Color color, Color fill, int faceIndex) {
        faces[faceIndex].setColor(color);
        faces[faceIndex].setFill(fill);
    }

    public void setColorsFace(Color color, int faceIndex) {
        faces[faceIndex].setColor(color);
        faces[faceIndex].setFill(color);
    }

    public void setColorFace(Color color, int faceIndex) {
        faces[faceIndex].setColor(color);
    }

    public void setFillFace(Color fill, int faceIndex) {
        faces[faceIndex].setFill(fill);
    }

    public String printZIndex() {
        String zIndexString = "";
        String indexString = "";
        for (int i = 0; i < facesZIndex[0].length; ++i) {
            zIndexString = zIndexString + facesZIndex[0][i] + " ";
            indexString = indexString + facesZIndex[1][i] + " ";
        }
        return zIndexString + "\n" + indexString;
    }

    public int[] getCenterFigure() {
        return centerFigure;
    }

    public int[] getCenterAxialTranslation() {
        return centerAxialTranslation;
    }

    public double[] getRotaionAngles() {
        return rotaionAngles;
    }
    public double[] getAxialTranslationAngles () {
        return axialTranslationAngles;
    }

    public void setAnchorFigure(Figures3D anchorFigure) {
        if (anchorFigure.isAnchored()) {
            this.centerAxialTranslation = anchorFigure.getCenterAxialTranslation();
            this.axialTranslationAngles = anchorFigure.getAxialTranslationAngles();
            this.centerFigure = anchorFigure.getCenterFigure();
            this.rotaionAngles = anchorFigure.getRotaionAngles();
            anchoredInAnchorFigure = true;
        }
        else {
            this.centerAxialTranslation = anchorFigure.getCenterFigure();
            this.axialTranslationAngles = anchorFigure.getRotaionAngles();
            anchored = true;
        }
        this.priorityTranslationAxis = anchorFigure.getPriorityAxis();
    }

    public void setCenterFigure() {
        int maxX = figure[0][0];
        int minX = figure[0][0];
        int maxY = figure[1][0];
        int minY = figure[1][0];
        int maxZ = figure[2][0];
        int minZ = figure[2][0];

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
            if (maxZ < figure[2][i]) {
                maxZ = figure[2][i];
            }
            if (minZ > figure[2][i]) {
                minZ = figure[2][i];
            }
        }
        centerFigure[0] = (minX + maxX) / 2;
        centerFigure[1] = (maxY + minY) / 2;
        centerFigure[2] = (maxZ + minZ) / 2;
    }

    public void createPlane(int x, int y, int z, int large) {
        int points = 4;
        int numFaces = 1;
        facesZIndex = new int[2][numFaces];
        faces = new Face[numFaces];

        faces[0] = new Face(points, cameraPoint);
        faces[0].setPoints(0, x - (large / 2), y - (large / 2), z);
        faces[0].setPoints(1, x + (large / 2), y - (large / 2), z);
        faces[0].setPoints(2, x + (large / 2), y + (large / 2), z);
        faces[0].setPoints(3, x - (large / 2), y + (large / 2), z);
        facesZIndex[0][0] = faces[0].getZIndex();
        facesZIndex[1][0] = 0;

        copyToOriginalFigure();
    }

    public void createCube(int x, int y, int z, int large) {
        int points = 4;
        int numFaces = 6;
        facesZIndex = new int[2][numFaces];
        faces = new Face[numFaces];

        faces[0] = new Face(points, cameraPoint);
        faces[0].setPoints(0, x - (large / 2), y - (large / 2), z - (large / 2));
        faces[0].setPoints(1, x + (large / 2), y - (large / 2), z - (large / 2));
        faces[0].setPoints(2, x + (large / 2), y + (large / 2), z - (large / 2));
        faces[0].setPoints(3, x - (large / 2), y + (large / 2), z - (large / 2));

        faces[1] = new Face(points, cameraPoint);
        faces[1].setPoints(1, x - (large / 2), y - (large / 2), z + (large / 2));
        faces[1].setPoints(0, x + (large / 2), y - (large / 2), z + (large / 2));
        faces[1].setPoints(3, x + (large / 2), y + (large / 2), z + (large / 2));
        faces[1].setPoints(2, x - (large / 2), y + (large / 2), z + (large / 2));

        faces[2] = new Face(points, cameraPoint);
        faces[2].setPoints(0, x - (large / 2), y - (large / 2), z - (large / 2));
        faces[2].setPoints(1, x + (large / 2), y - (large / 2), z - (large / 2));
        faces[2].setPoints(2, x + (large / 2), y - (large / 2), z + (large / 2));
        faces[2].setPoints(3, x - (large / 2), y - (large / 2), z + (large / 2));

        faces[3] = new Face(points, cameraPoint);
        faces[3].setPoints(1, x - (large / 2), y + (large / 2), z - (large / 2));
        faces[3].setPoints(0, x + (large / 2), y + (large / 2), z - (large / 2));
        faces[3].setPoints(3, x + (large / 2), y + (large / 2), z + (large / 2));
        faces[3].setPoints(2, x - (large / 2), y + (large / 2), z + (large / 2));

        faces[4] = new Face(points, cameraPoint);
        faces[4].setPoints(0, x - (large / 2), y - (large / 2), z - (large / 2));
        faces[4].setPoints(1, x - (large / 2), y - (large / 2), z + (large / 2));
        faces[4].setPoints(2, x - (large / 2), y + (large / 2), z + (large / 2));
        faces[4].setPoints(3, x - (large / 2), y + (large / 2), z - (large / 2));

        faces[5] = new Face(points, cameraPoint);
        faces[5].setPoints(0, x + (large / 2), y - (large / 2), z - (large / 2));
        faces[5].setPoints(1, x + (large / 2), y - (large / 2), z + (large / 2));
        faces[5].setPoints(2, x + (large / 2), y + (large / 2), z + (large / 2));
        faces[5].setPoints(3, x + (large / 2), y + (large / 2), z - (large / 2));/**/

        for (int face = 0; face < faces.length; face++) {
            facesZIndex[0][face] = faces[face].getZIndex();
            facesZIndex[1][face] = face;
        }
        copyToOriginalFigure();
    }

    public void createPrism(int x, int y, int z, int width, int height, int depth) {
        int points = 4;
        int numFaces = 6;
        facesZIndex = new int[2][numFaces];
        faces = new Face[numFaces];

        faces[0] = new Face(points, cameraPoint);
        faces[0].setPoints(0, x - (width / 2), y - (height / 2), z - (depth / 2));
        faces[0].setPoints(1, x + (width / 2), y - (height / 2), z - (depth / 2));
        faces[0].setPoints(2, x + (width / 2), y + (height / 2), z - (depth / 2));
        faces[0].setPoints(3, x - (width / 2), y + (height / 2), z - (depth / 2));

        faces[1] = new Face(points, cameraPoint);
        faces[1].setPoints(1, x - (width / 2), y - (height / 2), z + (depth / 2));
        faces[1].setPoints(0, x + (width / 2), y - (height / 2), z + (depth / 2));
        faces[1].setPoints(3, x + (width / 2), y + (height / 2), z + (depth / 2));
        faces[1].setPoints(2, x - (width / 2), y + (height / 2), z + (depth / 2));

        faces[2] = new Face(points, cameraPoint);
        faces[2].setPoints(0, x - (width / 2), y - (height / 2), z - (depth / 2));
        faces[2].setPoints(1, x + (width / 2), y - (height / 2), z - (depth / 2));
        faces[2].setPoints(2, x + (width / 2), y - (height / 2), z + (depth / 2));
        faces[2].setPoints(3, x - (width / 2), y - (height / 2), z + (depth / 2));

        faces[3] = new Face(points, cameraPoint);
        faces[3].setPoints(1, x - (width / 2), y + (height / 2), z - (depth / 2));
        faces[3].setPoints(0, x + (width / 2), y + (height / 2), z - (depth / 2));
        faces[3].setPoints(3, x + (width / 2), y + (height / 2), z + (depth / 2));
        faces[3].setPoints(2, x - (width / 2), y + (height / 2), z + (depth / 2));

        faces[4] = new Face(points, cameraPoint);
        faces[4].setPoints(0, x - (width / 2), y - (height / 2), z - (depth / 2));
        faces[4].setPoints(1, x - (width / 2), y - (height / 2), z + (depth / 2));
        faces[4].setPoints(2, x - (width / 2), y + (height / 2), z + (depth / 2));
        faces[4].setPoints(3, x - (width / 2), y + (height / 2), z - (depth / 2));

        faces[5] = new Face(points, cameraPoint);
        faces[5].setPoints(0, x + (width / 2), y - (height / 2), z - (depth / 2));
        faces[5].setPoints(1, x + (width / 2), y - (height / 2), z + (depth / 2));
        faces[5].setPoints(2, x + (width / 2), y + (height / 2), z + (depth / 2));
        faces[5].setPoints(3, x + (width / 2), y + (height / 2), z - (depth / 2));/**/

        for (int face = 0; face < faces.length; face++) {
            facesZIndex[0][face] = faces[face].getZIndex();
            facesZIndex[1][face] = face;
        }
        copyToOriginalFigure();
    }

    public void addFigure(Figures3D figure) {
        Face[] newFaces = figure.getFigure();
        Face[] allFaces = new Face[newFaces.length + faces.length];
        facesZIndex = new int[2][allFaces.length];
        System.arraycopy(faces, 0, allFaces, 0, faces.length);
        System.arraycopy(newFaces, 0, allFaces, faces.length, newFaces.length);
        faces =  new Face[allFaces.length];
        System.arraycopy(allFaces, 0, faces, 0, allFaces.length);
        for (int face = 0; face < faces.length; face++) {
            facesZIndex[0][face] = faces[face].getZIndex();
            facesZIndex[1][face] = face;
        }
        copyToOriginalFigure();
    }

    public boolean isAnchored() {
        return anchored;
    }

    private void rotaion() {
        switch (priorityAxis) {
            case 0:
                xAxisRotation();
                yAxisRotation();
                zAxisRotation();
                break;

            case 1:
                yAxisRotation();
                xAxisRotation();
                zAxisRotation();
                break;

            case 2:
                zAxisRotation();
                xAxisRotation();
                yAxisRotation();
                break;
        }
    }

    private void axialTranslation() {
        switch (priorityTranslationAxis) {
            case 0:
                xAxisTranslation();
                yAxisTranslation();
                zAxisTranslation();
                break;

            case 1:
                yAxisTranslation();
                xAxisTranslation();
                zAxisTranslation();
                break;

            case 2:
                zAxisTranslation();
                xAxisTranslation();
                yAxisTranslation();
                break;
        }
    }

    private void xAxisRotation() {
        for (int i = 0; i < figure[0].length; i++) {
            figure[1][i] -= centerFigure[1];
            figure[2][i] -= centerFigure[2];
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
            figure[1][i] += centerFigure[1];
            figure[2][i] += centerFigure[2];
        }
    }

    private void yAxisRotation() {
        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] -= centerFigure[0];
            figure[2][i] -= centerFigure[2];
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
            figure[0][i] += centerFigure[0];
            figure[2][i] += centerFigure[2];
        }
    }

    private void zAxisRotation() {
        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] -= centerFigure[0];
            figure[1][i] -= centerFigure[1];
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
            figure[0][i] += centerFigure[0];
            figure[1][i] += centerFigure[1];
        }
    }

    private void xAxisTranslation() {
        for (int i = 0; i < figure[0].length; i++) {
            figure[1][i] -= centerAxialTranslation[1];
            figure[2][i] -= centerAxialTranslation[2];
        }

        double[][] identityMatrixX = {
                {1, 0, 0, 0},
                {0, Math.cos(axialTranslationAngles[0]), -Math.sin(axialTranslationAngles[0]), 0},
                {0, Math.sin(axialTranslationAngles[0]), Math.cos(axialTranslationAngles[0]), 0},
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
            figure[1][i] += centerAxialTranslation[1];
            figure[2][i] += centerAxialTranslation[2];
        }
    }

    private void yAxisTranslation() {
        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] -= centerAxialTranslation[0];
            figure[2][i] -= centerAxialTranslation[2];
        }

        double[][] identityMatrixY = {
                {Math.cos(axialTranslationAngles[1]), 0, Math.sin(axialTranslationAngles[1]), 0},
                {0, 1, 0, 0},
                {-Math.sin(axialTranslationAngles[1]), 0, Math.cos(axialTranslationAngles[1]), 0, 0},
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
            figure[0][i] += centerAxialTranslation[0];
            figure[2][i] += centerAxialTranslation[2];
        }
    }

    private void zAxisTranslation() {
        for (int i = 0; i < figure[0].length; i++) {
            figure[0][i] -= centerAxialTranslation[0];
            figure[1][i] -= centerAxialTranslation[1];
        }

        double[][] identityMatrixZ = {
                {Math.cos(axialTranslationAngles[2]), -Math.sin(axialTranslationAngles[2]), 0, 0},
                {Math.sin(axialTranslationAngles[2]), Math.cos(axialTranslationAngles[2]), 0, 0},
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
            figure[0][i] += centerAxialTranslation[0];
            figure[1][i] += centerAxialTranslation[1];
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

    private void copyToOriginalFigure() {
        int figuresLenght = 0;
        int i = 0;
        for (int face = 0; face < faces.length; face++) {
            figuresLenght += faces[face].getPoints();
        }
        figure = new int[4][figuresLenght];
        originalFigure = new int[4][figuresLenght];
        for (int face = 0; face < faces.length; face++) {
            int[][] vertices = faces[face].getVertices();
            System.arraycopy(vertices[0], 0, originalFigure[0], i, vertices[0].length);
            System.arraycopy(vertices[1], 0, originalFigure[1], i, vertices[1].length);
            System.arraycopy(vertices[2], 0, originalFigure[2], i, vertices[2].length);
            System.arraycopy(vertices[3], 0, originalFigure[3], i, vertices[3].length);
            i += vertices[0].length;
        }
    }

    private void copyToVertices() {
        int i = 0;
        for (int face = 0; face < faces.length; face++) {
            int[][] vertices = new int[4][faces[face].getPoints()];
            System.arraycopy(figure[0], i, vertices[0], 0, vertices[0].length);
            System.arraycopy(figure[1], i, vertices[1], 0, vertices[1].length);
            System.arraycopy(figure[2], i, vertices[2], 0, vertices[2].length);
            System.arraycopy(figure[3], i, vertices[3], 0, vertices[3].length);
            faces[face].setVertices(vertices);
            facesZIndex[0][face] = faces[face].getZIndex();
            facesZIndex[1][face] = face;
            i += faces[0].getPoints();
        }
    }
}
public class RubikFace {
    private Figures3D center;
    private Figures3D[][] sharedFigures = new Figures3D[4][3];
    private NeighborFace[] neighborFaces = new NeighborFace[4];

    public RubikFace(Figures3D center) {
        this.center = center;
    }

    public Figures3D getCenter() {
        return center;
    }

    public void setCenter(Figures3D center) {
        this.center = center;
    }

    public void setUpNeighborFace(RubikFace upFace, int index, Figures3D cornerLeft, Figures3D arist, Figures3D cornerRight) {
        sharedFigures[0][0] = cornerLeft;
        sharedFigures[0][1] = arist;
        sharedFigures[0][2] = cornerRight;
        this.neighborFaces[0] = new NeighborFace(upFace, index);
    }

    public void setRightNeighborFace(RubikFace rightFace, int index, Figures3D cornerUp, Figures3D arist, Figures3D cornerDown) {
        sharedFigures[1][0] = cornerUp;
        sharedFigures[1][1] = arist;
        sharedFigures[1][2] = cornerDown;
        this.neighborFaces[1] = new NeighborFace(rightFace, index);
    }

    public void setDownNeighborFace(RubikFace downFace, int index, Figures3D cornerLeft, Figures3D arist, Figures3D cornerRight) {
        sharedFigures[2][0] = cornerRight;
        sharedFigures[2][1] = arist;
        sharedFigures[2][2] = cornerLeft;
        this.neighborFaces[2] = new NeighborFace(downFace, index);
    }

    public void setLeftNeighborFace(RubikFace leftFace, int index, Figures3D cornerUp, Figures3D arist, Figures3D cornerDown) {
        sharedFigures[3][0] = cornerDown;
        sharedFigures[3][1] = arist;
        sharedFigures[3][2] = cornerUp;
        this.neighborFaces[3] = new NeighborFace(leftFace, index);
    }

    public void setSharedFigures(Figures3D[] sharedFigures, int index) {
        this.sharedFigures[index] = sharedFigures;
    }

    public void spin(double angle) {
        for (int j = 0; j < sharedFigures.length; j++) {
            for (int i = 0; i < sharedFigures[0].length; i++) {
                sharedFigures[j][i].setAnchorFigure(center);
            }
        }
        try {
            for (int i = 0; i < 90; i++) {
                switch (center.getPriorityAxis()) {
                    case 0:
                        center.setXAxisRotationAngle(angle, false);
                        break;

                    case 1:
                        center.setYAxisRotationAngle(angle, false);
                        break;

                    case 2:
                        center.setZAxisRotationAngle(angle, false);
                        break;
                }
                Thread.sleep(10);
            }
            changeFigures();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeFigures() {
        Figures3D[] bufferSharedFigures = sharedFigures[sharedFigures.length - 1];
        for (int i = sharedFigures.length - 1; i >= 1; i--) {
            sharedFigures[i] = sharedFigures[i - 1];
            neighborFaces[i].getFace().setSharedFigures(sharedFigures[i], neighborFaces[i].getIndexNeighborFace());
        }
        sharedFigures[0] = bufferSharedFigures;
        neighborFaces[0].getFace().setSharedFigures(sharedFigures[0], neighborFaces[0].getIndexNeighborFace());
    }
}

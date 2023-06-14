public class NeighborFace {
    public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    private RubikFace face;
    private int indexNeighborFace;

    public NeighborFace(RubikFace face, int indexNeighborFace) {
        this.face = face;
        this.indexNeighborFace = indexNeighborFace;
    }

    public RubikFace getFace() {
        return face;
    }

    public void setFace(RubikFace face) {
        this.face = face;
    }

    public int getIndexNeighborFace() {
        return indexNeighborFace;
    }

    public void setIndexNeighborFace(int indexNeighborFace) {
        this.indexNeighborFace = indexNeighborFace;
    }
}

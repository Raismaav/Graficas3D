public class MergeSort {
    private int facesZIndex[][];

    public MergeSort(int[][] facesZIndex) {
        this.facesZIndex = facesZIndex;
    }

    public void sort(int izquierda, int derecha) {
        if (izquierda < derecha) {
            int centro = (izquierda + derecha) / 2;
            sort(izquierda, centro);
            sort(centro + 1, derecha);
            merge(izquierda, centro, derecha);
        }
    }

    private void merge(int izquierda, int centro, int derecha) {
        int indexL = 0;
        int indexR = 0;
        int index = izquierda;
        int lengthL = centro - izquierda + 1;
        int lengthR = derecha - centro;
        int facesZIndexL[][] = new int[2][lengthL];
        int facesZIndexR[][] = new int[2][lengthR];

        for (int i = 0; i < lengthL; i++) {
            facesZIndexL[0][i] = facesZIndex[0][izquierda + i];
            facesZIndexL[1][i] = facesZIndex[1][izquierda + i];
        }

        for (int i = 0; i < lengthR; i++) {
            facesZIndexR[0][i] = facesZIndex[0][centro + 1 + i];
            facesZIndexR[1][i] = facesZIndex[1][centro + 1 + i];
        }

        while (indexL < lengthL && indexR < lengthR) {
            if (facesZIndexL[0][indexL] <= facesZIndexR[0][indexR]) {
                facesZIndex[0][index] = facesZIndexL[0][indexL];
                facesZIndex[1][index] = facesZIndexL[1][indexL];
                indexL++;
            } else {
                facesZIndex[0][index] = facesZIndexR[0][indexR];
                facesZIndex[1][index] = facesZIndexR[1][indexR];
                indexR++;
            }
            index++;
        }

        while (indexL < lengthL) {
            facesZIndex[0][index] = facesZIndexL[0][indexL];
            facesZIndex[1][index] = facesZIndexL[1][indexL];
            indexL++;
            index++;
        }

        while (indexR < lengthR) {
            facesZIndex[0][index] = facesZIndexR[0][indexR];
            facesZIndex[1][index] = facesZIndexR[1][indexR];
            indexR++;
            index++;
        }
    }

    public String imprimir() {
        String zIndexString = "";
        String indexString = "";
        for (int i = 0; i < facesZIndex[0].length; ++i) {
            zIndexString = zIndexString + facesZIndex[0][i] + " ";
            indexString = indexString + facesZIndex[1][i] + " ";
        }
        return zIndexString + "\n" + indexString;
    }
}

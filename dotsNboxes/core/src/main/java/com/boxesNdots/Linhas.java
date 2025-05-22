package com.boxesNdots;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Linhas {
    private final float[][] posX;
    private final float[][] posY;
    private final int tamanho;

    public Linhas(float[][] posX, float[][] posY, int tamanho) {
        this.posX = posX;
        this.posY = posY;
        this.tamanho = tamanho;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 0.5f);

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                float x1 = posX[i][j];
                float y1 = posY[i][j];

                if (j < tamanho - 1) {
                    float x2 = posX[i][j + 1];
                    float y2 = posY[i][j + 1];
                    shapeRenderer.rectLine(x1, y1, x2, y2, 3);
                }

                if (i < tamanho - 1) {
                    float x2 = posX[i + 1][j];
                    float y2 = posY[i + 1][j];
                    shapeRenderer.rectLine(x1, y1, x2, y2, 3);
                }
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public float[][] getPosX() {
        return posX;
    }

    public float[][] getPosY() {
        return posY;
    }
}

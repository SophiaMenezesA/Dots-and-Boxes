package com.boxesNdots;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bolinhas {
    private static final int tabuleiro = 6;
    private static final float espacamento = 100;
    private static final float tamanhoBolinha = 10;

    private float[][] posicoesX;
    private float[][] posicoesY;

    public Bolinhas() {
        posicoesX = new float[tabuleiro][tabuleiro];
        posicoesY = new float[tabuleiro][tabuleiro];

        float offsetX = (800 - (tabuleiro - 1) * espacamento) / 2;
        float offsetY = (650 - (tabuleiro - 1) * espacamento) / 2;

        for (int i = 0; i < tabuleiro; i++) {
            for (int j = 0; j  < tabuleiro; j ++) {
                posicoesX[i][j] = offsetX + j * espacamento;
                posicoesY[i][j] = offsetY + i * espacamento;
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.85f, 0.5f, 1f, 0.5f);
        for (int i = 0; i < posicoesX.length; i++) {
            for (int j = 0; j < posicoesX[i].length; j++) {
                shapeRenderer.circle(posicoesX[i][j], posicoesY[i][j], tamanhoBolinha);
            }
        }
    }

    public float[][] getPosicoesX(){
        return posicoesX;
    }

    public float[][] getPosicoesY(){
        return posicoesY;
    }

    public int getTamanho(){
        return posicoesX.length;
    }
}

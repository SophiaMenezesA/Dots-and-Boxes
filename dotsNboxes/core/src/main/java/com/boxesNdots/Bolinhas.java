package com.boxesNdots;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bolinhas {
    private static final int tabuleiro = 6;
    private static final float espacamento = 110;
    private static final float tamanhoBolinha = 10;

    private float[][] posicoes;

    public Bolinhas() {
        posicoes = new float[tabuleiro][tabuleiro];

        float offsetX = (820 - (tabuleiro - 1) * espacamento) / 2;
        float offsetY = (820 - (tabuleiro - 1) * espacamento) / 2;

        for (int row = 0; row < tabuleiro; row++) {
            for (int col = 0; col < tabuleiro; col++) {
                float x = offsetX + col * espacamento;
                float y = offsetY + row * espacamento;
                posicoes[row][col] = x;
                posicoes[col][row] = y;
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.85f, 0.5f, 1f, 0.5f);
        for (int row = 0; row < tabuleiro; row++) {
            for (int col = 0; col < tabuleiro; col++) {
                float x = posicoes[row][col];
                float y = posicoes[col][row];
                shapeRenderer.circle(x, y, tamanhoBolinha);
            }
        }
    }
}

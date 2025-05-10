package com.boxesNdots;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class BoxesAndDots extends ApplicationAdapter {
    private SpriteBatch batch;
    private static final int tabuleiro = 6; // tamanho da matriz
    private static final float tamanhoBolinha = 10; // tamanho da bolinha
    private static final float espacamento = 110; // espaçamento 

    private float[][] posicaoBolinhas; 
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        posicaoBolinhas = new float[tabuleiro][tabuleiro];

        float offsetX = (820 - (tabuleiro - 1) * espacamento) / 2;
        float offsetY = (820 - (tabuleiro - 1) * espacamento) / 2;

        for (int row = 0; row < tabuleiro; row++){
            for (int col = 0; col < tabuleiro; col++){
                posicaoBolinhas[row][col] = offsetX + col * espacamento;
                posicaoBolinhas[row][col] = offsetY + row * espacamento; 
            }
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(1f, 0.85f, 0.9f, 1f); // cor de fundo

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.85f, 0.5f, 1f, 0.5f); // cor das bolinhas

        for (int row = 0; row < tabuleiro; row++){ // desenhas as bolinhas na tla
            for (int col = 0; col < tabuleiro; col++){
                float x = posicaoBolinhas[row][col];
                float y = posicaoBolinhas[col][row];
                shapeRenderer.circle(x, y, tamanhoBolinha);

            }
        }

        shapeRenderer.end(); 
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
    }
}

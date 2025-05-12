package com.boxesNdots;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class BoxesAndDots extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Bolinhas bolinhas;
    private Linhas linhas;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        bolinhas = new Bolinhas();
        linhas = new Linhas(bolinhas.getPosicoesX(), bolinhas.getPosicoesY(), bolinhas.getTamanho());
    }

    @Override
    public void render() {
        ScreenUtils.clear(1f, 0.85f, 0.9f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        linhas.render(shapeRenderer);
        bolinhas.render(shapeRenderer);
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

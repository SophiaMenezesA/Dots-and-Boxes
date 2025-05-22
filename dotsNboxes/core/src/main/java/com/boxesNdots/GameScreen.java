package com.boxesNdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Music musicaJogo;
    private BoxesAndDots game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private Bolinhas bolinhas;
    private Linhas linhas;
    private Cliques cliques;
    private BitmapFont fonte;

    public GameScreen(BoxesAndDots game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        fonte = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        bolinhas = new Bolinhas();
        linhas = new Linhas(bolinhas.getPosicoesX(), bolinhas.getPosicoesY(), bolinhas.getTamanho());
        GerenciaVezDoJogador gerenciaVez = new GerenciaVezDoJogador(2);
        cliques = new Cliques(linhas.getPosX(), linhas.getPosY(), linhas.getTamanho(), gerenciaVez,0,0);
        musicaJogo = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        musicaJogo.play();
        musicaJogo.setVolume(0.5f);
        musicaJogo.setLooping(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1f, 0.85f, 0.9f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        linhas.render(shapeRenderer);
        cliques.render(shapeRenderer);
        bolinhas.render(shapeRenderer);
        shapeRenderer.end();
        batch.begin();
       
        fonte.draw(batch, "Jogador 1: " + cliques.getPontosJogador1(), 50, 600);

        fonte.draw(batch, "Jogador 2: " + cliques.getPontosJogador2(), 650,600);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        if (musicaJogo != null) {
            musicaJogo.stop();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        musicaJogo.dispose();
        fonte.dispose();
    }
    
}

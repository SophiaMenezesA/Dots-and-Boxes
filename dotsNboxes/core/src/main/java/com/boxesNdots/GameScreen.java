package com.boxesNdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Music musicaJogo;
    private BoxesAndDots game;
    private SpriteBatch batch;
    private BitmapFont fonte;
    private ShapeRenderer shapeRenderer;

    private Bolinhas bolinhas;
    private Linhas linhas;
    private Cliques cliques;
    private GerenciaVezDoJogador gerenciaVez;

    public GameScreen(BoxesAndDots game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        fonte = new BitmapFont();
        fonte.getData().setScale(1.5f);
        shapeRenderer = new ShapeRenderer();
        bolinhas = new Bolinhas();
        linhas = new Linhas(bolinhas.getPosicoesX(), bolinhas.getPosicoesY(), bolinhas.getTamanho());
        gerenciaVez = new GerenciaVezDoJogador(2);
        cliques = new Cliques(linhas.getPosX(), linhas.getPosY(), linhas.getTamanho(), gerenciaVez);
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
        verificarFimDoJogo();
        batch.begin();
        int pontosJogador1 = gerenciaVez.getPontuacao(1);
        int pontosJogador2 = gerenciaVez.getPontuacao(2);
        fonte.draw(batch, "Jogador 1: " + pontosJogador1, 40, 630);
        fonte.draw(batch, "Jogador 2: " + pontosJogador2, 640, 630);
        batch.end();
    }

    private void verificarFimDoJogo() {
        if (cliques.estaCompleto()) {  
            int pontosJogador1 = gerenciaVez.getPontuacao(1);
            int pontosJogador2 = gerenciaVez.getPontuacao(2);
            int vencedor = 0;

            if (pontosJogador1 > pontosJogador2) {
                vencedor = 1;
            } else if (pontosJogador2 > pontosJogador1) {
                vencedor = 2;
            }
            game.setScreen(new GameOverScreen(game, vencedor, pontosJogador1,pontosJogador2));
        }
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
        fonte.dispose();
        shapeRenderer.dispose();
        musicaJogo.dispose();
    }
}

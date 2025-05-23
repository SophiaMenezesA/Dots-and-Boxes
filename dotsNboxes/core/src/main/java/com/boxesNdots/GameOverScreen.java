package com.boxesNdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {
    private BoxesAndDots game;
    private int vencedor;
    private int pontosJogador1;
    private int pontosJogador2;
    private SpriteBatch batch;
    private BitmapFont font;

    public GameOverScreen(BoxesAndDots game, int vencedor, int pontosJogador1, int pontosJogador2) {
        this.game = game;
        this.vencedor = vencedor;
        this.pontosJogador1 = pontosJogador1;
        this.pontosJogador2 = pontosJogador2;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3);
        font.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0.85f, 0.9f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        String mensagem;
        if (vencedor == 0) {
            mensagem = "Empate!";
        } else {
            mensagem = "Jogador " + vencedor + " venceu!";
        }

        font.draw(batch, mensagem, 200, 500);
        font.draw(batch, "Pontos Jogador 1: " + pontosJogador1, 200, 400);
        font.draw(batch, "Pontos Jogador 2: " + pontosJogador2, 200, 350);
        font.draw(batch, "Clique no ESPAÇO para jogar \n novamente", 50, 200);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
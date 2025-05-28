package com.boxesNdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
    private boolean modoBot;
    private Music musicaTelaFinal;

    public GameOverScreen(BoxesAndDots game, int vencedor, int pontosJogador1, int pontosJogador2, boolean modoBot) {
        this.game = game;
        this.vencedor = vencedor;
        this.pontosJogador1 = pontosJogador1;
        this.pontosJogador2 = pontosJogador2;
        this.modoBot = modoBot;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3);
        font.setColor(0.180f, 0.000f, 0.243f, 1);
        musicaTelaFinal = Gdx.audio.newMusic(Gdx.files.internal("energetic-chiptune-video-game-music-platformer-8-bit-318348.mp3"));
        musicaTelaFinal .play();
        musicaTelaFinal .setVolume(0.5f);
        musicaTelaFinal .setLooping(true);
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
        font.draw(batch, "Clique no ESPAÇO para jogar", 80, 200);
        font.draw(batch, "novamente", 300, 150);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new SimpleMenuScreen(game));
            musicaTelaFinal.stop();
            dispose();
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override 
    public void hide() {
        if (musicaTelaFinal != null) {
            musicaTelaFinal.stop();
        }
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (musicaTelaFinal != null) musicaTelaFinal.dispose();
    }
}

package com.boxesNdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleMenuScreen implements Screen {
    private Music musicaMenu;
    private final BoxesAndDots game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final BitmapFont fontGrande;

    private int opcaoSelecionada = 0; 
    private final String[] opcoes = {"Jogar sozinho", "Jogar com 2 jogadores"};

    public SimpleMenuScreen(BoxesAndDots game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        fontGrande = new BitmapFont();
        font.getData().setScale(2f);
    }

    @Override
    public void show() {
        musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("lonely-wolf-goodbye-little-dog-335439.mp3"));
        musicaMenu.play();
        musicaMenu.setVolume(0.5f);
        musicaMenu.setLooping(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0.85f, 0.9f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        fontGrande.draw(batch, "Dots and Boxes", 250, 600);
        fontGrande.getData().setScale(3f);
        font.draw(batch, "Use UP/DOWN para escolher", 220, 450);
        font.draw(batch, "Enter para jogar", 300, 420);

        for (int i = 0; i < opcoes.length; i++) {
            if (i == opcaoSelecionada) {
                font.setColor(0.541f, 0.169f, 0.886f, 1f); 
            } else {
                font.setColor(0.360f, 0.102f, 0.419f, 1f); 
            }
            font.draw(batch, opcoes[i], 280, 250 - i * 40);
        }
        fontGrande.setColor(0.360f, 0.102f, 0.419f, 1f);
        font.setColor(0.180f, 0.000f, 0.243f, 1f); 
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            opcaoSelecionada--;
            if (opcaoSelecionada < 0) opcaoSelecionada = opcoes.length - 1;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            opcaoSelecionada++;
            if (opcaoSelecionada >= opcoes.length) opcaoSelecionada = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            boolean jogarContraBot = (opcaoSelecionada == 0);
            game.setScreen(new GameScreen(game, jogarContraBot));
            musicaMenu.stop();
            dispose();
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
        if (musicaMenu != null) {
            musicaMenu.stop();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (musicaMenu != null) musicaMenu.dispose();
    }
}

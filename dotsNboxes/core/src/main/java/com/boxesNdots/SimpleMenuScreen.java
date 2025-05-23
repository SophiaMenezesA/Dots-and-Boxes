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

    public SimpleMenuScreen(BoxesAndDots game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
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
        font.draw(batch, "Dots and Boxes", 300, 500);
        font.draw(batch, "Pressione ESPAÇO para jogar", 220, 400);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
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
        musicaMenu.dispose();
    }
}

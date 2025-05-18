package com.boxesNdots;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleMenuScreen implements Screen {

    private BoxesAndDots game;
    private SpriteBatch batch;
    private BitmapFont font;

    public SimpleMenuScreen(BoxesAndDots game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont(); 
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0.85f, 0.9f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Dots and Boxes", 300, 600);
        font.draw(batch, "Pressione ESPAÇO para jogar", 280, 500);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // Quando aperta espaço, troca para a tela do jogo
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

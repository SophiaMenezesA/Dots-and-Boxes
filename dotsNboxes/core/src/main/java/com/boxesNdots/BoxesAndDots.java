package com.boxesNdots;

import com.badlogic.gdx.Game;

public class BoxesAndDots extends Game {
    @Override
    public void create() {
        setScreen(new SimpleMenuScreen(this));
    }
}

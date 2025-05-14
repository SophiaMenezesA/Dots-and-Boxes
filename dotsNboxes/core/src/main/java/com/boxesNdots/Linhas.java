package com.boxesNdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Linhas {
    private float[][] posX;
    private float[][] posY;
    private int tamanho;
    private boolean[][] linhasH;
    private boolean[][] linhasV;
    private int[][] donoLinhasH;
    private int[][] donoLinhasV;
    private int[][] quadradosJogador; 
    private int jogadorAtual = 2;    

    public Linhas(float[][] posX, float[][] posY, int tamanho) {
        this.posX = posX;
        this.posY = posY;
        this.tamanho = tamanho;
        linhasH = new boolean[tamanho][tamanho - 1];
        linhasV = new boolean[tamanho - 1][tamanho];
        donoLinhasH = new int[tamanho][tamanho - 1];
        donoLinhasV = new int[tamanho - 1][tamanho];
        quadradosJogador = new int[tamanho - 1][tamanho - 1];
    }

    public void render(ShapeRenderer shapeRenderer) {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        boolean clique = Gdx.input.justTouched();

        shapeRenderer.setColor(1, 1, 1, 0.5f);

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                float x1 = posX[i][j];
                float y1 = posY[i][j];

                if (j < tamanho - 1) {
                    float x2 = posX[i][j + 1];
                    float y2 = posY[i][j + 1];

                    boolean sobreLinha = mouseSobreLinha(mouseX, mouseY, x1, y1, x2, y2);
                    //Verifica se o mouse passou em cima da linha e se houve clique nas linhas horizontais
                    if (linhasH[i][j]) {
                        if (donoLinhasH[i][j] == 1)
                            shapeRenderer.setColor(1f, 0f, 0f, 1f); 
                        else if (donoLinhasH[i][j] == 2)
                            shapeRenderer.setColor(0f, 0.5f, 1f, 1f); 
                    } else if (sobreLinha) {
                        shapeRenderer.setColor(0.5f, 0f, 0.25f, 1f);
                        if (clique) {
                            linhasH[i][j] = true;
                            donoLinhasH[i][j] = jogadorAtual;
                        }
                    } else {
                        shapeRenderer.setColor(1, 1, 1, 0.5f);
                    }
                    shapeRenderer.rectLine(x1, y1, x2, y2, 5);
                }

                if (i < tamanho - 1) {
                    float x2 = posX[i + 1][j];
                    float y2 = posY[i + 1][j];

                    boolean sobreLinha = mouseSobreLinha(mouseX, mouseY, x1, y1, x2, y2);
                    //Verifica se o mouse passou em cima da linha e se houve clique nas linhas verticais
                    if (linhasV[i][j]) {
                        if (donoLinhasV[i][j] == 1)
                            shapeRenderer.setColor(1f, 0f, 0f, 1f); 
                        else if (donoLinhasV[i][j] == 2)
                            shapeRenderer.setColor(0f, 0.5f, 1f, 1f);
                    } else if (sobreLinha) {
                        shapeRenderer.setColor(0.5f, 0f, 0.25f, 1f);
                        if (clique) {
                            linhasV[i][j] = true;
                            donoLinhasV[i][j] = jogadorAtual;
                        }
                    } else {
                        shapeRenderer.setColor(1, 1, 1, 0.5f);
                    }
                    shapeRenderer.rectLine(x1, y1, x2, y2, 5);
                }
            }
        }
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - 1; j++) {
                if (quadradosJogador[i][j] == 0 && linhasH[i][j] && linhasH[i + 1][j] && linhasV[i][j] && linhasV[i][j + 1]) {
                    quadradosJogador[i][j] = jogadorAtual;
                }
            }
        }
        //verifica qual jogador está clicando na linha e colore conforme a cor do jogador
        for (int i = 0; i < tamanho -1; i++) {
            for (int j = 0; j < tamanho - 1; j++) {
                int dono = quadradosJogador[i][j];
                if (dono != 0) {
                    float x1 = posX[i][j];
                    float y1 = posY[i][j];
                    float x2 = posX[i + 1][j + 1];
                    float y2 = posY[i + 1][j + 1];
                    float centroX = (x1 + x2) / 2;
                    float centroY = (y1 + y2) / 2;
                    if (dono == 1) {
                        shapeRenderer.setColor(1f, 0f, 0f, 1f);
                        shapeRenderer.circle(centroX, centroY, 20);
                    } else if (dono == 2) {
                        shapeRenderer.setColor(0f, 0f, 1f, 1f);
                        shapeRenderer.rectLine(centroX - 10, centroY - 10, centroX + 10, centroY + 10, 4);
                        shapeRenderer.rectLine(centroX - 10, centroY + 10, centroX + 10, centroY - 10, 4);
                    }
                }
            }
        }
    }

    private boolean mouseSobreLinha (float mouseX, float mouseY, float x1, float y1, float x2, float y2) {
        float margem = 8.f;
        float dx = x2 - x1;
        float dy = y2 - y1;
        float comprimento2 = dx * dx + dy * dy;
        if (comprimento2 == 0) {
            return false;
        }

        float t = ((mouseX - x1) * dx + (mouseY - y1) * dy) / comprimento2;
        if (t < 0 || t > 1) {
            return false;
        }

        float projX = x1 + t * dx;
        float projY = y1 + t * dy;
        float dist2 = (mouseX - projX) * (mouseX - projX) + (mouseY - projY) * (mouseY - projY);
        return dist2 <= margem * margem;
    }
}

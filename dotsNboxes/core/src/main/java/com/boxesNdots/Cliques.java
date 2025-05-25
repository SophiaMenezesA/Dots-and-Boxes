package com.boxesNdots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cliques {
    private final float[][] posX;
    private final float[][] posY;
    private final int tamanho;
    private final boolean[][] linhasH;
    private final boolean[][] linhasV;
    private final int[][] donoLinhasH;
    private final int[][] donoLinhasV;
    private final int[][] quadradosJogador;
    private final GerenciaVezDoJogador gerenciaVez;

    public Cliques(float[][] posX, float[][] posY, int tamanho, GerenciaVezDoJogador gerenciaVez) {
        this.posX = posX;
        this.posY = posY;
        this.tamanho = tamanho;
        this.gerenciaVez = gerenciaVez;
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
        boolean fechouQuadrado = false;
        boolean fechouQuadradoNestaRodada = false;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (j < tamanho - 1)
                    fechouQuadrado |= desenhaLinhaH(i, j, shapeRenderer, mouseX, mouseY, clique);
                if (i < tamanho - 1)
                    fechouQuadrado |= desenhaLinhaV(i, j, shapeRenderer, mouseX, mouseY, clique);
            }
        }
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - 1; j++) {
                if (quadradosJogador[i][j] == 0 &&
                        linhasH[i][j] && linhasH[i + 1][j] &&
                        linhasV[i][j] && linhasV[i][j + 1]) {
                    quadradosJogador[i][j] = gerenciaVez.getJogadorAtual();
                    gerenciaVez.adicionarPonto(gerenciaVez.getJogadorAtual());
                    fechouQuadradoNestaRodada = true;
                }
            }
        }
        if (clique && !fechouQuadradoNestaRodada) {
            gerenciaVez.passarVez();
        }
        coloreQuadrado(shapeRenderer);
    }

    private void coloreQuadrado(ShapeRenderer shapeRenderer) {
        for (int i = 0; i < tamanho - 1; i++) {
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
                        shapeRenderer.setColor(0.8f, 0.3f, 0.3f, 1f);
                        shapeRenderer.circle(centroX, centroY, 20);
                    } else if (dono == 2) {
                        shapeRenderer.setColor(0.3f, 0.5f, 0.8f, 1f);
                        shapeRenderer.rectLine(centroX - 10, centroY - 10, centroX + 10, centroY + 10, 4);
                        shapeRenderer.rectLine(centroX - 10, centroY + 10, centroX + 10, centroY - 10, 4);
                    }
                }
            }
        }
    }

    private boolean mouseSobreLinha(float mouseX, float mouseY, float x1, float y1, float x2, float y2) {
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

    private boolean desenhaLinhaH(int i, int j, ShapeRenderer sr, float mx, float my, boolean clique) {
        float x1 = posX[i][j], y1 = posY[i][j];
        float x2 = posX[i][j + 1], y2 = posY[i][j + 1];
        return desenhaLinha(mx, my, clique, sr, x1, y1, x2, y2, linhasH, donoLinhasH, i, j);
    }

    private boolean desenhaLinhaV(int i, int j, ShapeRenderer sr, float mx, float my, boolean clique) {
        float x1 = posX[i][j], y1 = posY[i][j];
        float x2 = posX[i + 1][j], y2 = posY[i + 1][j];
        return desenhaLinha(mx, my, clique, sr, x1, y1, x2, y2, linhasV, donoLinhasV, i, j);
    }

    private boolean desenhaLinha(float mx, float my, boolean clique, ShapeRenderer sr,
        float x1, float y1, float x2, float y2,
        boolean[][] linhas, int[][] donos, int i, int j) {
        boolean sobre = mouseSobreLinha(mx, my, x1, y1, x2, y2);
        if (linhas[i][j]) {
            sr.setColor(donos[i][j] == 1 ? 1f : 0.3f, donos[i][j] == 1 ? 0.6f : 0.5f, donos[i][j] == 1 ? 0.6f : 0.8f, 1f);
        } else if (sobre) {
            sr.setColor(0.5f, 0f, 0.25f, 1f);
            if (clique) {
                linhas[i][j] = true;
                donos[i][j] = gerenciaVez.getJogadorAtual();
            }
        } else {
            sr.setColor(1, 1, 1, 0.5f);
        }
        sr.rectLine(x1, y1, x2, y2, 5);
        return clique && sobre && !linhas[i][j];
    }

    public boolean estaCompleto() {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - 1; j++) {
                if (quadradosJogador[i][j] == 0) {
                    return false; 
                }
            }
        }
        return true;
    }

    public boolean podeFecharQuadrado(int i, int j) {
        if (quadradosJogador[i][j] != 0) return false;
            int linhas = 0;
            if (linhasH[i][j]) linhas++;
            if (linhasH[i+1][j]) linhas++;
            if (linhasV[i][j]) linhas++;
            if (linhasV[i][j+1]) linhas++;
            return linhas == 3;
    }

    public void marcarLinhaFaltante(int i, int j, int jogador) {
        if (!linhasH[i][j]) {
            linhasH[i][j] = true;
            donoLinhasH[i][j] = jogador;
        } else if (!linhasH[i + 1][j]) {
            linhasH[i + 1][j] = true;
            donoLinhasH[i + 1][j] = jogador;
        } else if (!linhasV[i][j]) {
            linhasV[i][j] = true;
            donoLinhasV[i][j] = jogador;
        } else if (!linhasV[i][j + 1]) {
            linhasV[i][j + 1] = true;
            donoLinhasV[i][j + 1] = jogador;
        }
        quadradosJogador[i][j] = jogador;
        gerenciaVez.adicionarPonto(jogador);
    }

    public boolean marcarLinhaAleatoria(int jogador) {
        List<int[]> linhasDisponiveis = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho - 1; j++) {
                if (!linhasH[i][j]) {
                    linhasDisponiveis.add(new int[]{0, i, j}); // 0 para horizontal
                }
            }
        }
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (!linhasV[i][j]) {
                    linhasDisponiveis.add(new int[]{1, i, j}); // 1 para vertical
                }
            }
        }
        if (linhasDisponiveis.isEmpty()) return false;
        Random random = new Random();
        int[] escolha = linhasDisponiveis.get(random.nextInt(linhasDisponiveis.size()));
        if (escolha[0] == 0) {
            linhasH[escolha[1]][escolha[2]] = true;
            donoLinhasH[escolha[1]][escolha[2]] = jogador;
        } else {
            linhasV[escolha[1]][escolha[2]] = true;
            donoLinhasV[escolha[1]][escolha[2]] = jogador;
        }
        return true;
    }

    public boolean fechouQuadradoNestaRodada(int jogador) {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - 1; j++) {
                if (quadradosJogador[i][j] == jogador) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getTamanho() {
        return tamanho;
    }
}

package com.boxesNdots;

import java.util.Random;

public class BotJogador {
    private final Cliques cliques;
    private final GerenciaVezDoJogador gerenciaVez;
    private final int idDoBot;
    private final Random random = new Random();

    public BotJogador(Cliques cliques, GerenciaVezDoJogador gerenciaVez, int idDoBot) {
        this.cliques = cliques;
        this.gerenciaVez = gerenciaVez;
        this.idDoBot = idDoBot;
    }

    public void jogar() {
        if (gerenciaVez.getJogadorAtual() != idDoBot) return;
            boolean fechouQuadrado;
            do {
                fechouQuadrado = false;
                outerLoop:
                for (int i = 0; i < cliques.getTamanho() - 1; i++) {
                    for (int j = 0; j < cliques.getTamanho() - 1; j++) {
                        if (cliques.podeFecharQuadrado(i, j)) {
                            cliques.marcarLinhaFaltante(i, j, idDoBot);
                            fechouQuadrado = true;
                            break outerLoop;
                        }
                    }
                }
                if (!fechouQuadrado) {
                    boolean jogou = cliques.marcarLinhaAleatoria(idDoBot);
                    if (!jogou) {
                        gerenciaVez.passarVez();
                        return;
                    }
                    gerenciaVez.passarVez();
                    return;
                }
        } while (fechouQuadrado);
    }
}

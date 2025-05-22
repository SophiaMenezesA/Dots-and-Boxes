package com.boxesNdots;

public class GerenciaVezDoJogador {
    private int jogadorAtual;          // guarda qual jogador está jogando agora (1 ou 2)
    private final int[] pontuacao;     // armazena a pontuação de cada jogador

    public GerenciaVezDoJogador(int totalJogadores){
        this.jogadorAtual = 1;       
        this.pontuacao = new int[totalJogadores]; // inicializa a pontuação 0 a 0
    }

    // get pra saber quem é o jogador atual
    public int getJogadorAtual() {
        return jogadorAtual;
    }

    // passa a vez para o outro jogador
    public void passarVez() {
        // se for o jogador 1, troca para o 2; se for o 2, troca para o 1
        jogadorAtual = jogadorAtual == 1 ? 2 : 1;
    }

    public void adicionarPonto(int jogador){
        if (jogador >= 1 && jogador <= pontuacao.length){
            pontuacao[jogador - 1] ++;  // incrementa a pontuação do jogador que completou a caixa
        }
    }

    public int getPontuacao(int jogador) {
        if(jogador >= 1 && jogador <= pontuacao.length){
            return pontuacao[jogador - 1];
        }
        return 0;  
    }

    // zera todas as pontuações (útil pra começar um jogo novo)
    public void resetarPontuacoes(){
        for (int i=0; i < pontuacao.length; i++){
            pontuacao[i] = 0;
        }
    } 
}

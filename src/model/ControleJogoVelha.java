package model;

public class ControleJogoVelha extends ControleJogo {

    public ControleJogoVelha() {
        tabuleiro = new char[9];
    }

    //INICIA E PEGA TEMPO
    @Override
    public void iniciaPartida() {
        for (int i = 0; i < 9; i++) {
            tabuleiro[i] = ' ';
        }
        tempoInicio = System.currentTimeMillis();
    }

    //MOVIMENTO DO TABULEIRO
    @Override
    public void adicionaMovimento(Character letra, int pos) {
        tabuleiro[pos] = letra;
    }
    //PEGA O TEMPO DO INICIO E COMPARA
    @Override
    public long getDuracaoPartida() {
        return System.currentTimeMillis() - tempoInicio;
    }

    //VERIFICACAO PARA SER FEITA A CADA JOGADA
    @Override
    public char avaliaPartida() {

        //VERIFICACAO LINHAS
        for (int i = 0; i < 9; i += 3) {
            if (tabuleiro[i] != ' ' && tabuleiro[i] == tabuleiro[i + 1] && tabuleiro[i] == tabuleiro[i + 2]) {
                return tabuleiro[i];
            }
        }
        //VERIFICACAO COLUNAS
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i] != ' ' && tabuleiro[i] == tabuleiro[i + 3] && tabuleiro[i] == tabuleiro[i + 6]) {
                return tabuleiro[i];
            }
        }
        //VERIFICACAO LINHAS DIAGONAIS
        if (tabuleiro[0] != ' ' && tabuleiro[0] == tabuleiro[4] && tabuleiro[0] == tabuleiro[8]) {
            return tabuleiro[0];
        }
        if (tabuleiro[2] != ' ' && tabuleiro[2] == tabuleiro[4] && tabuleiro[2] == tabuleiro[6]) {
            return tabuleiro[2];
        }

        //EMPATE
        boolean empate = true;
        for (char c : tabuleiro) {
            if (c == ' ') {
                empate = false;
                break;
            }
        }
        if (empate) return 'E';

        return 0; //DIZ QUE TA RODANDO
    }


    public String getStatusPartida() {
        char vencedor = avaliaPartida();
        double segundos = getDuracaoPartida() / 1000.0;

        return switch (vencedor) {
            case 'X' -> String.format("Jogador X venceu em %.2f segundos", segundos);
            case 'O' -> String.format("Jogador O venceu em %.2f segundos", segundos);
            case 'E' -> String.format("Partida empatada em %.2f segundos", segundos);
            default -> String.format("Partida em andamento (%.2f s)", segundos);
        };
    }
}

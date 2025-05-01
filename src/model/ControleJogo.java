package model;
public abstract class ControleJogo {
    protected long tempoInicio;
    protected char[] tabuleiro;
    public abstract void iniciaPartida();
    public abstract void adicionaMovimento(Character letra, int pos);
    public abstract long getDuracaoPartida();
    public abstract char avaliaPartida();
}
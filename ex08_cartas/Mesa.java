/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

/**
 *
 * @author Jean Hauck <jean.hauck at ufsc.br>
 * @date 15/04/2016
 */
public class Mesa {

    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Carta cartajogador1;
    private final Carta cartajogador2;

    public Mesa(Jogador jogador1, Jogador jogador2, Carta cartajogador1, Carta cartajogador2){
      this.jogador1 = jogador1;
      this.jogador2 = jogador2;
      this.cartajogador1 = cartajogador1;
      this.cartajogador2 = cartajogador2;
    }

    public Jogador getJogador1(){
      return jogador1;
    }

    public Jogador getJogador2(){
      return jogador2;
    }

    public Carta getCartaJogador1(){
      return cartajogador1;
    }

    public Carta getCartaJogador2(){
      return cartajogador2;
    }
}

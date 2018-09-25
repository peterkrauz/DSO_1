/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

import java.util.ArrayList;

/**
 *
 * @author Jean Hauck <jean.hauck at ufsc.br>
 * @date 15/04/2016
 */
public class Jogador implements IJogador {

    private String nome;
    private ArrayList<Carta> mao;

    public Jogador(){
        mao = new ArrayList<>();
    }

    public Jogador(String nome){
      this.nome = nome;
      mao = new ArrayList<>();
    }

    @Override
    public String getNome(){
      return nome;
    }
    @Override
    public void setNome(String nome){
      this.nome = nome;
    }

    /**
     * Retira uma das cartas do Jogador. Esta operacao eh utilizada para realizar uma jogada (baixar uma carta na mesa)
     * A carta sai da mao (ou seja, a carta sai da lista das cartas que o jogador possui)
     * @return Retorna a Carta que foi retirada da mao do jogador (lista das cartas que ele possui)
     */
    @Override
    public Carta baixaCartaDaMao(){
      if( !mao.isEmpty() ){
        Carta carta = mao.get(mao.size()-1);
        mao.remove(carta);
        return carta;
      }
      return new Carta();
    }

    /**
     *
     * @return Retorna a mao atual do jogador (lista das cartas que ele possui)
     */
    @Override
    public ArrayList<Carta> getMao(){
      return mao;
    }

    /**
     * Inclui na mao do jogador a carta passada como parametro
     * @param carta Carta que sera incluida na mao do jogador
     */
    @Override
    public void incluiCartaNaMao(Carta carta){
      mao.add(carta);
    }

    /**
     * Retira da mao do jogador a carta passada como parametro.
     * @param carta Carta que sera retirada da mao do jogador (lista das cartas que ele possui)
     */
    @Override
    public void removeCartaNaMao(Carta carta){
      mao.remove(carta);
    }

}

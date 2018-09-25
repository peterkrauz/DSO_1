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
public class Personagem {

    private TipoPersonagem tipo;

    private int energia;
    private int habilidade;
    private int velocidade;
    private int resistencia;

    public Personagem(){

    }

    public Personagem(int energia, int habilidade, int velocidade, int resistencia, TipoPersonagem tipoPersonagem, ){
      this.tipo = tipoPersonagem;
      this.energia = energia;
      this.habilidade = habilidade;
      this.velocidade = velocidade;
      this.resistencia = resistencia;
    }

    public int getEnergia(){
      return energia;
    }
    
    public void setEnergia(int energia){
      this.energia = energia;
    }

    public int getHabilidade(){
      return habilidade;
    }

    public void setHabilidade(int habilidade){
      this.habilidade = habilidade;
    }

    public int getVelocidade(){
      return velocidade;
    }

    public void setVelocidade(int velocidade){
      this.velocidade = velocidade;
    }

    public int getResistencia(){
      return resistencia;
    }

    public void setResistencia(int resistencia){
      this.resistencia = resistencia;
    }

}

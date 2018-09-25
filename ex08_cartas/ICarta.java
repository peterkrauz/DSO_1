/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

/**
 *
 * @author Jean Hauck <jean.hauck at ufsc.br>
 * @date   11/09/2015
 */
public interface ICarta {

    /**
     * Soma e retorna todos os valores dos atributos do personagem da Carta
     * @return Retorna o somatorio de todos os atributos do personagem da Carta
     */
    public int getValorTotalCarta();

    public void setPersonagem(Personagem personagem);
    
    public Personagem getPersonagem();
    
}

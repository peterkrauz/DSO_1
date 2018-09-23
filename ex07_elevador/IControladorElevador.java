package excecoes_evelador;

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
public interface IControladorElevador {

    /**
     * Faz o elevador subir um andar. Se jah estiver no ultimo andar, dispara excecao
     * @return Mensagem informando o que ocorreu com o elevador 
     * @throws ElevadorJahNoUltimoAndarException 
     */
    public String subir() throws ElevadorJahNoUltimoAndarException;    
    
    /**
     * Faz o elevador descer um andar. Se jah estiver no terreo, dispara excecao
     * @return Mensagem informando o que ocorreu com o elevador
     * @throws ElevadorJahNoTerreoException
     */
    public String descer() throws ElevadorJahNoTerreoException;

    /**
     * Entra uma pessoa no Elevador. Se nao for possivel permitir a entrada da pessoa, dispara excecao
     * @return Mensagem informando o que ocorreu com o elevador
     * @throws ElevadorCheioException
     */
    public String entraPessoa() throws ElevadorCheioException;
	
    /**
     * Sai uma pessoa no Elevador. Se nao for possivel permitir a saida de uma pessoa, dispara excecao
     * @return Mensagem informando o que ocorreu com o elevador
     * @throws ElevadorJahVazioException
     */
    public String saiPessoa() throws ElevadorJahVazioException;	
	
    /**
     *
     * @return Elevador
     */
    public Elevador getElevador();

    /**
     *
     * @param andarAtual andar atual do elevador
     * @param totalAndaresPredio total de andares do predio
     * @param capacidade capacidade maxima do elevador
     * @param totalPessoas total de pessoas atual do elevador
     * @return retorna o Elevador instanciado como um IElevador
     */
    public IElevador inicializarElevador(int andarAtual, int totalAndaresPredio, int capacidade, int totalPessoas);


    
}

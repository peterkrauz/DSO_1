/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

/**
 *
 * @author Jean Hauck
 */
public class ControladorElevador implements IControladorElevador {

  private Elevador elevador;

  public ControladorElevador(){}

  /**
   * Faz o elevador subir um andar. Se jah estiver no ultimo andar, dispara excecao
   * @return Mensagem informando o que ocorreu com o elevador
   * @throws ElevadorJahNoUltimoAndarException
   */
  public String subir() throws ElevadorJahNoUltimoAndarException{
    if( elevador.getAndarAtual() == elevador.getTotalAndaresPredio() ){
      throw new ElevadorJahNoUltimoAndarException();
    } else {
      elevador.subir();
    }
    return "Elevador subindo";
  }

  /**
   * Faz o elevador descer um andar. Se jah estiver no terreo, dispara excecao
   * @return Mensagem informando o que ocorreu com o elevador
   * @throws ElevadorJahNoTerreoException
   */
  public String descer() throws ElevadorJahNoTerreoException{
    if( elevador.getAndarAtual() == 0  ){
      throw new ElevadorJahNoTerreoException();
    } else {
      elevador.descer();
    }
    return "Elevador desceu";
  }

  /**
   * Entra uma pessoa no Elevador. Se nao for possivel permitir a entrada da pessoa, dispara excecao
   * @return Mensagem informando o que ocorreu com o elevador
   * @throws ElevadorCheioException
   */
  public String entraPessoa() throws ElevadorCheioException{
    if( elevador.getTotalPessoas() == elevador.getCapacidade() ){
      throw new ElevadorCheioException();
    } else {
      elevador.entraPessoa();
    }
    return "Pessoa entrando";
  }

  /**
   * Sai uma pessoa no Elevador. Se nao for possivel permitir a saida de uma pessoa, dispara excecao
   * @return Mensagem informando o que ocorreu com o elevador
   * @throws ElevadorJahVazioException
   */
  public String saiPessoa() throws ElevadorJahVazioException{
    if ( elevador.getTotalPessoas() == 0 ) {
      throw new ElevadorJahVazioException();
    } else {
      elevador.saiPessoa();
    }
    return "Pessoa saindo";
  }

  /**
   *
   * @return Elevador
   */
  public Elevador getElevador(){
    return elevador;
  }

  /**
   *
   * @param andarAtual andar atual do elevador
   * @param totalAndaresPredio total de andares do predio
   * @param capacidade capacidade maxima do elevador
   * @param totalPessoas total de pessoas atual do elevador
   * @return retorna o Elevador instanciado como um IElevador
   */
  public IElevador inicializarElevador(int andarAtual, int totalAndaresPredio, int capacidade, int totalPessoas){
    return elevador = new Elevador(andarAtual, totalAndaresPredio, capacidade, totalPessoas);
  }


}

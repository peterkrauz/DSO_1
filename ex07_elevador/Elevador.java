/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

/**
 *
 * @author Jean Hauck
 */
public class Elevador implements IElevador {

    private int capacidade;
    private int totalPessoas;
    private int totalAndaresPredio;
    private int andar;

    public Elevador(){}

    public Elevador(int andar, int totalAndaresPredio, int capacidade, int totalPessoas){
      this.capacidade = capacidade;
      this.totalPessoas = totalPessoas;
      this.totalAndaresPredio = totalAndaresPredio;
      this.andar = andar;
    }

    @Override
    public int getCapacidade(){
      return capacidade;
    }

    @Override
    public int getTotalPessoas(){
      return totalPessoas;
    }

    @Override
    public String descer() throws ElevadorJahNoTerreoException{
      if( andar > 0 ){
        setAndar(andar - 1);
      } else {
        throw new ElevadorJahNoTerreoException("Elevador já no térreo");
      }
      return "";
    }

    @Override
    public String entraPessoa() throws ElevadorCheioException{
      if ( getTotalPessoas() == capacidade ) {
        throw new ElevadorCheioException("Elevador cheio.");
      } else {
        setTotalPessoas( totalPessoas + 1 );
      }
      return "";
    }

    @Override
    public String saiPessoa() throws ElevadorJahVazioException{
      if( getTotalPessoas() == 0  ){
        throw new ElevadorJahVazioException();
      } else {
        setTotalPessoas( totalPessoas - 1 );
      }
      return "Pessoa saiu (?)";
    }

    @Override
    public String subir() throws ElevadorJahNoUltimoAndarException{
      if ( getAndarAtual() == totalAndaresPredio ) {
        throw new ElevadorJahNoUltimoAndarException();
      } else {
        setAndar( andar + 1 );
      }
      return "Elevador subiu (?)";
    }

    @Override
    public void setTotalAndaresPredio(int totalAndaresPredio){
      this.totalAndaresPredio = totalAndaresPredio;
    }

    @Override
    public int getTotalAndaresPredio(){
      return totalAndaresPredio;
    }

    @Override
    public int getAndarAtual(){
      return andar;
    }

    public void setAndar(int novoAndar){
      this.andar = novoAndar;
    }

    public void setTotalPessoas(int novoTotal){
      this.totalPessoas = novoTotal;
    }

}

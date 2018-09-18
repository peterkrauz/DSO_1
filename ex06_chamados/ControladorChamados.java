import java.util.Date;
import java.util.ArrayList;

public class ControladorChamados implements IControladorChamados{

  private List<Chamado> chamados;
  private List<TipoChamado> tipoChamados;

  public ControladorChamados(){
    this.chamados = new ArrayList<>();
    this.tipoChamados = new ArrayList<>();
  }

  @Override
  public getTotalChamadosPorTipo(TipoChamado tipo){
    List<Chamado> totalChamados = new ArrayList<>();
    for(Chamado chamado : chamados){
      if( chamado.getTipoChamado() == tipo ){
        totalChamados.add( chamado );
      }
    }
    return totalChamados;
  }

  @Override
  public IChamado incluiChamado(Date data, Cliente cliente, Tecnico tecnico, String titulo, String descricao, int prioridade, TipoChamado tipo){
    Chamado novoChamado = new Chamado(Cliente cliente, String descricao, int prioridade, Tecnico tecnico, TipoChamado tipoChamado, String titulo, Date date);
    if( !(chamados.contains(novoChamado)) ){
      chamados.add(novoChamado);
    }
    return novoChamado;
  }

  @Override
  public ITipoChamado incluiTipoChamado(int codigo, String nome, String descricao){
    TipoChamado novoTipoChamado = new TipoChamado(codigo, nome, descricao);
    if( !(tipoChamados.contains(novoTipoChamado)) ){
      tipoChamados.add(novoTipoChamado);
    }
    return novoTipoChamado;
  }

  /**
   * Permite incluir um novo chamado na lista de Chamados. O chamado incluido eh retornado como um IChamado
   * @param data data do chamado em formato Date
   * @param cliente referencia para o Cliente do chamado
   * @param tecnico referencia para o Tecnico do chamado
   * @param titulo titulo do chamado
   * @param descricao descricao do chamado
   * @param prioridade prioridade do chamado
   * @param tipo tipo do chamado (TipoChamado)
   * @return retorna o chamado cadastrado
   */
  IChamado incluiChamado(Date data, Cliente cliente, Tecnico tecnico, String titulo, String descricao, int prioridade, TipoChamado tipo);

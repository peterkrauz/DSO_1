import java.util.Date;

public class Chamado implements IChamado{

  private Cliente cliente;
  private String descricao;
  private int prioridade;
  private Tecnico tecnico;
  private TipoChamado tipoChamado;
  private String titulo;
  private Date date;

  public Chamado(){

  }

  public Chamado(Cliente cliente, String descricao, int prioridade, Tecnico tecnico, TipoChamado tipoChamado, String titulo, Date date){
    this.cliente = cliente;
    this.descricao = descricao;
    this.prioridade = prioridade;
    this.tecnico = tecnico;
    this.tipoChamado = tipoChamado;
    this.titulo = titulo;
    this.date = date;
  }

  public int getCliente(){
    return cliente;
  }

  public String getDescricao(){
    return descricao;
  }

  public int getPrioridade(){
    return prioridade;
  }

  public Tecnico getTecnico(){
    return tecnico;
  }

  public TipoChamado getTipoChamado(){
    return tipoChamado;
  }

  public String getTitulo(){
    return titulo;
  }

  public Date getDate(){
    return date;
  }

}

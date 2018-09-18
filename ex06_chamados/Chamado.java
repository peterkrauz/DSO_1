package ex06_chamados;

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

  @Override
  public Cliente getCliente(){
    return cliente;
  }
  @Override
  public String getDescricao(){
    return descricao;
  }
  @Override
  public int getPrioridade(){
    return prioridade;
  }
  @Override
  public Tecnico getTecnico(){
    return tecnico;
  }
  @Override
  public TipoChamado getTipo(){
    return tipoChamado;
  }
  @Override
  public String getTitulo(){
    return titulo;
  }

  public Date getDate(){
    return date;
  }

}

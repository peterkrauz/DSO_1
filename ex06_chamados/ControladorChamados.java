package ex06_chamados;

import java.util.Date;
import java.util.ArrayList;

public class ControladorChamados implements IControladorChamados {

  private ArrayList<Chamado> chamados;
  private ArrayList<TipoChamado> tipoChamados;

  public ControladorChamados() {
    this.chamados = new ArrayList<>();
    this.tipoChamados = new ArrayList<>();
  }

  @Override
  public int getTotalChamadosPorTipo(TipoChamado tipo) {
    int totalChamados = 0;
    for (Chamado chamado : chamados) {
      if (chamado.getTipo() == tipo) {
        totalChamados++;
      }
    }
    return totalChamados;
  }

  @Override
  public IChamado incluiChamado(Date data, Cliente cliente, Tecnico tecnico, String titulo, String descricao, int prioridade, TipoChamado tipo) {
    Chamado novoChamado = new Chamado(cliente, descricao, prioridade, tecnico, tipo, titulo, data);
    if (!(chamados.contains(novoChamado))) {
      chamados.add(novoChamado);
    }
    return novoChamado;
  }

  @Override
  public ITipoChamado incluiTipoChamado(int codigo, String nome, String descricao) {
    TipoChamado novoTipoChamado = new TipoChamado(codigo, nome, descricao);
    if (!(tipoChamados.contains(novoTipoChamado))) {
      tipoChamados.add(novoTipoChamado);
    }
    return novoTipoChamado;
  }

}
package ex06_chamados;

import java.util.ArrayList;

public class ControladorPessoas implements IControladorPessoas{

  private ArrayList<Cliente> clientes;
  private ArrayList<Tecnico> tecnicos;

  public ControladorPessoas(){
    this.clientes = new ArrayList<>();
    this.tecnicos = new ArrayList<>();
  }


  @Override
  public ArrayList<Cliente> getClientes() {
    return clientes;
  }

  @Override
  public ArrayList<Tecnico> getTecnicos() {
    return tecnicos;
  }

  @Override
  public IPessoa incluiCliente(int codigo, String nome) {
      Cliente novoCliente = new Cliente(codigo, nome);
      if(!clientes.contains(novoCliente)){
          clientes.add(novoCliente);
      }
      return novoCliente;
  }

  @Override
  public IPessoa incluiTecnico(int codigo, String nome) {
      Tecnico novoTecnico = new Tecnico(codigo, nome);
      if(!tecnicos.contains(novoTecnico)){
          tecnicos.add(novoTecnico);
      }
      return novoTecnico;
  }
}


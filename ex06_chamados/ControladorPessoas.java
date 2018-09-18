import java.util.ArrayList;

public class ControladorPessoas implements IControladorPessoas{

  private ArrayList<Cliente> clientes;
  private ArrayList<Tecnico> tecnicos;

  public ControladorPessoas(){
    this.clientes = new ArrayList<>();
    this.tecnicos = new ArrayList<>();
  }

  public ArrayList<Cliente> getClientes(){
    return clientes;
  }

  public ArrayList<Tecnico> getTecnicos(){
    return tecnicos;
  }

}

/**
 *
 * @return retorna a lista de clientes
 */
public ArrayList<Cliente> getClientes();

/**
 *
 * @return retorna a lista de tecnicos
 */
public ArrayList<Tecnico> getTecnicos();

/**
 * Permite a inclusao de um novo cliente na lista de clientes
 * @param codigo codigo do Cliente
 * @param nome nome do Cliente
 * @return retorna o cliente inserido como um IPessoa
 */
public IPessoa incluiCliente(int codigo, String nome);

/**
 * Permite a inclusao de um novo tecnico na lista de tecnicos
 * @param codigo codigo do tecnico
 * @param nome nome do tecnico
 * @return retorna o tecnico inserido como um IPessoa
 */
public IPessoa incluiTecnico(int codigo, String nome);

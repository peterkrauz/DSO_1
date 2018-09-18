/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

import java.util.ArrayList;

/**
 *
 * @author Jean Hauck <jean.hauck at ufsc.br>
 * @date   08/09/2015
 */
public interface IControladorPessoas {

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
    
}

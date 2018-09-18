/*
 * Universidade Federal de Santa Catarina.
 * CTC - Centro Tecnologico - http://ctc.ufsc.br
 * INE - Departamento de Informatica e Estatistica - http://inf.ufsc.br
 */

/**
 *
 * @author Jean Hauck <jean.hauck at ufsc.br>
 * @date   08/09/2015
 */
public interface IChamado {

    public Cliente getCliente();

    public String getDescricao();

    public int getPrioridade();

    public Tecnico getTecnico();

    public TipoChamado getTipo();

    public String getTitulo();
    
}

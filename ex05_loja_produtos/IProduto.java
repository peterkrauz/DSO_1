package ex05_loja_produtos;
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
public interface IProduto {

    public CategoriaProduto getCategoria();
    public int getCodigo();
    public String getDescricao();
    public String getNome();
    public float getPreco();
    public int getQuantidade();
    public void setCategoria(CategoriaProduto categoria);
    public void setCodigo(int codigo);
    public void setDescricao(String descricao);
    public void setNome(String nome);
    public void setPreco(float preco);
    public void setQuantidade(int quantidade);
    
}

package ex05_loja_produtos;

public class Produto implements IProduto{

    private CategoriaProduto categoriaProduto;
    private int codigo;
    private String descricao;
    private String nome;
    private float preco;
    private int quantidade;

    @Override
    public CategoriaProduto getCategoria() {
        return categoriaProduto;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public float getPreco() {
        return preco;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public void setCategoria(CategoriaProduto categoria) {
        this.categoriaProduto = categoria;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto(){}

    public Produto(int codigo, String nome, String descricao, float preco, int quantidade, CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
        this.codigo = codigo;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}

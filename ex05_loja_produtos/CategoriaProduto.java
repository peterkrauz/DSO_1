package ex05_loja_produtos;

public class CategoriaProduto implements ICategoriaProduto{

    private String nome;
    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaProduto(int codigo, String nome) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public CategoriaProduto(){}
}

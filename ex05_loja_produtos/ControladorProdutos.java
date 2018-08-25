package ex05_loja_produtos;

import java.util.ArrayList;

public class ControladorProdutos implements IControladorProdutos {

    private ArrayList<Produto> produtos;
    private ArrayList<CategoriaProduto> categorias;

    public ControladorProdutos(){
        produtos = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    @Override
    public void atualizaPrecos(float inflacao) {
        for (Produto p : produtos){
            float novoPreco = p.getPreco() + p.getPreco()*inflacao/100;
            p.setPreco(novoPreco);
        }
    }

    @Override
    public IProduto getProdutoPeloCodigo(int codigo) {
        Produto produto = null;
        for (Produto p : produtos){
            if (p.getCodigo()==codigo){
                produto = p;
            }
        }
        return produto;
    }

    @Override
    public ICategoriaProduto incluiCategoriaProduto(int codigo, String nome) {
        CategoriaProduto categoria = new CategoriaProduto(codigo, nome);
        if (!categorias.contains(categoria)){
            categorias.add(categoria);
        }
        return categoria;
    }

    @Override
    public IProduto incluiProduto(int codigo, String nome, String descricao, float preco, int quantidade, CategoriaProduto categoria) {
        Produto produto = new Produto(codigo, nome, descricao, preco, quantidade, categoria);
        if (!produtos.contains(produto)){
            produtos.add(produto);
        }
        return produto;
    }
}

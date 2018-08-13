public class Emprestimo {
    Livro livro;
    Amigo amigo;
    String dataEfetuacao;

    public Emprestimo(Livro livro, Amigo amigo, String dataEfetuacao) {
        this.livro = livro;
        livro.setEmprestado(true);
        this.amigo = amigo;
        this.dataEfetuacao = dataEfetuacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }

    public String getDataEfetuacao() {
        return dataEfetuacao;
    }

    public void setDataEfetuacao(String dataEfetuacao) {
        this.dataEfetuacao = dataEfetuacao;
    }
}

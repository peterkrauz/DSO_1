import java.util.Date;

public class Emprestimo {
    Livro livro;
    Amigo amigo;
    Date dataEfetuacao;

    public Emprestimo(Livro livro, Amigo amigo, Date dataEfetuacao) {
        this.livro = livro;
        livro.setEmprestado(true);
        this.amigo = amigo;
        this.dataEfetuacao = dataEfetuacao;
    }


}

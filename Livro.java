public class Livro {
    String titulo;
    String resumo;
    Autor autor;
    Pessoa personagemPrincipal;
    String genero;
    String faixaEtaria;
    boolean isEmprestado;

    public Livro(String titulo, String resumo, Autor autor, Pessoa personagemPrincipal, String genero, String faixaEtaria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.autor = autor;
        this.personagemPrincipal = personagemPrincipal;
        this.genero = genero;
        this.faixaEtaria = faixaEtaria;
        this.isEmprestado = false;
    }

    public boolean isEmprestado() {
        return isEmprestado;
    }

    public void setEmprestado(boolean emprestado) {
        isEmprestado = emprestado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Pessoa getPersonagemPrincipal() {
        return personagemPrincipal;
    }

    public void setPersonagemPrincipal(Pessoa personagemPrincipal) {
        this.personagemPrincipal = personagemPrincipal;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    //título, resumo, autor, personagem principal,
    //    //gênero (comédia, romance ou aventura, etc) e faixa
    //    //etária.

}

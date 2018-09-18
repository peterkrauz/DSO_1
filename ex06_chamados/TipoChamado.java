public class TipoChamado implements ITipoChamado{

  private int codigo;
  private String descricao;
  private String nome;

    public TipoChamado(){}

    public TipoChamado(int codigo, String descricao, String nome){
      this.codigo = codigo;
      this.descricao = descricao;
      this.nome = nome;
    }

    public int getCodigo(){
      return codigo;
    }

    public String getDescricao(){
      return descricao;
    }

    public String getNome(){
      return nome;
    }

}

public class Pessoa implements IPessoa{

  private int codigo;
  private String nome;

  public Pessoa(){}

  public Pessoa(int codigo, String nome){
    this.codigo = codigo;
    this.nome = nome;
  }

  public int getCodigo(){
    return codigo;
  }

  public String getNome(){
    return nome;
  }

}

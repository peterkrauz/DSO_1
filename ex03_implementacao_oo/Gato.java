package ex03_implementacao_oo;

public class Gato extends Mamifero{

    public Gato() {
        super(2, 2);
    }

    public String miar(){
        return super.produzirSom()+" SOM: MIAU";
    }

}

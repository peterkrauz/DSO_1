package ex03_implementacao_oo;

public abstract class Animal {
    private int tamanhoPasso;

    public Animal(int tamanhoPasso){
        this.tamanhoPasso = tamanhoPasso;
    }

    public String mover(){
        return "ANIMAL: DESLOCOU "+tamanhoPasso;
    }

    public String produzirSom(){
        return "PRODUZ SOM: ";
    }

}
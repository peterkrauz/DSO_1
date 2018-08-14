package ex03_implementacao_oo;

public abstract class Mamifero extends Animal{
    private int volumeSom;

    public Mamifero(int volumeSom, int tamanhoPasso) {
        super(tamanhoPasso);
        this.volumeSom = volumeSom;
    }

    public int getVolumeSom() {
        return volumeSom;
    }

    public void setVolumeSom(int volumeSom) {
        this.volumeSom = volumeSom;
    }

    public String produzirSom(){
        return "MAMIFERO: "+super.produzirSom()+volumeSom;
    }

}

package ex03_implementacao_oo;

public abstract class Ave extends Animal{
    private int alturaVoo;

    public Ave(int tamanhoPasso, int alturaVoo) {
        super(tamanhoPasso);
        this.alturaVoo = alturaVoo;
    }

    public int getAlturaVoo() {
        return alturaVoo;
    }

    public void setAlturaVoo(int alturaVoo) {
        this.alturaVoo = alturaVoo;
    }

    public String voar(){
        return mover();
    }

    public String mover(){
        return super.mover()+" VOANDO";
    }

    @Override
    public String produzirSom(){
        return "AVE: "+super.produzirSom();
    }

}

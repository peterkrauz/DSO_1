package ex03_implementacao_oo;

public class Runn {

    public static void main(String[] args){
        Cachorro c = new Cachorro();
        Gato g = new Gato();
        Canarinho can = new Canarinho(5,7);

        System.out.println(c.latir());
        System.out.println(g.miar());
        System.out.println(can.cantar());
    }

}

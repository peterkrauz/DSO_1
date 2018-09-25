import java.util.ArrayList;

public class ControladorJogo implements IControladorJogo {

    private ArrayList<Personagem> personagens;
    private ArrayList<Carta> cartas;
    private ArrayList<Jogador> jogadores;
    private Mesa mesa;

    public ControladorJogo(){
        this.personagens = new ArrayList<>();
        this.cartas = new ArrayList<>();
        this.jogadores = new ArrayList<>();
    }


    /**
     * Permite incluir um novo Personagem na lista de personagens do jogo
     * @param energia Energia do novo Personagem
     * @param habilidade Habilidade do novo Personagem
     * @param velocidade Velocidade do novo Personagem
     * @param resistencia Resistencia do novo Personagem
     * @param tipo TipoPersonagem (Enum) do novo Personagem. Deve ser utilizado TipoPersonagem.TIPO
     * @return Retorna o Personagem incluido na lista
     */
    @Override
    public Personagem incluiPersonagemNaLista(int energia, int habilidade, int velocidade, int resistencia, TipoPersonagem tipo){
        Personagem personagem = new Personagem(energia, habilidade, velocidade, resistencia, tipo);
        if(!personagens.contains(personagem)){
            personagens.add(personagem);
        }
        return personagem;
    }

    /**
     * Permite incluir uma nova Carta no baralho do jogo
     * @param personagem Personagem da nova carta que sera incluida
     * @return Retorna a Carta que foi incluida no baralho
     */
    @Override
    public Carta incluiCartaNoBaralho(Personagem personagem){
        Carta carta = new Carta(personagem);
        if(!cartas.contains(carta)){
            cartas.add(carta);
        }
        return carta;
    }

    /**
     * Permite incluir um novo Jogador, que eh colocado na lista de jogadores
     * @param nome Nome do Jogador a ser incluido
     * @return Retorna o Jogador incluido na lista
     */
    @Override
    public Jogador incluiJogador(String nome){
        Jogador jogador = new Jogador(nome);
        if (!jogadores.contains(jogador)) {
            jogadores.add(jogador);
        }
        return jogador;
    }

    /**
     * Inicia o jogo, incluindo os dois jogadores na partida
     * Tambem distribui aleatoriamente 5 cartas do baralho para cada jogador
     *
     * @param jogador1 Jogador 1
     * @param jogador2 Jogador 2
     */
    @Override
    public void iniciaJogo(Jogador jogador1, Jogador jogador2){
        for( int i=0; i<cartas.size(); i++ ){
            if( i%2 == 0 ){
                jogador1.incluiCartaNaMao( cartas.get(i) );
            } else {
                jogador2.incluiCartaNaMao( cartas.get(i) );
            }
        }
        jogadores.add(jogador1);
        jogadores.add(jogador2);
    }

    /**
     * Realiza uma jogada, ou seja:
     * 1. Recebe a mesa onde estao as cartas lancadas pelo Jogador 1 e pelo Jogador 2
     * 2. Compara os valores totais das cartas dos jogadores
     * 3. Inclui na mao do Jogador vencedor a carta do perdedor
     * 4. Se o outro jogador perdedor nao tem mais cartas na mao, retorna o Jogador vencedor como vencedor da partida
     * @param mesa Mesa atual, contendo: Jogador 1, Jogador 2, Carta do Jogador 1 e Carta do Jogador 2
     * @return Retorna o Jogador vencedor como vencedor da partida. Se nenhum jogador venceu a partida, retorna NULL
     */
    @Override
    public Jogador jogada(Mesa mesa){
        Jogador j1 = mesa.getJogador1();
        Jogador j2 = mesa.getJogador2();
        Carta carta1 = mesa.getCartaJogador1();
        Carta carta2 = mesa.getCartaJogador2();
        if( carta1.getValorTotalCarta() > carta2.getValorTotalCarta() ){
            j1.incluiCartaNaMao( carta2 );
        } else {
            j2.incluiCartaNaMao( carta1 );
        }

        if( j1.getMao().isEmpty() ){
            return j2;
        } else if( j2.getMao().isEmpty() ){
            return j1;
        } else {
            return null;
        }

    }

}

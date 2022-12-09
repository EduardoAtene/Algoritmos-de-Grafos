package ForkLibary.Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AlgoritmProfundidade extends ForkEntity{
    public class AlgoritmProfundidadeModel{
        Integer profundEntrada;
        Integer profundSaida;
        Integer profundSaida;
    }
    public Map<Object,>;
    public Map<Object,ForkEntity.Vertice> vertice = new HashMap<Object,ForkEntity.Vertice>();
    public Map<Object,ForkEntity.Aresta> aresta = new HashMap<Object,ForkEntity.Aresta>();

    public boolean direction = false;

    public AlgoritmProfundidade (){
    }
    /**
     * Função que gera uma Matriz Vazia de um Grafo.
     * 
     * @param quantidadeVertice     A quantidade de Vérticer de um Grafo
     * 
     */
    private void gerarMatrizVazia(int quantidadeVertice){
        int aux = 1;

        for (int i = 0; i < quantidadeVertice; i++) {
            this.vertice.put(aux,new ForkEntity.Vertice(aux,aux,i));
            for (int j = 0; j < quantidadeVertice; j++) {
                this.matriz[i][j] = (double) 0;
            }
            aux++;
        }
    };

    /**
     * Função que rotula um vertice de um Grafo.
     * 
     * @param key           A chave do vértice a ser rotulado
     * @param vertice_2     Nova chave do vértice informado
     * @return              Retorna True caso realizou a inserção com Sucesso.
     * @                    Retorna False caso a operação não foi um  Sucesso. Ocorre caso haja a existência do vértice com o mesmo nome OU não existe o vertice instanciado
     * 
     */
    public boolean rotularVertice(Object key,Object nomeVertice){
        if(this.vertice.get(key) != null || this.vertice.get(nomeVertice) == null){
            ForkEntity.Vertice auxVertice;
            auxVertice = this.vertice.get(key);
            this.vertice.remove(key);
            this.vertice.put(nomeVertice,new ForkEntity.Vertice(    nomeVertice,
                                                                    auxVertice.getEnumeracao(),
                                                                    auxVertice.getPosicao() ) );
            return true;
        }
        return false;
    };

    /**
     * Função que realiza a ponderação de um Vértice de um Grafo.
     * 
     * @param vertice     A chave do Vértice
     * @param peso          O Peso da Ponderação
     * @return              Retorna True caso realizou a inserção com Sucesso.
     * @                    Retorna False caso a operação não foi um  Sucesso. Ocorre quando não existe Vertice informado.
     */
    public boolean a ( Object vertice, Double peso) {
        if( this.doExistVertice(vertice)){
            this.vertice.get(vertice).setPonderamento(peso);
            return true;
        }
        return false;
    }

    /**
     * Função que realiza a inserção de Aresta em um Grafo Ponderado.
     * 
     * @param vertice_1     A chave do vértice 1
     * @param vertice_2     A chave do vértice 2
     * @param peso          O Peso da Adjacência
     * @return              Retorna True caso realizou a inserção com Sucesso.
     * @                    Retorna False caso a operação não foi um  Sucesso. Ocorre quando não existe Vertice informado.
     */
    public boolean inserirAresta ( Object vertice_1, Object vertice_2, Double peso) {
        if( this.doExistVertice(vertice_1) && this.doExistVertice(vertice_2) ){
            ForkEntity.Vertice auxVertice_1,auxVertice_2;
            auxVertice_1 = this.vertice.get(vertice_1);
            auxVertice_2 = this.vertice.get(vertice_2);
            this.matriz[auxVertice_1.getPosicao()] [auxVertice_2.getPosicao()] = peso;
            this.matriz[auxVertice_2.getPosicao()] [auxVertice_1.getPosicao()] = peso;
            return true;
        }
        return false;
    }

    /**
     * Função que realiza a inserção de Aresta em um Grafo Não Ponderado.
     * Por default, para a representação de adjacência, o valor será 1.
     * 
     * @param vertice_1     A chave do vértice 1
     * @param vertice_2     A chave do vértice 2
     * @return              Retorna True caso realizou a operação com Sucesso.
     * @                    Retorna False caso a operação não foi um  Sucesso. Ocorre quando não existe Vertice informado.
     */
    public boolean inserirAresta ( Object vertice_1, Object vertice_2) {
        return this.inserirAresta(vertice_1,vertice_2,(double) 1);
    }

    /**
     * Função que verifica existência do Vertice.
     * 
     * @param keyVertice     A chave do vértice
     * @return              Retorna True caso realizou a operação com Sucesso.
     * @                    Retorna False caso a operação não foi um  Sucesso. Ocorre quando não existe Vertice informado.
     */
    public boolean doExistVertice ( Object keyVertice) {
        if(this.vertice.get(keyVertice) != null)
            return true; // existe vertice
        return false;
    }
    
    
    public void gerarGrafico(){

    };

    public void removeVertice(){

    };
    public void removeAresta(){

    };
}
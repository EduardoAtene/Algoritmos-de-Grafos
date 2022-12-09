package ForkLibary.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Entity;

import java.util.Map;
import ForkLibary.Entity.ForkEntity.Aresta;
import ForkLibary.Entity.Arvore.VerticesElement;

/**
 * Arvore Elemento
 */
public class ArvoreElement<TIPO extends Arvore.VerticesElement> {
    public Arvore.VerticesElement aresta;
    public ArvoreElement<Arvore.VerticesElement> keyDireita;
    public ArvoreElement<Arvore.VerticesElement> keyEsquerda;

    public ArvoreElement (Arvore.VerticesElement newAresta){
        this.aresta = newAresta;
        this.keyDireita = null;
        this.keyEsquerda = null;
    }

    public Arvore.VerticesElement getAresta() {
        return aresta;
    }
    public void setAresta(Arvore.VerticesElement aresta) {
        this.aresta = aresta;
    }

    public ArvoreElement<Arvore.VerticesElement> getKeyDireita() {
        return keyDireita;
    }
    public void setKeyDireita(ArvoreElement<Arvore.VerticesElement> keyDireita) {
        this.keyDireita = keyDireita;
    }

    public ArvoreElement<Arvore.VerticesElement> getKeyEsquerda() {
        return keyEsquerda;
    }
    public void setKeyEsquerda(ArvoreElement<Arvore.VerticesElement> keyEsquerda) {
        this.keyEsquerda = keyEsquerda;
    }

}


    public ArvoreElement<Arvore.VerticesElement> raiz;
    public boolean direction = false;

    public Map<Object,Integer> PE;
    public Map<Object,Integer> PS;
    public Integer interactionAux;

    public Map<Object,VerticesElement> verticesVisitados;


    public class VerticesElement extends ForkEntity.Vertice {

        public Boolean explorado;
        public Map<Object,ArestaElement> arestasExploradas;

        public class ArestaElement extends ForkEntity.Aresta {

            public Boolean retorno;

            public ArestaElement(ForkEntity.Aresta aresta,Boolean retorno) {
                super(aresta.getVertice_1(),aresta.getVertice_2(),aresta.getPeso(),aresta.isDirecionado(),aresta.getPosicao());
                this.retorno = retorno;
                
            }

        }
        public VerticesElement(Object nome, int enumeracao, int posicao) {
            super(nome, enumeracao, posicao);
            this.explorado = false;
            this.arestasExploradas = new HashMap<Object,ArestaElement>();        
        }
        public VerticesElement(ForkEntity.Vertice vertice) {
            super(vertice.getNome(), vertice.getEnumeracao(), vertice.getPosicao());
            this.setPonderamento(vertice.getPonderamento());
            this.explorado = false;
            this.arestasExploradas = new HashMap<Object,ArestaElement>();        

        }

        public Boolean getExplorado() {
            return explorado;
        }
        public void setExplorado(Boolean explorado) {
            this.explorado = explorado;
        }

        public Map<Object, ArestaElement> getArestasExploradas() {
            return arestasExploradas;
        }
        public void setArestasExploradas(ForkEntity.Aresta aresta, Boolean retorno) {
            this.arestasExploradas.put(aresta.getVertice_1(), new ArestaElement(aresta, retorno));
        }
        
    }
    


    public Arvore() {
        this.interactionAux = 0;        
        this.PE = new HashMap<Object,Integer>();        
        this.PS = new HashMap<Object,Integer>();        
        this.verticesVisitados = new HashMap<Object,VerticesElement>();        
    }


    public void newChild (ForkEntity.Aresta aresta,Boolean direita){
        if(raiz == null){
            this.raiz = new ArvoreElement<ForkEntity.Aresta>(aresta);
        }else{
            if(direita){

            }else{

            }
            while (true) {
                
            }
        }
        this.quantidadeVertices = quantidadeVertice;

        // Geração da Matriz Vazia
        this.matriz = new Double [quantidadeVertice] [quantidadeVertice] ;
        this.gerarMatrizVazia(quantidadeVertice);
    }
    

    public void busca (ForkListAdjacencia grafo,ForkEntity.Vertice vertice,Boolean direcionado,Object daddyVertice){

        this.PE.put(vertice.getNome(), this.interactionAux);

        // if(raiz == null){
            // this.raiz = new ArvoreElement<ForkEntity.Aresta>(aresta);
            // this.interactionAux = 1;
        // }else{
        // }
        ArrayList<ForkEntity.Aresta> conjuntoAdjacencia = grafo.getVerticesAdjacentesByVerticesDaddy(vertice.getNome(), direcionado, daddyVertice);
        for (ForkEntity.Aresta arestaAdj : conjuntoAdjacencia) {
            
            this.interactionAux ++;

            if(direcionado){
            }else{
                ForkEntity.Vertice v = this.verticesVisitados.get(arestaAdj.getVertice_2());
                if(v == null){
                    ForkEntity.Vertice vertAdj = grafo.getVertice(arestaAdj.getVertice_2());
                    VerticesElement vElement = new VerticesElement(vertAdj);
                    vElement.setArestasExploradas(arestaAdj,false);
                    if(this.verticesVisitados.size() == 0){
                        this.interactionAux = 1;
                    }
                    this.verticesVisitados.put(vElement.getNome(), vElement);
                    busca(grafo,vertAdj,direcionado,vertice.getNome());
                }else{
                    this.verticesVisitados.get(arestaAdj.getVertice_2()).setArestasExploradas(arestaAdj, true);
                }
            }
        }
        this.verticesVisitados.get(vertice.getNome()).setExplorado(true);
        this.PS.put(vertice.getNome(), this.interactionAux);


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
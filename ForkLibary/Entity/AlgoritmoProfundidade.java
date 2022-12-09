package ForkLibary.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ForkLibary.Entity.ForkEntity.Vertice;

public class AlgoritmoProfundidade{

    public ArvoreElement<VerticesElement> raiz;
    ForkListAdjacencia grafoListaAdjacencia;

    public boolean direction = false;

    public Map<Object,Integer> PE;
    public Map<Object,Integer> PS;
    public Integer interactionAux;

    public Object verticeInicio;
    public Object verticeFinal;

    public boolean hasCaminho;

    public ArrayList<Object> verticesVisitado;
    public ArrayList<ArrayList<Object>> arestasVisitadas;


    public class VerticesElement extends ForkEntity.Vertice {

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
            this.arestasExploradas = new HashMap<Object,ArestaElement>();        
        }
        public VerticesElement(ForkEntity.Vertice vertice) {
            super(vertice.getNome(), vertice.getEnumeracao(), vertice.getPosicao());
            this.setPonderamento(vertice.getPonderamento());
            this.arestasExploradas = new HashMap<Object,ArestaElement>();        

        }

        public Map<Object, ArestaElement> getArestasExploradas() {
            return arestasExploradas;
        }
        public void setArestasExploradas(ForkEntity.Aresta aresta, Boolean retorno) {
            this.arestasExploradas.put(aresta.getVertice_1(), new ArestaElement(aresta, retorno));
        }
        
    }
    
    /**
     * Arvore Elemento
     */
    public class ArvoreElement<TIPO extends AlgoritmoProfundidade.VerticesElement> {
        public ForkEntity.Aresta arestaP;
        public AlgoritmoProfundidade.VerticesElement vertice;
        public ArvoreElement<AlgoritmoProfundidade.VerticesElement> keyDireita;
        public ArvoreElement<AlgoritmoProfundidade.VerticesElement> keyEsquerda;

        public ArvoreElement (TIPO newAresta){
            this.vertice = newAresta;
            this.arestaP = null;
            this.keyDireita = null;
            this.keyEsquerda = null;
        }
        public ArvoreElement (){
            this.vertice = null;
            this.arestaP = null;
            this.keyDireita = null;
            this.keyEsquerda = null;
        }
        public ArvoreElement (TIPO newAresta,ForkEntity.Aresta arestaP){
            this.vertice = newAresta;
            this.arestaP = arestaP;
            this.keyDireita = null;
            this.keyEsquerda = null;
        }

        public ForkEntity.Aresta getAresta() {
            return this.arestaP;
        }

        public void setAresta(ForkEntity.Aresta arestaPrincipal) {
            this.arestaP = arestaPrincipal;
        }
        
        public ArvoreElement<AlgoritmoProfundidade.VerticesElement> getKeyDireita() {
            return keyDireita;
        }
        public void setKeyDireita(ArvoreElement<AlgoritmoProfundidade.VerticesElement> keyDireita) {
            this.keyDireita = keyDireita;
        }

        public ArvoreElement<VerticesElement> getKeyEsquerda() {
            return keyEsquerda;
        }
        public void setKeyEsquerda(ArvoreElement<VerticesElement> keyEsquerda) {
            this.keyEsquerda = keyEsquerda;
        }
        public void setVer(TIPO keyEsquerda) {
            this.vertice = keyEsquerda;
        }
    }


    public AlgoritmoProfundidade() {
        this.raiz = null;
        this.interactionAux = 0;        
        this.PE = new HashMap<Object,Integer>();        
        this.PS = new HashMap<Object,Integer>();          
        this.verticesVisitado = new ArrayList<Object>();        
        this.arestasVisitadas = new ArrayList<ArrayList<Object>>();        

    }

    public void buscaProfundidade (ForkListAdjacencia grafo,ForkEntity.Vertice vertice,Boolean direcionado,ArvoreElement<AlgoritmoProfundidade.VerticesElement> atual){
        VerticesElement vElement = new VerticesElement(vertice);
        this.PE.put(vertice.getNome(), this.interactionAux);
        if(!this.verticesVisitado.contains(vertice.getNome())){
            this.verticesVisitado.add(vertice.getNome());
            if(atual == null){
                this.raiz = new ArvoreElement<AlgoritmoProfundidade.VerticesElement>(vElement);
                this.interactionAux = 1;
                atual = this.raiz;
            }
                atual.setVer(vElement);
    
        }
        ArrayList<ForkEntity.Aresta> conjuntoAdjacencia = grafo.getVerticesAdjacentesByVerticesDaddy(vertice.getNome(), direcionado, null);

        for (ForkEntity.Aresta arestaAdj : conjuntoAdjacencia) {
            if(!arestaVisitada(arestaAdj.getVertice_1(),arestaAdj.getVertice_2())){
                if(!this.verticesVisitado.contains(arestaAdj.getVertice_1())){
                    if(arestaAdj.getVertice_1() == this.verticeFinal || arestaAdj.getVertice_2() == this.verticeFinal )
                        this.hasCaminho = true;
                    this.interactionAux ++;
            
                    // Armazenar a visitação da aresta
                    ArrayList<Object> arestVistAux = new ArrayList<Object>();
                    arestVistAux.add(arestaAdj.getVertice_1());
                    arestVistAux.add(arestaAdj.getVertice_2());
                    this.arestasVisitadas.add(arestVistAux);

                    ForkEntity.Vertice vertAdj = grafo.getVertice(arestaAdj.getVertice_1());

                    vElement.setArestasExploradas(arestaAdj,false);
                    if(atual.getKeyEsquerda() == null){
                        atual.setKeyEsquerda(new ArvoreElement<AlgoritmoProfundidade.VerticesElement>());
                        ArvoreElement<AlgoritmoProfundidade.VerticesElement> atualE = atual.getKeyEsquerda();
                        atualE.setAresta(arestaAdj);
                        buscaProfundidade(grafo,vertAdj,direcionado,atualE);

                    }else if (atual.getKeyDireita() == null){
                        atual.setKeyDireita(new ArvoreElement<AlgoritmoProfundidade.VerticesElement>());
                        ArvoreElement<AlgoritmoProfundidade.VerticesElement> atualR = atual.getKeyDireita();
                        atualR.setAresta(arestaAdj);
                        buscaProfundidade(grafo,vertAdj,direcionado,atualR);
                    }

                }
            }
        }
        this.PS.put(vertice.getNome(), this.interactionAux);


    }

    public void emOrdem(ArvoreElement<VerticesElement> atual){
        if (atual != null){
            emOrdem(atual.getKeyEsquerda());
            System.out.println(atual.getAresta().getVertice_1());
            emOrdem(atual.getKeyDireita());
        }        
    }
    
    public void preOrdem(ArvoreElement<VerticesElement>atual){
        if (atual != null){
            System.out.println(atual.vertice.getNome());
            preOrdem(atual.getKeyEsquerda());            
            preOrdem(atual.getKeyDireita());            
        }        
    }
    
    public void posOrdem(ArvoreElement<VerticesElement> atual){
        if (atual != null){            
            posOrdem(atual.getKeyEsquerda());            
            posOrdem(atual.getKeyDireita());
            System.out.println(atual.getAresta().getVertice_1());

        }        
    }

    public Boolean arestaVisitada(Object vertice1, Object vertice2){
        for (int i = 0; i < this.arestasVisitadas.size(); i++) {
            if(this.arestasVisitadas.get(i).contains(vertice1) && this.arestasVisitadas.get(i).contains(vertice2))
                return true;
        }   
        return false;     
    }
    
    public ArvoreElement<VerticesElement> getArvoreProfundidade(){
        return this.raiz;
    };

    
    public void setGrafoAdjacencia(ForkListAdjacencia grafoListaAdjacencia){
        this.grafoListaAdjacencia = grafoListaAdjacencia;

    };

    public Boolean removeArestaGrafo(Object vertice1, Object vertice2){
        this.verticeInicio = vertice1;
        this.verticeFinal = vertice2;
        return this.grafoListaAdjacencia.removeAresta(vertice1, vertice2);
    };
    public Boolean isPonte(){
        Vertice vInicio = this.grafoListaAdjacencia.getVertice(this.verticeInicio);
        this.hasCaminho = false;

        buscaProfundidade(this.grafoListaAdjacencia,vInicio,false,null);
        return !this.hasCaminho;
    }
}
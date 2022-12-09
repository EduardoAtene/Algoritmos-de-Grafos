package ForkLibary.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Entity;

import java.util.Map;
import ForkLibary.Entity.ForkEntity.Aresta;

public class ArvoreProfundidade{

    public ArvoreElement<VerticesElement> raiz;
    public boolean direction = false;

    public Map<Object,Integer> PE;
    public Map<Object,Integer> PS;
    public Integer interactionAux;

    public Map<Object,VerticesElement> verticesVisitados;
    public ArrayList<Object> verticesVisitado;
    public ArrayList<ArrayList<Object>> arestasVisitadas;
    public ArrayList<ForkEntity.Aresta> arestasPontes;


    public class VerticesElement extends ForkEntity.Vertice {

        public Boolean explorado;
        public Integer bloco;
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

        public void setBloco(Integer bloco) {
            this.bloco = bloco;
        }

        public Integer getBloco() {
            return this.bloco;
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
    public class ArvoreElement<TIPO extends ArvoreProfundidade.VerticesElement> {
        public ForkEntity.Aresta arestaP;
        public ArvoreProfundidade.VerticesElement vertice;
        public Boolean isPonte;
        public ArvoreElement<ArvoreProfundidade.VerticesElement> keyDireita;
        public ArvoreElement<ArvoreProfundidade.VerticesElement> keyEsquerda;

        public ArvoreElement (TIPO newAresta){
            this.vertice = newAresta;
            this.arestaP = null;
            this.keyDireita = null;
            this.isPonte = true;
            this.keyEsquerda = null;
        }
        public ArvoreElement (){
            this.vertice = null;
            this.arestaP = null;
            this.keyDireita = null;
            this.isPonte = true;
            this.keyEsquerda = null;
        }
        public ArvoreElement (TIPO newAresta,ForkEntity.Aresta arestaP){
            this.vertice = newAresta;
            this.arestaP = arestaP;
            this.keyDireita = null;
            this.keyEsquerda = null;
            this.isPonte = true;
        }

        public ForkEntity.Aresta getAresta() {
            return this.arestaP;
        }

        public void setAresta(ForkEntity.Aresta arestaPrincipal) {
            this.arestaP = arestaPrincipal;
        }

        public void setIsPonte(Boolean value) {
            this.isPonte = value;
        }

        public Boolean isPonte() {
            return this.isPonte;
        }
        
        public ArvoreElement<ArvoreProfundidade.VerticesElement> getKeyDireita() {
            return keyDireita;
        }
        public void setKeyDireita(ArvoreElement<ArvoreProfundidade.VerticesElement> keyDireita) {
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


    public ArvoreProfundidade() {
        this.raiz = null;
        this.interactionAux = 0;        
        this.PE = new HashMap<Object,Integer>();        
        this.PS = new HashMap<Object,Integer>();        
        this.verticesVisitados = new HashMap<Object,VerticesElement>();        
        this.verticesVisitado = new ArrayList<Object>();        
        this.arestasVisitadas = new ArrayList<ArrayList<Object>>();        
        this.arestasPontes = new ArrayList<ForkEntity.Aresta>();        

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
    

    public void busca (ForkListAdjacencia grafo,ForkEntity.Vertice vertice,Boolean direcionado,Object daddyVertice,ArvoreElement<ArvoreProfundidade.VerticesElement> atual,Integer bloco){
        VerticesElement vElement = new VerticesElement(vertice);
        vElement.setBloco(bloco);

        this.PE.put(vertice.getNome(), this.interactionAux);

        if(!this.verticesVisitado.contains(vertice.getNome())){

            this.verticesVisitado.add(vertice.getNome());
            if(atual == null){
                this.raiz = new ArvoreElement<ArvoreProfundidade.VerticesElement>(vElement);

                this.interactionAux = 1;
    
                atual = this.raiz;

                // busca(grafo,vertice,direcionado,grafo.getVertice(arestaAdj.getVertice_1()),atual,bloco);
            }
                atual.setVer(vElement);
            


        }
        ArrayList<ForkEntity.Aresta> conjuntoAdjacencia = grafo.getVerticesAdjacentesByVerticesDaddy(vertice.getNome(), direcionado, null);

        for (ForkEntity.Aresta arestaAdj : conjuntoAdjacencia) {
            if(!arestaVisitada(arestaAdj.getVertice_1(),arestaAdj.getVertice_2())){
                if(!this.verticesVisitado.contains(arestaAdj.getVertice_1())){
                    this.interactionAux ++;
                    bloco++;
            
                    // Armazenar a visitação da aresta
                    ArrayList<Object> arestVistAux = new ArrayList<Object>();
                    arestVistAux.add(arestaAdj.getVertice_1());
                    arestVistAux.add(arestaAdj.getVertice_2());
                    this.arestasVisitadas.add(arestVistAux);

                    ForkEntity.Vertice vertAdj = grafo.getVertice(arestaAdj.getVertice_1());
                    // VerticesElement vElement = new VerticesElement(vertAdj);
                    // this.verticesVisitado.add(arestaAdj.getVertice_1());

                    vElement.setArestasExploradas(arestaAdj,false);
                    if(atual.getKeyEsquerda() == null){
                        atual.setKeyEsquerda(new ArvoreElement<ArvoreProfundidade.VerticesElement>());
                        ArvoreElement<ArvoreProfundidade.VerticesElement> atualE = atual.getKeyEsquerda();
                        atualE.setAresta(arestaAdj);
                        busca(grafo,vertAdj,direcionado,vertice.getNome(),atualE,bloco);

                    }else if (atual.getKeyDireita() == null){
                        atual.setKeyDireita(new ArvoreElement<ArvoreProfundidade.VerticesElement>());
                        ArvoreElement<ArvoreProfundidade.VerticesElement> atualR = atual.getKeyDireita();
                        atualR.setAresta(arestaAdj);
                        busca(grafo,vertAdj,direcionado,vertice.getNome(),atualR,bloco);
                    }

                }else{
                    preOrdemFind(this.raiz,arestaAdj.getVertice_1(),null);
                    // if(aux != null){
                    //     setFindCicloBack(aux,aux.vertice.getBloco());
                    // }
                }
            }
        }
        
        
        // this.verticesVisitados.get(vertice.getNome()).setExplorado(true);
        this.PS.put(vertice.getNome(), this.interactionAux);


    }
    public void setFindCicloBack(ArvoreElement<VerticesElement> atual,Integer Bloco){
        if (atual != null){
            atual.vertice.setBloco(Bloco);
            setFindCicloBack(atual.getKeyEsquerda(),Bloco);
            setFindCicloBack(atual.getKeyDireita(),Bloco);
        }        
    }

    public void emOrdem(ArvoreElement<VerticesElement> atual){
        if (atual != null){
            emOrdem(atual.getKeyEsquerda());
            System.out.println(atual.getAresta().getVertice_1());
            emOrdem(atual.getKeyDireita());
        }        
    }
    
    public void preOrdemPrint(ArvoreElement<VerticesElement>atual){
        if (atual != null){
            System.out.println(atual.vertice.getNome() +" | "+ atual.vertice.getBloco());
            // ArvoreElement<Arvore.VerticesElement> atualE = atual.getKeyEsquerda();
            // preOrdemPrint(atualE.getKeyEsquerda());            
            preOrdemPrint(atual.getKeyEsquerda());            
            preOrdemPrint(atual.getKeyDireita());            
            // ArvoreElement<Arvore.VerticesElement> atualR = atual.getKeyDireita();
            // preOrdemPrint(atualR.getKeyDireita());
        }        
    }

    public void printPontes(){
        for (ForkEntity.Aresta pontes : getArestasPontes()) {
            System.out.println(pontes.getVertice_1() + "-> " + pontes.getVertice_2());
        }
    }
    
    public void preOrdemFind(ArvoreElement<VerticesElement> atual, Object verticeElemento, Integer bloco){
        if (atual != null){
            if(atual.vertice.getNome() == verticeElemento){
                bloco = atual.vertice.getBloco();
            }

            if(bloco != null){
                atual.vertice.setBloco(bloco);
            }
            preOrdemFind(atual.getKeyEsquerda(),verticeElemento,bloco);
            preOrdemFind(atual.getKeyDireita(),verticeElemento,bloco);
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
    
    public ArrayList<ForkEntity.Aresta> getArestasPontes(){
        this.arestasPontes = new ArrayList<ForkEntity.Aresta>();        
        posOrdemSearchPonte(this.raiz);
        return this.arestasPontes;
    }
    public void posOrdemSearchPonte(ArvoreElement<VerticesElement> atual){
        if (atual != null){
            if(atual.getKeyEsquerda() != null){
                if(atual.vertice.getBloco() != atual.getKeyEsquerda().vertice.getBloco()){
                    this.arestasPontes.add(atual.getKeyEsquerda().getAresta());
                }
                posOrdemSearchPonte(atual.getKeyEsquerda());

            }
            if(atual.getKeyDireita() != null){
                if(atual.vertice.getBloco() != atual.getKeyDireita().vertice.getBloco()){
                    this.arestasPontes.add(atual.getKeyDireita().getAresta());
                }
                posOrdemSearchPonte(atual.getKeyDireita());

            }
        }
    }
    

    public ArvoreElement<VerticesElement> getArvoreProfundidade(){
        return this.raiz;
    };
}
package ForkLibary.Entity;

import java.util.ArrayList;
import java.util.Map;

import ForkLibary.Entity.ForkEntity.Vertice;
import ForkLibary.Entity.ForkListAdjacencia.grauVerticesEntry;

public class AlgoritmoFleury{
    public Boolean isEureliano;

    ForkListAdjacencia grafoListaAdjacenciaAux;

    public Map<Object,grauVerticesEntry> verticesGrau;
    public ArrayList<Object> verticesGrauImpar;
    public ArrayList<Object> verticesGrauPar;

    public ArrayList<Object> caminho;

    public AlgoritmoFleury(ForkListAdjacencia grafoListaAdjacencia){
        this.grafoListaAdjacenciaAux = grafoListaAdjacencia;
        this.verticesGrau = grafoListaAdjacencia.getGrausGrafos();
        this.dividirPorTipoNumero();
        this.caminho = new ArrayList<>();

        if(this.condicoesExistEureliano()){
            this.isEureliano = true;

            if(this.verticesGrauImpar.size() == 0){
                for (Object verticesInicio : verticesGrauPar) {
                    getCaminhoEureliano(this.grafoListaAdjacenciaAux,verticesInicio);
                    break; // Pegar Só um Caminho.
                }
            }else{
                for (Object verticesInicio : verticesGrauImpar) {
                    getCaminhoEureliano(this.grafoListaAdjacenciaAux,verticesInicio);
                    break; // Pegar Só um Caminho.
                }
            }
        }else{
            this.isEureliano = false;
        }
    }

    public void getCaminhoEureliano(ForkListAdjacencia grafoListaAdjacencia,Object verticeR){

        ArvoreTarjan arvoreProfundidade = new ArvoreTarjan();
        Vertice verticeRaiz = grafoListaAdjacencia.getVertice(verticeR);
        arvoreProfundidade.buscaTarjan(grafoListaAdjacencia,verticeRaiz, false,null,null,1);
        ArrayList<ForkEntity.Aresta> pontesArvore = arvoreProfundidade.getArestasPontes();

        ArrayList<ForkEntity.Aresta> conjuntoAdjacencia = grafoListaAdjacencia.getVerticesAdjacentesByVertices(verticeR, false, false);

        if(conjuntoAdjacencia.size() != 0){
            for (ForkEntity.Aresta arestaAdj : conjuntoAdjacencia) {
                Boolean isPonte = isArestaPonte(arestaAdj,pontesArvore);
                if(!isPonte || (isPonte && conjuntoAdjacencia.size() == 1)){
                    this.verticesGrau.get(arestaAdj.getVertice_1()).setGrau(true);
                    this.verticesGrau.get(arestaAdj.getVertice_2()).setGrau(true);
                    this.caminho.add(verticeR);
                    grafoListaAdjacencia.removeAresta(arestaAdj.getVertice_1(),arestaAdj.getVertice_2());
                    getCaminhoEureliano(grafoListaAdjacencia,arestaAdj.getVertice_2());
                    break;
                }
            }
        }else{
            this.caminho.add(verticeR);
        }


    }

    private void dividirPorTipoNumero(){
        this.verticesGrauImpar = new ArrayList<>();
        this.verticesGrauPar = new ArrayList<>();
        for (Map.Entry<Object,grauVerticesEntry> vertice : this.verticesGrau.entrySet()) {    
            if( (vertice.getValue().getGrau() % 2 == 0) ){
                this.verticesGrauPar.add(vertice.getKey());
            }else{
                this.verticesGrauImpar.add(vertice.getKey());
            }
        }
    }

    private Boolean condicoesExistEureliano(){
        if(this.verticesGrauImpar.size() == 0){
            return true;
        }
        if(this.verticesGrauImpar.size() == 2){
            return true;    
        }
        return false;
    }

    private Boolean isArestaPonte(ForkEntity.Aresta aresta,ArrayList<ForkEntity.Aresta> pontes){
        for (ForkEntity.Aresta ArestaPonte : pontes) {
            if( (aresta.getVertice_1() == ArestaPonte.getVertice_1() && aresta.getVertice_2() == ArestaPonte.getVertice_2() ) ||
                (aresta.getVertice_1() == ArestaPonte.getVertice_2() && aresta.getVertice_2() == ArestaPonte.getVertice_1() )    ){
                    return true;
                }
        }
        return false;
    }
}
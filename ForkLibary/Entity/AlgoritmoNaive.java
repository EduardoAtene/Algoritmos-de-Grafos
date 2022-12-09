package ForkLibary.Entity;

import java.util.ArrayList;
import java.util.Map;

import ForkLibary.Entity.ForkEntity.Vertice;
import ForkLibary.Entity.ForkListAdjacencia.grauVerticesEntry;

public class AlgoritmoNaive{
    public Boolean isEureliano;

    ForkListAdjacencia grafoListaAdjacenciaAux;

    public Map<Object,grauVerticesEntry> verticesGrau;
    public ArrayList<ForkEntity.Aresta> arestasExploradas;
    public ArrayList<ForkEntity.Aresta> arestasPontes;
    public ArrayList<Object> verticesGrauPar;

    public ArrayList<Object> caminho;

    public AlgoritmoNaive(ForkListAdjacencia grafoListaAdjacencia){
        this.arestasExploradas = new ArrayList<ForkEntity.Aresta>();
        this.arestasPontes = new ArrayList<ForkEntity.Aresta>();
        this.grafoListaAdjacenciaAux =  new ForkListAdjacencia(grafoListaAdjacencia);
        ArrayList<ForkEntity.Aresta> conjuntoAdjacencia = grafoListaAdjacencia.getAllAjacencia();
        for (ForkEntity.Aresta arestaAdj : conjuntoAdjacencia) {
            AlgoritmoProfundidade algProfundidade = new AlgoritmoProfundidade();
            algProfundidade.setGrafoAdjacencia(grafoListaAdjacencia);
            algProfundidade.removeArestaGrafo(arestaAdj.getVertice_1(),arestaAdj.getVertice_2());
            if(algProfundidade.isPonte()){
                arestasPontes.add(arestaAdj);
            };
            arestasExploradas.add(arestaAdj);
            grafoListaAdjacenciaAux.inserirAresta(arestaAdj.getVertice_1(),arestaAdj.getVertice_2());

        }
    }

    public ArrayList<ForkEntity.Aresta> getArestasPontes(){
        return this.arestasPontes;
    }
}
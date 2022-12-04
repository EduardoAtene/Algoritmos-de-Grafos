package ForkLibary.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ForkLibary.Entity.ForkEntity.Aresta;

public class ForkListAdjacencia extends ForkEntity{

    public Map<Object,LinkedList<
                        Map<Object,ForkEntity.Aresta>
                                >> listaAdjacencia = new HashMap<Object,LinkedList<Map<Object,ForkEntity.Aresta>>>();
    
    
    // private static class AdjacenciaLista {
    //     // public LinkedList<Map<Object,ForkEntity.Aresta>> listaAdjacencia = new HashMap<Object,LinkedList<Map<Object,ForkEntity.Aresta>>>();
    //     public ArestaDirecao ( Object vertice_Inicial, Object vertice_Final) {
    //         this.vertice_Inicial = vertice_Inicial;
    //         this.vertice_Final = vertice_Final;
    //     }
        
    //     public Object getVerticeInicial( ){ 
    //         return this.vertice_Inicial; 
    //     }
    //     public Object getVerticeFinal( ){ 
    //         return this.vertice_Final; 
    //     }
    //     public void invertDirection( ){ 
    //         Object aux;
            
    //         aux = this.vertice_Inicial; 
    //         this.vertice_Inicial = this.vertice_Final; 
    //         this.vertice_Final = aux ; 
    //     }
    // }

    public Map<Object,ForkEntity.Vertice> vertice = new HashMap<Object,ForkEntity.Vertice>();
    // public LinkedList<LinkedList<Integer>> listaAdjacencia 

    public Double[][] matriz;

    public ForkListAdjacencia() {
    }
    public ForkListAdjacencia (int quantidadeVertice){
        this.quantidadeVertices = quantidadeVertice;

        // Set Vertices Infomacoes
        this.setInformacaoVertices(quantidadeVertice);
    }


    private void setInformacaoVertices (int quantidadeVertice){
        int aux = 1;

        for (int i = 0; i < quantidadeVertice; i++) {
            this.vertice.put(aux,new ForkEntity.Vertice(aux,aux,i));
            this.listaAdjacencia.put(aux,new LinkedList<Map<Object, Aresta>>());
            aux++;
        }
    }

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
        if(this.vertice.get(key) != null && this.vertice.get(nomeVertice) == null){
            ForkEntity.Vertice auxVertice;
            ArrayList<ForkEntity.Aresta> arrayListAdjacenciaVertice = this.getVerticesAdjacentesByVertices(key,false,true);
            LinkedList<Map<Object, Aresta>> auxListAdj = this.listaAdjacencia.get(key);

            auxVertice = this.vertice.get(key);
            this.vertice.put(nomeVertice,this.vertice.remove(key));
            this.listaAdjacencia.remove(key);
            this.listaAdjacencia.put(nomeVertice,auxListAdj);

            

                                           
            for (ForkEntity.Aresta aresta : arrayListAdjacenciaVertice) {
                // if(!this.doVerticeExistAdjacencia(nomeVertice))
                    // this.listaAdjacencia.put(nomeVertice, vertice.get(nomeVertice));
                // this.listaAdjacencia.put(nomeVertice, new LinkedList<Map<Object,ForkEntity.Aresta>>());
                    
                if(aresta.getVertice_1().equals(key)){
                    Map<Object,ForkEntity.Aresta> arestaAux = new HashMap<Object,ForkEntity.Aresta>();

                    arestaAux.put(nomeVertice,aresta);
                    this.listaAdjacencia.get(aresta.getVertice_2()).add(arestaAux);
                    // this.listaAdjacencia.remove(aresta.getVertice_2()).remove(aresta.getPosicao());

                }
            }
            return true;
        }
        return false;
    };
    
    public Integer getCountAdjacentesVertices(Object vertice,Boolean grafoDirecionado){
        Integer quantidade = 0;
        if(this.vertice.get(vertice) != null ){
            if(this.doVerticeExistAdjacencia(vertice)){
                if(grafoDirecionado){
                    LinkedList<Map<Object, Aresta>> lista = this.listaAdjacencia.get(vertice);
                    if(lista != null){
                        int tamanho =  lista.size();
                        for (int i = 0; i < tamanho; i++) {            
                            for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.get(i).entrySet()) {
                                if( aresta.getValue().getVertice_1() == vertice || aresta.getValue().getVertice_2() == vertice){
                                    quantidade++;
                                }
                            }
                        } 
                    }
                }else{
                    for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
                        int tamanho =  lista.getValue().size();
                        for (int i = 0; i < tamanho; i++) {            
                            for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                                if( aresta.getValue().getVertice_1() == vertice || aresta.getValue().getVertice_2() == vertice){
                                    quantidade++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return quantidade;     
    };

    public Integer getCountVertices(){
        
        return this.vertice.size();
    };

    public ArrayList<ForkEntity.Aresta> getVerticesAdjacentesByVertices(Object vertice,Boolean includeHas,Boolean removeAllAdij){
        ArrayList<ForkEntity.Aresta> keysVertices = new ArrayList<ForkEntity.Aresta>(); // Create an ArrayList object
        if(this.vertice.get(vertice) != null ){
            if(this.doVerticeExistAdjacencia(vertice)){
                for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
                    if(lista.getValue() != null){
                        int tamanho =  lista.getValue().size();
                        for (int i = 0; i < tamanho; i++) {
                            
           
                        for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                            if(includeHas){
                                if(aresta.getValue().getVertice_1() == vertice){
                                    keysVertices.add(aresta.getValue());
                                    // if(removeAllAdij)
                                    //     this.listaAdjacencia.get(lista.getKey()).remove(i);
                                }
                            }
                            if(aresta.getValue().getVertice_1() == vertice){
                                keysVertices.add(aresta.getValue());
                                if(removeAllAdij){
                                    this.listaAdjacencia.get(lista.getKey()).remove(i);                                    
                                }
                            }
                            // keysVertices.add(aresta.getValue());

                        }
                        } 
                    }
                }
                return keysVertices; //Retornando adjacencias
            }else{
                return keysVertices; //Vertice não possui adjacencia
            }
        }else{
            return keysVertices; //Vertice não existe
        }      
    };

    /**
     * Função que realiza a ponderação de um Vértice de um Grafo.
     * 
     * @param vertice     A chave do Vértice
     * @param peso          O Peso da Ponderação
     * @return              Retorna True caso realizou a inserção com Sucesso.
     * @                    Retorna False caso a operação não foi um  Sucesso. Ocorre quando não existe Vertice informado.
     */
    // public boolean pondera ( Object vertice, Double peso) {
    //     if( this.doExistVertice(vertice)){
    //         this.vertice.get(vertice).setPonderamento(peso);
    //         this.vertice.get(listaAdjacencia).addLast(peso);
    //         return true;
    //     }
    //     return false;
    // }

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
            Map<Object,ForkEntity.Aresta> aresta1 = new HashMap<Object,ForkEntity.Aresta>();
            Map<Object,ForkEntity.Aresta> aresta2 = new HashMap<Object,ForkEntity.Aresta>();

            // Gerando um grafo não direcionado. Bidireção
            if( !this.doVerticeExistAdjacencia(vertice_1))
                this.listaAdjacencia.put(vertice_1, new LinkedList<Map<Object,ForkEntity.Aresta>>());
            if(!this.doVerticeExistAdjacencia(vertice_2))
                this.listaAdjacencia.put(vertice_2, new LinkedList<Map<Object,ForkEntity.Aresta>>());

            aresta1.put(vertice_1, new ForkEntity.Aresta(vertice_1, vertice_2, peso,true,(int) this.listaAdjacencia.get(vertice_1).size()));
            aresta2.put(vertice_2, new ForkEntity.Aresta(vertice_2, vertice_1, peso,true,(int) this.listaAdjacencia.get(vertice_2).size()));
            
            // Adicionando as aresta no grafo não direcionado. 
            this.listaAdjacencia.get(vertice_1).add(aresta2);
            this.listaAdjacencia.get(vertice_2).add(aresta1);


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
    public boolean doExistVertice ( Object vertice) {
        if(this.vertice.get(vertice) != null)
            return true; // existe vertice
        return false;
    }

    public boolean doVerticeExistAdjacencia ( Object vertice) {
        if(this.listaAdjacencia.get(vertice) != null)
            return true; // existe vertice
        return false;
    }
    

    public Boolean doVerticeExistAdjacencia(Object vertice1,Object vertice2){
        Boolean removeSomeone = false;
        if(this.vertice.get(vertice1) != null && this.vertice.get(vertice2) != null ){
            if(this.doVerticeExistAdjacencia(vertice1) || this.doVerticeExistAdjacencia(vertice2)){
                for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
                    if(lista.getValue() != null){
                        int tamanho =  lista.getValue().size();
                        for (int i = 0; i < tamanho; i++) { 
                            for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                                if( (aresta.getValue().getVertice_1() == vertice1 || aresta.getValue().getVertice_2() == vertice1) &&
                                    (aresta.getValue().getVertice_1() == vertice2 || aresta.getValue().getVertice_2() == vertice2)
                                ){
                                    removeSomeone = true;
                                }
                            }
                        } 
                    }
                }
            }
        }
        return removeSomeone;

      
    };


    public void printGrafoAdjacencia()
    {
        for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {
 
            // Printing the head
            System.out.print(lista.getKey() + "->");
            if(lista.getValue() != null){
                int tamanhoList =  lista.getValue().size();
                if(true){
                    for (int i = 0; i < tamanhoList; i++) {
                        for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                            System.out.print("  "+aresta.getKey() + "  {"+aresta.getValue().getPeso()+"}  :  ");
                        }
                    }
                }
            }
   

 
            // Now a new lin eis needed
            System.out.println();
        }
    }

    public Boolean removeVertice(Object vertice) {      
        Boolean removeSomeone = false;
        if(this.vertice.get(vertice) != null ){
            if(this.doVerticeExistAdjacencia(vertice)){
                for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
                    if(lista.getValue() != null){
                        int tamanho =  lista.getValue().size();
                        for (int i = 0; i < tamanho; i++) { 
                            for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                                if( (aresta.getValue().getVertice_1() == vertice || aresta.getValue().getVertice_2() == vertice)
                                ){
                                    this.listaAdjacencia.get(lista.getKey()).remove(i); 
                                    removeSomeone = true;
                                }
                            }
                        } 
                    }
                }
            }
            this.vertice.remove(vertice);
            this.listaAdjacencia.remove(vertice);
        }
        return removeSomeone;  
    }

    public Boolean inserirVertice(Object nomeVertice) {      
        if(this.vertice.get(nomeVertice) == null ){
            this.vertice.put(nomeVertice,new ForkEntity.Vertice(nomeVertice,(int) this.listaAdjacencia.size(),(int) this.listaAdjacencia.size()));
            this.listaAdjacencia.put(nomeVertice,new LinkedList<Map<Object, Aresta>>());
            return true;  
        }
        return false;  
    }


    public Boolean removeAresta(Object vertice1, Object vertice2) {
        Boolean removeSomeone = false;
        if(this.vertice.get(vertice1) != null && this.vertice.get(vertice2) != null ){
            if(this.doVerticeExistAdjacencia(vertice1) || this.doVerticeExistAdjacencia(vertice2)){
                for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
                    if(lista.getValue() != null){
                        int tamanho =  lista.getValue().size();
                        for (int i = 0; i < tamanho; i++) { 
                            for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                                if( (aresta.getValue().getVertice_1() == vertice1 || aresta.getValue().getVertice_2() == vertice1) &&
                                    (aresta.getValue().getVertice_1() == vertice2 || aresta.getValue().getVertice_2() == vertice2)
                                ){
                                    this.listaAdjacencia.get(lista.getKey()).remove(i); 
                                    removeSomeone = true;
                                }
                            }
                        } 
                    }
                }
            }
        }
        return removeSomeone;
    }

    
}



// Main class
class GFG {
 
    // Method 1
    // To make pair of nodes
    static void addEdge(LinkedList<LinkedList<Integer> > Adj, int u,
            int v)
    {
        // Creating bi-directional vertex
        Adj.get(u).add(v);
        Adj.get(v).add(u);
    }
 
    // Method 2
    // To print the adjacency list
    static void
    printadjacencylist(LinkedList<LinkedList<Integer> > adj)
    {
        for (int i = 0; i < adj.size(); ++i) {
 
            // Printing the head
            System.out.print(i + "->");
 
            for (int v : adj.get(i)) {
                // Printing the nodes
                System.out.print(v + " ");
            }
 
            // Now a new lin eis needed
            System.out.println();
        }
    }
 
    // Method 3
    // Main driver method
    public static void main(String[] args)
    {
 
        // Creating vertex
        int V = 5;
 
        LinkedList<LinkedList<Integer> > adj
            = new LinkedList<LinkedList<Integer> >();
        for (int i = 0; i < V; ++i) {
            adj.add(new LinkedList<Integer>());
        }
 
        // Inserting nodes
        // Custom input node elements
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 4);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
 
        // Printing adjacency list
        printadjacencylist(adj);
    }
}
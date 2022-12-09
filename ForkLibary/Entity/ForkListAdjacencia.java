package ForkLibary.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ForkLibary.Entity.ForkEntity.Aresta;

public class ForkListAdjacencia extends ForkEntity {

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

    
    class grauVerticesEntry{
        public Integer grau;
        
        public grauVerticesEntry (Integer valor){
            this.grau = valor;
        }

        public Integer getGrau() {
            return grau;
        }

        public void setGrau(Integer grau) {
            this.grau = grau;
        }
        public void setGrau(Boolean diminuirUm) {
            if(diminuirUm)
                this.grau--;
            else
                this.grau++;

        }

    }


    public ForkListAdjacencia() {
        this.quantidadeVertices = 0;
    }
    public ForkListAdjacencia (int quantidadeVertice){
        this.quantidadeVertices = quantidadeVertice;

        // Set Vertices Infomacoes
        this.setInformacaoVertices(quantidadeVertice);
    }
    public ForkListAdjacencia (ForkListAdjacencia forkList){
        this.listaAdjacencia = forkList.listaAdjacencia;
        this.vertice = forkList.vertice;
        this.quantidadeVertices = forkList.quantidadeVertices;

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
            auxVertice.setNome(nomeVertice);
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
    
    public Integer getCountAdjacentesGrafo(Boolean grafoDirecionado){
        Integer quantidade = 0;
        ArrayList<Object> arrayExplorados = new ArrayList<>();
        for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
            int tamanho =  lista.getValue().size();
            if(!grafoDirecionado){
                for (int i = 0; i < tamanho; i++) {            
                    for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                        if( !(ForkListAdjacencia.validHasExploradoGrafo(arrayExplorados,aresta.getValue().getVertice_1()) ||
                              ForkListAdjacencia.validHasExploradoGrafo(arrayExplorados,aresta.getValue().getVertice_2()) ) ){
                            quantidade++;
                        }    
                    }
                }
            }else{
                quantidade += tamanho;
            }

            arrayExplorados.add(lista.getKey());
        }

        return quantidade;     
    };

    private static Boolean validHasExploradoGrafo(ArrayList<Object> ConjuntoExplorados,Object elemento){
        for (Object element : ConjuntoExplorados) {
            if (element.equals(elemento)) {
                return true;
            }
        }
        return false;
    }
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
    
    public ForkEntity.Vertice getVertice ( Object vertice) {
        ForkEntity.Vertice v = this.vertice.get(vertice);
        if(v != null)
            return v; // Retorna o vertice
        return v;
    }

    public Map<Object,ForkEntity.Vertice> getAllVertices() {
    	return this.vertice;
    }

    public ArrayList<ForkEntity.Aresta> getAllAjacencia(){
        ArrayList<ForkEntity.Aresta> adjacencias = new ArrayList<ForkEntity.Aresta>(); // Create an ArrayList object

        for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
            if(lista.getValue() != null){
                int tamanho =  lista.getValue().size();
                if(tamanho != 0){
                    for (int i = 0; i < tamanho; i++) {
                        for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                            if(!isArestaEquals(aresta.getValue(), adjacencias)){
                                adjacencias.add(aresta.getValue());
                            }
                        }
                    }
                }
            }
        }
        return adjacencias;
    };


    public ForkEntity.Aresta getVerticeAdjacencia(Object vertice1,Object vertice2){
        ForkEntity.Aresta Aresta = null;
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
                                    Aresta = aresta.getValue();
                                }
                            }
                        } 
                    }
                }
            }
        }
        return Aresta;
    };

    public Map<Object,grauVerticesEntry> getGrausGrafos(){
        Map<Object,grauVerticesEntry> grausVertices = new HashMap<Object,grauVerticesEntry>();
        for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
            if(lista.getValue() != null){
                int tamanho =  lista.getValue().size();
                grausVertices.put(lista.getKey(), new grauVerticesEntry(tamanho));
            }
        }
        return grausVertices;
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

    public ArrayList<ForkEntity.Aresta> getVerticesAdjacentesByVerticesDaddy(Object vertice,Boolean includeHas,Object daddyVertice){
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
                                    }
                                }
                                if(daddyVertice == null){
                                    if(aresta.getValue().getVertice_2() == vertice){
                                        keysVertices.add(aresta.getValue());                              
                                    }
                                }else{
                                    if(aresta.getValue().getVertice_1() == vertice && aresta.getValue().getVertice_2() != daddyVertice){
                                        keysVertices.add(aresta.getValue());                              
                                    }
                                }
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

    /**
     *  Função que verifica se o Grafo instanciado é Nulo/Trivial.
     * 
     *  Observação: Grafo Nulo representa um gráfo que não possua arestas.
     * 
     * @return              Retorna True  caso o Grafo é Nulo/Trivial.
     * @                    Retorna False caso o Grafo é Nulo/Trivial.
     */
    public boolean isForkNulo() {
        for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> listaAdj : this.listaAdjacencia.entrySet()) {    
            if(this.doVerticeExistAdjacencia(listaAdj.getKey()))
                return false; // existe uma aresta
        }
        return true;
    }

    /**
     *  Função que verifica se o Grafo instanciado é Completo.
     * 
     *  Observação: Grafo Completo representa um gráfo que todo vértice é adjacente a todos os outros vértices.
     * 
     * @return              Retorna True  caso o Grafo é Nulo/Trivial.
     * @                    Retorna False caso o Grafo é Nulo/Trivial.
     */
    public Boolean isForkCompleto() {
            Double formula;
            Double countArestaAll = Double.valueOf(this.getCountAdjacentesGrafo(false));
            Double countVerticeAll = Double.valueOf(this.getCountVertices());
            formula =  (countVerticeAll * (countVerticeAll - 1.0)) / 2;
            if(formula.equals(countArestaAll))
                return true; // Possi (n*(n-1) )/2 arestas. Ou seja, completo
            return false;
    }
    public boolean doVerticeExistAdjacencia ( Object vertice) {
        if(this.listaAdjacencia.get(vertice).size() != 0)
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
                                if( (aresta.getValue().getVertice_1().equals(vertice1) || aresta.getValue().getVertice_2().equals(vertice1)) &&
                                    (aresta.getValue().getVertice_1().equals(vertice2) || aresta.getValue().getVertice_2().equals(vertice2))
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
        Map<Object,Integer> indexRemove = new HashMap<Object,Integer>();

        if(this.vertice.get(vertice1) != null && this.vertice.get(vertice2) != null ){
            if(this.doVerticeExistAdjacencia(vertice1) || this.doVerticeExistAdjacencia(vertice2)){
                for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : this.listaAdjacencia.entrySet()) {    
                    if(lista.getValue() != null){
                        int tamanho =  lista.getValue().size();

                        for (int i = 0; i < tamanho; i++) { 
                            if(lista.getValue().get(i) != null){
                                for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
                                    if(aresta.getValue() != null){
                                        if( (aresta.getValue().getVertice_1() == vertice1 || aresta.getValue().getVertice_2() == vertice1) &&
                                            (aresta.getValue().getVertice_1() == vertice2 || aresta.getValue().getVertice_2() == vertice2)
                                        ){
                                            removeSomeone = true;
                                            indexRemove.put(lista.getKey(),i);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        for (Map.Entry<Object,Integer> index : indexRemove.entrySet()) {
            this.listaAdjacencia.get(index.getKey()).remove((int)index.getValue()); 
        }
        return removeSomeone;
    }

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

    public void setGrafoByCsv(List<List<String>> arquivoLido)
    {
		arquivoLido.forEach(linha -> {
            Object verticeAdjP = null;

            for (int i = 0; i < linha.size(); i++) {
                if(i == 0){
                    if(!this.doExistVertice(linha.get(i)))
				        this.inserirVertice(linha.get(i));
                    verticeAdjP = linha.get(i);
                }else{
                    if(!this.doExistVertice(linha.get(i)))
                        this.inserirVertice(linha.get(i));
                    
                    if(!this.doVerticeExistAdjacencia(linha.get(i),verticeAdjP))
                        this.inserirAresta(linha.get(i), verticeAdjP);
                }
            }
			
		});
		
    }

    private Boolean isArestaEquals(ForkEntity.Aresta aresta,ArrayList<ForkEntity.Aresta> arestasValid){
        for (ForkEntity.Aresta ArestaPonte : arestasValid) {
            if( (aresta.getVertice_1() == ArestaPonte.getVertice_1() && aresta.getVertice_2() == ArestaPonte.getVertice_2() ) ||
                (aresta.getVertice_1() == ArestaPonte.getVertice_2() && aresta.getVertice_2() == ArestaPonte.getVertice_1() )    ){
                    return true;
                }
        }
        return false;
    }

}

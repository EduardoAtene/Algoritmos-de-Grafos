import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import ForkLibary.Entity.ForkMatrizAdjacencia;
import ForkLibary.Entity.ArvoreTarjan.VerticesElement;
import ForkLibary.Entity.ForkEntity.Aresta;
import ForkLibary.Entity.ForkEntity.Vertice;
import ForkLibary.Entity.ForkListAdjacencia;
import ForkLibary.Entity.AlgoritmoFleury;
import ForkLibary.Entity.AlgoritmoNaive;
import ForkLibary.Entity.ArvoreTarjan;
import ForkLibary.Log.LogModel;
import ForkLibary.ManipuladorArquivo.ManipuladorArquivo;
import ForkLibary.ManipuladorArquivo.Configuration.Configuracao;
import csv.CsvService;
import ForkLibary.Entity.ForkEntity;

import java.util.ArrayList;

/**
 * Principal
 */
class Principal {

    public static void main(String[] args) throws IOException {
        // LogModel.setErrorLog("Erro na hora de importar bla bla");
        
        // ForkMatrizAdjacencia a = new ForkMatrizAdjacencia(5);
        // // ForkMatrizAdjacencia.this

        // a.inserirAresta(1,2,(double) 10);
        // Principal.imprime(a.matriz,5);
        // a.rotularVertice(5,"Gustavo");
        // a.rotularVertice(1,"OlaMundo");

        // a.vertice.forEach( (k, v) -> {
        //     System.out.println("key: " + k + ", nome: " + v.getNome());
        // });
        
        // System.out. print (a.inserirAresta(1,4,(double) 5));
        // Principal.imprime(a.matriz,5);

        // System.out. print (a.inserirAresta("OlaMundo",4,(double) 5));
        // Principal.imprime(a.matriz,5);



        /*
         *      CRIANDO UM GRAFO: Lista Adjacencia.
         *      Como testes, criaremos um grafo não direcionado e ponderado com 8 Vertíces
         *      
         *      Functiton   : new ForkListAdjacencia()
         *      Return Case : ForkListAdjacencia Entry/Objeto/Entidade
         */
        
        ForkListAdjacencia forkListAdj = new ForkListAdjacencia(5);

        /**
         *      OPERAÇÕES DAS VERTICES
         */

        /**     INSERINDO VÉRTICES
         * 
         *      Functiton   : inserirVertice()
         *      Return Case : True
         */ 
        // forkListAdj.inserirVertice(18);
        // forkListAdj.inserirVertice(23);

        /**     INSERINDO NÃO EXISTENTES VÉRTICES
         * 
         *      Functiton   : inserirVertice()
         *      Return Case : False. Nome Referenciado para o Vértices já existênte no grafo
         */ 
        // forkListAdj.inserirVertice(1);
        // forkListAdj.inserirVertice(2);


        /**     ROTULANDO VÉRTICES EXISTENTES
         * 
         *      Functiton   : rotularVertice()
         *      Return Case : True. Rotulou com sucesso.
         */      
        // forkListAdj.rotularVertice(1,"Vertice_Gabriel");
        // forkListAdj.rotularVertice(2,"Vertice A");
        // forkListAdj.rotularVertice(3,"Vertice B");
        // forkListAdj.rotularVertice("Vertice B","Vertice X");

        forkListAdj.rotularVertice(1,'a');
        forkListAdj.rotularVertice(2,'b');
        forkListAdj.rotularVertice(3,'c');
        forkListAdj.rotularVertice(4,'d');
        forkListAdj.rotularVertice(5,'e');
        forkListAdj.rotularVertice(6,'f');
        forkListAdj.rotularVertice(7,'g');

        /**
         *      ROTULANDO VÉRTICES NÃO EXISTENTES
         * 
         *      Functiton   : rotularVertice()
         *      Return Case :   False:
         *                       Não rotulou, pos não há existência do vértice no gráfo.
         *                      Todos os casos não existe a chave, mas em um caso específoco que devemos notar.
         *                      Assim que realizamos a rotulação do vértice 1, acima, ele acabou sendo chamado de "Vertice_Gabriel". 
         *                      Ou seja, esse vétice não existe mais.    
         * 
         */ 
        // forkListAdj.rotularVertice(1313,6);
        // forkListAdj.rotularVertice("Jubileu",6);
        // forkListAdj.rotularVertice(2023,"Kalista");
        // forkListAdj.rotularVertice("<3","Caitliy");
        // forkListAdj.rotularVertice(1,"Sona");
                

        /**     CHEGANDO EXISTENCIA VÉRTICE
         * 
         *      Functiton   : doExistVertice()
         *      Return Case : True or False
         */ 
        // System.out.println(forkListAdj.doExistVertice(8));                           //  True
        // System.out.println(forkListAdj.doExistVertice(50));                          //  False
        // System.out.println(forkListAdj.doExistVertice(13));                          //  False
        // System.out.println(forkListAdj.doExistVertice("Vertice_Gabriel"));           //  True
        // System.out.println(forkListAdj.doExistVertice("Vertice X"));                 //  True
        // System.out.println(forkListAdj.doExistVertice("Teste"));                     //  False
        // System.out.println(forkListAdj.doExistVertice(7));                           //  True

        /**     CHEGANDO QUANTIDADE TOTAL DE VÉRTICE NO GRAFO
         * 
         *      Functiton   : getCountVertices()
         *      Return Case : Intiger / Quantidade total de vértices do grafo forkListAdj.
         */ 
        // System.out.println(forkListAdj.getCountVertices());
        
    
        /**
         *      OPERAÇÕES DAS ARESTAS
         */
        
        /**     INSERINDO ARESTAS EM VÉRTICES EXISTENTES
         * 
         *      Functiton   : inserirAresta()
         *      Return Case : True. Funcionou
         */ 
        // forkListAdj.inserirAresta("Vertice X",4,(double) 4);
        // forkListAdj.inserirAresta("Vertice X","Vertice A");
        // forkListAdj.inserirAresta("Vertice X","Vertice_Gabriel",(double) 10);
        // forkListAdj.inserirAresta(7,4,(double) 4);
        // forkListAdj.inserirAresta(6,7);
        // forkListAdj.inserirAresta(8,7,(double) 2.5);

        forkListAdj.inserirAresta('a','b'); // a - b
        forkListAdj.inserirAresta('a','c'); // a - c
        forkListAdj.inserirAresta('a','d'); // b - c
        forkListAdj.inserirAresta('b','d'); // b - d
        // forkListAdj.inserirAresta('c','d'); // c - d
        forkListAdj.inserirAresta('c','e'); // c - e
        // forkListAdj.inserirAresta('d','e'); // d - e
        // forkListAdj.inserirAresta('e','f'); // e - f
        // forkListAdj.inserirAresta('e','g'); // e - g
        // forkListAdj.inserirAresta('f','g'); // f - g

        // TESTE EM GERAL

            /**     INSERINDO ARESTAS EM VÉRTICES NÃO EXISTENTES
             * 
             *      Functiton   : inserirAresta()
             *      Return Case : False.
             *                      Aresta {100}, {'A' 'B'} e {'Nota_Total_Trabalho'} não existente.
             *                      _Será mesmo que o total vem? Veremos._
             */ 
            // forkListAdj.inserirAresta("Vertice_Gabriel","Nota_Total_Trabalho",(double) 10);
            // forkListAdj.inserirAresta("Vertice_Gabriel",100,(double) 10);
            // forkListAdj.inserirAresta("A","B",(double) 10);

            /**     CHEGANDO QUANTIDADE TOTAL ARESTAS DE UM VÉRTICE
             * 
             *      Functiton   : getCountAdjacentesVertices()
             *      Return Case : Intiger / Quantidade total de arestas do vértices informado do grafo forkListAdj.
             * 
             *      Observação  :   O grafo do teste é NÃO DIRECIONADO. Caso for direcionado, o valor resultante da 
             *                    operação é equivalente ao dobro dele. Pois estamos fazendo bidirecionamento ao inserir
             *                    arestas e não estamos informando a direção dele. Entretanto, o algoritmo está habilitado
             *                    em realiizar operações com grafos direcionados.
             */ 
            // System.out.println(forkListAdj.getCountAdjacentesVertices(7,false));

            
            /**     CHEGANDO QUANTIDADE TOTAL ARESTAS DO GRAFO
             * 
             *      Functiton   : getCountAdjacentesGrafo()
             *      Return Case : Intiger / Quantidade total de arestas do vértices informado do grafo forkListAdj.
             * 
             *      Observação  :   O grafo do teste é NÃO DIRECIONADO. Caso for direcionado, o valor resultante da 
             *                    operação é equivalente ao dobro dele. Pois estamos fazendo bidirecionamento ao inserir
             *                    arestas e não estamos informando a direção dele. Entretanto, o algoritmo está habilitado
             *                    em realiizar operações com grafos direcionados.
             */ 
            // System.out.println(forkListAdj.getCountAdjacentesGrafo(false));

            /**     CHEGANDO EXISTêNCIA DE ADJACENCIA DE UM VÉRTICE
             * 
             *      Functiton   : doVerticeExistAdjacencia()
             *      Return Case : True and False
             *                        1) First Case     : True
             *                        2) Second Case    : False. Assim identificamos um componente do gráfo. Já não é completo
             * 
             */ 
            // System.out.println(forkListAdj.doVerticeExistAdjacencia(7));
            // System.out.println(forkListAdj.doVerticeExistAdjacencia(18));

            /**     CHEGANDO EXISTêNCIA DE ADJACENCIA ENTRE DOIS VÉRTICES
             * 
             *      Functiton   : getCountVertices()
             *      Return Case : True and False
             *                        1) First Case     : True.
             *                        2) Second Case    : False. Assim identificamos um componente do gráfo. Já não é completo.
             * 
             */ 
            // System.out.println(forkListAdj.doVerticeExistAdjacencia(4,"Vertice X"));
            // System.out.println(forkListAdj.doVerticeExistAdjacencia(4,6));


            /**     CHEGANDO SE É UM GRAFO NULO/TRIVIAL
             * 
             *      Functiton   : isForkNulo()
             *      Return Case : True and False
             *                        1) True     : O Grafo instanciado é Nulo. Ou seja, não possui nenhuma aresta.
             *                        2) False    : O Grafo instanciado não é Nulo. Ou seja, possui aresta.
             * 
             */ 
            // System.out.println(forkListAdj.isForkNulo());

            /**     CHEGANDO SE É UM GRAFO COMPLETO
             * 
             *      Functiton   : isForkNulo()
             *      Return Case : True and False
             *                        1) True     : O Grafo instanciado é Nulo. Ou seja, não possui nenhuma aresta.
             *                        2) False    : O Grafo instanciado não é Nulo. Ou seja, possui aresta.
             * 
             */ 
            // System.out.println(forkListAdj.isForkCompleto());
            /**     CHEGANDO SE É UM GRAFO COMPLETO
             * 
             *      Functiton   : isForkNulo()
             *      Return Case : True and False
             *                        1) True     : O Grafo instanciado é Nulo. Ou seja, não possui nenhuma aresta.
             *                        2) False    : O Grafo instanciado não é Nulo. Ou seja, possui aresta.
             * 
             */ 
            // for (ForkEntity.Aresta string : forkListAdj.getVerticesAdjacentesByVertices("Vertice X",false,false)) {
            // System.out.println(string.getVertice_2());
                
            // }
            


        /**     IMPRIMINDO GRAFOS
         * 
         *      Functiton               : printGrafoAdjacencia()
         *      Return StorageDisplay   : Imprimi no terminal o grafo no formato de uma lista encademada 
         */ 
        // Arvore a = new Arvore();
        // Vertice v = forkListAdj.getVertice('c');
        // a.buscaTarjan(forkListAdj,v, false,null,null,1);
        // a.preOrdemPrint(a.getArvoreProfundidade());
        // a.printPontes();
        // ArrayList<ForkEntity.Aresta> conjuntoAdjacencia = forkListAdj.getVerticesAdjacentesByVertices('c', false, false);

        // forkListAdj.printGrafoAdjacencia();
        // System.out.println(forkListAdj.removeAresta('a','c'));
        // forkListAdj.printGrafoAdjacencia();
		CsvService csvService = new CsvService();
		// csvService.gerarCsv(forkListAdj, "grafoLeas");
		List<List<String>> arquivoLido1 = csvService.lerCsv("grafo2k");
        ForkListAdjacencia forkListAdjFile = new ForkListAdjacencia();
        forkListAdjFile.setGrafoByCsv(arquivoLido1);
        AlgoritmoFleury fleury = new AlgoritmoFleury(forkListAdjFile);
        fleury.getTimeNaive();
        fleury.getTimeTarjam();
        
        // forkListAdjFile.getAllAjacencia();
        // AlgoritmoNaive algNaive = new AlgoritmoNaive(forkListAdj);
		// arquivoLido1.forEach(linha -> {
		// 	if(arquivoLido1.indexOf(linha) == 0){
		// 		System.out.print(" ");
		// 	}
		// 	linha.stream().forEach(palavra -> {
		// 		System.out.print("   " + palavra);
		// 	});
			
		// 	System.out.println();
		// });
        
        // forkListAdjFile.printGrafoAdjacencia();
		
		System.out.println();
		System.out.println();
		System.out.println();
        // forkListAdjFile.printGrafoAdjacencia();
        
		
        // a.printPontes();


    }

    public static void imprime (Double [][] a , int n) {
        for ( int i = 0; i < n; i ++) {
            for ( int j = 0; j < n; j++){
                System.out. print (a[ i ] [ j ] + " " );
            }
            System.out. println ( ) ;

        }
    }
    


    
}
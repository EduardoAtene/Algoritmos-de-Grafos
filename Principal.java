import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import ForkLibary.Entity.ForkMatrizAdjacencia;
import ForkLibary.Entity.ForkEntity.Aresta;
import ForkLibary.Entity.ForkListAdjacencia;
import ForkLibary.Log.LogModel;
import ForkLibary.ManipuladorArquivo.ManipuladorArquivo;
import ForkLibary.ManipuladorArquivo.Configuration.Configuracao;
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
         * Grafo Lista Adjacencia.
         * Como testes, criaremos um grafo com 8 Vertíces
         */
        
        ForkListAdjacencia forkListAdj = new ForkListAdjacencia(8);

        // Principal.imprime(a.matriz,5);
        // a.rotularVertice(5,"Gustavo");
        // a.rotularVertice(1,"OlaMundo");

        /**     INSERINDO VÉRTICES
         * 
         *      Functiton   : inserirVertice()
         *      Return Case : True
         */ 
        forkListAdj.inserirVertice(18);
        forkListAdj.inserirVertice(23);

        /**     INSERINDO NÃO EXISTENTES VÉRTICES
         * 
         *      Functiton   : inserirVertice()
         *      Return Case : False
         */ 


        /**     CHEGANDO EXISTENCIA VÉRTICE
         * 
         *      Functiton   : doExistVertice()
         *      Return Case : True or False
         */ 
        // System.out.println(forkListAdj.doExistVertice(8));           //  True
        // System.out.println(forkListAdj.doExistVertice(50));          //  False
        // System.out.println(forkListAdj.doExistVertice(13));          //  False
        // System.out.println(forkListAdj.doExistVertice(1));           //  True
        // System.out.println(forkListAdj.doExistVertice(3));           //  True
        // System.out.println(forkListAdj.doExistVertice("Teste"));     //  False
        // System.out.println(forkListAdj.doExistVertice(7));           //  True



        /**     ROTULANDO VÉRTICES EXISTENTES
         * 
         *      Return True: Rotulou com sucesso.
         */      
        forkListAdj.rotularVertice(1,"Vertice_Gabriel");
        forkListAdj.rotularVertice(2,"Vertice A");
        forkListAdj.rotularVertice(3,"Vertice B");
        forkListAdj.rotularVertice("Vertice B","Vertice X");

        /**
         *      ROTULANDO VÉRTICES NÃO EXISTENTES
         * 
         *      Return False:   Não rotulou, pos não há existência do vértice no gráfo.
         *                      Todos os casos não existe a chave, mas em um caso específoco que devemos notar.
         *                      Assim que realizamos a rotulação do vértice 1, acima, ele acabou sendo chamado de "Vertice_Gabriel". 
         *                      Ou seja, esse vétice não existe mais.    
         * 
         */ 
        forkListAdj.rotularVertice(1313,6);
        forkListAdj.rotularVertice("Jubileu",6);
        forkListAdj.rotularVertice(2023,"Kalista");
        forkListAdj.rotularVertice("<3","Caitliy");
        forkListAdj.rotularVertice(1,"Sona");
                
        
        /**
         * Testes Arestas na Lista de Adjacencia
         */
        
        /**     INSERINDO ARESTAS EM VÉRTICES EXISTENTES
         * 
         *      Return True: Funcionou
         */ 
        forkListAdj.inserirAresta("Vertice X",4,(double) 4);
        forkListAdj.inserirAresta("Vertice X","Vertice_Gabriel");
        forkListAdj.inserirAresta("Vertice X","Vertice_Gabriel",(double) 10);
        forkListAdj.inserirAresta(7,4,(double) 4);
        forkListAdj.inserirAresta(6,7);
        forkListAdj.inserirAresta(8,7,(double) 2.5);

        /**     INSERINDO ARESTAS EM VÉRTICES NÃO EXISTENTES
         * 
         *      Return False: Aresta {100}, {'A' 'B'} e {'Nota_Total_Trabalho'} não existente. Será mesmo que o total vem? Veremos.
         */ 
        forkListAdj.inserirAresta("Vertice_Gabriel","Nota_Total_Trabalho",(double) 10);
        forkListAdj.inserirAresta("Vertice_Gabriel",100,(double) 10);
        forkListAdj.inserirAresta("A","B",(double) 10);

        /**     IMPRIMINDO GRAFOS
         * 
         *      Return StorageDisplay: Imprimi no terminal o grafo no formato de uma lista encademada 
         */ 
        // forkListAdj.printGrafoAdjacencia();

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
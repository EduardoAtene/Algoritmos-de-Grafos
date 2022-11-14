import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import ForkLibary.Entity.ForkMatrizAdjacencia;
import ForkLibary.Entity.ForkEntity;
import ManipuladorArquivo.ManipuladorArquivo;
import ManipuladorArquivo.Configuration.Configuracao;

import java.util.ArrayList;

/**
 * Principal
 */
class Principal {

    public static void main(String[] args) throws IOException {

        // ForkMatrizAdjacencia a = new ForkMatrizAdjacencia(5);
        // // ForkMatrizAdjacencia.this

        // a.inserirAresta(1,2,(double) 10);
        // Principal.imprime(a.matriz,5);
        // a.rotularVertice(1,"OlaMundo");

        // a.vertice.forEach( (k, v) -> {
        //     System.out.println("key: " + k + ", nome: " + v.getNome());
        // });
        
        // System.out. print (a.inserirAresta(1,4,(double) 5));
        // Principal.imprime(a.matriz,5);

        // System.out. print (a.inserirAresta("OlaMundo",4,(double) 5));
        // Principal.imprime(a.matriz,5);

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
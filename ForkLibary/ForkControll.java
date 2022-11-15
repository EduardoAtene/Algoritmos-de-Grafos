
package ForkLibary;

import java.util.HashMap;
import java.util.Map;

import ForkLibary.Entity.ForkMatrizAdjacencia;
import ForkLibary.Entity.ForkListAdjacencia;

public class  ForkControll {

    public static Map<Object,ForkMatrizAdjacencia> ListaGrafosMatrizAdjacencia = new HashMap<Object,ForkMatrizAdjacencia>();
    public static Map<Object,ForkListAdjacencia> ListaGrafosListaAdjacencia = new HashMap<Object,ForkListAdjacencia>();

    public ForkControll() {

    }

    public static void main(String[] args) {
        System.out.println("teste");
    }
}
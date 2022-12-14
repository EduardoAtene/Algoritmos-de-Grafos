package ForkLibary.Entity;

import java.util.HashMap;
import java.util.Map;

import ForkLibary.ManipuladorArquivo.Configuration.Configuracao;

/*
 * Classe de um Modelo Entidade de Grafo utilizada para o gerenciamento de dados durante a leitura de um arquivo.
 */
public class ForkModelEntity extends ForkEntity {

    
    public static Map<Object,ForkModelEntity> grafos = new HashMap<Object,ForkModelEntity>();


    public ForkModelEntity(){

    }

    public void gerarGrafico(){
        System.out.println(1);

    };
    public void removeVertice(){
        System.out.println(1);

    };
    public void removeAresta(){
        System.out.println(1);

    };

    /**
     * @param configuration
     */
    public void setValoresFilePram(String valueConfig,String values){
        Object valores[] = values.split(Configuracao.getSEPARADOR());

        if(Configuracao.getGRAFO_START() == valueConfig){
            if( grafos.get(values) != null ){

            }
        }else if(Configuracao.getGRAFO_END() == valueConfig){

        }else if(Configuracao.getVERTICE_COUNT() == valueConfig){
            if(valores.length == 1){
                this.quantidadeVertices = (int) valores[0];
            }
        }else if(Configuracao.getVERTICE_ROTULO() == valueConfig){

        }else if(Configuracao.getVERTICE_PONDERAMENTO() == valueConfig){

        }else if(Configuracao.getARESTA() == valueConfig){
            if(valores.length == 1){
                this.quantidadeVertices = (int) valores[0];
            }
        }
    };
}
package ManipuladorArquivo.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Configuracao {
    private static String SEPARADOR=";";
    private static String GRAFO_START="Gs";
    private static String GRAFO_END="Ge";
    private static String VERTICE_COUNT="Vq";
    private static String VERTICE_ROTULO="Vr";
    private static String VERTICE_PONDERAMENTO="Vp";
    private static String ARESTA="Aa";

    /*
     * Função que seta as configurações através de um arquivo
     */
    public static void setConfigurationByFile(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				String lineConfig[] = linha.split("=");
                if(Configuracao.verificationAtributeClassConfig(lineConfig[0]) != null){
                    Configuracao.setConfiguration(lineConfig[0],lineConfig[1]);
                }

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
    }

    private static String verificationAtributeClassConfig(String variable){
        for (Field field : Configuracao.class.getDeclaredFields()) {
            if(field.getName() == variable){
                return field.getName();
            }
        }
        return null;
    }

    private static void setConfiguration(String variable,String value){
        switch (variable) {
            case "SEPARADOR":
                Configuracao.SEPARADOR = value;
            break;

            case "GRAFO_START":
                Configuracao.GRAFO_START = value;
            break;

            case "GRAFO_END":
                Configuracao.GRAFO_END = value;
            break;

            case "VERTICE_COUNT":
                Configuracao.VERTICE_COUNT = value;
            break;

            case "VERTICE_ROTULO":
                Configuracao.VERTICE_ROTULO = value;
            break;

            case "VERTICE_PONDERAMENTO":
                Configuracao.VERTICE_PONDERAMENTO = value;
            break;
        
            case "ARESTA":
                Configuracao.ARESTA = value;
            break;
        }
    }

    // Getters
    public static String getARESTA() {
        return ARESTA;
    }
    public static String getGRAFO_START() {
        return GRAFO_START;
    }

    public static String getVERTICE_COUNT() {
        return VERTICE_COUNT;
    }

    public static String getGRAFO_END() {
        return GRAFO_END;
    }
    public static String getSEPARADOR() {
        return SEPARADOR;
    }
    public static String getVERTICE_ROTULO() {
        return VERTICE_ROTULO;
    }
    public static String getVERTICE_PONDERAMENTO() {
        return VERTICE_PONDERAMENTO;
    }
}

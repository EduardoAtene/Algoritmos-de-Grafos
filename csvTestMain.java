import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;

import ForkLibary.Entity.ForkMatrizAdjacencia;
import csv.CsvService;

public class csvTestMain {
	public static void main(String[] args) throws IOException {
		
		//Classe que gtera os arquivos csv
		CsvService service = new CsvService();
		
		ForkMatrizAdjacencia grafoLetras = new ForkMatrizAdjacencia(5);
		grafoLetras.rotularVertice(1, "A");
		grafoLetras.rotularVertice(2, "B");
		grafoLetras.rotularVertice(3, "C");
		grafoLetras.rotularVertice(4, "D");
		grafoLetras.rotularVertice(5, "E");
		
		grafoLetras.inserirAresta("A", "A");
		grafoLetras.inserirAresta("A", "B");
		grafoLetras.inserirAresta("A", "C");
		grafoLetras.inserirAresta("A", "D");
		grafoLetras.inserirAresta("A", "E");
		
		service.gerarCsv(grafoLetras, "grafoLetras");
		
		
		ForkMatrizAdjacencia grafoUF = new ForkMatrizAdjacencia(4);
		
		//Estados do sudeste
		grafoUF.rotularVertice(1, "MG");
		grafoUF.rotularVertice(2, "RJ");
		grafoUF.rotularVertice(3, "ES");
		grafoUF.rotularVertice(4, "SP");
		
		
		//aresta ligando os estados adjacentes
		grafoUF.inserirAresta("MG", "RJ");
		grafoUF.inserirAresta("MG", "ES");
		grafoUF.inserirAresta("MG", "SP");
		
		grafoUF.inserirAresta("RJ", "MG");
		grafoUF.inserirAresta("RJ", "ES");
		grafoUF.inserirAresta("RJ", "SP");
		
		grafoUF.inserirAresta("SP", "MG");
		grafoUF.inserirAresta("SP", "RJ");
		
		service.gerarCsv(grafoUF, "Estados Sudeste");
	}
}

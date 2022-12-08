import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;

import ForkLibary.Entity.ForkListAdjacencia;
import ForkLibary.Entity.ForkMatrizAdjacencia;
import csv.CsvService;

public class csvTestMain {
	public static void main(String[] args) throws IOException {
		
		//Classe que gtera os arquivos csv
		CsvService csvService = new CsvService();
		
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
		
		csvService.gerarCsv(grafoLetras, "grafoLetras");
		
		
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
		
		csvService.gerarCsv(grafoUF, "Estados Sudeste");
		
		
		
		//Leitura dos arquivos CSV
		
		List<List<String>> arquivoLido1 = csvService.lerCsv("grafoLetras");
		arquivoLido1.forEach(linha -> {
			if(arquivoLido1.indexOf(linha) == 0){
				System.out.print(" ");
			}
			linha.stream().forEach(palavra -> {
				System.out.print("   " + palavra);
			});
			
			System.out.println();
		});
		
		System.out.println();
		
		
		List<List<String>> arquivoLido2 = csvService.lerCsv("Estados Sudeste");
		arquivoLido2.forEach(linha -> {
			if(arquivoLido1.indexOf(linha) == 0){
				System.out.print(" ");
			}
			linha.stream().forEach(palavra -> {
				System.out.print("   " +palavra);
			});
			
			System.out.println();
		});
		
		System.out.println();
		
		
		
		
		ForkListAdjacencia listaAdj = new ForkListAdjacencia(3);
		
		listaAdj.inserirAresta(1,2);
		listaAdj.inserirAresta(1,3);
		listaAdj.printGrafoAdjacencia();
		
	}
}

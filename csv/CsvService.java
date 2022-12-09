package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

import ForkLibary.Entity.ForkEntity;
import ForkLibary.Entity.ForkListAdjacencia;
import ForkLibary.Entity.ForkMatrizAdjacencia;

public class CsvService {


	private List<List<String>> data = new ArrayList();
	private List<List<String>> dataList = new ArrayList();
	private Double[][]matrizGrafo;
	private FileWriter file;
	CSVWriter csvWriter; 
	
	public boolean gerarCsv(ForkMatrizAdjacencia grafo,String nomeArquivo) throws IOException {
		Path myPath = Paths.get("csv/arquivos/"+nomeArquivo + ".csv");
		this.csvWriter = new CSVWriter(Files.newBufferedWriter(myPath,
			    StandardCharsets.UTF_8), ';',
			    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER,
			    "\n");
		this.matrizGrafo = grafo.matriz;
		;
		
		List<String> headers = getGrafoHeaders(grafo);
		getGrafoData(grafo);
		
		String[] csvHeaders = new String[this.data.get(0).size()];
		headers.toArray(csvHeaders);
		
		List<String[]> csvData = new ArrayList();
		
		for(int i = 0; i < this.data.size(); i++) {
			String[] linha = new String[this.data.get(0).size()];
			List<String> data = this.data.get(i);
			data.toArray(linha);
			csvData.add(linha);
		}
		
		this.csvWriter.writeNext(csvHeaders);
		this.csvWriter.writeAll(csvData);
		
//		for(int i = 0; i < this.data.size(); i++) {
//			String[] linha = new String[this.data.get(0).size()];
//			List<String> data = this.data.get(i);
//			data.toArray(linha);
//			this.csvWriter.writeNext(linha);
//		}
		
		
		this.csvWriter.close();
		
		this.data = new ArrayList();
		return true;
	}
	
	public boolean gerarCsv(ForkListAdjacencia grafo,String nomeArquivo) throws IOException {
		Path myPath = Paths.get("csv/arquivos/"+nomeArquivo + ".csv");
		this.csvWriter = new CSVWriter(Files.newBufferedWriter(myPath,
			    StandardCharsets.UTF_8), ';',
			    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER,
			    "\n");
		;
		
		// List<String> headers = getGrafoHeaders(grafo);
		getGrafoData(grafo);
		
		// String[] csvHeaders = new String[this.data.get(0).size()];
		// headers.toArray(csvHeaders);
		
		List<String[]> csvData = new ArrayList();
		
		for(int i = 0; i < this.dataList.size(); i++) {
			String[] linha = new String[this.dataList.get(i).size()];
			List<String> dataList = this.dataList.get(i);
			dataList.toArray(linha);
			csvData.add(linha);
		}

		this.csvWriter.writeAll(csvData);
		
//		for(int i = 0; i < this.data.size(); i++) {
//			String[] linha = new String[this.data.get(0).size()];
//			List<String> data = this.data.get(i);
//			data.toArray(linha);
//			this.csvWriter.writeNext(linha);
//		}
		
		
		this.csvWriter.close();
		
		this.data = new ArrayList();
		return true;
	}
	
	public CsvService() throws IOException {
		this.data = new ArrayList();
		
	}
	
	private List<String> getGrafoHeaders(ForkMatrizAdjacencia grafo) {
		List<String> headers = new ArrayList();
		headers.add("");
		grafo.getVertices().forEach((key, value) -> {
			headers.add(value.getNome().toString()); 
		});
		return headers;
	}
	private List<String> getGrafoHeaders(ForkListAdjacencia grafo) {
		List<String> headers = new ArrayList();
		headers.add("");
		grafo.getAllVertices().forEach((key, value) -> {
			headers.add(value.getNome().toString()); 
		});
		return headers;
	}
	private void getGrafoData(ForkMatrizAdjacencia grafo) {
		for(int i = 0; i < this.matrizGrafo.length; i++) {
			data.add(new ArrayList());
			data.get(i).add(this.getGrafoHeaders(grafo).get(i + 1));
    		for(int j = 0; j < this.matrizGrafo[i].length; j++) {
    			String s = ""+ Math.round(this.matrizGrafo[i][j]);
    			data.get(i).add(s);
    		}
		}
	}
	
	private void getGrafoData(ForkListAdjacencia grafo) {
		int x = 0;
        for (Map.Entry<Object,LinkedList<Map<Object,ForkEntity.Aresta>>> lista : grafo.listaAdjacencia.entrySet()) {
			dataList.add(new ArrayList<String>());
			dataList.get(x).add(String.valueOf(lista.getKey()));
            if(lista.getValue() != null){
                int tamanhoList =  lista.getValue().size();
				for (int i = 0; i < tamanhoList; i++) {
					for (Map.Entry<Object,ForkEntity.Aresta> aresta : lista.getValue().get(i).entrySet()) {
						dataList.get(x).add(String.valueOf(aresta.getKey()));
					}
				}
            }
			x++;
        }
	}
	public List<List<String>> lerCsv(String nomeArquivo) throws IOException {
		List<List<String>> regs = new ArrayList();
		BufferedReader br =  new BufferedReader(new FileReader("csv/arquivos/"+ nomeArquivo + ".csv"));
		String linha;
		while((linha = br.readLine()) != null) {
			String[] colunas = linha.split(";");
			regs.add(Arrays.asList(colunas));
		}
		
		return regs;
	}
	
}

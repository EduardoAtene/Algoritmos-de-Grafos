package ForkLibary.ManipuladorArquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import ForkLibary.ForkControll;
import ForkLibary.Entity.ForkModelEntity;
import ForkLibary.ManipuladorArquivo.Configuration.Configuracao;

public class ManipuladorArquivo {

	private static String ConfigurationNow = null;
	private static ForkModelEntity forkModelEntity;

	public static void leitor(String path,ForkControll Grafos) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				if(Configuracao.verificationAtributeClassConfig(linha.trim()) != null){ // Entrou na em uma das configuracoesw
					ManipuladorArquivo.ConfigurationNow = linha.trim();
				}else if(ManipuladorArquivo.ConfigurationNow == null){ 					// Caso a Linha For Valores a ser lidos;
					Object valores[] = linha.split(Configuracao.getSEPARADOR());

				}

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
	}

	// Possível Escrita para a incrementação da outra parte do trabalho
	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = in.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

}
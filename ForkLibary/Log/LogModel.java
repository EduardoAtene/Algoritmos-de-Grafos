package ForkLibary.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogModel {
    
    private static List errosFile = new ArrayList();

    public static void setErrorLog(String errorLine) throws IOException {
        Date dataHoraAtual = new Date(0);
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

		try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter("log.txt"))) {
            String linha ="Erro Log {"+data +" "+hora+"} : "+errorLine;
            Scanner in = new Scanner(System.in);
            // linha = in.nextLine();
            buffWrite.append(linha + "\n");
            buffWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    };
}

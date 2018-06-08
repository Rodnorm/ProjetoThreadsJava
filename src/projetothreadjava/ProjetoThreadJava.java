package projetothreadjava;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProjetoThreadJava {

    static String primo;
    static long begin;
    static long end;

    public static void main(String[] args) throws IOException, Exception {
        StringBuilder sb = new StringBuilder();// stringbuilder permite guardar pedaços de strings para usa-las depois
        Thread primo_1 = new Thread() { //thread primo_1 criada
            public void run() {
                begin = System.currentTimeMillis();// guarda o tempo gasto pelo processo
                sb.append(System.lineSeparator()); //lineseparator insere um elemento separador de linhas dentro da sb
                sb.append("**********INICIO DA THREAD 1**********");
                sb.append(System.lineSeparator());
                for (int i = 0; i <= 49999; i++) { //verifica todos os números entre 0 e 49999
                    if (Primo_1(i)) {//chama a funcção Primo_1, passa um número inteiro como parâmetro e retorna um boolean se true é primo 
                        sb.append(i);
                        sb.append(" é primo.");
                        sb.append(System.lineSeparator());
                    }
                }
                sb.append("**********FINAL DA THREAD 1**********");
                sb.append(System.lineSeparator());
                
            }

            private boolean Primo_1(int numero) {
                for (int j = 2; j < numero; j++) {// verifica se o número em questão é primo, verifica todos os número entre "numero" e "j"
                    if (numero % j == 0) {// todo número primo é dívisivel apenas por ele mesmo e por 1, 
                        return false;//portanto se o resto da divisão entre numero e j for 0 retorna false, o número não é primo
                    }
                }
                return true;
            }
        };
        

        Thread primo_2 = new Thread() {// a lógica da thread2 é a mesma da anterior
            public void run() {
                sb.append(System.lineSeparator());
                sb.append("**********INICIO DA THREAD 2**********");
                sb.append(System.lineSeparator());
                for (int i = 49999; i <= 99999; i++) {
                    if (Primo_2(i)) {
                        sb.append(i);
                        sb.append(" é primo.");
                        sb.append(System.lineSeparator());
                    }
                }
                sb.append("**********FINAL DA THREAD 2**********");
                sb.append(System.lineSeparator());
                end = System.currentTimeMillis();
            }

            private boolean Primo_2(int numero) {
                for (int j = 2; j < numero; j++) {
                    if (numero % j == 0) {
                        return false;
                    }
                }
                return true;
            }

        };
        primo_1.start();//primeira thread iniciada
        primo_1.join();//join põe o resto do código em espera até que a thread seja executada
        primo_2.start();
        primo_2.join();
        
        
        
        primo = sb.toString();// pega o stringbuilder e monta uma string
        Scanner c = new Scanner(System.in);
        System.out.print("Insira um caminho para uma pasta para salvar o arquivo: ");
        String ca = c.next();
        System.out.print("Insira um nome para o seu arquivo sem espaços: ");
        String nome = c.next();
        String barra = "\\";
        String caminho = ca+barra+nome+".txt";
        
        File file = new File(caminho);
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.newLine();
        writer.write("Caminho da gravação: " + caminho);
        writer.newLine();
        writer.write(primo);
        writer.write("Tempo de gravação: " + (end - begin) + "ms.");
        //Cria o conteúdo do arquivo
        writer.flush();
        //Fecha conexão e escrita do arquivo.
        writer.close();
        System.out.println("Arquivo gravado em: " + caminho);

    }

    
}

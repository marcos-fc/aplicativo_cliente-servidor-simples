package application;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws UnknownHostException, IOException{
        //inicia o servidor
    	Servidor servidor = new Servidor(61000);
    	
		Scanner sc = new Scanner(System.in);
        System.out.println("Digite o numero correspondente ao serviço que deseja utilizar: ");
        System.out.println("1 - Iniciar servidor.");
        System.out.println("2 - Conectar a um servidor.");
        System.out.println("3 - Encerrar aplicação.");
        int r = sc.nextInt();
        switch(r){
            case 1:
                //inicia o servidor
                servidor.executa();
                break;
            case 2:
                //conecta o cliente
		        new Cliente("127.0.0.1", 61000).executa();
                break;
            default:
                System.out.println("Encerrando aplicação...");
                sc.close();
                break;
        }
            
    }
}
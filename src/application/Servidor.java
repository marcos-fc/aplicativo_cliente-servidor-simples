package application;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	private int porta;
	private List<PrintStream>clientes;

	public Servidor(int porta){
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
	}
	
	public void executa()throws IOException{
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Porta "+ this.porta +" aberta!");

		while(true){
			//aceita um cliente
			Socket cliente = servidor.accept();
			System.out.println("Nova Conexão com o cliente " + 
				cliente.getInetAddress().getHostAddress()
			);
		
		// adiciona saida do cliente à lista
		PrintStream ps = new PrintStream(cliente.getOutputStream());
		this.clientes.add(ps);

		//cria tratador de cliente numa nova thread
		TrataCliente tc =
			new TrataCliente(cliente.getInputStream(), this);
		new Thread(tc).start();
		}
	}

	public void distribuiMensagem(String msg){
		//envia mensagem para todo mundo
		for(PrintStream cliente : this.clientes){
			cliente.println(msg);
		}
	}
}
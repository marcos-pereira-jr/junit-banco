package br.com.cefet.banco.persistencia.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	// Esse m�todo dever� retornar um objeto do tipo Connection ou uma Exce��o
	public Connection getConexao(){
		try{
			System.out.println("Conectando com o banco...");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/banco?autoReconnect=true&useSSL=false","demo_java","1234");
		} catch (SQLException e){
			System.err.println("Erro na conex�o!");
			throw new RuntimeException(e);
		}
	}
}



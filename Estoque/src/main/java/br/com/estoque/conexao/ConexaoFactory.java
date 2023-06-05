package br.com.estoque.conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexaoFactory{
		private static final String USUARIO= "root";
		private static final String SENHA="ced03112021";
		private static final String URL="jdbc:mysql://127.0.0.1/estoque";
		
		//private static final String URL="com.musql.cj.jdbc.Driver://localhost:3306/estoque";
		
		public static Connection conectar() throws SQLException{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				Connection conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
				return conexao;
			}
		
		public static void main(String[] args) {
			try {
				Connection conexao = ConexaoFactory.conectar();
				System.out.println("Conexao realizada com sucesso!");
			}
			catch (SQLException erro) {
				System.out.println("Erro ao conectar o banco de dados!" + erro);
			}
		}
}
package br.com.estoque.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.estoque.conexao.ConexaoFactory;
import br.com.estoque.domain.Fornecedores;


public class FornecedoresDAO {
	
	public void salvar(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into fornecedores");
		sql.append("(descricao)");
		sql.append("values(?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement (sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}
	
	
	public void excluir (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from fornecedores where codigo= ?");
		//sql.append("where codigo= ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("update fornecedores  set descricao=? where codigo= ? ");
		//sql.append("set descricao=?");
		//sql.append("where codigo= ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}
	
	public Fornecedores buscaPorCodigo (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao ");
		sql.append("from fornecedores ");
	    sql.append("where codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}
	
	
	public ArrayList<Fornecedores> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("order by descricao asc ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores>lista= new ArrayList<Fornecedores>();
		
		while(resultado.next()){
			Fornecedores f=new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		return lista;
	}
	
	public ArrayList<Fornecedores>buscarPorDescricao(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("where descricao like ? ");
		sql.append("order by descricao asc ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" +f.getDescricao()+ "%");
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores>lista= new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}
		return lista;
	}
	
	public static void main(String[] args) {
			
			// SALVAR I
		
			Fornecedores f1 = new Fornecedores();
			f1.setDescricao("Juliana Freitas");
			
			Fornecedores f2 = new Fornecedores();
			f2.setDescricao("Marcio Antunes");
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try {
				fdao.salvar(f1);
				fdao.salvar(f2);
				System.out.println("Dados salvos com sucesso!");
			}
			catch(SQLException e) {
				System.out.println("Erro ao salvar os dados");
				e.printStackTrace();
			}
			
		/*
			//SALVAR II
		
			Fornecedores f1 = new Fornecedores();
			f1.setDescricao("Descrição 3");
			
			Fornecedores f2 = new Fornecedores();
			f2.setDescricao("Descrição 4");
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try {
				fdao.salvar(f1);
				fdao.salvar(f2);
				System.out.println("Dados salvos com sucesso!");
			}
			catch(SQLException e) {
				System.out.println("Erro ao salvar os dados");
				e.printStackTrace();
			}
		*/
		/*
		//BuscaPorCodigo
			Fornecedores f1 = new Fornecedores();
			f1.setCodigo(1L);
			
			Fornecedores f2 = new Fornecedores();
			f2.setCodigo(5L);
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try{
				Fornecedores f3 = fdao.buscaPorCodigo(f1);
				Fornecedores f4 = fdao.buscaPorCodigo(f2);
				
				System.out.println("Resultado 1:" + f3);
				System.out.println("Resultado 2:" + f4);
			}
			catch(SQLException e ){
				System.out.println("Erro ao buscar os dados");
				e.printStackTrace();
		}
		*/
		
		 // EXCLUIR
		/*
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(2L);
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.excluir(f1);
			System.out.println("Deletado com sucesso!");
		}
		catch(SQLException e) {
			System.out.println("Erro ao excluir o registro!");
			e.printStackTrace();
		}
		*/
	/*
		//EDITAR
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1L);
		f1.setDescricao("Desc Alterado");
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		
		try {
			fdao.editar(f1);
			System.out.println("Alterado com sucesso!");
		}
		catch(SQLException e ){
			System.out.println("Erro ao altera o regsitro!");
			e.printStackTrace();
			
		}
		*/
		/*
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			ArrayList<Fornecedores>lista = fdao.listar();
			for(Fornecedores f : lista) {
				System.out.println("Resultado:"+ f);
			}
		}
		catch(SQLException e ) {
			System.out.println("Erro ao buscar os dados");
			e.printStackTrace();
		}
		*/
		/*
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("scr");
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
			
			for(Fornecedores f: lista) {
				System.out.println("Resultado: " + f);
			}
		}
		catch(SQLException e) {
			System.out.println("Erro ao buscar os dados ");
			e.printStackTrace();
		}*/
	}
}

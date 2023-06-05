package br.com.estoque.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.estoque.DAO.FornecedoresDAO;
import br.com.estoque.domain.Fornecedores;
import br.com.estoque.util.JSFUtil;
@ManagedBean (name = "MBFornecedores")
@ViewScoped

public class FornecedoresBean {
	private Fornecedores fornecedores;
	
	public Fornecedores getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores (Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	private ListDataModel<Fornecedores>itens;
	
	public ListDataModel<Fornecedores>getItens(){
		return itens;
	}
	public void setItens(ListDataModel <Fornecedores>itens) {
	this.itens = itens;
  }
	//@PostConstruct
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
		}
		catch (SQLException e ) {
			JSFUtil.adicionarMensagemErro("ex.getMensagem()");
			e.printStackTrace();
		}
	}
	public void PrepararNovo() {
		fornecedores = new Fornecedores();
	}
	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
			
			JSFUtil.adicionarMensagemSucesso("Fornecedores salvo com sucesso!");
		}catch (SQLException e ) {
			JSFUtil.adicionarMensagemErro("ex.getMensage()");
			e.printStackTrace();
		}
	}
}
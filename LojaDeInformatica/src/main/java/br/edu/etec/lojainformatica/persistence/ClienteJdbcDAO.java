package br.edu.etec.lojainformatica.persistence;

import java.io.LineNumberInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.etec.lojainformatica.model.Cliente;

public class ClienteJdbcDAO {

	private Connection conn;
	
	
	public ClienteJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Cliente c) throws SQLException{
		String sql = "insert into tb_clientes values ('" + c.getNome() + "','" + c.getEndereco() + "','" + c.getFone() + "','"+c.getEmail() + "')";
		System.out.println(sql);
		PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public void alterar(Cliente cExample) {
		String sql = "update tb_clientes set nome ='" + cExample.getNome()+"','" + cExample.getEndereco() + "','" + cExample.getFone() + "','" + cExample.getEmail() + "')";
		System.out.println(sql);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	public void excluir(int id){
		String sql = "delete from tb_clientes where id_clientes='" +id+"';";
		System.out.println(sql);
	}
	
	public List<Cliente> listar(){
		String sql = "select * from tb_clientes";
		System.out.println(sql);
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id_clientes");
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				String fone = rs.getString("fone");
				String email = rs.getString("email");
				Cliente cliente = new Cliente();
				cliente.setId_cliente(id);
				cliente.setNome(nome);
				cliente.setEndereco(endereco);
				cliente.setFone(fone);
				cliente.setEmail(email);
				clientes.add(cliente);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public Cliente findById(Integer id) {
		String sql = "select * from tb_clientes where id_cliente = "+id;
		System.out.println(sql);
		Cliente cliente = null;
		
		try {
				PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					cliente = new Cliente();
					int idCli = rs.getInt("id_cliente");
					String nome = rs.getString("nome");
					String endereco = rs.getString("endereco");
					String fone = rs.getString("fone");
					String email = rs.getString("email");
					
					cliente.setId_cliente(id);
					cliente.setNome(nome);
					cliente.setEndereco(endereco);
					cliente.setFone(fone);
					cliente.setEmail(email);					
				}
				preparedStatement.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
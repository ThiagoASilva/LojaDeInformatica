package br.edu.etec.lojainformatica.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.etec.lojainformatica.model.Vendas;

public class VendasJdbcDAO {
	
	private Connection conn;
	
	public VendasJdbcDAO(Connection conn){
		this.conn = conn;
	}
	
	public void salvar(Vendas v) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = sdf.format(v.getData());
		String sql = "insert into tb_vendas (id_cliente, data, vlr_total, desconto, vlr_pago) values ('" +v.getId_cliente()+"','"+dataFormatada+"','"+v.getVlr_total()+"','"+v.getDesconto()+"','"+v.getVlr_pago()+"')";
		System.out.println(sql);
		PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
		preparedStatement.executeQuery();
		preparedStatement.close();
	}
	
	public void alterar(Vendas vExample) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = sdf.format(vExample.getData());
		System.out.println(dataFormatada);
		String sql = "update tb_vendas set data='"+dataFormatada+"'',vlr_total="+vExample.getVlr_total()+",desconto="+vExample.getDesconto()+", vlr_pago="+vExample.getVlr_pago()+" where id_venda='"+vExample.getId_venda()+"';";
		System.out.println(sql);
		PreparedStatement preparedStatement;
		try {
				preparedStatement = this.conn.prepareStatement(sql);
				preparedStatement.executeUpdate();
				preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id) {
		String sql =  "delete from tb_vendas where id_vendas='"+id+"';";
		System.out.println(sql);
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
	public List<Vendas> listar(){
		String sql = "select * from tb_vendas";
		System.out.println(sql);
		List<Vendas> vendas = new ArrayList<Vendas>();
		try {
			PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int idVda = rs.getInt("id_venda");
				int idCli = rs.getInt("id_cliente");
				Date data = rs.getDate("data");
				Double vlrTotal = rs.getDouble("vlr_total");
				Double desconto = rs.getDouble("desconto");
				Double vlrPago = rs.getDouble("vlr_pago");
				Vendas venda = new Vendas();
				venda.setId_venda(idVda);
				venda.setId_cliente(idCli);
				venda.setData(data);
				venda.setDesconto(desconto);
				venda.setVlr_total(vlrTotal);
				venda.setDesconto(desconto);
				venda.setVlr_pago(vlrPago);
				vendas.add(venda);
				System.out.println(venda.getId_venda());				
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendas;
	}
	
	public Vendas findById(Integer id) {
		String sql = "select * from tb_vendas where id_venda = "+id;
		System.out.println(sql);
		Vendas venda = null;
			try {
				PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					venda = new Vendas();
					int idVda = rs.getInt("id_venda");
					int idCli = rs.getInt("id_cliente");
					Date data = rs.getDate("data");
					Double vlrTotal = rs.getDouble("vlr_total");
					Double desconto = rs.getDouble("desconto");
					Double vlrPago = rs.getDouble("vlr_pago");
					venda.setId_venda(idVda);
					venda.setId_cliente(idCli);
					venda.setData(data);
					venda.setDesconto(desconto);
					venda.setVlr_total(vlrTotal);
					venda.setVlr_pago(vlrPago);					
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return venda;
	}
}

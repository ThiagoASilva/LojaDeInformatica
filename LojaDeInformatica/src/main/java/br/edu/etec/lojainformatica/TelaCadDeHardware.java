package br.edu.etec.lojainformatica;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.etec.lojainformatica.model.Hardware;
import br.edu.etec.lojainformatica.persistence.HardwareJdbcDAO;
import br.edu.etec.lojainformatica.persistence.JdbcUtil;

public class TelaCadDeHardware extends TelaDeCadastro{

	Hardware hardware = new Hardware();
	
	JLabel lb1 = new JLabel("Descrição");
	JTextField txbDecricao = new JTextField();
	
	JLabel lb2 = new JLabel("Preço unitário");
	JTextField txbPrecoUnit = new JTextField();
	
	JLabel lb3 = new JLabel("Quantidade atual");
	JTextField txbQtdeAtual = new JTextField();
	
	JLabel lb4 = new JLabel("Quantidade minima");
	JTextField txbQtdeMinima = new JTextField();	
	
	public TelaCadDeHardware() {
		super(4, 2);//quatro linhas e duas colunas
		//Na hora de add os componentes, considerar a ordem deles
		//conforme usamos abaixo
		this.painelParaCampos.add(lb1);
		this.painelParaCampos.add(txbDecricao);
		
		this.painelParaCampos.add(lb2);
		this.painelParaCampos.add(txbPrecoUnit);
		
		this.painelParaCampos.add(lb3);
		this.painelParaCampos.add(txbQtdeAtual);
		
		this.painelParaCampos.add(lb4);
		this.painelParaCampos.add(txbQtdeMinima);
		
		this.btnLimpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				TelaCadDeHardware.this.limparFormulario();
				
			}
		});
		
		this.btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadDeHardware.this.salvar();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		this.btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				TelaCadDeHardware.this.cancelar();
				
			}
		});
		
		this.btnAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadDeHardware.this.alterar();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
				
			}
		});
		
		this.btnListar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadDeHardware.this.listar();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
				
			}
		});
	}

	@Override
	void limparFormulario(){
		System.out.println("void limparFormulario(){....");
		this.txbDecricao.setText("");
		this.txbPrecoUnit.setText("");
		this.txbQtdeAtual.setText("");
		this.txbQtdeMinima.setText("");
		
	}

	@Override
	void salvar(){
		String salvarOuAlterar = "salvar";
		
		//o botao salvar vai salvar ou alterar. Se tiver id ele altera se nao ele salva
		String id = this.txtId.getText();
		int idInt = -1;
		
		try {
			idInt = Integer.parseInt(id);
			salvarOuAlterar = "alterar"; //se deu para converter num int então altera
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.hardware.setDescricao(this.txbDecricao.getText());
			this.hardware.setPreco_unit(Double.parseDouble(this.txbPrecoUnit.getText().replaceAll(",", ".")));
			this.hardware.setQtde_atual(Integer.parseInt(this.txbQtdeAtual.getText()));
			this.hardware.setQtde_minima(Integer.parseInt(this.txbQtdeMinima.getText()));
			
			Connection connection = JdbcUtil.getConnection();
			HardwareJdbcDAO hardwareJdbcDAO = new HardwareJdbcDAO(connection);
			
			if (salvarOuAlterar.equals("salvar")) {
				hardwareJdbcDAO.salvar(this.hardware);
			} else {
				this.hardware.setId_hardware(idInt);
				hardwareJdbcDAO.alterar(this.hardware);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	void cancelar() {
		this.setVisible(false);
		
	}

	@Override
	void alterar() throws SQLException {
		String id = this.txtId.getText();
		try {
			int idInt = Integer.parseInt(id);
			Connection conn = JdbcUtil.getConnection();
			HardwareJdbcDAO hardwareJdbcDAO = new HardwareJdbcDAO(conn);
			
			Hardware hard = hardwareJdbcDAO.findById(idInt);
			if(hard != null) {
					this.txbDecricao.setText(hard.getDescricao());
					this.txbPrecoUnit.setText(hard.getPreco_unit()+"");
					this.txbQtdeAtual.setText(hard.getQtde_atual()+"");
					this.txbQtdeMinima.setText(hard.getQtde_minima()+"");
			}else {
				JOptionPane.showMessageDialog(this, "Nao ha hardwares com esse id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	void excluir() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	void listar() throws SQLException {
		Connection conn;
		try {
			conn = JdbcUtil.getConnection();
			HardwareJdbcDAO hardwareJdbcDAO = new HardwareJdbcDAO(conn);
			List<Hardware> list = hardwareJdbcDAO.listar();
			String[] strArr = new String[list.size() + 1]; // +1 pro cabeçalho
			strArr[0] = "id_Hardware \t descricao \t preco_unit \t qtde_atual \t qtde_minima ";
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					String id_hardware = list.get(i).getId_hardware() + "";
					String descricao = list.get(i).getDescricao();
					String preco_unit = list.get(i).getPreco_unit() + "";
					String qtde_atual = list.get(i).getQtde_atual() + "";
					String qtde_minima = list.get(i).getQtde_minima() + "";
					strArr[i + 1] = id_hardware + "\t " + descricao + " \t " + preco_unit + " \t " + qtde_atual + " \t " + qtde_minima;
					System.out.println(strArr[i]);
				}
			}
			this.list.setListData(strArr);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

package br.edu.etec.lojainformatica;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaCadClientes extends TelaDeCadastro{

	Cliente cliente = new Cliente();
	
	JLabel lbNome = new JLabel("Nome");
	JTextField txtNome = new JTextField();
	
	JLabel lbEndereco = new JLabel("Endereço");
	JTextField txtEndereco = new JTextField();
	
	JLabel lbFone = new JLabel("Fone");
	JTextField txtFone = new JTextField();
	
	JLabel lbEmail = new JLabel("Email");
	JTextField txtEmail = new JtextField();
	
	
	public TelaCadClientes() {
		super(4, 2);//quatro linhas e duas colunas
		// Na hora de add od componentes, considerar a ordem deles
		// conforme usamos abaixo
		
		this.painelParaCampos.add(lbNome);
		this.painelParaCampos.add(txtNome);
		
		this.painelParaCampos.add(lbEndereco);
		this.painelParaCampos.add(txtEndereco);
		
		this.painelParaCampos.add(lbFone);
		this.painelParaCampos.add(txtFone);
		
	}

	
}

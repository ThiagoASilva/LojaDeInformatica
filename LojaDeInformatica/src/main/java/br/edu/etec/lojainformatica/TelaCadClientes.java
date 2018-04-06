package br.edu.etec.lojainformatica;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
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
	JTextField txtEmail = new JTextField();
	
	
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
		
		this.painelParaCampos.add(lbEmail);
		this.painelParaCampos.add(txtEmail);
		System.out.println("terminando de adicionar os campos, add agora actionlistener...");
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		this.painelListagem.add(list);
		
		this.btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadClientes.this.limparFormulario();
			}			
		});
		
		this.btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
						TelaCadClientes.this.salvar();
				}catch (Exception e1){
						e1.printStackTrace();
				}
			}
			
		});
		
		this.btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				TelaCadClientes.this.cancelar();
				
			}
		});
	}

	
}

package sistema.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sistema.BancoDeDados;
import sistema.Navegador;
import sistema.entidades.Cargo;

public class CargosConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Cargo cargoAtual;
	JLabel labelTitulo, labelCargo;
	JTextField campoCargo;
	JButton botaoPesquisar,botaoEditar,botaoExcluir;
	DefaultListModel<Cargo> listasCargosModelo = new DefaultListModel<Cargo>();
	JList<Cargo> listaCargos;
	
	public CargosConsultar() {
		criarComponentes();
		criarEventos();
	}
	
	public void criarComponentes() {
		setLayout(null);
		
		labelTitulo = new JLabel("Consulta de Cargos", JLabel.CENTER);
		labelTitulo.setFont (new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
		labelCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		campoCargo = new JTextField();
		botaoPesquisar = new JButton("Pesquisar Cargo");
		botaoEditar = new JButton("Editar Cargo");
		botaoEditar.setEnabled(false);
		botaoExcluir = new JButton("Excluir Cargo");
		botaoExcluir.setEnabled(false);
		listasCargosModelo = new DefaultListModel<Cargo>();
		listaCargos = new JList<Cargo>();
		listaCargos.setModel(listasCargosModelo);
		listaCargos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		labelTitulo.setBounds(20,20,660,40);
		labelCargo.setBounds(150,120,400,20);
		campoCargo.setBounds(150,140,400,40);
		botaoPesquisar.setBounds(560,140,130,40);
		listaCargos.setBounds(150,200,400,240);
		botaoEditar.setBounds(560,360,130,40);
		botaoExcluir.setBounds(560,400,130,40);
		
		add(labelTitulo);
		add(labelCargo);
		add(campoCargo);
		add(listaCargos);
		add(botaoPesquisar);
		add(botaoEditar);
		add(botaoExcluir);
		
		setVisible(true);	
		
	}
	
	public void criarEventos() {
		botaoPesquisar.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sqlPesquisarCargos(campoCargo.getText());
			}
			
		});
		
		botaoEditar.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Navegador.cargosEditar(cargoAtual);
			}
		});
		
		botaoExcluir.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sqlDeletarCargo();
			}

			private void sqlDeletarCargo() {
				// TODO Auto-generated method stub
				
			}
		});
		
		listaCargos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cargoAtual = listaCargos.getSelectedValue();
				if(cargoAtual == null) {
					botaoEditar.setEnabled(false);
					botaoExcluir.setEnabled(false);
				}else {
					botaoEditar.setEnabled(true);
					botaoExcluir.setEnabled(true);
				}
				
			}
		});
		
	}
	
	private void sqlPesquisarCargos(String nome) {
		//conexão
		Connection conexao;
		//Instrução SQL
		Statement instrucaoSQL;
		//Resultados
		ResultSet resultados;
		
		try {
			//conectando ao banco de dados
			conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao,BancoDeDados.usuario, BancoDeDados.senha);
			
			//criando a instução sql
			instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultados = instrucaoSQL.executeQuery("SELECT * FROM cargos WHERE nome like'%"+nome+"%'");
			
			listasCargosModelo.clear();
								
			while(resultados.next()) {
				Cargo cargo = new Cargo();				
				cargo.setId(resultados.getInt("id"));
				cargo.setNome(resultados.getString("nome"));
				
				listasCargosModelo.addElement(cargo);
			}
						
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar os cargos");
			Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

}

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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.BancoDeDados;
import sistema.entidades.Cargo;

public class CargosEditar extends JPanel {

	private static final long serialVersionUID = 1L;
	Cargo cargoAtual;
	JLabel labelTitulo, labelCargo;
	JTextField campoCargo;
	JButton botaoGravar;
	
	public CargosEditar(Cargo cargo) {
		cargoAtual = cargo;
		criarComponentes();
		criarEventos();
	}
		
	private void criarComponentes() {
		setLayout(null);
		
		labelTitulo = new JLabel("Edição de cargo",JLabel.CENTER);
		labelTitulo.setFont (new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
		labelCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		campoCargo = new JTextField(cargoAtual.getNome());
		botaoGravar = new JButton("Salvar");
	
		labelTitulo.setBounds(20,20,660,40);
		labelCargo.setBounds(150,120,400,20);
		campoCargo.setBounds(150,140,400,40);
		botaoGravar.setBounds(250,380,200,40);
		
		add(labelTitulo);
		add(labelCargo);
		add(campoCargo);
		add(botaoGravar);
		
		setVisible(true);
	}
	
	private void criarEventos() {
		botaoGravar.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargoAtual.setNome(campoCargo.getText());
				sqlAtualizarCargo();
			}
		});

	}
	
	private void sqlAtualizarCargo() {
		//validando nome
		if(campoCargo.getText().length() <= 3) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha o nome corretamente");
			return;
		}
		//conexão
		Connection conexao;
		//instrução SQL
		Statement instrucaoSQL;
		//resultados
		ResultSet resultados;
		
		try {
			//conectando ao banco de dados
			conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
			//criando a instrução SQL
			instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			instrucaoSQL.executeUpdate("UPDATE cargos set nome='"+campoCargo.getText()+"' WHERE id='"+cargoAtual.getId()+"'");
			
			JOptionPane.showMessageDialog(null, "Cargo atualizado com sucesso!");
			
			conexao.close();
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar o cargo");
			Logger.getLogger(CargosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	
}

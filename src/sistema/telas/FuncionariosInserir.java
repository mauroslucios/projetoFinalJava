package sistema.telas;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import sistema.entidades.Funcionario;

public class FuncionariosInserir extends JPanel {

	JLabel labelTitulo, labelNome, labelSobrenome, labelDataNascimento, labelEmail, labelCargo,labelSalario;
	JTextField campoNome, campoSobrenome, campoEmail, campoSalario;
	JFormattedTextField campoDataNascimento;
	JComboBox comboboxCargo;
	JButton botaoGravar;
	
	private void criarComponentes() {
		setLayout(null);
		
		labelTitulo = new JLabel("Cadastro de Funcionário", JLabel.CENTER);
		labelTitulo.setFont (new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
		labelNome = new JLabel("Nome:", JLabel.LEFT);
		campoNome = new JTextField();
		labelSobrenome = new JLabel("Sobrenome:", JLabel.LEFT);
		campoSobrenome = new JTextField();
		labelDataNascimento = new JLabel("Data de Nascimento", JLabel.LEFT);
		campoDataNascimento = new JFormattedTextField();
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.install(campoDataNascimento);
		}catch(ParseException ex) {
			Logger.getLogger(FuncionariosInserir.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		labelEmail = new JLabel("E-mail:", JLabel.LEFT);
		campoEmail = new JTextField(); 
		labelCargo = new JLabel("Cargo:",JLabel.LEFT);
		comboboxCargo = new JComboBox<Object>();
		labelSalario = new JLabel("Salário:", JLabel.LEFT);
		DecimalFormat formatter = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
		campoSalario = new JFormattedTextField(formatter);
		campoSalario.setValue(0.00);
		botaoGravar = new JButton("Adicionar");
		
		labelTitulo.setBounds(20,20,660,40);
		labelNome.setBounds(150,80,400,20);
		campoNome.setBounds(150,160,400,20);
		labelSobrenome.setBounds(150,140,400,20);
		campoSobrenome.setBounds(150,160,400,20);
		labelDataNascimento.setBounds(150,200,400,20);
		campoDataNascimento.setBounds(150,220,400,40);
		labelEmail.setBounds(150,260,400,20);
		campoEmail.setBounds(150,280,400,40);
		labelCargo.setBounds(150,340,400,40);
		comboboxCargo.setBounds(150,340,400,40);
		labelSalario.setBounds(150,380,400,20);
		botaoGravar.setBounds(560,400,130,40);
		
		add(labelTitulo);
		add(labelNome);
		add(campoNome);
		add(labelSobrenome);
		add(campoSobrenome);
		add(labelDataNascimento);
		add(campoDataNascimento);
		add(labelEmail);
		add(campoEmail);
		add(labelCargo);
		add(comboboxCargo);
		add(labelSalario);
		add(campoSalario);
		add(botaoGravar);		
		
		sqlCarregarCargos();
		
		setVisible(true);	
		
		}
	
	private void criarEventos() {
		botaoGravar.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Funcionario novoFunconario = new Funcionario();
			}
		}
	}
}

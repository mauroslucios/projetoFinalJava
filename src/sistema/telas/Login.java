package sistema.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sistema.Navegador;

public class Login extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel labelUsuario;
	JTextField campoUsuario;
	JLabel labelSenha;
	JPasswordField campoSenha;
	JButton botaoEntrar;
	
	public Login() {
		criarComponentes();
		criarEventos();
	}
	
	private void criarComponentes() {
		setLayout(null);
		
		JLabel labelTitulo = new JLabel("Bem vindo ao Sistema da Company SA",JLabel.CENTER);
		labelTitulo.setFont (new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
		
		labelUsuario = new JLabel("Usuário", JLabel.LEFT);
		campoUsuario = new JTextField();
		labelSenha = new JLabel("Senha", JLabel.LEADING);
		campoSenha = new JPasswordField();
		botaoEntrar = new JButton("Entrar");
		
		labelTitulo.setBounds(20,100,660,40);
		labelUsuario.setBounds(250,220,200,20);
		campoUsuario.setBounds(250,240,200,40);
		labelSenha.setBounds(250,280,200,20);
		campoSenha.setBounds(250,300,200,40);
		botaoEntrar.setBounds(250,350,200,40);
		
		add(labelTitulo);
		add(labelUsuario);
		add(campoUsuario);
		add(labelSenha);
		add(campoSenha);
		add(botaoEntrar);
		
		setVisible(true);
	}
	
	private void criarEventos() {
		botaoEntrar.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(campoUsuario.getText().equals("admin") && new String(campoSenha.getPassword()).equals("admin")) {
					Navegador.inicio();
				}else {
					JOptionPane.showMessageDialog(null, "Acesso não permitido");
				}
			}
		});
	}
	
}

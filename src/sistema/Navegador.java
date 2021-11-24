package sistema;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import sistema.entidades.Cargo;
import sistema.telas.CargosConsultar;
import sistema.telas.CargosEditar;
import sistema.telas.CargosInserir;
import sistema.telas.Inicio;
import sistema.telas.Login;

public class Navegador {
	
	//Menu
	private static boolean menuConstruido;
	private static boolean menuHabilitar;
	private static JMenuBar menuBar;
	private static JMenu menuArquivo, menuFuncionario, menuCargos, menyRelatorios;
	private static JMenuItem miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar;
	private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miReltoriosSlarios;
	
	public static void login() {
		Sistema.tela = new  Login();
		Sistema.frame.setTitle("Funcionários Company SA");
		Navegador.atualizarTela();
	}
	
	public static void inicio() {
		Sistema.tela = new Inicio();
		Sistema.frame.setTitle("Funcionários Company SA");
		Navegador.atualizarTela();
	}
	
	public static void cargosCadastrar() {
		Sistema.tela = new CargosInserir();
		Sistema.frame.setTitle("Cadastrar Cargos");
		Navegador.atualizarTela();
	}
	
	public static void cargosConsultar() {
		Sistema.tela = new CargosConsultar();
		Sistema.frame.setTitle("Consultar Cargos");
		Navegador.atualizarTela();
	}
	
	public static void cargosEditar(Cargo cargo) {
		Sistema.tela = new CargosEditar(cargo);
		Sistema.frame.setTitle("Editar Cargos");
		Navegador.atualizarTela();
	}
	
	//método que remove a tela atual e adiciona a próxima tela
	private static void atualizarTela() {
		Sistema.frame.getContentPane().removeAll();
		Sistema.tela.setVisible(true);
		Sistema.frame.add(Sistema.tela);
	}
	
	
	

}

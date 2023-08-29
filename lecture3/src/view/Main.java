package lecture3.src.view;

import javax.swing.JOptionPane;

import lecture3.src.controller.RedesController;

public class Main {
	public static void main(String[] args) {
		RedesController redesController = new RedesController();

		redesController.ip();
		redesController.ping();

		// String[] options = { "IP", "Ping", "Sair" };
		// int choice;
		// do {
		// choice = JOptionPane.showOptionDialog(
		// null,
		// "Escolha uma opção:",
		// "Redes",
		// JOptionPane.DEFAULT_OPTION,
		// JOptionPane.QUESTION_MESSAGE,
		// null,
		// options,
		// options[0]);

		// switch (choice) {
		// case 0:
		// redesController.ip();
		// break;
		// case 1:
		// redesController.ping();
		// break;
		// case 2:
		// System.out.println("Encerrando o programa.");
		// break;
		// default:
		// System.out.println("Opção inválida.");
		// break;
		// }
		// } while (choice != 2);
	}
}

package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {

		int resp = 0;

		do {
			resp = Integer.parseInt(
					JOptionPane.showInputDialog(null, "Escolh o metodo para executar: \n 1- PING \n 2- IP \n 9- Sair"));

			switch (resp) {
			case 1:
				RedesController rc1 = new RedesController();
				rc1.ping();
				break;
			case 2:
				RedesController rc2 = new RedesController();
				rc2.ip();
				break;
			}
		} while (resp != 9);

	}

}

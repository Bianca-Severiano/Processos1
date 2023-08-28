package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	public void chamaProcesso(String c, int i, String os) {
		try {
			Process processo = Runtime.getRuntime().exec(c);

			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			if (i == 1) { // IP e Rede Windows - Apresenta Resultado
				while (linha != null) {
					if (linha.contains(" IPv4.")) {
						String[] l = linha.split(":");
						System.out.println(l[l.length - 1]);

					} else if (linha.contains("Adaptador Ethernet")) {
						String[] l = linha.split(" ");
						System.out.println(l[l.length - 1]);
					}
					linha = buffer.readLine();
				}
			} else if (i == 2) { // IP e Rede Linux - Apresenta Resultado
				while (linha != null) {
					if (linha.contains("ether ")) {
						String[] l = linha.split(" ");
						System.out.println(l[l.length-1]);
					}					
					if (linha.contains("inet ") && linha.contains("broadcast") ) {
						String[] l = linha.split(" ");
						System.out.println(l[9]);
					}
					linha = buffer.readLine();
				}
			} else if (i == 3) { // PING Windows - Apresenta Resultado
				while (linha != null) {
					if (linha.contains("ms, ")) {
						String[] l = linha.split("= ");
						System.out.println(l[l.length - 1]);
					}
					linha = buffer.readLine();
				}

			} else if (i == 4) { // PING Linux - Apresenta Resultado
				while (linha != null) {
					if (linha.contains("rtt ")) {
						String[] l = linha.split("/");
						System.out.println(l[4]);
					}
					linha = buffer.readLine();
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (Exception e) {
			String msg = e.getMessage();
			System.err.println(msg);
		}

	}

	public void ip() {
		String os = os();
		if (os.contains("Windows")) {
			String c = "IPCONFIG";
			chamaProcesso(c, 1, os);

		} else if (os.contains("Linux")) {
			String c = "ifconfig";
			chamaProcesso(c, 2, os);
		}
	}

	public void ping() {
		String os = os();
		if (os.contains("Windows")) {
			String c = "ping -4 -n 10 www.google.com.br";
			chamaProcesso(c, 3, os);

		} else if (os.contains("Linux")) {
			String c = "ping -4 -c 10 www.google.com.br";
			chamaProcesso(c, 4, os);
		}
	}

}

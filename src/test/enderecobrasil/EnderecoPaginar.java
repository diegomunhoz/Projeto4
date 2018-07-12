package test.enderecobrasil;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import to.enderecobrasil.Cidade;
import to.enderecobrasil.Endereco;
import to.enderecobrasil.Estado;
import util.GeraPDF;
import dao.enderecobrasil.EnderecoDAO;

/**
*
* @author Diego Munhoz
*/
public class EnderecoPaginar {
	private static EnderecoDAO dao = new EnderecoDAO();
	private static Endereco endereco = new Endereco();
	private static StringBuffer buffer = new StringBuffer();
	private static Scanner scanner = new Scanner(System.in);
	private static String estado = "";
	private static String cidade = "";

	private static void obtemEstado() {
		endereco.setCidade(new Cidade());
		endereco.getCidade().setEstado(new Estado());

		buffer.append("\n" + "=====================================================================================");
		buffer.append("\n" + " entre com o dados de pesquisa".toUpperCase());
		buffer.append("\n" + " entre com a sigla do estado desejado".toUpperCase());

		System.out.println(buffer.toString());

		while (estado != null && estado.isEmpty()) {
			estado = scanner.nextLine().toUpperCase();
			if (estado != null && !estado.isEmpty() && estado.length() == 2) {
				endereco.getCidade().getEstado().setSigla(estado);
				if (dao.paginar(1, 1, endereco).size() == 0) {
					System.out.println(" sigla não encontrada no banco".toUpperCase());
					scanner.nextLine();
					estado = "";
				}
			} else {
				estado = "";
			}
			limparTela();
			System.out.println(buffer.toString());
		}

	}

	private static void obtemCidade() {
		limparTela();
		buffer.append("\n" + " ------> " + estado.toUpperCase());
		buffer.append("\n" + " entre com a cidade desejada ".toUpperCase());
		System.out.println(buffer.toString());

		while (cidade != null && cidade.isEmpty()) {
			cidade = scanner.nextLine().toUpperCase();
			if (cidade != null && !cidade.isEmpty()) {
				endereco.getCidade().setNome(cidade);
				if (dao.paginar(1, 1, endereco).size() == 0) {
					System.out.println(" cidade não encontrada neste estado".toUpperCase());
					scanner.nextLine();
					cidade = "";
				}
			} else {
				cidade = "";
			}
			limparTela();
			System.out.println(buffer.toString());
		}
	}

	public static void main(String[] args) {
		try {
			obtemEstado();
			obtemCidade();

			Integer pagina = 1;
			buffer.append("\n" + " ------> " + cidade);
			buffer.append("\n"
					+ "=====================================================================================");
			buffer.append("\n" + "                           relatório de endereços pág.: ");
			String opcao = "";

			List<Endereco> lista;
			while (true) {
				limparTela();
				System.out.println(buffer.toString().toUpperCase() + pagina);
				lista = dao.paginar(pagina, 10, endereco);
				if (lista.size() != 0) {
					for (Endereco e : lista) {
						System.out
								.println("-------------------------------------------------------------------------------------");
						System.out.println(" CEP.: " + e.getCep() + " Bairro.: " + e.getBairro() + " Logradouro.: "
								+ e.getLogradouro());
					}
				} else {
					System.out.println(" não existe mais dados".toUpperCase());
				}
				System.out
						.println("-------------------------------------------------------------------------------------");
				System.out.println(" Tecle, F - Fim, A - Anterior, P - Próximo, R - Relatorio".toUpperCase());
				opcao = scanner.nextLine().toUpperCase();

				if (opcao.equals("F")) {
					return;
				} else if (opcao.equals("A")) {
					if (pagina == 1) {
						System.out.println(" não existe dados para retornar".toUpperCase());
						scanner.nextLine();
					} else {
						pagina--;
					}
				} else if (opcao.equals("P")) {
					pagina++;
				} else if (opcao.equals("R")) {
					System.out.println(" processamento parado até fechar o relatório".toUpperCase());
					gerarPDF(lista);
				} else {
					System.out.println(" opção inválida".toUpperCase());
					scanner.nextLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void limparTela() {
		for (int i = 0; i < 10000; ++i) {
			System.out.println();
		}
	}

	@SuppressWarnings("unchecked")
	private static void gerarPDF(List<Endereco> lista) {

		if (lista == null || lista.size() == 0) {
			JOptionPane.showMessageDialog(null, "PESQUISA VAZIA NãO A O QUE REPORTAR EM PDF".toUpperCase());
			return;
		}

		List<Endereco> beanCollection = lista;
		HashMap parametros = new HashMap();

		parametros.put("dataGeracao", new Date());

		InputStream inputStream = GeraPDF.class
				.getResourceAsStream("/repositorio/enderecobrasil/report/RelatorioEnderecos.jasper");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(beanCollection);
		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(inputStream, parametros, ds);
			GeraPDF showPDF = new GeraPDF(impressao);
			showPDF.setLocationRelativeTo(null);
			showPDF.setModal(true);
			showPDF.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}

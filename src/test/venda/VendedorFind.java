package test.venda;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import to.venda.ItemDaVenda;
import to.venda.Venda;
import to.venda.Vendedor;
import dao.venda.VendedorDAO;

/**
*
* @author Diego Munhoz
*/
public class VendedorFind {
	public static void main(String[] args) {
		try {
			Vendedor vendedor = new VendedorDAO().buscaPorId(1L);

			if (vendedor == null) {
				System.out.println(" vendedor não encontrado".toUpperCase());
				return;
			}

			System.out.println(" =======================================================");
			System.out.println("                  relatório do vendedor".toUpperCase());
			System.out.println(" =======================================================");
			System.out.println("  Nome......: " + vendedor.getNome());
			System.out.println("  Telefone..: " + vendedor.getTelefone());
			System.out.println(" -------------------------------------------------------");
			System.out.println("             relatório de vendas do vendedor".toUpperCase());
			System.out.println(" -------------------------------------------------------");
			if (vendedor.getListaDeVendasEfetivadas() == null || vendedor.getListaDeVendasEfetivadas().isEmpty()) {
				System.out.println("  o vendedor ainda não efetuou nenhuma venda".toUpperCase());
				return;
			}
			for (Venda venda : vendedor.getListaDeVendasEfetivadas()) {

				System.out.println("   _______________________________________________");
				System.out.println("\n     Data.......: "
						+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(venda.getDataDaVenda()));
				System.out.println("     Cliente....: " + venda.getCliente().getNome());
				System.out.println("     Total......: ".toUpperCase() + venda.getValorTotal());
				System.out.println("   _______________________________________________");

				for (ItemDaVenda item : venda.getItensDaVenda()) {
					System.out.println("       Produto....: " + item.getProduto().getNome());
					System.out.println("       Descrição..: "

					+ limitaString(item.getProduto().getDescricao(), 26));
					System.out.println("       ValorUnit..: R$" + formatador(item.getValorUnitario()));
					System.out.println("       Quantidade.: " + item.getQuantidade());
					System.out.println("       SubTotal...: R$"
							+ formatador(item.getQuantidade() * item.getValorUnitario()));
					System.out.println("   -----------------------------------------------");
				}

			}
			System.out.println(" =======================================================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String formatador(double valor) {
		DecimalFormat formatador = new DecimalFormat("##,##00.00");
		return formatador.format(valor).replace(".", ",");
	}

	public static String limitaString(String testes, int tamanho) {
		if (testes != null && testes.length() > tamanho) {
			return testes.substring(0, tamanho + 1) + "...";
		}
		return testes;
	}
}

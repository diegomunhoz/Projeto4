package test.venda;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import to.venda.Cliente;
import to.venda.ItemDaVenda;
import to.venda.Venda;
import dao.venda.ClienteDAO;

/**
*
* @author Diego Munhoz
*/
public class ClienteFind {
	public static void main(String[] args) {
		try {
			Cliente cliente = new ClienteDAO().buscaPorId(1L);

			if (cliente == null) {
				System.out.println(" cliente não encontrado".toUpperCase());
				return;
			}

			System.out.println(" =======================================================");
			System.out.println("                  relatório do cliente".toUpperCase());
			System.out.println(" =======================================================");
			System.out.println("  Nome......: " + cliente.getNome());
			System.out.println("  Telefone..: " + cliente.getTelefone());
			System.out.println(" -------------------------------------------------------");
			System.out.println("             relatório de compras cliente".toUpperCase());
			System.out.println(" -------------------------------------------------------");
			if (cliente.getListaDeComprasEfetivadas() == null || cliente.getListaDeComprasEfetivadas().isEmpty()) {
				System.out.println("  o cliente ainda não efetuou nenhuma compra".toUpperCase());
				return;
			}
			for (Venda venda : cliente.getListaDeComprasEfetivadas()) {

				System.out.println("   _______________________________________________");
				System.out.println("\n     Data.......: "
						+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(venda.getDataDaVenda()));
				System.out.println("     Vendedor...: " + venda.getVendedor().getNome());
				System.out.println("     Total......: ".toUpperCase() + venda.getValorTotal());
				System.out.println("   _______________________________________________");

				for (ItemDaVenda iten : venda.getItensDaVenda()) {
					System.out.println("       Produto....: " + iten.getProduto().getNome());
					System.out.println("       Descricao..: "

					+ limitaString(iten.getProduto().getDescricao(), 26));
					System.out.println("       ValorUnit..: R$" + formatador(iten.getValorUnitario()));
					System.out.println("       Quantidade.: " + iten.getQuantidade());
					System.out.println("       SubTotal...: R$"
							+ formatador(iten.getQuantidade() * iten.getValorUnitario()));
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
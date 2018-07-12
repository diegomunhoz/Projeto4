package test.venda;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import to.venda.ItemDaVenda;
import to.venda.Venda;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaFind {
	public static void main(String[] args) {
		try {
			Venda venda = (Venda) new VendaDAO().buscaPorId(1L);
			if (venda == null) {
				System.out.println("venda não encontrada".toUpperCase());
				return;
			}

			System.out.println(" ===============================================");
			System.out.println(" |             relatório de venda              |".toUpperCase());
			System.out.println(" ===============================================");
			System.out.println("   Data.......: "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(venda.getDataDaVenda()));
			System.out.println("   Cliente....: " + venda.getCliente().getNome());
			System.out.println("   Vendedor...: " + venda.getVendedor().getNome());
			System.out.println(" ===============================================");

			for (ItemDaVenda item : venda.getItensDaVenda()) {
				System.out.println("     Produto....: " + item.getProduto().getNome());
				System.out.println("     Descricao..: "

				+ limitaString(item.getProduto().getDescricao(), 26));
				System.out.println("     ValorUnit..: R$" + formatador(item.getValorUnitario()));
				System.out.println("     Quantidade.: " + item.getQuantidade());
				System.out.println("     SubTotal...: R$" + formatador(item.getQuantidade() * item.getValorUnitario()));
				System.out.println(" -----------------------------------------------");

			}

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

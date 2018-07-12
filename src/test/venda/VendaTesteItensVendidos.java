package test.venda;

import java.text.DecimalFormat;
import java.util.List;

import to.venda.Produto;
import dao.venda.ClienteDAO;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaTesteItensVendidos {
	public static void main(String[] args) {
		try {

			String periodoDe = null;// 2012-12-31 23:59:59
			String periodoAte = null;// 2012-01-01 00:00:00
			Long idCliente = null;

			List<List<Object>> lista = new VendaDAO().buscaRelacaoDeProdutosVendidos(periodoDe, periodoAte, idCliente);

			if (lista == null || lista.isEmpty()) {
				System.out.println(" lista vazia processamento encerrado".toUpperCase());
			}
			{
				System.out.println(" ================================================================");
				System.out.println("                  relatório de produtos vendidos ".toUpperCase());
				System.out.println("                  ------------------------------ ".toUpperCase());
				System.out.println("  Periodo Analisado: ".toUpperCase()
						+ (periodoDe == null ? "0001-01-01 00:00:00" : periodoDe) + " até ".toUpperCase()
						+ (periodoAte == null ? "9999-12-31 23:59:59" : periodoAte));
				System.out.println("  Cliente: "
						+ ((idCliente == null || idCliente == 0) ? "todos".toUpperCase() : +idCliente + " - "
								+ new ClienteDAO().buscaPorId(idCliente).getNome()));
				System.out.println(" ================================================================");
			}
			Double ValorMedioGastoEmCompras = 0.0;
			Double ValorTotalGastoEmCompras = 0.0;
			Integer i = 0;
			for (List<Object> linha : lista) {

				Produto produto = (Produto) linha.get(0);
				Long totalProdutosVendidos = (Long) linha.get(1);
				Double porcentagem = (Double) linha.get(2);
				Integer qtdVendida = (Integer) linha.get(3);
				Double totVendidoDoProduto = (Double) linha.get(4);
				Double valorMedioDeVenda = (Double) linha.get(5);
				{
					System.out.println(" ----------------------------------------------------------------");
					System.out.println(" Produto................: " + produto.getNome() + " - "
							+ limitaString(produto.getDescricao(), 25));
					System.out.println(" Total de Vendas Gerais.: " + totalProdutosVendidos);
					System.out.println(" Quantidade Vendida.....: " + qtdVendida);
					System.out.println(" Porcentagem de Venda...: " + formatador(porcentagem) + " %");
					System.out.println(" Valor Medio Vendido....: " + formatador(valorMedioDeVenda));
					System.out.println(" Valor Arrecadado Do Pro: " + formatador(totVendidoDoProduto));
				}
				i++;
				ValorMedioGastoEmCompras += valorMedioDeVenda;
				ValorTotalGastoEmCompras += totVendidoDoProduto;

			}
			System.out.println(" ----------------------------------------------------------------");
			{
				System.out.println(" ================================================================");
				System.out.println("    Valor total das compras...: R$".toUpperCase()
						+ formatador((ValorTotalGastoEmCompras)));
				System.out.println("    Valor médio gasto.........: R$".toUpperCase()
						+ formatador((ValorMedioGastoEmCompras / i)));
				System.out.println(" ================================================================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String formatador(double valor) {
		DecimalFormat formatador = new DecimalFormat("##,##00.00");
		return formatador.format(valor).replace(".", ",");
	}

	private static String limitaString(String testes, int tamanho) {
		if (testes != null && testes.length() > tamanho) {
			return testes.substring(0, tamanho + 1) + "...";
		}
		return testes;
	}

}

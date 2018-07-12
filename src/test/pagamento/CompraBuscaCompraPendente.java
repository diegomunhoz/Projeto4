package test.pagamento;

import java.text.SimpleDateFormat;
import java.util.List;

import to.pagamento.Compra;
import to.pagamento.FormaPagamento;
import dao.pagamento.CompraDAO;

/**
*
* @author Diego Munhoz
*/
public class CompraBuscaCompraPendente {
	public static void main(String[] args) {
		try {

			List<Object> lista = new CompraDAO().findAll(Compra.class);

			if (lista == null || lista.isEmpty()) {
				System.out.println(" não existe compras pendentes ".toUpperCase());
				return;
			}
			for (Object o : lista) {
				Compra c = (Compra) o;
				if (c.isPendenteStatusPagamento()) {
					System.out.println(" Compra...: " + c.getObservacao());
					System.out.println(" Data.....: " + new SimpleDateFormat("dd/MM/yyyy").format(c.getData()));
					System.out.println(" ValorTot.: " + c.getValorTotal());
					for (FormaPagamento fp : c.getParcelasPendentes()) {
						System.out.println("     DataVencimento: "
								+ new SimpleDateFormat("dd/MM/yyyy").format(fp.getDataVencimento()) + " Valor: "
								+ fp.getValor());
					}
					System.out.println(" ------------------------------------------------------");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

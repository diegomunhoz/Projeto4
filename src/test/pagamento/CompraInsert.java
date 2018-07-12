package test.pagamento;

import java.util.Date;

import to.pagamento.Compra;
import dao.pagamento.CompraDAO;

/**
*
* @author Diego Munhoz
*/
public class CompraInsert {
	public static void main(String[] args) {
		Compra compra = new Compra();
		compra.setObservacao(" teste de compra ".toUpperCase());
		compra.setValorTotal(200.00);
		try {
			compra.addFormaPagamentoDinheiro(50.00, null);
			compra.addFormaPagamentoCartaoDebito(25.00, null);
			compra.addFormaPagamentoCartaoCredito(25.0, 2, new Date());
			compra.addFormaPagamentoCartaoCredito(100.0, 2, new Date());

			if (compra.somaParcelasCadastradas().equals(compra.getValorTotal())) {
				compra.setStatusPagamentoToPago();
				new CompraDAO().saveOrUpdate(compra);
				System.out.println(" compra gravada com sucesso ".toUpperCase());
			} else {
				System.out.println(" compra com pendedências no pagamento ".toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

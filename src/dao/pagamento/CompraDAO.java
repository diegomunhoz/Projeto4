package dao.pagamento;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import to.pagamento.Compra;
import to.pagamento.FormaPagamento;
import util.HibernateUtil;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class CompraDAO extends AbstractDAO {

	public Compra buscaPorId(Long id) throws Exception {
		return (Compra) super.buscaPorId(Compra.class, id);
	}

	@Override
	public void saveOrUpdate(Object pObjeto) throws Exception {
		Compra compra = (Compra) pObjeto;
		if (!compra.getValorTotal().equals(compra.somaParcelasCadastradas())) {
			throw new Exception(" o valor da compra não bate com as parcelas ".toUpperCase());
		}
		compra.setStatusPagamentoToPago();
		for (FormaPagamento fp : compra.getFormasDePagamentos()) {
			if (!fp.getStatusPagamento().equals("PAGO")) {
				compra.setStatusPagamentoToPendente();
				break;
			}
		}
		super.saveOrUpdate(pObjeto);
	}

	@SuppressWarnings("unchecked")
	public List<Compra> buscaCompraPendente() throws Exception {

		List<Compra> lista;

		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();

			StringBuffer sql = new StringBuffer();
			sql.append("\n" + " SELECT ");
			sql.append("\n" + "     c ");
			sql.append("\n" + " FROM ");
			sql.append("\n" + "     Compra c ");
			sql.append("\n" + "     JOIN FormaPagamento fp ");
			sql.append("\n" + "       ON ( ");
			sql.append("\n" + "	           (c.id = fp.id) ");
			sql.append("\n" + "	            AND ");
			sql.append("\n" + "	    	   (fp.statusPagamento LIKE 'PENDENTE') ");
			sql.append("\n" + "	         ) ");

			Query query = this.sessao.createQuery(sql.toString());

			lista = (ArrayList<Compra>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			this.sessao.close();
		}
		return lista;
	}

}

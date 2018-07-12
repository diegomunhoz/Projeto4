package test.venda;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.GeraPDF;
import vo.venda.RelatorioTotalizadorVenda;
import dao.venda.ClienteDAO;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaRelatorioTotaisVendas {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {
			String periodoDe = null;// 2012-12-31 23:59:59
			String periodoAte = null;// 2012-01-01 00:00:00
			Long idCliente = null;

			List<RelatorioTotalizadorVenda> lista = new VendaDAO().buscaTotaisDeVendasPorPeriodoECliente(periodoDe,
					periodoAte, idCliente);

			if (lista == null || lista.isEmpty()) {
				System.out.println(" lista vazia processamento encerrado".toUpperCase());
				return;
			}

			HashMap parametros = new HashMap();
			parametros.put("dataGeracao", new Date());
			parametros.put("nomeCliente", ((idCliente == null || idCliente == 0) ? "todos".toUpperCase() : +idCliente
					+ " - " + new ClienteDAO().buscaPorId(idCliente).getNome()));
			parametros.put("periodoInicial", (periodoDe == null ? "0001-01-01" : periodoDe));
			parametros.put("periodoFinal", (periodoAte == null ? "9999-12-31" : periodoAte));

			InputStream inputStream = GeraPDF.class
					.getResourceAsStream("/repositorio/venda/report/RelatorioTotalizadorVenda.jasper");

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
			JasperPrint impressao = null;

			impressao = JasperFillManager.fillReport(inputStream, parametros, ds);
			GeraPDF showPDF = new GeraPDF(impressao);
			showPDF.setLocationRelativeTo(null);
			showPDF.setModal(true);
			showPDF.setVisible(true);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package test.venda;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import dao.venda.ClienteDAO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import util.GeraPDF;
import util.JDBCUtil;

/**
*
* @author Diego Munhoz
*/
public class VendaRelatorioItensVendidos {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Connection conexao = null;
		try {

			String periodoDe = "2012-01-01 00:00:00";
			String periodoAte = "2012-12-31 23:59:59";

			Long idCliente = null;

			HashMap parametros = new HashMap();
			parametros.put("dataGeracao", new Date());
			parametros.put("nomeCliente", ((idCliente == null || idCliente == 0) ? "todos".toUpperCase() : +idCliente
					+ " - " + new ClienteDAO().buscaPorId(idCliente).getNome()));
			parametros.put("periodoInicial", (periodoDe == null ? "0001-01-01 00:00:00" : periodoDe));
			parametros.put("periodoFinal", (periodoAte == null ? "9999-12-31 23:59:59" : periodoAte));
			parametros.put("chaveInicialCliente", (idCliente == null || idCliente == 0) ? 0L : idCliente);
			parametros.put("chaveFinalCliente", (idCliente == null || idCliente == 0) ? 9999999999999999L : idCliente);

			InputStream inputStream = GeraPDF.class
					.getResourceAsStream("/repositorio/venda/report/RelatorioPorcentualDeProdutosVendidosSQL.jasper");

			JasperPrint impressao = null;

			conexao = JDBCUtil.getConnection();

			impressao = JasperFillManager.fillReport(inputStream, parametros, conexao);
			GeraPDF showPDF = new GeraPDF(impressao);
			showPDF.setTitle("RelatorioPorcentualDeProdutosVendidosSQL");
			showPDF.setLocationRelativeTo(null);
			showPDF.setModal(true);
			showPDF.setVisible(true);

			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

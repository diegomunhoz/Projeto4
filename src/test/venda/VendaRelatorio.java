package test.venda;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import to.venda.ItemDaVenda;
import to.venda.Venda;
import util.GeraPDF;
import dao.venda.VendaDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaRelatorio {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Venda venda = new VendaDAO().buscaPorId(1L);

			if (venda == null || venda.getItensDaVenda() == null || venda.getItensDaVenda().size() == 0) {
				JOptionPane.showMessageDialog(null, "PESQUISA VAZIA NÃO A O QUE REPORTAR EM PDF".toUpperCase());
				return;
			}
			List<ItemDaVenda> lista = new ArrayList<ItemDaVenda>();
			for (ItemDaVenda i : venda.getItensDaVenda()) {
				lista.add(i);
			}

			HashMap parametros = new HashMap();

			parametros.put("dataGeracao", new Date());
			parametros.put("nomeVendedor", venda.getVendedor().getNome());
			parametros.put("nomeCliente", venda.getCliente().getNome());
			parametros.put("foneCliente", venda.getCliente().getTelefone());
			parametros.put("vendaData", venda.getDataDaVenda());
			parametros.put("vendaValorTotal", venda.getValorTotal());

			InputStream inputStream = GeraPDF.class
					.getResourceAsStream("/repositorio/venda/report/RelatorioVenda.jasper");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

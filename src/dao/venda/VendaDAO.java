package dao.venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

import to.venda.Produto;
import to.venda.Venda;
import util.HibernateUtil;
import util.JDBCUtil;
import vo.venda.RelatorioTotalizadorVenda;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class VendaDAO extends AbstractDAO {

	public Venda buscaPorId(Long id) throws Exception {
		return (Venda) super.buscaPorId(Venda.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Venda> filtroGenerico(Venda venda) throws Exception {
		super.sessao = null;
		ArrayList<Venda> lista = new ArrayList<Venda>();
		try {
			super.sessao = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sessao.createCriteria(Venda.class);

			lista = (ArrayList<Venda>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		} catch (Exception e) {
			lista = null;
		} finally {
			sessao.close();
		}
		return lista;
	}

	/**
	 * Busca relação de produtos vendidos.
	 * 
	 * @param periodoDe
	 * @param periodoAte
	 * @return para cada posição da lista : [0-Produto..:Produto
	 *         analisado] [1-Integer..:Quantidade total de todos os produtos
	 *         vendidos] [2-Double...:Porcentagem de produtos vendidos entre todas
	 *         as vendas] [3-Integer..:Quantidade vendida do produto]
	 *         [4-Double...:Valor total arrecadado do produto]
	 *         [5-Double...:Valor médio de venda do produto]
	 * @throws Exception
	 */
	public List<List<Object>> buscaRelacaoDeProdutosVendidos(String periodoDe, String periodoAte, Long idCliente)
			throws Exception {
		try {
			SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (periodoAte != null && !periodoAte.isEmpty()) {
				FORMAT.setLenient(false);
				FORMAT.parse(periodoAte);
			}
			if (periodoDe != null && !periodoDe.isEmpty()) {
				FORMAT.setLenient(false);
				FORMAT.parse(periodoDe);
			}
		} catch (ParseException e) {
			throw new Exception("parâmetros | períodoDe: ".toUpperCase() + periodoDe + " períodoAté: ".toUpperCase()
					+ periodoAte + " | está fora de formato ".toUpperCase() + "yyyy-MM-dd HH:mm:ss");
		}

		List<List<Object>> lista = new ArrayList<List<Object>>();

		Connection pegaConexao = null;

		try {

			StringBuffer sql = new StringBuffer();

			sql.append(" SELECT ");

			// -- id_produto
			sql.append("     (sub.id_produto) ");

			// -- quantidade total de todos os produtos vendidos
			sql.append("    ,(sub.qt_total) ");

			// -- porcentagem de produto vendido entre todas as vendas
			sql.append("    ,(sub.qt_vendida / sub.qt_total * 100) as porcentagem ");

			// -- quantidade de produto vendida
			sql.append("    ,(sub.qt_vendida) ");

			// -- valor total arredadado
			sql.append("    ,(total_vendido) ");

			// -- valor médio de venda do produto
			sql.append("    ,(sub.total_vendido / sub.qt_vendida) as valor_medio_produto ");

			sql.append(" FROM  ");
			sql.append("    (  ");
			sql.append("     SELECT ");
			sql.append("         i1.id_produto ");
			sql.append("        ,sum(i1.vl_unit) as total_vendido ");
			sql.append("        ,sum(i1.quantidade) qt_vendida ");
			sql.append("        ,(  ");
			sql.append("             SELECT SUM( i2.quantidade ) ");
			sql.append("             FROM ");
			sql.append("                 itens_da_venda i2 ");
			sql.append("                 INNER JOIN vendas v2 ");
			sql.append("                     ON i2.id_venda = v2.id_venda ");
			sql.append("             WHERE ");
			sql.append("                 (v2.dt_venda BETWEEN periodo.dt_ini AND periodo.dt_fim) ");
			sql.append("             AND ");
			sql.append("                 (v2.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin) ");
			sql.append("         ) qt_total ");
			sql.append("     FROM ");
			sql.append("         itens_da_venda i1 ");
			sql.append("         INNER JOIN vendas v1 ");
			sql.append("             ON i1.id_venda = v1.id_venda ");
			sql.append("         INNER JOIN ( SELECT ? dt_ini, ? dt_fim  ) AS periodo ");
			sql.append("             ON v1.dt_venda BETWEEN periodo.dt_ini AND periodo.dt_fim ");
			sql.append("         INNER JOIN ( SELECT ? cli_ini, ? cli_fin  ) AS cliente ");
			sql.append("             ON v1.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin ");
			sql.append("     GROUP BY ");
			sql.append("         i1.id_produto ");
			sql.append("     ) sub ");
			sql.append(" ORDER BY ");
			sql.append("     porcentagem ");

			pegaConexao = JDBCUtil.getConnection();

			PreparedStatement preparo = pegaConexao.prepareStatement(sql.toString());

			{
				if (periodoDe != null && !periodoDe.isEmpty()) {
					preparo.setString(1, periodoDe);
				} else {
					preparo.setString(1, "0001-01-01 00:00:00");
				}
				if (periodoAte != null && !periodoAte.isEmpty()) {
					preparo.setString(2, periodoAte);
				} else {
					preparo.setString(2, "9999-12-31 23:59:59");
				}
				if (idCliente != null && idCliente != 0) {
					preparo.setLong(3, idCliente);
					preparo.setLong(4, idCliente);
				} else {
					preparo.setLong(3, 0L);
					preparo.setLong(4, 999999999999999999L);
				}
			}

			ResultSet registroDoBanco = preparo.executeQuery();

			while (registroDoBanco.next()) {

				List<Object> linha = new ArrayList<Object>();

				linha.add(new ProdutoDAO().buscaPorId((registroDoBanco.getLong("id_produto"))));
				linha.add(registroDoBanco.getLong("qt_total"));
				linha.add(registroDoBanco.getDouble("porcentagem"));
				linha.add(registroDoBanco.getInt("qt_vendida"));
				linha.add(registroDoBanco.getDouble("total_vendido"));
				linha.add(registroDoBanco.getDouble("valor_medio_produto"));

				lista.add(linha);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pegaConexao.close();
			} catch (Exception e) {
				throw new Exception("erro ao conectar no banco".toUpperCase());
			}
		}
		return lista;
	}

	public List<Produto> buscaProdutosNaoVendidos(Long idProduto) throws Exception {

		Connection pegaConexao = null;

		List<Produto> lista = new ArrayList<Produto>();

		try {

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ");
			sql.append("     sub.id_produto ");
			sql.append(" FROM ");
			sql.append("    ( ");
			sql.append("     SELECT ");
			sql.append("         p.id_produto ");
			sql.append("     FROM ");
			sql.append("         Produtos p ");
			sql.append("     WHERE ");
			sql.append("         p.id_produto NOT IN");
			sql.append("        ( ");
			sql.append("         SELECT ");
			sql.append("             i.id_produto ");
			sql.append("         FROM");
			sql.append("             Itens_da_venda i ");
			sql.append("        ) ");
			sql.append("    ) AS sub ");
			sql.append(" WHERE ");
			sql.append("   (sub.id_produto BETWEEN ? AND ?) ");

			pegaConexao = JDBCUtil.getConnection();

			PreparedStatement preparo = pegaConexao.prepareStatement(sql.toString());
			{
				if (idProduto != null && idProduto != 0) {
					preparo.setLong(1, idProduto);
					preparo.setLong(2, idProduto);
				} else {
					preparo.setLong(1, 0L);
					preparo.setLong(2, 999999999999999999L);
				}
			}

			ResultSet registroDoBanco = preparo.executeQuery();
			{
				ProdutoDAO produtoDAO = new ProdutoDAO();
				while (registroDoBanco.next()) {
					lista.add(produtoDAO.buscaPorId(registroDoBanco.getLong("sub.id_produto")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			try {
				pegaConexao.close();
			} catch (Exception e) {
				throw new Exception("erro ao conectar no banco".toUpperCase());
			}
		}
		return lista;
	}

	public List<RelatorioTotalizadorVenda> buscaTotaisDeVendasPorPeriodoECliente(String periodoDe, String periodoAte,
			Long idCliente) throws Exception {

		try {
			SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
			if (periodoAte != null && !periodoAte.isEmpty()) {
				FORMAT.setLenient(false);
				FORMAT.parse(periodoAte);
			}
			if (periodoDe != null && !periodoDe.isEmpty()) {
				FORMAT.setLenient(false);
				FORMAT.parse(periodoDe);
			}
		} catch (ParseException e) {
			throw new Exception("parâmetros | periodoDe: ".toUpperCase() + periodoDe + " periodoAté: ".toUpperCase()
					+ periodoAte + " | está fora de formato ".toUpperCase() + "yyyy-MM-dd");
		}

		Connection pegaConexao = null;

		List<RelatorioTotalizadorVenda> lista = new ArrayList<RelatorioTotalizadorVenda>();

		try {

			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ");
			sql.append("     (sub.dt_venda) ");
			sql.append("    ,(sub.soma_vendas) ");
			sql.append(" FROM ");
			sql.append("     ( ");
			sql.append("     SELECT ");
			sql.append("	      substring(v1.dt_venda,1,10) AS dt_venda ");
			sql.append("	 	 ,sum(v1.vl_total) AS soma_vendas ");
			sql.append("     FROM ");
			sql.append("         vendas v1 ");
			sql.append("         INNER JOIN ( SELECT ? dt_ini, ? dt_fim  ) AS periodo ");
			sql.append("             ON substring(v1.dt_venda,1,10) BETWEEN periodo.dt_ini AND periodo.dt_fim ");
			sql.append("         INNER JOIN ( SELECT ? cli_ini, ? cli_fin  ) AS cliente ");
			sql.append("             ON v1.id_cliente BETWEEN cliente.cli_ini AND cliente.cli_fin ");
			sql.append("     GROUP BY ");
			sql.append("         substring(v1.dt_venda,1,10) ");
			sql.append("     ) sub ");
			sql.append(" ORDER BY ");
			sql.append("     sub.dt_venda ");

			pegaConexao = JDBCUtil.getConnection();

			PreparedStatement preparo = pegaConexao.prepareStatement(sql.toString());

			{
				if (periodoDe != null && !periodoDe.isEmpty()) {
					preparo.setString(1, periodoDe);
				} else {
					preparo.setString(1, "0001-01-01 00:00:00");
				}
				if (periodoAte != null && !periodoAte.isEmpty()) {
					preparo.setString(2, periodoAte);
				} else {
					preparo.setString(2, "9999-12-31 23:59:59");
				}
				if (idCliente != null && idCliente != 0) {
					preparo.setLong(3, idCliente);
					preparo.setLong(4, idCliente);
				} else {
					preparo.setLong(3, 0L);
					preparo.setLong(4, 999999999999999999L);
				}
			}

			ResultSet registroDoBanco = preparo.executeQuery();
			{
				while (registroDoBanco.next()) {
					RelatorioTotalizadorVenda relatorioVenda = new RelatorioTotalizadorVenda();
					relatorioVenda.setDataVenda(registroDoBanco.getString("sub.dt_venda"));
					relatorioVenda.setValorVendasDoDia(registroDoBanco.getDouble("sub.soma_vendas"));
					lista.add(relatorioVenda);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			try {
				pegaConexao.close();
			} catch (Exception e) {
				throw new Exception("erro ao conectar no banco".toUpperCase());
			}
		}
		return lista;
	}

}

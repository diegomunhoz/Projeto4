package dao.agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import to.agendamento.Agendamento;
import util.DataUtils;
import util.HibernateUtil;
import dao.AbstractDAO;

/**
*
* @author Diego Munhoz
*/
public class AgendamentoDAO extends AbstractDAO {

	public Agendamento buscaPorId(Long id) throws Exception {
		return (Agendamento) super.buscaPorId(Agendamento.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> rangeDeAgendamentos(Agendamento pAgendamento) throws Exception {

		if (pAgendamento == null || pAgendamento.getFuncionario() == null
				|| pAgendamento.getDataEHoraAgendamento() == null || pAgendamento.getHorarioEstimadoFim() == null) {
			throw new Exception(" Erro de parâmetro ".toUpperCase());
		}

		List<Agendamento> lista;

		try {
			this.sessao = HibernateUtil.getSessionFactory().openSession();

			StringBuffer sql = new StringBuffer();

			sql.append("FROM Agendamento M WHERE");
			sql.append("(  M.funcionario.id = ?");
			sql.append("AND");
			sql.append("	(");
			// ----> se o início do agendamento estiver um agendamento já
			// existente
			sql.append("		( ( ? >= M.dataEHoraAgendamento ) AND ( ? < M.horarioEstimadoFim ) )");
			sql.append("	OR");
			// ----> se o fim do agendamento estiver um agendamento já existente
			sql.append("		( ( ? >= M.dataEHoraAgendamento ) AND ( ? < M.horarioEstimadoFim ) )");
			sql.append("	)");
			sql.append("OR (");
			// ----> se tiver um agendamento grande, porém tem agendamento
			// menores na tabela, no meio deste agendamento
			sql.append("		( ( ? <= M.dataEHoraAgendamento ) AND ( ? > M.horarioEstimadoFim ) )");
			sql.append("	)");
			sql.append(")ORDER BY M.dataEHoraAgendamento");

			SimpleDateFormat dataFormatacao = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Query query = this.sessao.createQuery(sql.toString());

			query.setLong(0, pAgendamento.getFuncionario().getId());

			query.setString(1, dataFormatacao.format(DataUtils
					.segundosLowValues(pAgendamento.getDataEHoraAgendamento())));
			query.setString(2, dataFormatacao.format(DataUtils.segundosHighValues(pAgendamento
					.getDataEHoraAgendamento())));

			query
					.setString(3, dataFormatacao.format(DataUtils.segundosLowValues(pAgendamento
							.getHorarioEstimadoFim())));
			query.setString(4, dataFormatacao
					.format(DataUtils.segundosHighValues(pAgendamento.getHorarioEstimadoFim())));

			query.setString(5, dataFormatacao.format(DataUtils.segundosHighValues(pAgendamento
					.getDataEHoraAgendamento())));
			query
					.setString(6, dataFormatacao.format(DataUtils.segundosLowValues(pAgendamento
							.getHorarioEstimadoFim())));

			lista = (ArrayList<Agendamento>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			this.sessao.close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> buscaAgendamentoPorPeriodo(String inicio, String fim, String opcao_yyyy_MM_dd_HH_mm_ss)
			throws Exception {

		// validacoes de inicio
		try {
			SimpleDateFormat FORMAT = null;
			if (opcao_yyyy_MM_dd_HH_mm_ss.equals("yyyy")) {
				FORMAT = new SimpleDateFormat("yyyy-");
				FORMAT.setLenient(false);
				FORMAT.parse(inicio + "-");
				FORMAT.parse(fim + "-");
			} else if (opcao_yyyy_MM_dd_HH_mm_ss.equals("yyyy-MM")) {
				FORMAT = new SimpleDateFormat("yyyy-MM");
				FORMAT.setLenient(false);
				FORMAT.parse(inicio);
				FORMAT.parse(fim);
			} else if (opcao_yyyy_MM_dd_HH_mm_ss.equals("MM")) {
				FORMAT = new SimpleDateFormat("MM-");
				FORMAT.setLenient(false);
				FORMAT.parse(inicio + "-");
				FORMAT.parse(fim + "-");
			} else {
				throw new Exception("opção não cadastrada".toUpperCase());
			}
		} catch (ParseException e) {
			throw new Exception("parâmetros | início: ".toUpperCase() + inicio + " fim: ".toUpperCase() + fim
					+ " | não condiz com opção: ".toUpperCase() + opcao_yyyy_MM_dd_HH_mm_ss);
		}

		super.sessao = null;
		ArrayList<Agendamento> lista = null;

		try {
			super.sessao = HibernateUtil.getSessionFactory().openSession();
			StringBuffer sql = new StringBuffer();

			if (opcao_yyyy_MM_dd_HH_mm_ss.equals("yyyy")) {
				sql.append(" FROM Agendamento a WHERE ");
				sql.append(" ( SUBSTRING(a.dataEHoraAgendamento,1,4) BETWEEN ? AND ? ) ");
				sql.append(" ORDER BY a.dataEHoraAgendamento ");
			} else if (opcao_yyyy_MM_dd_HH_mm_ss.equals("yyyy-MM")) {
				sql.append(" FROM Agendamento a WHERE ");
				sql.append(" ( SUBSTRING(a.dataEHoraAgendamento,1,7) BETWEEN ? AND ? ) ");
				sql.append(" ORDER BY a.dataEHoraAgendamento ");
			} else if (opcao_yyyy_MM_dd_HH_mm_ss.equals("MM")) {
				sql.append(" FROM Agendamento a WHERE ");
				sql.append(" ( SUBSTRING(a.dataEHoraAgendamento,6,2) BETWEEN ? AND ? ) ");
				sql.append(" ORDER BY a.dataEHoraAgendamento ");
			}

			Query query = this.sessao.createQuery(sql.toString());
			query.setString(0, inicio);
			query.setString(1, fim);

			lista = (ArrayList<Agendamento>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			lista = null;
		} finally {
			sessao.close();
		}
		return lista;
	}
}

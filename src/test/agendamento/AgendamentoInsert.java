package test.agendamento;

import java.util.List;

import to.agendamento.Agendamento;
import dao.agendamento.AgendamentoDAO;
import dao.agendamento.FuncionarioDAO;
import dao.agendamento.ServicoDAO;

/**
*
* @author Diego Munhoz
*/
public class AgendamentoInsert {
	public static void main(String[] args) {
		try {
			Agendamento agendamento = new Agendamento();
			agendamento.agendar("2012-02-02 14:01", new ServicoDAO().buscaPorId(1L), new FuncionarioDAO()
					.buscaPorId(1L));
			AgendamentoDAO dao = new AgendamentoDAO();
			List<Agendamento> agendamentos = dao.rangeDeAgendamentos(agendamento);
			if (agendamentos == null || agendamentos.size() != 0) {
				System.out.println("data indisponivel".toUpperCase());
				return;
			}
			dao.saveOrUpdate(agendamento);
			System.out.println("agendamento salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

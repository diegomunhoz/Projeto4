package test.agendamento;

import java.text.SimpleDateFormat;

import dao.agendamento.AgendamentoDAO;
import to.agendamento.Agendamento;

/**
*
* @author Diego Munhoz
*/
public class AgendamentoBuscaMes {
	public static void main(String[] args) {
		try {
			for (Agendamento a : new AgendamentoDAO().buscaAgendamentoPorPeriodo("201a23", "2013", "yyyy")) {
				System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(a.getDataEHoraAgendamento()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

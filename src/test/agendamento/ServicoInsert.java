package test.agendamento;

import dao.agendamento.ServicoDAO;
import to.agendamento.Servico;

/**
*
* @author Diego Munhoz
*/
public class ServicoInsert {
	public static void main(String[] args) {
		try {
			Servico servico = new Servico();
			servico.setNome("servico 1".toUpperCase());
			servico.setDecricao("descricao do servico 1".toUpperCase());
			servico.setTempoNecessario(10);
			new ServicoDAO().saveOrUpdate(servico);
			System.out.println("servico salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package test.agendamento;

import dao.agendamento.FuncionarioDAO;
import to.agendamento.Funcionario;

/**
*
* @author Diego Munhoz
*/
public class FuncionarioInsert {
	public static void main(String[] args) {
		try {
			Funcionario funcionario = new Funcionario();
			funcionario.setNome("DIEGO MUNHOZ".toUpperCase());
			funcionario.setCpf("222.222.222-22");
			funcionario.setTelefone("1111-2334");
			new FuncionarioDAO().saveOrUpdate(funcionario);
			System.out.println("funcionario salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package test.turma;

import java.util.Date;

import to.turma.Aluno;
import dao.turma.AlunoDAO;

/**
*
* @author Diego Munhoz
*/
public class AlunoInsert {

	public static void main(String[] args) {
		try {
			Aluno aluno = new Aluno();
			aluno.setNome("joana tinerari".toUpperCase());
			aluno.setTelefone("1111-111");
			aluno.setDataNascimento(new Date());
			new AlunoDAO().saveOrUpdate(aluno);
			System.out.println("aluno salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Aluno aluno = new Aluno();
			aluno.setNome("amanda mello".toUpperCase());
			aluno.setTelefone("1111-111");
			aluno.setDataNascimento(new Date());
			new AlunoDAO().saveOrUpdate(aluno);
			System.out.println("aluno salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Aluno aluno = new Aluno();
			aluno.setNome("adriana cunha".toUpperCase());
			aluno.setTelefone("1111-111");
			aluno.setDataNascimento(new Date());
			new AlunoDAO().saveOrUpdate(aluno);
			System.out.println("aluno salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Aluno aluno = new Aluno();
			aluno.setNome("juliano".toUpperCase());
			aluno.setTelefone("1111-111");
			aluno.setDataNascimento(new Date());
			new AlunoDAO().saveOrUpdate(aluno);
			System.out.println("aluno salvo com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

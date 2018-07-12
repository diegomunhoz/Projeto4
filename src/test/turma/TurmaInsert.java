package test.turma;

import java.text.SimpleDateFormat;
import java.util.Date;

import to.turma.Aluno;
import to.turma.Professor;
import to.turma.Turma;
import dao.turma.AlunoDAO;
import dao.turma.ProfessorDAO;
import dao.turma.TurmaDAO;

/**
*
* @author Diego Munhoz
*/
public class TurmaInsert {
	public static void main(String[] args) {
		try {
			Turma turma = new Turma();
			turma.setProfessor((Professor) new ProfessorDAO().buscaPorId(2L));
			if (turma.getProfessor() == null) {
				System.out.println(" professor não encontrado, processamento encerrado".toUpperCase());
				return;
			}

			turma.setDataFim(new Date());
			turma.setDataInicio(new Date());
			turma.setNome("turma de java ".toUpperCase()
					+ new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));

			AlunoDAO alunoDAO = new AlunoDAO();

			Aluno aluno = (Aluno) alunoDAO.buscaPorId(2L);
			if (aluno != null) {
				turma.addAlunosNaTurma(aluno);
			}

			aluno = (Aluno) alunoDAO.buscaPorId(3L);
			if (aluno != null) {
				turma.addAlunosNaTurma(aluno);
			}

			aluno = (Aluno) alunoDAO.buscaPorId(4L);
			if (aluno != null) {
				turma.addAlunosNaTurma(aluno);
			}

			aluno = (Aluno) alunoDAO.buscaPorId(5L);
			if (aluno != null) {
				turma.addAlunosNaTurma(aluno);
			}

			aluno = (Aluno) alunoDAO.buscaPorId(6L);
			if (aluno != null) {
				turma.addAlunosNaTurma(aluno);
			}

			new TurmaDAO().saveOrUpdate(turma);
			System.out.println("turma salva com sucesso".toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

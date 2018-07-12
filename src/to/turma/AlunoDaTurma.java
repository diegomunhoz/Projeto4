package to.turma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "alunos_turma")
public class AlunoDaTurma {

	@Id
	@GeneratedValue
	@Column(name = "id_aluno_turma", nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_aluno", nullable = false)
	private Aluno aluno = new Aluno();

	@ManyToOne
	@JoinColumn(name = "id_turma", nullable = false)
	private Turma turma = new Turma();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}

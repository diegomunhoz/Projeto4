package to.turma;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "turmas")
public class Turma {

	@Id
	@GeneratedValue
	@Column(name = "id_turma", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "dt_inicio", nullable = false)
	private Date dataInicio;

	@Column(name = "dt_fim", nullable = false)
	private Date dataFim;

	@ManyToOne
	@JoinColumn(name = "id_professor", nullable = false)
	private Professor professor;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "turma")
	@Cascade( { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private Set<AlunoDaTurma> alunosDaTurma;

	public Turma() {
		alunosDaTurma = new HashSet<AlunoDaTurma>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<AlunoDaTurma> getAlunosDaTurma() {
		return alunosDaTurma;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void removeAlunos(Aluno aluno) {
		for (AlunoDaTurma a : alunosDaTurma) {
			if (a.getAluno().getId().equals(aluno.getId())) {
				alunosDaTurma.remove(a);
				return;
			}
		}
	}

	public void addAlunosNaTurma(Aluno aluno) {
		AlunoDaTurma alunoDaTurma = new AlunoDaTurma();
		alunoDaTurma.setAluno(aluno);
		alunoDaTurma.setTurma(this);
		this.alunosDaTurma.add(alunoDaTurma);
	}
}

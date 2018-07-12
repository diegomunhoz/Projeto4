package to.agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
*
* @author Diego Munhoz
*/
@Entity
@Table(name = "agendamentos")
public class Agendamento {

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true, name = "id_agendamento")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_funcionario", nullable = false)
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "id_servico", nullable = false)
	private Servico servico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "dt_agendamento")
	private Date dataEHoraAgendamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_atendimento")
	private Date dataEHoraAtendimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "dt_estim_fim")
	private Date horarioEstimadoFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Servico getServico() {
		return servico;
	}

	public Date getDataEHoraAgendamento() {
		return dataEHoraAgendamento;
	}

	public void setDataEHoraAtendimento(String yyyy_MM_dd_HH_mm) {
		this.dataEHoraAtendimento = geraData(yyyy_MM_dd_HH_mm);
	}

	public Date getDataEHoraAtendimento() {
		return dataEHoraAtendimento;
	}

	public Date getHorarioEstimadoFim() {
		return horarioEstimadoFim;
	}

	public void agendar(String dataAgendamento_yyyy_MM_dd_HH_mm, Servico servico, Funcionario funcionario) {
		this.funcionario = funcionario;
		this.servico = servico;
		this.dataEHoraAgendamento = geraData(dataAgendamento_yyyy_MM_dd_HH_mm);
		Calendar gc = Calendar.getInstance();
		gc.setTime(dataEHoraAgendamento);
		gc.add(Calendar.MINUTE, servico.getTempoNecessario());
		this.horarioEstimadoFim = gc.getTime();
	}

	private Date geraData(String data) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
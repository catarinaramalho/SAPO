package sapo.atividade;

import java.util.NoSuchElementException;
import java.util.Optional;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class AtividadeService {

	private AtividadeRepository atividadeRepository;
	private PessoaService pessoaService;
	private Validador validador;

	public AtividadeService(PessoaService pessoaService) {
		this.atividadeRepository = new AtividadeRepository();
		this.pessoaService = pessoaService;
		this.validador = new Validador();
	}

	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		Pessoa responsavel = null; // this.pessoaService.recuperarPessoa(cpf);
		return this.atividadeRepository.cadastrarAtividade(nome, descricao, responsavel);
	}

	public void encerrarAtividade(String atividadeId) {
		Atividade atividade = this.recuperaAtividadeOuFalhe(atividadeId);
		if (atividade.tarefasPendentes().size() != 0) {
			throw new IllegalStateException("Não é possível encerrar atividades com tarefas pendentes");
		}
		atividade.setEstado(1);
	}

	public void desativarAtividade(String atividadeId) {
		Atividade atividade = this.recuperaAtividadeOuFalhe(atividadeId);
		if (atividade.tarefasPendentes().size() != 0) {
			throw new IllegalStateException("Não é possível desativar atividades com tarefas pendentes");
		}
		atividade.setEstado(2);
	}

	public void reabrirAtividade(String atividadeId) {
		Atividade atividade = this.recuperaAtividadeOuFalhe(atividadeId);
		if (atividade.getEstado() == 0) {
			throw new IllegalStateException("Não é possível reabrir uma atividade já aberta");
		}
		atividade.setEstado(0);
	}

	public String exibirAtividade(String atividadeId) {
		return this.recuperaAtividadeOuFalhe(atividadeId).toString();
	}

	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		this.recuperaAtividadeOuFalhe(atividadeId).setDescricao(descricao);
	}

	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		Pessoa responsavel = null; // this.pessoaService.recuperarPessoa(cpf);
		this.recuperaAtividadeOuFalhe(atividadeId).setResponsavel(responsavel);
	}

	public Atividade recuperaAtividadeOuFalhe(String atividadeId) {
		this.validador.validacaoId(atividadeId);
		Optional<Atividade> optional = this.atividadeRepository.recuperarAtividade(atividadeId);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("A atividade com id " + atividadeId + " não existe");
		}
		return optional.get();
	}
}

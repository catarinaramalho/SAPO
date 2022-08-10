package sapo.atividade;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 
 * @author Jônatas Tavares dos Santos - 121110769
 */
public class AtividadeService {

	private AtividadeRepository atividadeRepository;
	// private PessoaService pessoaService;
	private ValidadorAtividades validador; // Perguntar

	public AtividadeService() {
		this.atividadeRepository = new AtividadeRepository();
		// this.pessoaService = pessoaService;
		this.validador = new ValidadorAtividades();
	}

	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		// Pessoa resposavel = this.pessoaService.recuperaPessoa(cpf);
		return this.atividadeRepository.cadastrarAtividade(nome, descricao, null);
	}

	public Atividade recuperaAtividadeOuFalhe(String atividadeId) {
		Optional<Atividade> optional = this.atividadeRepository.recuperarAtividade(atividadeId);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("A atividade com id " + atividadeId + " não existe");
		}
		return optional.get();
	}
}

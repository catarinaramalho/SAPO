package sapo.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.pessoa.Pessoa;

public class BuscaAtividade extends BuscaAbstract {
	private Set<String> resultado;
	
	public BuscaAtividade(String criterioBusca) {
		super(criterioBusca);
	}

	public Set<String> busca(AtividadeService atividadeService) {
		this.resultado = atividadeService.busca(super.getCriterio());
		return this.resultado;
	}

	@Override
	public String[] representaBusca() {
		List<String> representacao = new ArrayList<>();
		representacao.add("ATIVIDADE");
		for (String atividade : this.resultado) {
			representacao.add(atividade);
		}

		String[] retorno = new String[representacao.size()];
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = representacao.get(i);
		}

		return retorno;
	}



}

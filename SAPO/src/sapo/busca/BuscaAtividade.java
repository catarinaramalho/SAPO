package sapo.busca;

import java.util.Set;

import sapo.atividade.AtividadeService;
import sapo.pessoa.Pessoa;

public class BuscaAtividade extends BuscaAbstract {
	
	public BuscaAtividade(String criterioBusca) {
		super(criterioBusca);
	}

	public Set<String> busca(AtividadeService atividadeService) {
		return atividadeService.busca(super.getCriterio());
	}



}

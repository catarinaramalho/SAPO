package sapo.busca;

import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class BuscaPessoa extends BuscaAbstract {
	
	public BuscaPessoa(String criterioBusca) {
		super(criterioBusca);
	}

	public Set<Pessoa> busca(PessoaService pessoaService) {
		return pessoaService.busca(super.getCriterio());
	}


	

}

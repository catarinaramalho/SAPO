package sapo.busca;

import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public abstract class BuscaAbstract implements BuscaInterface {
	private String[] criterio;
	
	public BuscaAbstract(String criterioBusca) {
		this.criterio = criterioBusca.split(" ");
	}
	
	public abstract Set<Pessoa> busca(PessoaService pessoaService);
	
	public String[] getCriterio() {
		return this.criterio;
	}
}

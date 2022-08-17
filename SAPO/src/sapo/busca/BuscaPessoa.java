package sapo.busca;

import java.util.ArrayList;
import java.util.List;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;

public class BuscaPessoa {
	private PessoaService pessoaService;
	private List<Pessoa> resultadosBusca;
	
	public BuscaPessoa(String criterioBusca) {
		this.pessoaService = new PessoaService();
		this.resultadosBusca = new ArrayList<>();
		
		
	}
	
}

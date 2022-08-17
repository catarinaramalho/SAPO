package sapo.busca;

import java.util.List;
import java.util.Set;

import sapo.pessoa.Pessoa;
import sapo.pessoa.PessoaService;


public class BuscaService {
	PessoaService pessoaService;
	
	public BuscaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	public String[] busca(String criterioBusca) {
		Set<Pessoa> resultadosBusca  = this.pessoaService.busca(criterioBusca);
		
		List<Pessoa> listaResultados = new ArrayList<>(); 
	}
}

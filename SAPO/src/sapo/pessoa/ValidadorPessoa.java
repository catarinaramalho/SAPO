package sapo.pessoa;

import sapo.ValidadorPadrao;

public class ValidadorPessoa extends ValidadorPadrao{
	
	public void validacao(String cpf, String nome, String[] habilidades) {
		this.validacaoCpf(cpf);
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
		
	}
}

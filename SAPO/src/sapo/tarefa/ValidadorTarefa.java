package sapo.tarefa;

import sapo.ValidadorPadrao;

public class ValidadorTarefa extends ValidadorPadrao {
	public void validacao(String nome, String[] habilidades) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}
}

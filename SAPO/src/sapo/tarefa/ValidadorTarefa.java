package sapo.tarefa;

import java.util.Objects;

import sapo.ValidadorPadrao;

public class ValidadorTarefa extends ValidadorPadrao {
	public void validacao(String nome, String[] habilidades) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}
	public void validacao(String nome, String[] habilidades, String[] idTarefas) {
		this.validacaoNome(nome);
		this.validacaoHabilidades(habilidades);
	}
	
	public void validacaoHabilidades(String[] idTarefas) {
		for (String id : idTarefas) {
			Objects.requireNonNull(id, "Não pode id nulo!");
			if (id.isBlank()) {
				throw new IllegalArgumentException("Não pode id vazio!");
			}
		}
	}
}

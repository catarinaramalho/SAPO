package sapo.busca;

import java.util.Objects;

import sapo.ValidadorPadrao;

public class ValidadorBusca extends ValidadorPadrao {
	public ValidadorBusca() {
	}

	public void validaCriterio(String criterio) {
		Objects.requireNonNull(criterio, "O criterio não pode ser nulo!");
		if (criterio.isBlank()) {
			throw new IllegalArgumentException("O criterio não pode ser vazio!");
		}
	}
	
	public void validaNBuscas(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("O numero de buscas solicitadas tem que ser um valor positivo!");
		}
	}
}

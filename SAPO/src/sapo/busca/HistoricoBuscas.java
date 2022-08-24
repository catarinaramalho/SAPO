package sapo.busca;

import java.util.ArrayList;
import java.util.List;

public class HistoricoBuscas {
	private List<String[]> buscas;

	public HistoricoBuscas() {
		this.buscas = new ArrayList<>();
	}

	public void registaBusca(String[] representaBusca) {
		buscas.add(representaBusca);
	}

	public String[] buscasMaisRecentes(int nBuscas) {
		return this.buscas.get(this.buscas.size() - 1);
	}

	public String[] exibirHistóricoBusca(int indexBusca) {
		return this.buscas.get(indexBusca);
	}
}

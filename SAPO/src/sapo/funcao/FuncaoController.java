package sapo.funcao;

public class FuncaoController {

	private FuncaoService funcaoService;
	private ValidadorFuncao validador;

	public FuncaoController(FuncaoService funcaoService) {
		this.funcaoService = funcaoService;
		this.validador = new ValidadorFuncao();
	}

	public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.validador.validacao(cpf, nome, matr, periodo, habilidades);
		this.funcaoService.cadastrarAluno(cpf, nome, matr, periodo, habilidades);
	}

	public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.validador.validacao(cpf, nome, siape, disciplinas, habilidades);
		this.funcaoService.cadastrarProfessor(cpf, nome, siape, disciplinas, habilidades);
	}

	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.validador.validacaoProfessor(cpf, siape, disciplinas);
		this.funcaoService.definirFuncaoProfessor(cpf, siape, disciplinas);
	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.validador.validacaoAluno(cpf, matr, periodo);
		this.funcaoService.definirFuncaoAluno(cpf, matr, periodo);
	}

	public void removerFuncao(String cpf) {
		this.validador.validacaoCpf(cpf);
		this.funcaoService.removerFuncao(cpf);
	}

	public int pegarNivel(String cpf) {
		this.validador.validacaoCpf(cpf);
		return this.funcaoService.pegarNivel(cpf);
	}

	public String[] listarPessoas() {
		return this.funcaoService.listarPessoas();
	}
}

package sapo.funcao;

import sapo.pessoa.PessoaService;

public class FuncaoService {

	private PessoaService pessoaService;

	public FuncaoService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {

	}

	public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {

	}

	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {

	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {

	}

	public void removerFuncao(String cpf) {

	}

	public int pegarNivel(String cpf) {
		// TODO
		return 0;
	}

	public String[] listarPessoas() {
		// TODO
		return null;
	}
}

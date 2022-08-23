package sapo.pessoa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representação de um Comentário. Um comentário possui o autor do comentário
 * (Pessoa), o texto do comentário e uma data em que foi gerado.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class Comentario {
	private Pessoa autor;
	private String comentario;
	private Date data;

	/**
	 * Construtor de Comentario. Constrói um comentário a partir do autor do
	 * comentário e do comentário, bem como gera a data em que foi feito o comentário.
	 * 
	 * @param autorCpf   String com o identificador único da pessoa.
	 * @param comentario String com o nome da pessoa.
	 */
	public Comentario(Pessoa autor, String comentario) {
		this.autor = autor;
		this.comentario = comentario;
		this.data = new Date();
	}

	/**
	 * Representação Textual do comentário, que contém o comentário e o nome do
	 * autor.
	 * 
	 * @return Data formatada.
	 */
	@Override
	public String toString() {
		return this.comentario + " (" + this.autor.getNome() + ")";
	}

	/**
	 * Retorna a data formatada do comentário gerado. Exemplo: 10/01/2010 09:30:10
	 * 
	 * @return Data formatada.
	 */
	public String formataData() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(this.data);
	}

}

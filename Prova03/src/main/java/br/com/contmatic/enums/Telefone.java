package br.com.contmatic.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum Telefone.
 */
public enum Telefone {

	/** The celular. */
	CELULAR("Celular", 9), /** The fixo. */
 FIXO("Fixo", 8);

	/** The descricao. */
	private String descricao;
	
	/** The tamanho. */
	private int tamanho;

	/**
	 * Instantiates a new telefone.
	 *
	 * @param descricao the descricao
	 * @param tamanho the tamanho
	 */
	private Telefone(String descricao, int tamanho) {
		this.descricao = descricao;
		this.tamanho = tamanho;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Gets the tamanho.
	 *
	 * @return the tamanho
	 */
	public int getTamanho() {
		return this.tamanho;
	}
}
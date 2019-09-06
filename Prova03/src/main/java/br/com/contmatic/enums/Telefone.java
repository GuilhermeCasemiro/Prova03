package br.com.contmatic.enums;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * The Enum Telefone.
 */
public enum Telefone {

                      /** The celular. */
                      CELULAR("Celular", 9),
                      /** The fixo. */
                      FIXO("Fixo", 8);

    /** The descricao. */
    @Size(max = 30, message = "A descrição não pode ter mais de 30 caracteres.")
    private String descricao;

    /** The tamanho. */
    @Min(value = 8, message = "O tamanho do telefone não pode ser menor que 9.")
    @Max(value = 9, message = "O tamanho do telefone não pode ser maior que 9.")
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

    /**
     * Sets the descricao.
     *
     * @param descricao the new descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Sets the tamanho.
     *
     * @param tamanho the new tamanho
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

}

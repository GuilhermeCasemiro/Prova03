package br.com.contmatic.models;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Cidade {
    
    @NotBlank(message = "Nome não pode ser nulo ou estar vazio.")
    @Size(min = 5, max = 25, message = "Nome não pode conter menos de 5 caracteres e mais de 25.")
    @Pattern(regexp = "[^_\\W\\d][záéíóúàèìòùâêîôûãõ]*", message = "Nome não pode ter números ou caracteres especiais.")
    private String nome;

    @Min(value = 1, message = "O código da cidade não pode ser menor que 1.")
    @Max(value = 100, message = "O código da cidade não pode ser maior que 100.")
    private int codigoCidade;

    @Valid
    @NotNull(message = "Cidade deve conter um estado.")
    private Estado estado;

    public Cidade() {
        super();
    }

    public Cidade(String nome, int codigoCidade, Estado estado) {
        super();
        this.nome = nome;
        this.codigoCidade = codigoCidade;
        this.estado = estado;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoCidade() {
        return this.codigoCidade;
    }

    public void setCodigoCidade(int codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(codigoCidade).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Cidade cidade = (Cidade) obj;
        return new EqualsBuilder().append(codigoCidade, cidade.codigoCidade).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}

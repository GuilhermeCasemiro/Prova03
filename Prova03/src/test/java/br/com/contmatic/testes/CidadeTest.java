package br.com.contmatic.testes;

import static br.com.contmatic.easy.random.CidadeRandom.gerarCidade;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import br.com.contmatic.models.Cidade;
import br.com.contmatic.models.Estado;

/**
 * The Class CidadeTest.
 */
public class CidadeTest {

    /** The validator. */
    private Validator validator;

    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /** The cidade. */
    Cidade cidade = gerarCidade();

    /**
     * Nao deve aceitar nome nulo.
     */
    @Test
    public void nao_deve_aceitar_nome_nulo() {
        cidade.setNome(null);
        assertFalse(isValid(cidade, "Nome não pode ser nulo ou estar vazio."));
    }

    /**
     * Nao deve aceitar nome vazio.
     */
    @Test
    public void nao_deve_aceitar_nome_vazio() {
        cidade.setNome(EMPTY);
        assertFalse(isValid(cidade, "Nome não pode ser nulo ou estar vazio."));
    }

    /**
     * Deve aceitar uma cidade valida.
     */
    @Test
    public void deve_aceitar_uma_cidade_valida() {
        cidade.setNome("São Paulo");
        assertTrue(isValid(cidade, cidade.getNome()));
    }

    /**
     * Nao deve aceitar nome com numeros.
     */
    @Test
    public void nao_deve_aceitar_nome_com_numeros() {
        cidade.setNome("7 de Setembro");
        assertFalse(isValid(cidade, "Nome não pode ter números ou caracteres especiais."));
    }

    /**
     * Nao deve aceitar nome com caracteres especiais.
     */
    @Test
    public void nao_deve_aceitar_nome_com_caracteres_especiais() {
        cidade.setNome("$Rio de Janeir#");
        assertFalse(isValid(cidade, "Nome não pode ter números ou caracteres especiais."));
    }

    /**
     * Nao deve aceitar nome com mais de 25 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_com_mais_de_25_caracteres() {
        cidade.setNome(random(35, true, false));
        assertFalse(isValid(cidade, "Nome não pode conter menos de 5 caracteres e mais de 25."));
    }

    /**
     * Nao deve aceitar nome com menos de 5 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_com_menos_de_5_caracteres() {
        cidade.setNome(random(3, true, false));
        assertFalse(isValid(cidade, "Nome não pode conter menos de 5 caracteres e mais de 25."));
    }

    /**
     * Deve aceitar nome entre 5 e 25 caracteres.
     */
    @Test
    public void deve_aceitar_nome_entre_5_e_25_caracteres() {
        cidade.setNome(random(20, true, false));
        assertTrue(isValid(cidade, cidade.getNome()));
    }

    /**
     * Nao deve codigo acima de 100.
     */
    @Test
    public void nao_deve_codigo_acima_de_100() {
        cidade.setCodigoCidade(105);
        assertFalse(isValid(cidade, "O código da cidade não pode ser maior que 100."));
    }

    /**
     * Nao deve codigo abaixo de 1.
     */
    @Test
    public void nao_deve_codigo_abaixo_de_1() {
        cidade.setCodigoCidade(0);
        assertFalse(isValid(cidade, "O código da cidade não pode ser menor que 1."));
    }

    /**
     * Deve aceitar um codigo valido.
     */
    @Test
    public void deve_aceitar_um_codigo_valido() {
        cidade.setCodigoCidade(31);
        assertTrue(isValid(cidade, String.valueOf(cidade.getCodigoCidade())));
    }

    /**
     * Nao deve aceitar um estado nulo.
     */
    @Test
    public void nao_deve_aceitar_um_estado_nulo() {
        cidade.setEstado(null);
        assertFalse(isValid(cidade, "Cidade deve conter um estado."));
    }

    /**
     * Deve aceitar um estado valido.
     */
    @Test
    public void deve_aceitar_um_estado_valido() {
        Estado estado = new Estado();
        cidade.setEstado(estado);
        assertTrue(isValid(cidade, cidade.getEstado().toString()));
    }

    /**
     * Deve retornar falso se as cidades forem diferentes.
     */
    @Test
    public void deve_retornar_falso_se_as_cidades_forem_diferentes() {
        cidade.setCodigoCidade(31);
        Cidade cidade2 = new Cidade();
        cidade2.setCodigoCidade(11);
        assertFalse(cidade.equals(cidade2));
    }

    /**
     * Deve retornar verdadeiro se as cidades forem iguais.
     */
    @Test
    public void deve_retornar_verdadeiro_se_as_cidades_forem_iguais() {
        cidade.setCodigoCidade(31);
        Cidade cidade2 = new Cidade();
        cidade2.setCodigoCidade(31);
        assertTrue(cidade.equals(cidade2));
    }

    @Test
    public void deve_conter_um_construtor() {
        assertThat(Cidade.class, hasValidBeanConstructor());
    }

    @Test
    public void deve_conter_os_gets_e_sets() {
        assertThat(Cidade.class, hasValidGettersAndSetters());
    }

    /**
     * Deve respeitar o hash code.
     */
    @Test
    public void deve_respeitar_o_hash_code() {
        assertThat(Cidade.class, hasValidBeanHashCodeFor("codigoCidade"));
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        assertThat(Cidade.class, hasValidBeanEqualsFor("codigoCidade"));
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_to_string() {
        cidade.setCodigoCidade(25);
        assertTrue(cidade.toString().contains("25"));
    }

    /**
     * Checks if is valid.
     *
     * @param cidade the cidade
     * @param mensagem the mensagem
     * @return true, if is valid
     */
    public boolean isValid(Cidade cidade, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<Cidade>> restricoes = validator.validate(cidade);
        for(ConstraintViolation<Cidade> constraintViolation : restricoes)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }
}

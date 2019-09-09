package br.com.contmatic.testes;

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

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.DDD;
import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Endereco;
import br.com.six2six.fixturefactory.Fixture;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {
    /** The validator. */
    private Validator validator;

    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }
    /* Testes para o bairro */

    /**
     * Nao deve aceitar bairro vazio.
     */
    @Test
    public void nao_deve_aceitar_bairro_nulo() {
        endereco.setBairro(null);
        assertFalse(isValid(endereco, "Bairro não pode ser nulo e nem estar vazio."));
    }

    @Test
    public void nao_deve_aceitar_bairro_vazio() {
        endereco.setBairro(EMPTY);
        assertFalse(isValid(endereco, "Bairro não pode ser nulo e nem estar vazio."));
    }

    @Test
    public void deve_aceitar_um_bairro_valido() {
        endereco.setBairro("Vila Mariana");
        assertTrue(isValid(endereco, "Vila Mariana"));
    }

    @Test
    public void nao_deve_aceitar_um_bairro_invalido() {
        endereco.setBairro("Vil@ Marian@");
        assertFalse(isValid(endereco, "Bairro não pode conter números e caracteres especiais."));
    }

    @Test
    public void deve_aceitar_bairro_com_acentuacao() {
        endereco.setBairro("Tatuapé");
        assertTrue(isValid(endereco, endereco.getBairro()));
    }

    @Test
    public void nao_deve_aceitar_bairro_com_numero() {
        endereco.setBairro("T4tuap3");
        assertFalse(isValid(endereco, "Bairro não pode conter números e caracteres especiais."));
    }

    /**
     * Nao deve conter bairro com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_conter_bairro_com_mais_de_30_caracteres() {
        endereco.setBairro(random(31));
        assertFalse(isValid(endereco, "Bairro não pode conter menos de 5 caracteres e mais 30 de caracteres."));
    }

    /**
     * Nao deve conter bairro com menos de 5 caracteres.
     */
    @Test
    public void nao_deve_conter_bairro_com_menos_de_5_caracteres() {
        endereco.setBairro(random(4));
        assertFalse(isValid(endereco, "Bairro não pode conter menos de 5 caracteres e mais 30 de caracteres."));
    }

    @Test
    public void bairro_deve_conter_entre_5_e_30_caracteres() {
        endereco.setBairro(random(15, true, true));
        assertTrue(isValid(endereco, endereco.getBairro()));
    }

    /* Testes do UF */

    /**
     * Nao deve conter uf nulo.
     */
    @Test
    public void nao_deve_conter_uf_nulo() {
        endereco.setUf(null);
        assertFalse(isValid(endereco, "DDD não pode ser nulo."));
    }

    @Test
    public void deve_aceitar_um_ddd_valido() {
        endereco.setUf(DDD.SAO_PAULO);
        assertTrue(isValid(endereco, DDD.SAO_PAULO.getDDD()));
    }
    /* Testes do CEP */

    /**
     * Nao deve aceitar cep nulo.
     */
    @Test
    public void nao_deve_aceitar_cep_nulo() {
        endereco.setCep(null);
        assertFalse(isValid(endereco, "CEP não pode ser nulo e nem vazio."));
    }

    @Test
    public void nao_deve_aceitar_cep_vazio() {
        endereco.setCep(EMPTY);
        assertFalse(isValid(endereco, "CEP não pode ser nulo e nem vazio."));
    }

    @Test
    public void deve_aceitar_um_cep_valido() {
        endereco.setCep("70385530");
        assertTrue(isValid(endereco, endereco.getCep()));
    }

    @Test
    public void nao_deve_aceitar_um_cep_invalido() {
        endereco.setCep("70385-53A");
        assertFalse(isValid(endereco, "CEP inválido."));
    }

    @Test
    public void na_deve_aceitar_um_cep_com_caracteres_especiais() {
        endereco.setCep("7038&-53@");
        assertFalse(isValid(endereco, "CEP inválido."));
    }

    /**
     * Nao deve conter cep com mais de 9 caracteres.
     */
    @Test
    public void nao_deve_conter_cep_com_mais_de_9_caracteres() {
        endereco.setCep(random(10, false, true));
        assertFalse(isValid(endereco, "CEP não pode ter mais de 9 caracteres."));
    }

    @Test
    public void nao_deve_conter_cep_com_menos_de_9_caracteres() {
        endereco.setCep(random(8, false, true));
        assertFalse(isValid(endereco, "CEP não pode ter mais de 9 caracteres."));
    }

    @Test
    public void deve_aceitar_cep_com_9_caracteres() {
        endereco.setBairro(random(9));
        assertTrue(isValid(endereco, endereco.getCep()));
    }
    /*-------------------------------------------------------------------------*/

    /* Test do Complemento */
    @Test
    public void pode_aceitar_complemento_nulo() {
        endereco.setComplemento(null);
        assertTrue(isValid(endereco, null));
    }

    /**
     * Pode aceitar complemento vazio.
     */
    @Test
    public void pode_aceitar_complemento_vazio() {

        endereco.setComplemento(EMPTY);
        assertTrue(isValid(endereco, EMPTY));
    }

    @Test
    public void deve_aceitar_um_complemento_valido() {
        endereco.setComplemento("Apartamento 41B");
        assertTrue(isValid(endereco, endereco.getComplemento()));
    }

    @Test
    public void deve_aceitar_um_complemento_com_menos_de_30_caracteres() {
        endereco.setComplemento(random(29, true, true));
        assertTrue(isValid(endereco, endereco.getComplemento()));
    }

    @Test
    public void nao_deve_aceitar_complemento_com_caracteres_especiais() {
        endereco.setComplemento("$Apartamento 41-B");
        assertFalse(isValid(endereco, "Deve inserir um complemento válido."));
    }

    /**
     * Nao deve aceitar complemento com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_aceitar_complemento_com_mais_de_30_caracteres() {
        endereco.setComplemento(random(31));
        assertFalse(isValid(endereco, "Complemento não pode ter mais de 30 caracteres."));
    }
    /*---------------------------------------------------------------------------------------*/

    /* Teste do número */

    @Test
    public void nao_deve_aceitar_numero_residencia() {
        endereco.setNumeroResidencia(null);
        assertFalse(isValid(endereco, "O número da residência não pode ser nulo."));
    }

    @Test
    public void nao_deve_aceitar_numero_residencia_negativo() {
        endereco.setNumeroResidencia(-1);
        assertFalse(isValid(endereco, "O número da residência não ser negativo."));
    }

    @Test
    public void nao_deve_aceitar_numero_residencia_maior_que_1500() {
        endereco.setNumeroResidencia(1501);
        assertFalse(isValid(endereco, "O número da residência não pode ser maior que 1500."));
    }

    @Test
    public void deve_aceitar_um_numero_de_residencia_valido() {
        endereco.setNumeroResidencia(999);
        assertTrue(isValid(endereco, endereco.getNumeroResidencia().toString()));
    }

    @Test
    public void deve_retornar_falso_se_os_enderecos_forem_diferentes() {
        endereco.setCep("08253700");
        endereco.setComplemento("Apartamento 41B");
        endereco.setNumeroResidencia(360);
        Endereco endereco2 = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertFalse(endereco.equals(endereco2));
    }

    @Test
    public void deve_retornar_verdadeiro_se_os_enderecos_forem_iguais() {
        Endereco endereco2 = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        endereco2.setCep(endereco.getCep());
        endereco2.setComplemento(endereco.getComplemento());
        endereco2.setNumeroResidencia(endereco.getNumeroResidencia());
        assertTrue(endereco.equals(endereco2));
    }

    /**
     * Deve respeitar o construtor.
     */
    @Test
    public void deve_respeitar_o_construtor() {
        assertThat(Endereco.class, hasValidBeanConstructor());
    }

    /**
     * Deve respeitar os gets e sets.
     */
    @Test
    public void deve_respeitar_os_gets_e_sets() {
        assertThat(Endereco.class, hasValidGettersAndSetters());
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        assertThat(Endereco.class, hasValidBeanEqualsFor("cep", "numeroResidencia", "complemento"));
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        assertThat(Endereco.class, hasValidBeanHashCodeFor("cep", "numeroResidencia", "complemento"));
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {

        endereco.setUf(DDD.SAO_PAULO);
        assertTrue(endereco.toString().contains("SAO_PAULO"));
    }

    public boolean isValid(Endereco endereco, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<Endereco>> restricoes = validator.validate(endereco);
        for(ConstraintViolation<Endereco> constraintViolation : restricoes)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }

}

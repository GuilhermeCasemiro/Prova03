package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
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

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Empresa;
import br.com.six2six.fixturefactory.Fixture;

/**
 * The Class EmpresaTest.
 */
public class EmpresaTest {

    /** The validator. */
    private Validator validator;

    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Empresa empresa = Fixture.from(Empresa.class).gimme("EmpresaFixture");

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }
    /* Testes do CNPJ */

    /**
     * Nao deve aceita cnpj nulo.
     */
    @Test
    public void nao_deve_aceita_cnpj_nulo() {
        empresa.setCnpj(null);
        assertFalse(isValid(empresa, "CNPJ obrigatório."));
    }

    @Test
    public void nao_deve_aceitar_cnpj_vazio() {
        empresa.setCnpj(EMPTY);
        assertFalse(isValid(empresa, "CNPJ obrigatório."));
    }

    /**
     * Nao deve aceitar cnpj com mais de 14 caracteres.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_mais_de_14_caracteres() {

        empresa.setCnpj(randomNumeric(15));
        assertFalse(isValid(empresa, "CNPJ deve conter exatamente 14 caracteres."));
    }

    /**
     * Nao deve aceitar cnpj com menos de 14 caracteres.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_menos_de_14_caracteres() {

        empresa.setCnpj(randomNumeric(13));
        assertFalse(isValid(empresa, "CNPJ deve conter exatamente 14 caracteres."));
    }

    /**
     * So deve aceitar cnpj com 14 caracteres.
     */
    @Test
    public void deve_aceitar_cnpj_com_14_caracteres() {

        empresa.setCnpj("23495307000106");
        assertTrue(isValid(empresa, "23495307000106"));
    }

    @Test
    public void deve_aceitar_so_cnpj_valido() {

        empresa.setCnpj("55602187000195");
        assertTrue(isValid(empresa, "55602187000195"));
    }

    @Test
    public void nao_deve_aceitar_um_cnpj_invalido() {

        empresa.setCnpj("23A95307000106");
        assertFalse(isValid(empresa, "O CNPJ deve ser válido."));
    }
    /* Testes da Razão Social */

    @Test
    public void nao_deve_aceitar_razao_social_nula() {

        empresa.setRazaoSocial(null);
        assertFalse(isValid(empresa, "Razão Social é obrigatório."));
    }

    @Test
    public void nao_deve_aceitar_razao_social_vazia() {

        empresa.setRazaoSocial(EMPTY);
        assertFalse(isValid(empresa, "Razão Social é obrigatório."));
    }

    @Test
    public void deve_so_aceitar_razao_sociao_valida() {

        empresa.setRazaoSocial("Rodrigo e Milena Alimentos ME");
        assertTrue(isValid(empresa, "Rodrigo e Milena Alimentos ME"));
    }

    @Test
    public void nao_deve_aceitar_razao_social_invalida() {
        empresa.setRazaoSocial("Rodrig0 e Milena 4limentos M3");
        assertFalse(isValid(empresa, "Razão Social inválida."));
    }

    @Test
    public void nao_deve_aceitar_razao_social_com_caracteres_especiais() {
        empresa.setRazaoSocial("Rodrig0 e Milen@ 4lim$ntos M3");
        assertFalse(isValid(empresa, "Razão Social inválida."));
    }

    /**
     * Nao deve aceitar razao social com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_aceitar_razao_social_com_mais_de_30_caracteres() {

        empresa.setRazaoSocial(random(31, true, false));
        assertFalse(isValid(empresa, "Razão social deve ter no minino 10 caracteres e no maixmo 30 caracteres."));
    }

    /**
     * Nao deve aceitar razao social com menos de 10 caracteres.
     */
    @Test
    public void nao_deve_aceitar_razao_social_com_menos_de_10_caracteres() {

        empresa.setRazaoSocial(random(9, true, false));
        assertFalse(isValid(empresa, "Razão social deve ter no minino 10 caracteres e no maixmo 30 caracteres."));
    }

    @Test
    public void so_deve_aceitar_razao_social_entre_10_e_30_caracteres() {
        empresa.setRazaoSocial("Nicolas e Severino Comercio de Bebidas Ltda");
        assertTrue(isValid(empresa, "Nicolas e Severino Comercio de Bebidas Ltda"));
    }

    @Test
    public void deve_aceitar_razao_social_valida() {

        empresa.setRazaoSocial("Lara e Enrico Filmagens");
        assertTrue(isValid(empresa, "Lara e Enrico Filmagens"));
    }

    /* Testes da Nome Fantasia */

    @Test
    public void nao_deve_aceitar_nome_fantasia_nulo() {

        empresa.setNomeFantasia(null);
        assertFalse(isValid(empresa, "Nome fantasia é obrigatório."));
    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_vazia() {

        empresa.setNomeFantasia(EMPTY);
        assertFalse(isValid(empresa, "Nome fantasia é obrigatório."));
    }

    @Test
    public void deve_so_aceitar_nome_fantasia_valida() {

        empresa.setNomeFantasia("StarWood Games");
        assertTrue(isValid(empresa, "StarWood Games"));
    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_invalida() {
        empresa.setNomeFantasia("St4rW00d G4m3s");
        assertFalse(isValid(empresa, "Nome Fantasia inválido."));
    }

    @Test
    public void nao_deve_aceitar_nome_fantasia_com_caracteres_especiais() {
        empresa.setNomeFantasia("St@rW**d Games");
        assertFalse(isValid(empresa, "Nome Fantasia inválido."));
    }

    /**
     * Nao deve aceitar razao social com mais de 30 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_fantasia_com_mais_de_30_caracteres() {

        empresa.setNomeFantasia(random(31, true, false));
        assertFalse(isValid(empresa, "Nome fantasia deve ter no minimo 10 caracteres e no maixmo 30 caracteres."));
    }

    /**
     * Nao deve aceitar razao social com menos de 10 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_fantasia_com_menos_de_10_caracteres() {

        empresa.setNomeFantasia(random(9, true, false));
        assertFalse(isValid(empresa, "Nome fantasia deve ter no minimo 10 caracteres e no maixmo 30 caracteres."));
    }

    @Test
    public void so_deve_aceitar_nome_fantasia_entre_10_e_30_caracteres() {
        empresa.setNomeFantasia("Mercearia União");
        assertTrue(isValid(empresa, "Mercearia União"));
    }

    @Test
    public void deve_aceitar_nome_fantasia_valida() {

        empresa.setNomeFantasia("Western Games");
        assertTrue(isValid(empresa, "Western Games"));
    }

    @Test
    public void deve_retornar_falso_se_os_cnpjs_forem_diferentes() {
        Empresa empresa2 = Fixture.from(Empresa.class).gimme("EmpresaFixture");
        empresa.setCnpj("08314240000172");
        empresa2.setCnpj("53551311000105");
        assertFalse(empresa.equals(empresa2));
    }

    @Test
    public void deve_retornar_verdadeiro_se_os_cnpjs_forem_iguais() {
        Empresa empresa2 = Fixture.from(Empresa.class).gimme("EmpresaFixture");
        empresa.setCnpj("53551311000105");
        empresa2.setCnpj("53551311000105");
        assertTrue(empresa.equals(empresa2));
    }

    /**
     * Deve aceitar as regras do equals.
     */
    @Test
    public void deve_aceitar_as_regras_do_equals() {
        assertThat(Empresa.class, hasValidBeanEqualsFor("cnpj"));
    }

    /**
     * Deve aceitar as regras do hashcode.
     */
    @Test
    public void deve_aceitar_as_regras_do_hashcode() {
        assertThat(Empresa.class, hasValidBeanHashCodeFor("cnpj"));
    }

    /**
     * Deve aceitar as regras do contrutor.
     */
    @Test
    public void deve_aceitar_as_regras_do_contrutor() {
        assertThat(Empresa.class, hasValidBeanConstructor());
    }

    /**
     * Deve aceitar as regras do to string.
     */
    @Test
    public void deve_aceitar_as_regras_do_toString() {

        empresa.setCnpj("34755982000143");
        assertTrue(empresa.toString().contains("34755982000143"));
    }

    public boolean isValid(Empresa empresa, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa);
        for(ConstraintViolation<Empresa> constraintViolation : restricoes)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }
}

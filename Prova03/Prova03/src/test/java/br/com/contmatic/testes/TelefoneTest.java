package br.com.contmatic.testes;

import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import br.com.contmatic.enums.Telefone;

public class TelefoneTest {
	
	/** The validator. */
	private Validator validator;

	/** The factory. */
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Telefone telefone = Telefone.FIXO;

	@Test
	public void nao_deve_aceitar_descricao_nula() {
		telefone.setDescricao(null);
		assertFalse(isValid(telefone, "A descrição não pode ser nula ou vazia."));
	}

	@Test
	public void nao_deve_aceitar_descricao_vazia() {
		telefone.setDescricao(EMPTY);
		assertFalse(isValid(telefone, "A descrição não pode ser nula ou vazia."));
	}

	@Test
	public void deve_aceitar_descricao_valida() {
		telefone.setDescricao("Celular pessoal.");
		assertTrue(isValid(telefone, telefone.getDescricao()));
	}

	@Test
	public void nao_deve_aceitar_descricao_com_a_primeira_letra_minuscula() {
		telefone.setDescricao("celular empresarial.");
		assertFalse(isValid(telefone, "A descrição não pode conter acentos, caracteres especiais e números."));
	}

	@Test
	public void nao_deve_aceitar_descricao_com_numeros() {
		telefone.setDescricao(random(10, true, true));
		assertFalse(isValid(telefone, "A descrição não pode conter acentos, caracteres especiais e números."));
	}

	@Test
	public void nao_deve_aceitar_descricao_com_caracteres_especiais() {
		telefone.setDescricao("celular empresarial: (11)9####-####.");
		assertFalse(isValid(telefone, "A descrição não pode conter acentos, caracteres especiais e números."));
	}

	@Test
	public void nao_deve_aceitar_descricao_com_mais_de_30_caracteres() {
		telefone.setDescricao(random(35, true, false));
		assertFalse(isValid(telefone, "A descrição não pode ter menos de 15 e mais de 30 caracteres."));
	}

	@Test
	public void nao_deve_aceitar_descricao_com_menos_de_15_caracteres() {
		telefone.setDescricao(random(35, true, false));
		assertFalse(isValid(telefone, "A descrição não pode ter menos de 15 e mais de 30 caracteres."));
	}

	@Test
	public void deve_aceitar_descricao_entre_15_e_30_caracteres_valido() {
		telefone.setDescricao(random(20, true, false));
		assertTrue(isValid(telefone, telefone.getDescricao()));
	}

	@Test
	public void nao_deve_aceitar_tamanho_telefone_fixo_com_mais_de_9_numeros() {
		telefone.setTamanho(10);
		assertFalse(isValid(telefone, "O tamanho do telefone não pode ser maior que 9."));
	}

	@Test
	public void nao_deve_aceitar_tamanho_telefone_fixo_com_menos_de_8_numeros() {
		telefone.setTamanho(7);
		assertFalse(isValid(telefone, "O tamanho do telefone não pode ser menor que 8."));
	}

	@Test
	public void deve_aceitar_tamanho_com_9_numeros() {
		telefone.setTamanho(9);
		assertTrue(isValid(telefone, String.valueOf(telefone.getTamanho())));
	}

	@Test
	public void deve_aceitar_tamanho_com_8_numeros() {
		telefone.setTamanho(8);
		assertTrue(isValid(telefone, String.valueOf(telefone.getTamanho())));
	}

	public boolean isValid(Telefone telefone, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Telefone>> restricoes = validator.validate(telefone);
		for (ConstraintViolation<Telefone> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;

		return valido;
	}
}

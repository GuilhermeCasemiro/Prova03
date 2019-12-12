package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;
import static java.time.LocalDate.of;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.PessoaFisica;

public class PessoaFisicaRandom {

    public static PessoaFisica gerarPessoaFisica() {
        EasyRandomParameters parametroPessoaFisica = new EasyRandomParameters();

        parametroPessoaFisica.charset(forName("UTF-8"));
        parametroPessoaFisica.stringLengthRange(8, 11);
        parametroPessoaFisica.dateRange(of(1996, 03, 27), of(2018, 03, 27));
        parametroPessoaFisica.ignoreRandomizationErrors(true);
        parametroPessoaFisica.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroPessoaFisica);
        PessoaFisica pessoaFisica = easyRandom.nextObject(PessoaFisica.class);
        return pessoaFisica;
    }
}

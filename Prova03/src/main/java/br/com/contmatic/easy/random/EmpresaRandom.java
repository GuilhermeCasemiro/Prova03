package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Empresa;

public class EmpresaRandom {
    
    public static Empresa gerarEmpresa() {
        EasyRandomParameters parametroEmpresa = new EasyRandomParameters();
        parametroEmpresa.charset(forName("UTF-8"));
        parametroEmpresa.stringLengthRange(3, 30);
        parametroEmpresa.ignoreRandomizationErrors(true);
        parametroEmpresa.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroEmpresa);
        Empresa empresa = easyRandom.nextObject(Empresa.class);
        return empresa;
    }
}

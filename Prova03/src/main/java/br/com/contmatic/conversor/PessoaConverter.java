package br.com.contmatic.conversor;

import static br.com.contmatic.easy.random.CidadeRandom.gerarCidade;
import static br.com.contmatic.easy.random.ContatoRandom.gerarContato;
import static br.com.contmatic.easy.random.EmpresaRandom.gerarEmpresa;
import static br.com.contmatic.easy.random.EstadoRandom.gerarEstado;
import static br.com.contmatic.easy.random.FuncionarioRandom.gerarFuncionario;
import static br.com.contmatic.easy.random.PessoaFisicaRandom.gerarPessoaFisica;
import static br.com.contmatic.easy.random.PessoaRandom.gerarPessoa;

import java.util.ArrayList;
import java.util.List;

import br.com.contmatic.models.Cidade;
import br.com.contmatic.models.Contato;
import br.com.contmatic.models.Empresa;
import br.com.contmatic.models.Estado;
import br.com.contmatic.models.Funcionario;
import br.com.contmatic.models.Pessoa;
import br.com.contmatic.models.PessoaFisica;

public class PessoaConverter {
    public static void main(String[] args) {

        List<Pessoa> p = new ArrayList<Pessoa>();
        // Gson gson = new Gson();

        p.add((Pessoa) gerarPessoa());
        Empresa empresa = gerarEmpresa();
        Contato contato = gerarContato();
        Funcionario funcionario = gerarFuncionario();
        Cidade cidade  = gerarCidade();
        Estado estado = gerarEstado();
        PessoaFisica  pessoaFisica = gerarPessoaFisica();
        // String json = gson.toJson(p);
        //
        // try {
        // BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\guilherme.veloso\\Desktop\\pessoa.json"));
        // for(Pessoa pessoa : p) {
        // writer.write(pessoa.toString());
        // }
        //
        // writer.close();
        // } catch (IOException e) {
        //
        // e.printStackTrace();
        // }
        System.out.println(empresa.toString());
        System.out.println(contato.toString());
        System.out.println(funcionario.toString());
        System.out.println(cidade.toString());
        System.out.println(estado.toString());
        System.out.println(pessoaFisica.toString());   
        System.out.println(p.toString());
    }
}

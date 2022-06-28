package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EfetuarAtualizacaoTest {

    @Test
    public void run_quandoContaCorrenteCom100AplicadoTaxaDe1_deveAplicarTerSaldo99(){
        List<ContaCorrente> contaCorrentes = new ArrayList<>();
        AtualizadorDeContas atualizadorDeContas = new AtualizadorDeContas(0.01);
        contaCorrentes.add(new ContaCorrente(100));
        EfetuarAtualizacao efetuarAtualizacao = new EfetuarAtualizacao(contaCorrentes,atualizadorDeContas);
        efetuarAtualizacao.run();

        Assert.assertEquals(99.98,efetuarAtualizacao.getContas().get(0).getSaldo(),00.001);
    }
}
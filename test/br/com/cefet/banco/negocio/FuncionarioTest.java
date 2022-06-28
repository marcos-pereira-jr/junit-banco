package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FuncionarioTest {

    @Test
    public void aumentarSalario_quandoCaixaTiveSalarioDe1000Aumentar1_deveFicarComSalarioDe1010() {
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixaD", 1000);
        caixa.aumentarSalario(1);
        Assert.assertEquals(1010,caixa.getSalario(),0.01);

    }

    @Test
    public void aumentarSalario_quandoPassarNegativo_deveRetornarFalso() {
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixaD", 1000);

        Assert.assertFalse(caixa.aumentarSalario(-1));


    }
}
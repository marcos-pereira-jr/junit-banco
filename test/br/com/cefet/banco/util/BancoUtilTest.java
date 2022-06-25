package br.com.cefet.banco.util;

import br.com.cefet.banco.negocio.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static  org.mockito.Mockito.*;

import static org.junit.Assert.*;
public class BancoUtilTest {

    @Test
    public void realizarLogin_quandoUsuarioSenhaValido_deveCriarUmAutenticavel() {
        Autenticavel autenticavel = BancoUtil.realizarLogin("llogin","1234");
        Assert.assertNotNull(autenticavel);
    }

    @Test
    public void realizarLogin_quandoUsuarioSenhaInvalido_deveRetornarNull() {
        Autenticavel autenticavel = BancoUtil.realizarLogin("ademar","1234");
        Assert.assertNull(autenticavel);
    }

    @Test
    public void converteSenha_quandoArrayCharValido_deveString(){
        char[] nameChar = new char[4];

        nameChar[0] = '1';
        nameChar[1] = '2';
        nameChar[2] = '3';
        nameChar[3] = '4';

        String convertSenha = BancoUtil.converteSenha(nameChar);

        Assert.assertEquals("1234",convertSenha);
    }

    @Test
    public void usuarioEhDiretor_quandoEhDiretor_deveRetornarTrue(){
        Diretor diretor = new Diretor("Caixa", "Avenida", "192.169.90-22", "Contas", "1234", "ddiretor", 20000);
        diretor.setId(1);

        Assert.assertTrue(BancoUtil.usuarioEhDiretor(diretor));
    }

    @Test
    public void possuiFuncionalidadesCaixa_quandoEhCaixa_deveRetornarTrue(){
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixaD", 10000);
        caixa.setId(4);

        Assert.assertTrue(BancoUtil.possuiFuncionalidadesCaixa(caixa));
    }

    @Test
    public void possuiFuncionalidadesGerente_quandoEhGerente_deveRetornarTrue(){
        Gerente gerente =  new Gerente("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ggerenteD", 10000);
        gerente.setId(5);

        Assert.assertTrue(BancoUtil.possuiFuncionalidadesGerente(gerente));
    }

    @Test
    public void possuiFuncionalidadesDiretor_quandoEhDiretor_deveRetornarTrue(){
        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        diretor.setId(6);

        Assert.assertTrue(BancoUtil.possuiFuncionalidadesDiretor(diretor));
    }

    @Test
    public void pegarNomeUsuario_quandoEhFuncionario_deveRetornarDiretor(){
        Diretor diretor =  new Diretor("Diretor","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        diretor.setId(6);

        Assert.assertEquals("Diretor",BancoUtil.pegarNomeUsuario(diretor));
    }

    @Test
    public void pegarNomeUsuario_quandoEhCliente_deveRetornarDiretor(){
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");

        Assert.assertEquals("Marcos Pereira",BancoUtil.pegarNomeUsuario(cliente));
    }

}
package br.com.cefet.banco.persistencia.bd;

import br.com.cefet.banco.negocio.Caixa;
import br.com.cefet.banco.negocio.Diretor;
import br.com.cefet.banco.negocio.Funcionario;
import br.com.cefet.banco.negocio.Gerente;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioDAOTest {

    private FuncionarioDAO funcionarioDAO;

    @Before
    public void setUp() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    @Test
    public void adicionaFuncionario_quandoEDiretorValido_deveSalvar() {
        Diretor diretor = new Diretor("Fernando","Avenida","192.169.90-22","Contas","2566","fdiretor",50000);
        this.funcionarioDAO.adicionaFuncionario(diretor);

        Assert.assertNotNull(this.funcionarioDAO.getFuncionario("fdiretor"));
    }




    @Test
    public void getListaFuncionarios_quandoECaixa_deveSalvar() {
        Caixa caixa = new Caixa("Luiza","Avenida","192.169.90-22","Contas","2566","lcaixa",6000);
        this.funcionarioDAO.adicionaFuncionario(caixa);

        Assert.assertNotNull(this.funcionarioDAO.getFuncionario("lcaixa"));
    }

    @Test
    public void getListaFuncionarios_quandoEGerente_deveSalvar() {
        Gerente gerente = new Gerente("Marcelle","Avenida","192.169.90-22","Contas","2566","lcaixa",10000);
        this.funcionarioDAO.adicionaFuncionario(gerente);

        Assert.assertNotNull(this.funcionarioDAO.getFuncionario("lcaixa"));
    }

    @Test
    public void getFuncionarioCount_quandoTiverRegistro_deveEstaVazia() {
        List<Funcionario> funcionarios = this.funcionarioDAO.getListaFuncionarios();
        Assert.assertFalse(funcionarios.isEmpty());
    }

    @Test
    public void altera_quandoAlterarSalarioDeUmCaixa_deveSalvar() {
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixa", 10000);
        caixa.setId(2);
        this.funcionarioDAO.altera(caixa);

        Caixa caixaFromBase = (Caixa) this.funcionarioDAO.getFuncionario("ccaixa");
        Assert.assertEquals(10000,caixaFromBase.getSalario(),0);
    }

    @Test(expected = SQLException.class)
    public void altera_quandoFuncionarioNaoExiste_deveSQLException(){
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixa", 10000);
        caixa.setId(20100);
        this.funcionarioDAO.altera(caixa);
    }

    @Test
    public void altera_quandoAlterarSalarioDeUmDiretor_deveSalvar() {
        Diretor diretor =  new Diretor("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ddiretor", 20000);
        diretor.setId(1);
        this.funcionarioDAO.altera(diretor);

        Diretor diretorFromBase = (Diretor) this.funcionarioDAO.getFuncionario("ddiretor");
        Assert.assertEquals(20000,diretorFromBase.getSalario(),0);
    }

    @Test
    public void altera_quandoAlterarSalarioDeUmGerente_deveSalvar() {
        Gerente gerente =  new Gerente("Gerente","Avenida","192.169.90-22", "Gerente",   "1234", "ggerente", 100000);
        gerente.setId(3);
        this.funcionarioDAO.altera(gerente);

        Gerente diretorFromBase = (Gerente) this.funcionarioDAO.getFuncionario("ggerente");
        Assert.assertEquals(100000,diretorFromBase.getSalario(),0);
    }

    @Test
    public void remove_quandoRemoverUmCaixa_naoDeveSerAchado(){
        Caixa caixa =  new Caixa("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ccaixaD", 10000);
        caixa.setId(4);
        this.funcionarioDAO.remove(caixa);

        Caixa caixaFromBase = (Caixa) this.funcionarioDAO.getFuncionario("ccaixaD");
        Assert.assertNull(caixaFromBase);

    }

    @Test
    public void remove_quandoRemoverUmGerente_naoDeveSerAchado(){
        Gerente gerente =  new Gerente("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ggerenteD", 10000);
        gerente.setId(5);
        this.funcionarioDAO.remove(gerente);

        Gerente gerenteFromBase = (Gerente) this.funcionarioDAO.getFuncionario("ggerenteD");
        Assert.assertNull(gerenteFromBase);

    }

    @Test
    public void remove_quandoRemoverUmDiretor_naoDeveSerAchado(){
        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        diretor.setId(6);
        this.funcionarioDAO.remove(diretor);

        Diretor diretorFromBase = (Diretor) this.funcionarioDAO.getFuncionario("ddiretorD");
        Assert.assertNull(diretorFromBase);

    }

    @Test
    public void getFuncionario_quandoExitirDiretor_deveNaoTrazerNulo(){
        Diretor diretor = (Diretor) this.funcionarioDAO.getFuncionario(1);
        Assert.assertNotNull(diretor);
    }

    @Test
    public void getFuncionario_quandoExitirGerente_deveNaoTrazerNulo(){
        Gerente gerente = (Gerente) this.funcionarioDAO.getFuncionario(3);
        Assert.assertNotNull(gerente);
    }

    @Test
    public void getFuncionario_quandoExitirCaixae_deveNaoTrazerNulo(){
        Caixa caixa = (Caixa) this.funcionarioDAO.getFuncionario(2);
        Assert.assertNotNull(caixa );
    }

    @Test
    public void getFuncionarioCount_quandoExitir3Registro_deveRetornar6() {
        int count = this.funcionarioDAO.getFuncionarioCount();
        Assert.assertEquals(7,count);
    }

    @Test
    public void calendarParaSQLDate_quandoUmCalendarValido_deveTranformarParaSQLDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,10,4);
        Date date = FuncionarioDAO.calendarParaSQLDate(calendar);
        Assert.assertEquals("2020-11-04",date.toString());
    }

    @Test
    public void sqlDateParaCalendar_quandoSQLValido_deveTranformarParaCalendar() {
        Date date = Date.valueOf("2020-11-4");
        Calendar calendar = FuncionarioDAO.sqlDateParaCalendar(date);
        Assert.assertEquals("Wed Nov 04 00:00:00 BRT 2020",calendar.getTime());
    }

}
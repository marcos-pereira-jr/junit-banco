package br.com.cefet.banco.persistencia.bd;

import br.com.cefet.banco.negocio.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class ClienteDAOTest {

    private ClienteDAO clienteDAO;

    @Before
    public  void setUp(){
        this.clienteDAO = new ClienteDAO();
    }

    @Test
    public void adicionarCliente_quandoEnviadoUmClienteValido_deveSalvarNaBase() {
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        this.clienteDAO.adicionaCliente(cliente);

         Cliente clienteSaved = clienteDAO.getCliente("mpereira");
         Assert.assertNotNull(clienteSaved);
    }

    @Test
    public void ClienteDAO_quandoChamadoListaClientes_deveRetornarUmaListaNaoVazia() {
        List<Cliente> clientes = clienteDAO.getListaClientes();
        Assert.assertTrue(clientes.size() > 0);
    }

    @Test
    public void ClienteDAO_quandoChamadoGetClientesPassandoUsuario_deveRetornarUmCliente() {
        Cliente cliente = clienteDAO.getCliente("aJapones");
        Assert.assertNotNull(cliente);
    }

    @Test
    public void ClienteDAO_quandoChamadoGetClientesPassandoId_deveRetornarUmCliente() {
        Cliente cliente = clienteDAO.getCliente(2);
        Assert.assertNotNull(cliente);
    }

    @Test
    public void ClienteDAO_quandoAlterarUmDadoDeUmCliente_deveAlterarNaBase() {
        Cliente cliente = clienteDAO.getCliente("aJapones");
        cliente.setNome("Pedro Pereira");
        clienteDAO.altera(cliente);
        Cliente clienteFromBase =  clienteDAO.getCliente("aJapones");
        Assert.assertEquals(cliente.getNome(),clienteFromBase.getNome());

    }

    @Test
    public void ClienteDAO_quandoRemoverUmCLiente_deveNaoSerAchado() {
        Cliente cliente = clienteDAO.getCliente("pferreira");
        clienteDAO.remove(cliente);
        Assert.assertNull(clienteDAO.getCliente("pferreira"));
    }

    @Test
    public void calendarParaSQLDate_quandoUmCalendarValido_deveTranformarParaSQLDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020,10,4);
        Date date = ClienteDAO.calendarParaSQLDate(calendar);
        Assert.assertEquals("2020-11-04",date.toString());
    }

    @Test
    public void sqlDateParaCalendar_quandoSQLValido_deveTranformarParaCalendar() {
        Date date = Date.valueOf("2020-11-4");
        Calendar calendar = ClienteDAO.sqlDateParaCalendar(date);
        Assert.assertEquals("Wed Nov 04 00:00:00 BRT 2020",calendar.getTime());
    }

    @Test
    public void testGetCliente() {
    }
}
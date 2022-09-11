package com.acme.credvarejo.conta;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.*;

import com.acme.credvarejo.classesGerais.RepositorioRegistro;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;

public class MovimentoCrediarioTest {

	MovimentoCrediario mc;
	ContaCrediario ccred;
	Cliente client;
	Cpf cpf;
	IdentificadorContaCrediario icc;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void requirements() {
		icc = new IdentificadorContaCrediario((long) 13122018);
		cpf = new Cpf(1910000000);
		Date dt = new Date();
		client = new Cliente(cpf, "Thiago Hortencio", 20, dt, 2000, 0);
		ccred = new ContaCrediario(icc, client, 100, 200, 13, true);
		mc = new MovimentoCrediarioCredito(ccred, 200, dt, 300);
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void nomeExtratoTest() {
		assertEquals(mc.getNomeExtrato(0), "HORTENCIO, THIAGO MR.");
	}
	
	@Test
	public void nomeExtrato2Test() {
		client.setNome("Dayse Pinheiro");
		ccred.setCliente(client);
		mc.setContaCrediario(ccred);
		assertEquals(mc.getNomeExtrato(1), "PINHEIRO, DAYSE MRS.");
	}
	
	@Test
	public void isValidTest() {
		mc.validar();
		assertEquals("Movimento Validado", outputStreamCaptor.toString().trim());
	}
	@Test
	public void isNotValidTest() {
		mc.setDataHoraDaOperacao(null);
		mc.validar();
		assertEquals("Movimento Inv?lido", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void chaveTest() {
		mc.getChave();
		assertEquals("", outputStreamCaptor.toString().trim());
	}
}

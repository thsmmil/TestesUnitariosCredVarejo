package com.acme.credvarejo.cliente;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.*;

public class ControladorClienteTest {

	Cliente client;
	Cpf cpf;
	Date dt;
	ControladorCliente cc;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void requirements() {
		cpf = new Cpf(1910000000);
		dt = new Date();
		client = new Cliente(cpf, "Thiago", 20, dt, 2000, 0);
		cc = new ControladorCliente(null);
		System.setOut(new PrintStream(outputStreamCaptor));
		
	}
}

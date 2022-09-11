package com.acme.credvarejo.classesGerais;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class RegistroTest {

	RepositorioRegistro reg;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void requirements() {
		reg = new RepositorioRegistro(0);
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void incluirTest() {
		reg.incluir("Chave1");
		reg.buscarChave("Chave1");
		assertEquals("Chave: Chave1", outputStreamCaptor.toString()
			      .trim()); 
	}
	
	@Test
	public void buscarTodosTest() {
		reg.incluir("Chave1");
		reg.buscarTodos();
		assertEquals("Chave1", outputStreamCaptor.toString()
			      .trim()); 
	}
	@Test
	public void alterarTest() {
		reg.incluir("Chave1");
		reg.alterar("Chave1", "Chave2");
		reg.buscarChave("Chave2");
		assertEquals("Chave: Chave2", outputStreamCaptor.toString()
					      .trim()); 
		
	}
	
	@Test
	public void alterarNullTest() {
		reg.alterar("Chave1", "Chave2");
		assertEquals("Vazio!\r\n"
				+ "Chave Inv?lida!", outputStreamCaptor.toString()
					      .trim()); 
		
	}
	
	@Test
	public void excluirTest() {
		reg.incluir("Chave1");
		reg.excluir("Chave1");
		reg.buscarChave("Chave1");
		assertEquals("Vazio!", outputStreamCaptor.toString()
					      .trim()); 
		
	}
	
	@Test
	public void excluirNullTest() {
		reg.excluir("Chave1");
		assertEquals("Vazio!", outputStreamCaptor.toString()
					      .trim()); 
		
	}
}

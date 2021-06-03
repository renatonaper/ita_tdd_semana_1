import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class TestCamelCase {

	
	@Test
	public void testeNome(){
		List<String> listaResultado = CamelCase.converterCamelCase("Nome");
		assertEquals("nome",listaResultado.get(0));
	}

	
	@Test
	public void textoComDoisTextos(){
		List<String> listaResultado = CamelCase.converterCamelCase("numeroCPF");
		assertEquals("numero",listaResultado.get(0));
		assertEquals("CPF",listaResultado.get(1));
	}

	@Test
	public void textoComTresTextos(){
		List<String> listaResultado = CamelCase.converterCamelCase("numeroCPFContribuinte");
		assertEquals("numero",listaResultado.get(0));
		assertEquals("CPF",listaResultado.get(1));
		assertEquals("contribuinte",listaResultado.get(2));
	}

	@Test
	public void textoComTextoseNumeros(){
		List<String> listaResultado = CamelCase.converterCamelCase("recupera10Primeiros");
		assertEquals("recupera",listaResultado.get(0));
		assertEquals("10",listaResultado.get(1));
		assertEquals("primeiros",listaResultado.get(2));
	}
	
	@Test(expected=CamelCaseException.class)
	public void textoComecaComNumeros(){
		CamelCase.converterCamelCase("10Primeiros");
	}
	
	@Test(expected=CamelCaseException.class)
	public void textoContemEspecial(){
		CamelCase.converterCamelCase("nome#Composto");
	}
	
	@Test(expected=CamelCaseException.class)
	public void testeVazio(){
		List<String> listaResultado = CamelCase.converterCamelCase("");
	}

	@Test(expected=CamelCaseException.class)
	public void testeNulo(){
		List<String> listaResultado = CamelCase.converterCamelCase(null);
	}

}

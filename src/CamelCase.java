import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCase {
	

	private static String REGEX_NUMERO = "((.*)([0-9]+)(.*))";
	private static String REGEX_CORTAR = "(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])";
	
	public CamelCase() {
	}

	public static List<String> converterCamelCase(String original) {
		
		if (!validacaoNuloOuVazio(original)) {
			throw new CamelCaseException("Não pode ser vazio");
		}
		
		if (!naoPodeComecarComNumero(original)) {
			throw new CamelCaseException("Não deve começar com números ");	
		}
		
		if (contemTextoEspecial(original)) {
			throw new CamelCaseException("Caracteres especiais não são permitidos, somente letras e números");
			
		}
		
		return processaTexto(original);
	
	}
	
	
	public static List<String> contemNumero(String str) {
	
		Pattern ptt = Pattern.compile("[A-Za-z]+|[0-9]+");
		Matcher mtr = ptt.matcher(str);
		
		List<String> lstString = new ArrayList<String>();
		
		while (mtr.find())
			lstString.add(mtr.group().trim());

		String[] strSplit = new String[lstString.size()];
		return retornarTextos(lstString.toArray(strSplit));
	}
	
	

	public static List<String> retornarTextos(String[] cortarTexto){
		List<String> listaTextos = new ArrayList<String>();
		for (int i = 0; i < cortarTexto.length; i++)
			if (!cortarTexto[i].isEmpty())
				if (cortarTexto[i].equals(cortarTexto[i].toUpperCase()))
					listaTextos.add(cortarTexto[i]);
				else
					listaTextos.add(cortarTexto[i].toLowerCase());
		
		
		return listaTextos ;
	}
	
	public static boolean contemTextoEspecial(String str){
		boolean charEspecial = false;
		for (int i = 0; i < str.length(); i++) {
			if (!(Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i)))) {
				charEspecial = true;
				break;
			}			
		}
		return charEspecial;
	}
	
	
	public static boolean validacaoNuloOuVazio(String original) {
		boolean status = true;
		if (original == null || original.length() == 0 ) {
			status= false;
		}
		
		return status;
	}
	
	public static boolean naoPodeComecarComNumero(String original) {
		boolean status = true;
		if (Character.isDigit(original.charAt(0))) {
			status = false;
		}
		
		return status;
	}
 	
	
	public static List<String> processaTexto(String original) {
		
		if (original.matches(REGEX_NUMERO)){
			return contemNumero(original);
			
		}
		else {
			String[] cortarTexto = original.split(REGEX_CORTAR);
			return retornarTextos(cortarTexto);
		}
		
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println(converterCamelCase("nome"));
		System.out.println(converterCamelCase("Nome"));
		System.out.println(converterCamelCase("nomeComposto"));
		System.out.println(converterCamelCase("NomeComposto"));
		System.out.println(converterCamelCase("CPF"));
		System.out.println(converterCamelCase("numeroCPF"));
		System.out.println(converterCamelCase("numeroCPFContribuinte"));
		System.out.println(converterCamelCase("recupera10Primeiros"));
		//System.out.println(converter("10Primeiros"));
		//System.out.println(converter("nome#Composto"));
		
	}

	
	
}

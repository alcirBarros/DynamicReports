package org.interfaces;


import org.objectFake.OperadorLogado;
import java.util.Map;

public interface IRelatorio {

    public Object gerarPDF(Map<String, String[]> parametros, OperadorLogado operadorLogado, String pathRelatorios) throws Exception;
}
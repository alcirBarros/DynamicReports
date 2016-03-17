package org.interfaces;


import org.objectFake.OperadorLogado;
import org.objectFake.EntityManager;
import java.util.Map;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;

public interface IRelatorioDynamicReports extends IRelatorio{

    public JasperReportBuilder geraRelatorioCom(Object... obj);

    public JRDataSource createDataSource(Object obj);

    public JasperReportBuilder parametro(Map<String, String[]> parametro, OperadorLogado operadorLogado);
}
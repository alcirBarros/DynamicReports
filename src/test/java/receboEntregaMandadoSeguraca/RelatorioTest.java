package receboEntregaMandadoSeguraca;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import org.junit.Before;
import org.junit.Test;
import org.objectFake.OperadorLogado;

public class RelatorioTest {

    private RelatorioDynamic relatorio = new RelatorioDynamic();
    private OperadorLogado operadorLogado;

    private static final String[] ESTABELECIMENTO_ID = {"1"};

    @Before
    public void setUp() {

    }

    @Test
    public void gerarRelatorio() throws Exception {

        String[] dataInicio = {"01/06/2015"};
        String[] dataFim = {"30/06/2015"};

        Map<String, String[]> valores = new HashMap<>();
        valores.put("dataInicio", dataInicio);
        valores.put("dataFim", dataFim);
        valores.put("estabelecimentoId", ESTABELECIMENTO_ID);
        
        JasperReportBuilder report = relatorio.parametro(valores, operadorLogado);
        report.toPdf(new FileOutputStream("//tmp//SALUTE.pdf"));
    }
}

package receboEntregaMandadoSeguraca;

import java.text.ParseException;
import java.util.Calendar;
import org.objectFake.Source;
import org.objectFake.OperadorLogado;
import org.objectFake.EntityManager;
import org.interfaces.IRelatorioDynamicReports;
import java.util.Date;
import java.util.Map;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.margin;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import static org.style.DynamicReportStyles.*;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import org.objectFake.Estabelecimento;
import org.objectFake.GeradorCabecalho;
import org.objectFake.GeradorRodape;
import org.objectFake.Municipio;
import org.objectFake.Operador;
import org.util.DateUtils;

public class RelatorioDynamic implements IRelatorioDynamicReports {

    private final JasperReportBuilder report = DynamicReports.report();
    private final Municipio municipio = Municipio.criaInstancia();
    private final Operador operador = Operador.criaInstancia();
    private final Estabelecimento estabelecimento = Estabelecimento.criaInstancia();

    @Override
    public Object setEm(EntityManager em) {
        return null;
    }

    @Override
    public JasperReportBuilder geraRelatorioCom(Object... obj) {

        VerticalListBuilder verticalList = cmp.verticalList(
                cmp.text("Nº Movimento: ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text("Nº Processo: ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text("Nº Ordem: ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text(" ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text("Recebi do Departamento Municipal de Saúde de São João da Boa Vista/SP o medicamento abaixo descrito para uso da paciente ALZIRA MAROCO DANTAS").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text(" ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100)
        );
        


        report.title(new GeradorCabecalho(municipio, "RECIBO").get()).addTitle(verticalList);

        TextColumnBuilder< Integer> columnCodigo = col.column("Código", "codigo", type.integerType()).setWidth(10).setHorizontalAlignment(HorizontalAlignment.RIGHT);
        TextColumnBuilder<String> columnNumeroLote = col.column("Lote", "numeroLote", type.stringType()).setWidth(20).setHorizontalAlignment(HorizontalAlignment.LEFT);
        TextColumnBuilder<Date> columnDataValidade = col.column("Data Validade", "dataValidade", type.dateType()).setWidth(20).setHorizontalAlignment(HorizontalAlignment.CENTER);
        TextColumnBuilder<String> columnProduto = col.column("Produto", "produto", type.stringType()).setWidth(40).setHorizontalAlignment(HorizontalAlignment.LEFT);
        TextColumnBuilder<String> columnValorUnit = col.column("Valor Unit.", "valorUnitario", type.stringType()).setWidth(15).setHorizontalAlignment(HorizontalAlignment.LEFT);
        TextColumnBuilder<String> columnQuantidade = col.column("Quant.", "quantidade", type.stringType()).setWidth(15).setHorizontalAlignment(HorizontalAlignment.LEFT);
         TextColumnBuilder<String> columnValorTotal = col.column("Valor Total", "valorTotal", type.stringType()).setWidth(15).setHorizontalAlignment(HorizontalAlignment.LEFT);

        
        
        VerticalListBuilder summary = cmp.verticalList(

                cmp.text(" ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text("___________________________                                                                                ___________________________").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100),
                cmp.text(" ").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(100)
        );
        
        
        report.summary(summary).addSummary(new GeradorRodape(operador, estabelecimento).get());
        
        return report
                .columns(columnCodigo, columnNumeroLote, columnDataValidade, columnProduto, columnValorUnit, columnQuantidade, columnValorTotal)
                .setPageFormat(PageType.A4, PageOrientation.PORTRAIT)
                .setPageMargin(margin(35))
                .highlightDetailEvenRows();
    }

    @Override
    public JRDataSource createDataSource(Object obj) {
        DRDataSource dataSource = new DRDataSource("codigo", "numeroLote", "dataValidade", "produto", "valorUnitario", "quantidade", "valorTotal");
        dataSource.add(1, "aaaa", new Date(), "aaaaaaaaaaaa", "", "", "");
        dataSource.add(2, "bbbb", new Date(), "bbbbbbbbbb", "", "", "");
        return dataSource;
    }

    @Override
    public JasperReportBuilder parametro(Map<String, String[]> parametro, OperadorLogado operadorLogado) {
        try {
            Calendar dataInicio = DateUtils.toCalendar(DateUtils.FORMAT_DD_MM_YYYYY.parse(parametro.get("dataInicio")[0]));
            Calendar dataFim = DateUtils.toCalendar(DateUtils.FORMAT_DD_MM_YYYYY.parse(parametro.get("dataFim")[0]));
            String[] estabelecimentoId = (parametro.get("estabelecimentoId") != null) ? parametro.get("estabelecimentoId") : null;

            Source source = new Source();
            source.put("dataInicio", dataInicio);
            source.put("dataFim", dataFim);
            source.put("estabelecimentoId", estabelecimentoId);

            JRDataSource createDataSource = createDataSource(source);
            JasperReportBuilder reportBuilder = geraRelatorioCom(operadorLogado, source);
            reportBuilder.setDataSource(createDataSource);
            return reportBuilder;
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public Object gerarPDF(Map<String, String[]> parametros, OperadorLogado operadorLogado, String pathRelatorios) throws Exception {
        return null;
    }
}

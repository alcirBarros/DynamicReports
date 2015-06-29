import java.util.Date;
import java.util.Map;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.margin;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class RelatorioDynamic implements IRelatorioDynamicReports {

    private final JasperReportBuilder report = DynamicReports.report();

    @Override
    public Object setEm(EntityManager em) {
        return null;
    }

    @Override
    public JasperReportBuilder geraRelatorioCom(Object... obj) {

        TextColumnBuilder<Integer> columnCodigo = col.column("CÓDIGO", "codigo", type.integerType()).setWidth(15).setHorizontalAlignment(HorizontalAlignment.RIGHT);
        TextColumnBuilder<String> columnNumeroLote = col.column("LOTE", "numeroLote", type.stringType()).setWidth(30).setHorizontalAlignment(HorizontalAlignment.CENTER);
        TextColumnBuilder<Date> columnDataValidade = col.column("DATA VALIDADE", "dataValidade", type.dateType()).setWidth(25).setHorizontalAlignment(HorizontalAlignment.CENTER);
        TextColumnBuilder<String> columnProduto = col.column("PRODUTO", "produto", type.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER);

        return report
                .title()
                .columns(columnCodigo, columnNumeroLote, columnDataValidade, columnProduto)
                .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                .setPageMargin(margin(35))
                .highlightDetailEvenRows();
    }

    @Override
    public JRDataSource createDataSource(Object obj) {
        DRDataSource dataSource = new DRDataSource("codigo", "numeroLote", "dataValidade", "produto");
        dataSource.add(1, "", new Date(), "aaaaaaaaaaaa");
        dataSource.add(2, "", new Date(), "bbbbbbbbbb");
        return dataSource;
    }

    @Override
    public JasperReportBuilder parametro(Map<String, String[]> parametro, OperadorLogado operadorLogado) {

//        Calendar dataInicio = DateUtils.toCalendar(DateUtils.FORMAT_DD_MM_YYYYY.parse(parametro.get("dataInicio")[0]));
//        Calendar dataFim = DateUtils.toCalendar(DateUtils.FORMAT_DD_MM_YYYYY.parse(parametro.get("dataFim")[0]));
//        String[] grupoProdutoId = (parametro.get("estabelecimentoId") != null) ? parametro.get("estabelecimentoId") : null;
//        
        Source source = new Source();
//        source.put("dataInicio", dataInicio);
//        source.put("dataFim", dataFim);
//        source.put("estabelecimentoId", geraListArray(estabelecimentoFacade.nomeEstabelecimento(estabelecimentoId)));

        JRDataSource createDataSource = createDataSource(source);
        JasperReportBuilder reportBuilder = geraRelatorioCom(operadorLogado, source);
        reportBuilder.setDataSource(createDataSource);
        return reportBuilder;
    }

    @Override
    public Object gerarPDF(Map<String, String[]> parametros, OperadorLogado operadorLogado, String pathRelatorios) throws Exception {
        return null;
    }
}

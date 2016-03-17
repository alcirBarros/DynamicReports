package receboEntregaMandadoSeguraca;

import org.objectFake.OperadorLogado;
import org.interfaces.IRelatorioDynamicReports;
import java.util.Map;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.jasperreports.engine.JRDataSource;
import org.objectFake.Estabelecimento;
import org.objectFake.GeradorRelatorioPDF;
import org.objectFake.Municipio;
import org.objectFake.Operador;
import org.templat.Templates;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.Date;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import net.sf.dynamicreports.report.builder.barcode.Code128BarcodeBuilder;

import net.sf.dynamicreports.report.builder.barcode.Ean128BarcodeBuilder;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;

import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import net.sf.dynamicreports.report.constant.PageType;
import org.objectFake.Source;
import org.templat.ShippingLabelData;

public class RelatorioDynamic implements IRelatorioDynamicReports {

    private ShippingLabelData data = new ShippingLabelData();
    private StyleBuilder bold14Style;

    private final JasperReportBuilder report = DynamicReports.report();
    private final Municipio municipio = Municipio.criaInstancia();
    private final Operador operador = Operador.criaInstancia();
    private final Estabelecimento estabelecimento = Estabelecimento.criaInstancia();

    @Override
    public JasperReportBuilder geraRelatorioCom(Object... obj) {

        ShippingLabel shippingLabel = data.getShippingLabel();

        StyleBuilder textStyle = stl.style()
                .setFontSize(12);

        bold14Style = stl.style(Templates.boldStyle).setFontSize(14);

        StyleBuilder boldCentered30Style = stl.style(bold14Style)
                .setFontSize(30)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        StyleBuilder boldCentered100Style = stl.style(boldCentered30Style)
                .setFontSize(100);
//
        Ean128BarcodeBuilder shippingContainerCode = bcode.ean128("100264835710351")
                .setModuleWidth(2.5)
                .setStyle(bold14Style);
//
//        Code128BarcodeBuilder shipToPostalCode = bcode.code128("09820")
//                .setModuleWidth(3d)
//                .setStyle(bold14Style);

        TextFieldBuilder<Integer> priority = cmp.text(shippingLabel.getPriority()).setStyle(boldCentered100Style);

        TextFieldBuilder<String> pod = cmp.text(shippingLabel.getPod()).setStyle(boldCentered30Style);

        TextFieldBuilder<Date> dateShipped = cmp.text(exp.date(shippingLabel.getDateShipped())).setDataType(type.dateType());

        TextFieldBuilder<String> po = cmp.text(shippingLabel.getPo()).setStyle(boldCentered30Style);

        report
                .setTemplate(Templates.reportTemplate)
                .setPageFormat(PageType.A5)
                .setTextStyle(textStyle)
                .title(
                        Templates.createTitleComponent("ShippingLabel"),
                        cmp.horizontalList(
                                createCustomerComponent("From", shippingLabel.getFrom()),
                                createCustomerComponent("To", shippingLabel.getTo())),
                        cmp.horizontalList(
                                cmp.verticalList(
                                        createCellComponent("Priority", priority),
                                        createCellComponent("POD", pod)),
                                cmp.verticalList(
                                        cmp.horizontalList(
                                                createCellComponent("Carrier", cmp.text(shippingLabel.getCarrier())),
                                                createCellComponent("Date shipped", dateShipped)),
                                        cmp.horizontalList(
                                                createCellComponent("Weight", cmp.text(shippingLabel.getWeight())),
                                                createCellComponent("Quantity", cmp.text(shippingLabel.getQuantity())))
//                                        createCellComponent("Ship to postal code", shipToPostalCode)
                                )),
                        createCellComponent("PO", po),
                        createCellComponent("Serial shipping container", shippingContainerCode));

        return report;

    }

    private ComponentBuilder<?, ?> createCellComponent(String label, ComponentBuilder<?, ?> content) {
        VerticalListBuilder cell = cmp.verticalList(
                cmp.text(label).setStyle(bold14Style),
                cmp.horizontalList(
                        cmp.horizontalGap(20),
                        content,
                        cmp.horizontalGap(5)));

        cell.setStyle(stl.style(stl.pen2Point()));
        return cell;
    }

    private ComponentBuilder<?, ?> createCustomerComponent(String label, Customer customer) {
        VerticalListBuilder content = cmp.verticalList(
                cmp.text(customer.getName()),
                cmp.text(customer.getAddress()),
                cmp.text(customer.getCity()));
        return createCellComponent(label, content);
    }

    @Override
    public JRDataSource createDataSource(Object obj) {
        return null;
    }

    @Override
    public JasperReportBuilder parametro(Map<String, String[]> parametro, OperadorLogado operadorLogado) {
        Source source = new Source();
        JasperReportBuilder geraRelatorioCom = geraRelatorioCom(source);
        return geraRelatorioCom;
    }

    @Override
    public Object gerarPDF(Map<String, String[]> parametros, OperadorLogado operadorLogado, String pathRelatorios) throws Exception {
        JasperReportBuilder reportBuilder = parametro(parametros, operadorLogado);
        return new GeradorRelatorioPDF(reportBuilder, "relatorio").getByteArrayOutputStream();
    }
}

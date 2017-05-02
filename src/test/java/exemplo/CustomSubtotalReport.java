package exemplo;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.templat.Templates;

public class CustomSubtotalReport {

    private AggregationSubtotalBuilder<Integer> quantitySum;

    private AggregationSubtotalBuilder<BigDecimal> priceSum;

    public CustomSubtotalReport() {

        build();

    }

    private class ValueFormatter extends AbstractValueFormatter<String, BigDecimal> {
        public String format(BigDecimal value, ReportParameters reportParameters) {
            return value.setScale(4, RoundingMode.HALF_UP).toString();
        }
    }

    private void build() {

        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());

        TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());

        TextColumnBuilder<BigDecimal> priceColumn = col.column("Price", "price", type.bigDecimalType());

        TextColumnBuilder<BigDecimal> unitPriceColumn = priceColumn.divide(2, quantityColumn).setTitle("Price / Quantity");
        unitPriceColumn.setValueFormatter(new ValueFormatter());

        quantitySum = sbt.sum(quantityColumn).setLabel("sum");

        priceSum = sbt.sum(priceColumn).setLabel("sum");

        CustomSubtotalBuilder<BigDecimal> unitPriceSbt = sbt.customValue(new UnitPriceSubtotal(), unitPriceColumn).setLabel("sum(price) / sum(quantity)").setDataType(type.bigDecimalType());
        unitPriceSbt.setValueFormatter(new ValueFormatter());

        try {

            report()
                    .setTemplate(Templates.reportTemplate)
                    .columns(
                            itemColumn, quantityColumn, priceColumn, unitPriceColumn)
                    .subtotalsAtSummary(
                            quantitySum, priceSum, unitPriceSbt)
                    .title(Templates.createTitleComponent("CustomSubtotal"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();

        } catch (DRException e) {
            e.printStackTrace();

        }

    }

    private class UnitPriceSubtotal extends AbstractSimpleExpression<BigDecimal> {

        private static final long serialVersionUID = 1L;

        @Override

        public BigDecimal evaluate(ReportParameters reportParameters) {

            Integer quantitySumValue = reportParameters.getValue(quantitySum);

            BigDecimal priceSumValue = reportParameters.getValue(priceSum);

            return priceSumValue.divide(new BigDecimal(quantitySumValue), 2, BigDecimal.ROUND_HALF_UP);

        }

    }

    private JRDataSource createDataSource() {

        DRDataSource dataSource = new DRDataSource("item", "quantity", "price");

        dataSource.add("Tablet", 3, new BigDecimal(330.6457324).setScale(5, RoundingMode.HALF_UP));

        dataSource.add("Tablet", 1, new BigDecimal(150));

        dataSource.add("Laptop", 3, new BigDecimal(900));

        dataSource.add("Smartphone", 8, new BigDecimal(720));

        dataSource.add("Smartphone", 6, new BigDecimal(720));

        return dataSource;

    }

    public static void main(String[] args) {
        new CustomSubtotalReport();
    }

}

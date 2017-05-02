package exemplo;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Date;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.definition.ReportParameters;

import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;
import org.templat.Templates;

public class ConversionColumnReport {

    public ConversionColumnReport() {

        build();

    }

    private void build() {
        try {

            TextColumnBuilder<Date> orderDateColumn1 = col.column("Order date", new OrderDateColumn1()).setDataType(type.dateType());
            
            TextColumnBuilder<Date> orderDateColumn2 = col.column("Order date", new OrderDateColumn2()).setDataType(type.dateType());
            
            TextColumnBuilder<BigDecimal> quantityColumn1 = col.column("Quantity", new QuantityColumn1()).setDataType(type.bigDecimalType());
            
            TextColumnBuilder<BigDecimal> quantityColumn2 = col.column("Quantity", new QuantityColumn2()).setDataType(type.bigDecimalType());
            
            report()
                    .setTemplate(Templates.reportTemplate)
                    .fields(
                            field("orderdate", String.class),
                            field("quantity", String.class))
                    .columns(
                            col.column("Item", "item", type.stringType()),
                            orderDateColumn1, orderDateColumn2, quantityColumn1, quantityColumn2)
                    .title(Templates.createTitleComponent("ConversionColumn"))
                    .pageFooter(Templates.footerComponent)
                    .subtotalsAtSummary(
                            sbt.min(orderDateColumn1), sbt.min(orderDateColumn2), sbt.sum(quantityColumn1), sbt.sum(quantityColumn2))
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }

    }

    private JRDataSource createDataSource() {

        DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity");

        dataSource.add("Notebook", "1/1/2010", "100.96567");

        dataSource.add("Notebook", "2/2/2010", "100.2");

        return dataSource;

    }

    public static void main(String[] args) {

        new ConversionColumnReport();

    }

    private class OrderDateColumn1 extends AbstractSimpleExpression<Date> {

        private static final long serialVersionUID = 1L;

        @Override

        public Date evaluate(ReportParameters reportParameters) {

            String value = reportParameters.getValue("orderdate");

            try {

                return new SimpleDateFormat("MM/dd/yyyy", reportParameters.getLocale()).parse(value);

            } catch (ParseException e) {

                e.printStackTrace();

            }

            return null;

        }

    }

    private class OrderDateColumn2 extends AbstractSimpleExpression<Date> {

        private static final long serialVersionUID = 1L;

        @Override

        public Date evaluate(ReportParameters reportParameters) {

            try {

                return type.dateType().stringToValue("orderdate", reportParameters);

            } catch (DRException e) {

                e.printStackTrace();

            }

            return null;

        }
    }

    private class QuantityColumn1 extends AbstractSimpleExpression<BigDecimal> {

        private static final long serialVersionUID = 1L;

        @Override

        public BigDecimal evaluate(ReportParameters reportParameters) {

            String value = reportParameters.getValue("quantity");

            try {

                Number number = new DecimalFormat("#,###.####", new DecimalFormatSymbols(reportParameters.getLocale())).parse(value);

                return new BigDecimal(number.doubleValue()).setScale(4, RoundingMode.HALF_UP);

            } catch (ParseException e) {

                e.printStackTrace();

            }

            return null;

        }

    }

    private class QuantityColumn2 extends AbstractSimpleExpression<BigDecimal> {

        private static final long serialVersionUID = 1L;

        @Override

        public BigDecimal evaluate(ReportParameters reportParameters) {

            try {

                return type.bigDecimalType().stringToValue("quantity", reportParameters).setScale(4, RoundingMode.HALF_UP);

            } catch (DRException e) {

                e.printStackTrace();

            }

            return null;

        }

    }

}

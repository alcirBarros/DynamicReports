package exemplo;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.Calendar;

import java.util.Date;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;

import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;

import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;

import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;

import net.sf.dynamicreports.report.constant.Calculation;

import net.sf.dynamicreports.report.constant.PageOrientation;

import net.sf.dynamicreports.report.constant.PageType;

import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.definition.ReportParameters;

import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;



public class GroupExpressionCrosstabReport {

    public GroupExpressionCrosstabReport() {

        build();

    }

    private void build() {

        CrosstabRowGroupBuilder<String> rowGroup = ctab.rowGroup("state", String.class);

        CrosstabColumnGroupBuilder<Integer> columnYearGroup = ctab.columnGroup(new YearExpression());

        CrosstabColumnGroupBuilder<String> columnQuarterGroup = ctab.columnGroup(new QuarterExpression());

        CrosstabBuilder crosstab = ctab.crosstab()
                .setCellWidth(50)
      //          .headerCell(cmp.text("State / Date").setStyle(Templates.boldCenteredStyle))
                .rowGroups(rowGroup)
                .columnGroups(columnYearGroup, columnQuarterGroup)
                .measures(
                        ctab.measure("quantity", Integer.class, Calculation.SUM));

        try {

            report()
                    .fields(field("orderdate", Date.class))
                    .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                   // .setTemplate(Templates.reportTemplate)
                  //  .title(Templates.createTitleComponent("GroupExpressionCrosstab"))
                    .summary(crosstab)
                   // .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();

        } catch (DRException e) {

            e.printStackTrace();

        }

    }

    private JRDataSource createDataSource() {

        DRDataSource dataSource = new DRDataSource("state", "orderdate", "quantity");

        Calendar c = Calendar.getInstance();

        for (int i = 0; i < 700; i++) {

            Date date = c.getTime();

            dataSource.add("New York", date, (int) (Math.random() * 10) + 1);

            dataSource.add("Washington", date, (int) (Math.random() * 10) + 1);

            dataSource.add("Florida", date, (int) (Math.random() * 10) + 1);

            c.add(Calendar.DAY_OF_MONTH, -1);
        }

        return dataSource;

    }

    public static void main(String[] args) {

        new GroupExpressionCrosstabReport();

    }

    private class YearExpression extends AbstractSimpleExpression<Integer> {

        private static final long serialVersionUID = 1L;

        @Override
        public Integer evaluate(ReportParameters reportParameters) {
            Calendar c = Calendar.getInstance();
            c.setTime((Date) reportParameters.getValue("orderdate"));
            return c.get(Calendar.YEAR);
        }

    }

    private class QuarterExpression extends AbstractSimpleExpression<String> {

        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            Calendar c = Calendar.getInstance();
            c.setTime((Date) reportParameters.getValue("orderdate"));
            return "Q" + (c.get(Calendar.MONTH) / 3 + 1);
        }
    }
}

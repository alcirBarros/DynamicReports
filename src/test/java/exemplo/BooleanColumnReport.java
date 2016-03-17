package exemplo;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.templat.Templates;

public class BooleanColumnReport {

    public BooleanColumnReport() {
        build();
    }

    private void build() {

        try {

            report()
                    .setTemplate(Templates.reportTemplate)
                    .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
                    .columns(
                            col.booleanColumn("Boolean\ndefault", "boolean"),
                            col.booleanColumn("Boolean\nTEXT_TRUE_FALSE", "boolean").setComponentType(BooleanComponentType.TEXT_TRUE_FALSE).setEmptyWhenNullValue(true),
                            col.booleanColumn("Boolean\nTEXT_YES_NO", "boolean").setComponentType(BooleanComponentType.TEXT_YES_NO),
                            col.booleanColumn("Boolean\nIMAGE_STYLE_1", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_1).setEmptyWhenNullValue(true),
                            col.booleanColumn("Boolean\nIMAGE_STYLE_2", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_2),
                            col.booleanColumn("Boolean\nIMAGE_STYLE_3", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_3),
                            col.booleanColumn("Boolean\nIMAGE_STYLE_4", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_4),
                            col.booleanColumn("Boolean\nIMAGE_CHECKBOX_1", "boolean").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1),
                            col.booleanColumn("Boolean\nIMAGE_CHECKBOX_2", "boolean").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_2),
                            col.booleanColumn("Boolean\nIMAGE_BALL", "boolean").setComponentType(BooleanComponentType.IMAGE_BALL))
                    .title(Templates.createTitleComponent("BooleanColumn"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();

        } catch (DRException e) {
            e.printStackTrace();
        }

    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("boolean");
        dataSource.add(true);
        dataSource.add(false);
        dataSource.add();
        dataSource.add(false);
        return dataSource;

    }

    public static void main(String[] args) {
        new BooleanColumnReport();
    }

}

package exemplo;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import java.util.ArrayList;
import java.util.List;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.templat.Templates;

public class ColumnListDataTypeReport {
    
    public ColumnListDataTypeReport() {
        build();
    }
    
    private void build() {
        try {
            report()
                    .setTemplate(Templates.reportTemplate)
                    .columns(
                            col.column("Item", "item", type.stringType()),
                            col.column("Quantity", "quantity", type.integerType()),
                            col.column("Comments", "comments", type.listType()))
                    .title(Templates.createTitleComponent("ColumnListDataType"))
                    .pageFooter(Templates.footerComponent)
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        List<ReportData> datasource = new ArrayList<ReportData>();
        ReportData data = new ReportData();
        List<String> comments = new ArrayList<String>();
        comments.add("comment1");
        comments.add("comment2");
        comments.add("comment3");
        data.setItem("Book");
        data.setQuantity(new Integer(10));
        data.setComments(comments);
        datasource.add(data);
        data = new ReportData();
        comments = new ArrayList<String>();
        comments.add("comment1");
        comments.add("comment2");
        data.setItem("Notebook");
        data.setQuantity(new Integer(20));
        data.setComments(comments);
        datasource.add(data);
        return new JRBeanCollectionDataSource(datasource);

    }

    public class ReportData {
        
        private String item;
        private Integer quantity;
        private List<String> comments;
        
        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public List<String> getComments() {
            return comments;
        }

        public void setComments(List<String> comments) {
            this.comments = comments;
        }
    }

    public static void main(String[] args) {
        new ColumnListDataTypeReport();
    }
}

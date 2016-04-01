package sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

public class DataTable {

    private StyleBuilder boldCenteredStyle;

    private StyleBuilder borderedStyle;

    public DataTable() {
        build();
    }

    private void build() {
        boldCenteredStyle = stl.style().bold().setHorizontalAlignment(HorizontalAlignment.CENTER);
        borderedStyle = stl.style(stl.pen1Point());
        try {

            List<TipoEnum> a = new ArrayList();
            a.add(TipoEnum.MINIMO);
            a.add(TipoEnum.MEDIO);
            a.add(TipoEnum.MAXIMO);
            
            List<SelectItemDynamic> selectItemDynamicList = new ArrayList<>();
            
            for(TipoEnum tipoEnum: TipoEnum.values()){
                SelectItemDynamic selectItemDynamic = new SelectItemDynamic();
                selectItemDynamic.setDescricao(tipoEnum.getDescricao());
                if(a.contains(tipoEnum)){
                    selectItemDynamic.setSelect(Boolean.TRUE);
                }
                selectItemDynamicList.add(selectItemDynamic);
            }
            
            ComponentBuilder<?, ?> createDataTable = createDataTable(selectItemDynamicList, 3);

            report().title(createDataTable).show();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private HorizontalListBuilder printSelectItemDynamic(SelectItemDynamic selectItemDynamic) {
        HorizontalListBuilder horizontalList = cmp.horizontalList(
                cmp.booleanField(selectItemDynamic.getSelect()).setComponentType(BooleanComponentType.IMAGE_CHECKBOX_2).setWidth(10).setStyle(stl.style().setPadding(0)).setHorizontalAlignment(HorizontalAlignment.LEFT),
                cmp.text(selectItemDynamic.getDescricao()).setHorizontalAlignment(HorizontalAlignment.LEFT)
        ).setStyle(borderedStyle).setWidth(10);
        return horizontalList;
    }

    private TextFieldBuilder<String> createTextField(String label) {
        return cmp.text(label).setStyle(borderedStyle).setMinWidth(10);
    }

    private ComponentBuilder<?, ?> createDataTable(List<SelectItemDynamic> a, Integer column) {
        VerticalListBuilder verticalList = cmp.verticalList();
        Iterator<SelectItemDynamic> iterator = a.iterator();
        while (iterator.hasNext()) {
            HorizontalListBuilder horizontalList = cmp.horizontalList();
            for (int x = 0; x < column; x++) {
                horizontalList.add((iterator.hasNext()) ? printSelectItemDynamic(iterator.next()) : createTextField(""));
            }
            verticalList.add(horizontalList);
        }

        return verticalList;
    }

    public static void main(String[] args) {
        new DataTable();
    }
    
    class SelectItemDynamic{
        private String descricao;
        private Boolean select = Boolean.FALSE;

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public Boolean getSelect() {
            return select;
        }

        public void setSelect(Boolean select) {
            this.select = select;
        }
    }

}

enum TipoEnum {

    MINIMO("MINIMO", "Mínimo"),
    MEDIO("MEDIO", "Médio"),
    MAXIMO("MAXIMO", "Máximo"),
    VENCIDO("VENCIDO", "Vencido"),
    TEST("TEST", "TES");

    private final String value;
    private final String descricao;

    private TipoEnum(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public String getValue() {
        return value;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEnum getByValue(String value) {
        for (TipoEnum t : values()) {
            if (t.value.equals(value)) {
                return t;
            }
        }
        return null;
    }

}

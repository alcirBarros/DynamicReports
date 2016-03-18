package exemplo;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;

import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;

import net.sf.dynamicreports.report.constant.BooleanComponentType;

import net.sf.dynamicreports.report.exception.DRException;
import org.templat.Templates;

public class ComponentsReport {

    public ComponentsReport() {

        build();

    }

    private void build() {

        URL image = Templates.class.getResource("images/pda.png");

        try {
            report()
                    .setTemplate(template().setBarcodeHeight(50))
                    .setTextStyle(stl.style(stl.pen1Point()))
                    .title(
                            Templates.createTitleComponent("Components"),
                            components(
                                    "rectangle", cmp.rectangle(),
                                    "round rectangle", cmp.roundRectangle(10),
                                    "ellipse", cmp.ellipse()),
                            cmp.verticalGap(10),
                            components(
                                    "text field", cmp.text("text"),
                                    "image", cmp.image(image).setFixedDimension(30, 30),
                                    "line", cmp.line()),
                            cmp.verticalGap(10),
                            components(
                                    "boolean field", cmp.booleanField(true).setComponentType(BooleanComponentType.IMAGE_CHECKBOX_2).setFixedDimension(20, 20),
                                    "center horizontal", cmp.centerHorizontal(cmp.image(image).setFixedDimension(50, 50)),
                                    "center vertical", cmp.centerVertical(cmp.text("text").setFixedRows(1))),
                            cmp.verticalGap(10),
                            components(
                                    "text field", cmp.text("text"),
                                    "empty space", cmp.filler(),
                                    "text field", cmp.text("text")),
                            cmp.verticalGap(50),
                            cmp.horizontalList(cmp.text("text"), cmp.horizontalGap(100), cmp.text("text")))
                    .show();
        } catch (DRException e) {
            e.printStackTrace();
        }

    }

    private ComponentBuilder<?, ?> components(String label1, ComponentBuilder<?, ?> component1, String label2, ComponentBuilder<?, ?> component2, String label3, ComponentBuilder<?, ?> component3) {

        HorizontalListBuilder list = cmp.horizontalList()
                .setGap(10);

        list.add(component(label1, component1));

        list.add(component(label2, component2));

        list.add(component(label3, component3));

        return list;

    }

    private ComponentBuilder<?, ?> component(String label, ComponentBuilder<?, ?> component) {

        TextFieldBuilder<String> labelField = cmp.text(label)
                .setFixedRows(1)
                .setStyle(Templates.bold12CenteredStyle);

        return cmp.verticalList(labelField, component);

    }

    public static void main(String[] args) {

        new ComponentsReport();

    }
}

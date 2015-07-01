package org.objectFake;

import java.util.Calendar;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static org.style.DynamicReportStyles.*;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import org.interfaces.Gerador;
import org.util.DateUtils;

public class GeradorRodape implements Gerador {

    private Operador operador;
    private final Estabelecimento estabelecimento;

    public GeradorRodape(Operador operador, Estabelecimento estabelecimento) {
        this.operador = operador;
        this.estabelecimento = estabelecimento;
    }

    @Override
    public ComponentBuilder get() {

        VerticalListBuilder content = cmp.verticalList(
                cmp.verticalGap(15),
                cmp.horizontalList(
                        cmp.text("Operador: " + "Nome do Operador Logado").setStyle(fonte10).setHorizontalAlignment(HorizontalAlignment.LEFT).setWidth(250),
                        cmp.text("Unidade: " + "Unidade Selecionada").setStyle(fonte10).setWidth(250),
                        cmp.text(DateUtils.FORMAT_DD_MM_YYYYY_hh_mm_ss.format(Calendar.getInstance().getTime())).setStyle(fonte10_right).setWidth(130),
                        cmp.pageXofY().setFormatExpression("Página {0} / {1}").setStyle(fonte10_right.setAlignment(HorizontalAlignment.LEFT, VerticalAlignment.JUSTIFIED)).setWidth(80)
                )
        );
        return content;
    }

}

package org.objectFake;

import java.io.ByteArrayInputStream;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static org.style.DynamicReportStyles.*;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import org.interfaces.Gerador;
import org.style.DynamicReportStyles;

public class GeradorCabecalho implements Gerador {

    private byte[] img = {};
    private Municipio municipio;
    private String nomeRelatorio;

    public GeradorCabecalho(Municipio municipio, String nomeRelatorio) {
        this.municipio = municipio;
        this.nomeRelatorio = nomeRelatorio;
    }

    @Override
    public HorizontalListBuilder get() {
        return cmp.horizontalList().setStyle(stl.style().setBottomBorder(stl.pen1Point()))
                .add(cmp.image( new ByteArrayInputStream(img) ).setFixedDimension(50, 35),
                        cmp.verticalList()
                        .add(cmp.verticalGap(2),
                                cmp.text("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").setStyle(title1StyleBold),
                                cmp.text("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB").setStyle(title2Style),
                                cmp.text("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC").setStyle(title2Style),
                                cmp.verticalGap(1),
                                cmp.text(nomeRelatorio).setStyle(title1StyleBold),
                                cmp.verticalGap(8)
                        )
                );
    }
}

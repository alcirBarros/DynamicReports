package org.style;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

public class DynamicReportStyles {

    public static final StyleBuilder fonte10 = stl.style().setFontSize(10);
    public static final StyleBuilder fonte10_right = stl.style(fonte10).setAlignment(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE);
    public static final StyleBuilder title1StyleBold = stl.style().setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE).setFontSize(11).bold();
    public static final StyleBuilder title2Style = stl.style().setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE).setFontSize(9);
    public static final StyleBuilder columnPedding = stl.style().setBorder(stl.pen1Point()).setPadding(3);
}

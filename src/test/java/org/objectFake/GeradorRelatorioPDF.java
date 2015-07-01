package org.objectFake;

import java.io.ByteArrayOutputStream;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

public class GeradorRelatorioPDF {

    public GeradorRelatorioPDF(JasperReportBuilder builder, String prefixo) {

    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        return os;
    }
}
package org.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final Calendar referenceCalendar = Calendar.getInstance();
    public static final SimpleDateFormat FORMAT_MM_YYYYY = new SimpleDateFormat("MM/yyyy");
    public static final SimpleDateFormat FORMAT_DD_MM_YYYYY = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat FORMAT_DD_MM_YYYYY_hh_mm = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static final SimpleDateFormat FORMAT_DD_MM_YYYYY_hh_mm_ss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final SimpleDateFormat FORMAT_HH_mm = new SimpleDateFormat("HH:mm");
    public static final String[] MESES = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public static Calendar toCalendar(Date data) {
        if (data == null) {
            return null;
        }
        return org.apache.commons.lang3.time.DateUtils.toCalendar(data);
    }
}

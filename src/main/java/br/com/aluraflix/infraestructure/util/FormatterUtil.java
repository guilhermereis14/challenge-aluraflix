package br.com.aluraflix.infraestructure.util;

import org.apache.logging.log4j.util.Strings;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FormatterUtil {

    public static final String DDMMYYYY_HHmmss = "dd/MM/yyyy HH:mm:ss";

    public static String localDateToString(LocalDateTime localDateTime, String pattern) {
        if (localDateTime != null) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        }
        return Strings.EMPTY;
    }
}

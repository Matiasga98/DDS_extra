package ui.representacion;

import org.apache.commons.collections15.Transformer;
import org.uqbar.arena.bindings.ValueTransformer;
import org.uqbar.arena.filters.TextFilter;
import org.uqbar.arena.widgets.TextInputEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

public class Fecha implements ValueTransformer<LocalDateTime,String> ,Transformer<LocalDateTime, String>, TextFilter {

    private static DateTimeFormatter FECHA_REGEX_1;
    private static DateTimeFormatter FECHA_REGEX_2;
    private static DateTimeFormatter FECHA_REGEX_3;
    static {
        FECHA_REGEX_1 = new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('\\')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('\\')
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .toFormatter();


        FECHA_REGEX_2 = new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('/')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('/')
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .toFormatter();


        FECHA_REGEX_3 = new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .toFormatter();
    }

    public static String REGEX_1 = "[0-9]{2}\\\\[0-9]{2}\\\\[0-9]{4}";
    public static String REGEX_2 = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    public static String REGEX_3 = "[0-9]{2}-[0-9]{2}-[0-9]{4}";

    @Override
    public String transform(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @Override
    public boolean accept(TextInputEvent event) {
        String input = event.getPotentialTextResult();
        return input==null||input.trim().equals("")||esFecha(input);
    }

    private boolean esFecha(String fecha){
        if(fecha == null) return false;
        return fecha.matches(REGEX_1)||fecha.matches(REGEX_2)||fecha.matches(REGEX_3);
    }

    @Override
    public LocalDateTime viewToModel(String valueFromView) {
        LocalTime horaCero = LocalTime.of(0, 0, 0);
        if(valueFromView.matches(REGEX_1))
            return LocalDateTime.of(LocalDate.parse(valueFromView,FECHA_REGEX_1),horaCero);
        else if(valueFromView.matches(REGEX_2))
            return LocalDateTime.of(LocalDate.parse(valueFromView,FECHA_REGEX_2),horaCero);
        else
            return LocalDateTime.of(LocalDate.parse(valueFromView,FECHA_REGEX_3),horaCero);
    }

    @Override
    public String modelToView(LocalDateTime valueFromModel) {
        return valueFromModel.toString();
    }

    @Override
    public Class<LocalDateTime> getModelType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<String> getViewType() {
        return String.class;
    }
}

package javaschool.app;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DateTimeParser {
    private static final List<Function<String, ZonedDateTime>> parsers = Arrays.asList(
            ZonedDateTime::parse,
            (String v) -> ZonedDateTime.ofLocal(LocalDate.parse(v).atStartOfDay(), ZoneId.systemDefault(), null),
            (String v) -> ZonedDateTime.ofLocal(LocalDateTime.of(LocalDate.now(), LocalTime.parse(v)), ZoneId.systemDefault(), null)
    );

    public static ZonedDateTime parse(String s) throws DateTimeParseException {
        ZonedDateTime result = null;
        for (Function<String, ZonedDateTime> parser: parsers) {
            try {
                result = parser.apply(s);
                break;
            } catch (Exception e) {
            }
        }

        if (result == null) {
            throw new DateTimeParseException(null, s, -1);
        }

        return result;
    }
}

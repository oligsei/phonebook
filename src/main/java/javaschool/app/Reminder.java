package javaschool.app;

import asg.cliche.Command;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Reminder extends Note {
    private ZonedDateTime when = null;

    Reminder(String name) {
        super(name);
    }

    ZonedDateTime getWhen() {
        return when;
    }

    @Command
    public void setWhen(String when) {
        final List<Function<String, ZonedDateTime>> parsers = new ArrayList<>();

        parsers.add(ZonedDateTime::parse);
        parsers.add((String v) -> ZonedDateTime.ofLocal(LocalDate.parse(v).atStartOfDay(), ZoneId.systemDefault(), null));
        parsers.add((String v) -> ZonedDateTime.ofLocal(LocalDateTime.of(LocalDate.now(), LocalTime.parse(v)), ZoneId.systemDefault(), null));

        for (Function<String, ZonedDateTime> parser: parsers) {
            try {
                this.when = parser.apply(when);
                break;
            } catch (Exception e) {
            }
        }

//        try {
//            this.when = ZonedDateTime.parse(when);
//        } catch (DateTimeParseException localDateTimeException) {
//            try {
//                this.when = ZonedDateTime.ofLocal(LocalDate.parse(when).atStartOfDay(), ZoneId.systemDefault(), null);
//            } catch (DateTimeParseException localDateException) {
//                try {
//                    this.when = ZonedDateTime.ofLocal(LocalDateTime.of(LocalDate.now(), LocalTime.parse(when)), ZoneId.systemDefault(), null);
//                } catch (DateTimeParseException localTimeException) {
//                }
//            }
//        }
    }

    boolean hasWhen() {
        return when != null;
    }

    @Override
    @Command(abbrev = "i", name = "info", description = "Print reminder\'s information")
    public String toString() {
        String result = super.toString();
        if (hasWhen()) {
            result += String.format("\n     reminder set to: %s", when.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        }
        return result;
    }

    @Override
    protected String getType() {
        return "reminder";
    }
}

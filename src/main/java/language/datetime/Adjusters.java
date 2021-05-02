package language.datetime;

import java.time.*;
import java.time.temporal.*;

public class Adjusters {
    public static class  NextSunday implements TemporalAdjuster {
        public Temporal adjustInto(Temporal temporal) {
            //Check if it is already sunday.
            Integer dayOfWeek = temporal.get(ChronoField.DAY_OF_WEEK);
            if (dayOfWeek == 7) {
                return temporal;
            }
            return temporal.plus(7 - dayOfWeek, ChronoUnit.DAYS);
        }
    }

    public static class NextBirthDay implements  TemporalAdjuster {
        public Temporal adjustInto(Temporal temporal) {
            Integer dobDayOfYear = temporal.get(ChronoField.DAY_OF_YEAR);
            ZonedDateTime now = ZonedDateTime.now(ZoneId.from(temporal));
            if ( now.getDayOfYear() == dobDayOfYear ) {
                return now;
            } else if ( now.getDayOfYear() < dobDayOfYear ) {
                return now.with(TemporalAdjusters.firstDayOfYear()).plus(dobDayOfYear - 1 , ChronoUnit.DAYS);
            } else {
                return now.with(TemporalAdjusters.firstDayOfNextYear()).plus(dobDayOfYear - 1, ChronoUnit.DAYS);
            }
        }
    }
}

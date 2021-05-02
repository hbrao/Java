package language.datetime;

import java.util.*;
import java.time.*;
import java.time.temporal.*;

public class Basics {
    public static void main(String[] args)  throws Exception {
        String  date = "1983-08-16", time = "23:10:50.000000000", sign = "+" , hourOffset = "05", minuteOffset = "30", zone = "Calcutta";
        Optional<ZonedDateTime> zdt = Arrays.stream(
                    TimeZone.getAvailableIDs(
                (sign == "-" ? -1 : 1) * (int) (
                            Duration.ofHours(Long.parseLong(hourOffset)).toMillis() +
                            Duration.ofMinutes(Long.parseLong(minuteOffset)).toMillis()
                        )
                    )
               )
              .filter( zoneId -> zoneId.contains(zone) )
              .findFirst()
              .map( zoneId -> ZonedDateTime.parse( date + "T" + time + sign + hourOffset + ":" + minuteOffset + "[" +zoneId + "]") );

        ZonedDateTime dob = zdt.get();
        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(Clock.systemUTC()), dob.getZone());
        System.out.println("It is now " + now);

        if ( dob.isBefore(now) ) {
            Period p_age = Period.between(dob.toLocalDate(), now.toLocalDate());
            Duration d_age = Duration.between(dob, now);

            System.out.printf("Your age is %d Years %d months %d days %d hours %d minutes %d seconds \n"
                    , p_age.getYears()
                    , p_age.getMonths()
                    , p_age.getDays()
                    , d_age.toHoursPart()
                    , d_age.toMinutesPart()
                    , d_age.toSecondsPart()
            );

            //Calculate time until turning 40 years.
            ZonedDateTime age_40 = null;
            age_40 = dob.plus(40, ChronoUnit.YEARS); //Method 1
            age_40 = dob.plus(Period.ofYears(40)); //Method 2
            age_40 = dob.plus(Duration.ofDays(40 * 365)); //Method 3

            Period p_to_40 = Period.between(now.toLocalDate(), age_40.toLocalDate());
            Duration d_to_40 = Duration.between(now, age_40);

            System.out.printf("Your turn 40 in %d Years %d months %d days %d hours %d minutes %d seconds \n"
                    , p_to_40.getYears()
                    , p_to_40.getMonths()
                    , p_to_40.getDays()
                    , d_to_40.toHoursPart()
                    , d_to_40.toMinutesPart()
                    , d_to_40.toSecondsPart()
            );
        }
    }
}

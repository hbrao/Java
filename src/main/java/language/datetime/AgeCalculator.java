package language.datetime;

import java.time.*;
import java.time.temporal.*;

public class AgeCalculator {
    public static void main(String[] args)  throws Exception {
        String  date = "1983-08-16", time = "14:45:00.000000000", zone = "Asia/Calcutta";

        System.out.printf("%s: Started execution...... \n", Instant.now(Clock.systemUTC()));

        //Get user time zone
        ZoneId userTimeZone = ZoneId.getAvailableZoneIds().stream()
                .filter( id -> id.contains(zone) )
                .findFirst()
                .map( id -> ZoneId.of(id) )
                .get();

        ZonedDateTime dob = LocalDateTime.parse(date+ "T" + time).atZone(userTimeZone);
        ZonedDateTime now = ZonedDateTime.now(userTimeZone);
        if ( dob != null && dob.isBefore(now) ) {
            System.out.printf("%s: Your dob is %s \n" ,Instant.now(Clock.systemUTC()), dob);
            System.out.printf("%s: It is now %s \n" ,Instant.now(Clock.systemUTC()) , now);
            Period p_age = Period.between(dob.toLocalDate(), now.toLocalDate());
            Duration d_age = Duration.between(dob, now);

            System.out.printf("%s: You born on %s\n", Instant.now(Clock.systemUTC()), dob.getDayOfWeek());

            System.out.printf("%s: Your age is %d Years %d months %d days %d hours %d minutes %d seconds \n"
                    , Instant.now(Clock.systemUTC())
                    , p_age.getYears()
                    , p_age.getMonths()
                    , p_age.getDays()
                    , d_age.toHoursPart()
                    , d_age.toMinutesPart()
                    , d_age.toSecondsPart()
            );

            //Info about next birthday
            ZonedDateTime nextBirthday = dob.with(new Adjusters.NextBirthDay());
            System.out.printf("%s: Your next birthday %s is on %s \n"
                    , Instant.now(Clock.systemUTC())
                    , nextBirthday
                    , nextBirthday.getDayOfWeek());
            System.out.printf("%s: Next holiday to celebrate your birthday %s \n"
                    , Instant.now(Clock.systemUTC())
                    , nextBirthday.with(new Adjusters.NextSunday()));

            //Calculate time until turning 40 years.
            ZonedDateTime age_40 = null;
            age_40 = dob.plus(40, ChronoUnit.YEARS); //Method 1
            age_40 = dob.plus(Period.ofYears(40)); //Method 2
            age_40 = dob.plus(Duration.ofDays(40 * 365)); //Method 3

            Period p_to_40 = Period.between(now.toLocalDate(), age_40.toLocalDate());
            Duration d_to_40 = Duration.between(now, age_40);

            System.out.printf("%s: You turn 40 in %d Years %d months %d days %d hours %d minutes %d seconds \n"
                    , Instant.now(Clock.systemUTC())
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

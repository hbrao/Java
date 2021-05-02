package problems.project03;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

    }

    public static Long findNextAvailableTime(List<User > users, Integer meetingDuration) {
        Long nextAvailableTime = System.currentTimeMillis();

        while ( true ) {
            Boolean available = true;
            for( User u : users ) {
                available = available && u.isAvailable(nextAvailableTime , meetingDuration);
                if ( ! available ) break;
            }
            if ( available ) return nextAvailableTime;
            else nextAvailableTime += 1000 * 60;
        }
    }


    private static class User {
        private SortedSet<Meeting> meetings = new TreeSet<>();

        public Boolean isAvailable(Long meetingTime, Integer duration ) {

            for( Meeting m : meetings ) {
                Long finishTime = meetingTime + duration;

                //Check if there is any overlap.
                if ( m.startTime >= meetingTime || finishTime <= ( m.startTime + m.duration ) ) {
                    return false;
                }

                //Exit
                if ( m.startTime > finishTime  ) {
                    return true;
                }
            }

            return true;
        }
    }

    private static class Meeting {
        public Long startTime;
        public Integer duration;
    }
}


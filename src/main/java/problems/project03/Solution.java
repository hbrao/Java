package problems.project03;

import java.util.*;

class Solution {

    public static void main(String[] args) {

    }

    public static Long findNextAvailableTime(List<User> users, Integer meetingDuration) {
        Long nextAvailableTime = System.currentTimeMillis();

        SortedMap<Long,Integer> conflicts = null;
        Integer i =0, availableUsers = 0;
        while ( availableUsers < users.size() ) {
            if ( i == users.size() ) i = 0;
            User u = users.get(i);
            conflicts = u.isAvailable(nextAvailableTime, meetingDuration);
            if ( ! conflicts.isEmpty() ) {
                nextAvailableTime += conflicts.firstKey() + conflicts.get(conflicts.firstKey());
                availableUsers = 0;
            } else {
                i += 1;
                availableUsers += 1;
            }
        }
        return nextAvailableTime;
    }


    private static class User {
        private SortedMap<Long,Integer> meetings = new TreeMap<>();

        public SortedMap<Long,Integer> isAvailable(Long meetingTime, Integer duration ) {
            return meetings.subMap(meetingTime, meetingTime + duration);
        }
    }
}


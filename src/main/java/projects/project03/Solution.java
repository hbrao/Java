package projects.project03;

import java.util.*;

class Solution {

    public static void main(String[] args) {

    }

    public static Long findNextAvailableTime(List<User> users, Integer meetingDuration) {
        Long nextAvailableTime = System.currentTimeMillis();

        Integer i =0, availableUsers = 0;
        while ( availableUsers < users.size() ) {
            if ( i == users.size() ) i = 0;
            User u = users.get(i);
            Long nxtMeetingFinishTime = u.isAvailable(nextAvailableTime, meetingDuration);
            if ( nxtMeetingFinishTime != -1L ) { // Conflict
                nextAvailableTime = nxtMeetingFinishTime;
                availableUsers = 0;
            } else {
                i += 1;
                availableUsers += 1;
            }
        }
        return nextAvailableTime;
    }


    private static class User {
        //Finish Time, Duration
        private NavigableMap<Long,Integer> meetings = new TreeMap<>();

        public Long isAvailable(Long meetingTime, Integer duration ) {
            Map.Entry<Long, Integer> nxtMtg = meetings.higherEntry(meetingTime);
            Long nxtMtgStartTime = nxtMtg.getKey() - nxtMtg.getValue();
            if ( nxtMtgStartTime > meetingTime + duration) {
                return -1L;
            } else {
                return nxtMtg.getKey();
            }
        }
    }
}


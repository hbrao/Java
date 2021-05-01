package language.numbers;

import java.util.*;

class Statistics {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Statistics().sampleStats(
           new int[] {
                   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                   0,0,3510,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6718,
                   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,27870,0,0,0,0,0,0,0,0,0,0,
                   0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,35,0,0,0,0,0,
                   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                   0,0,26256,0,0,0,0,9586565,0,0,0,0,0,0,0,2333,0,0,0,0
              }
        )));

        //Expected [32.00000,251.00000,242.40435,243.00000,243.00000]
    }

    public double[] sampleStats(int[] count) {

        int min_elem = -1;
        int max_elem = -1;

        int num_values = 0;
        double val_total = 0;

        int mode = -1 ;
        int max_freq = -1;

        for(int i = 0 ; i < count.length ; i ++ ) {
            int freq = count[i];
            if ( freq  !=  0 ) {
                if ( min_elem == -1 ) min_elem  = i;
                if ( max_elem == -1 || max_elem < i ) max_elem = i ;

                val_total += (double) ( i * freq );
                System.out.println(val_total);
                num_values += freq;

                if ( mode == -1 || freq > max_freq ) {
                    mode = i;
                    max_freq = freq;
                }
            }
        }

        double mean = val_total / num_values;

        double median = 0.0;
        int k = num_values / 2;
        if ( num_values % 2 == 0 ) {
            Integer k1 = helperGetKthElem(count, k - 1);
            Integer k2 = helperGetKthElem(count, k);
            median = (float) ( k1 + k2 ) / 2 ;
        } else {
            median = helperGetKthElem(count, k);
        }

        double[] stats = new double[] { min_elem, max_elem, mean, median, mode } ;

        return stats;
    }

    public static Integer helperGetKthElem(int[] count, Integer k ) {
        Integer i = 0;
        Integer prev = null;
        for (Integer j = 0; j < count.length; j++) {
            if ( count[j] != 0 ) prev = j;
            if (k + 1 <= (i + count[j])) {
                break;
            } else {
                i += count[j];
            }
        }
        if (prev != null)
            return prev;
        else
            return -1;
    }
}


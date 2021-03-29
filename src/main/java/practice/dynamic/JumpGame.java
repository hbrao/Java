package practice.dynamic;

class JumpGame {

    enum Index {
        GOOD, UNKNOWN
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[] { 3, 2, 1, 0, 2, 1}));
        System.out.println(canJump(new int[] { 3, 2, 2, 0, 2, 1}));
    }

    public static boolean canJump(int[] nums) {
        //Initialize memory.
        Index[] mem = new Index[nums.length];
        for( Integer i = 0; i < nums.length ; i ++ ) {
            mem[i] = Index.UNKNOWN;
        }
        //Set last index as good index.
        mem[nums.length -1] = Index.GOOD;

        //Traverse array end to start.
        for( Integer i = nums.length - 2; i >= 0 ; i -- ) {
            Integer furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (Integer j = i + 1; j <= furthestJump; j ++ ) {
                if( mem[j] == Index.GOOD) {
                    //Memorize the index i as good index.
                    mem[i] = Index.GOOD;
                    break;
                }
            }
        }

        return mem[0] == Index.GOOD;
    }
}
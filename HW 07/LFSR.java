/**
 * Name : Hector Herrera
 * PennKey : Hectorh
 * Recitation : 216
 * 
 * Execution: java LFSR
 * 
 * This program will create a linear feedback shift register (LFSR). An LFSR 
 * is a structure that can produce a stream of pseudo-random bits.
 * 
 */

public class LFSR {
    private int [] seedIntArr;  //int array to represent shift register
    private int tapPosition;    //the tapped position
    private int leastSig;       //the least significant bit
    
    /**
     * Description: constructor that creates a LFSR using a string and a tap
     * position 
     * 
     * Input: a string of 0s and 1s and an int representing tap position
     * 
     * Output: Creates a LFSR in the form of an int array
     */
    public LFSR(String seed, int tapPosition) {
        //safety check to see if seed is null
        if (seed == null) {
            throw new RuntimeException("ERROR: Seed is null");
        }
        //safety checks if tapPosition is an imposible position in register
        if (tapPosition >= seed.length() || tapPosition < 0) {
            throw new RuntimeException("ERROR: tapPosition refers to an " +
                                       "imposible position");
        }
        //saftey checks if the seed has characters of 0's and 1's
        for (int i = 0; i < seed.length(); i++) {
            if (!(seed.charAt(i) == '1' || seed.charAt(i) == '0')) {
                throw new RuntimeException("ERROR: Seed contains numbers " + 
                                            "other than 0 and 1");
            }
        }
        
        //update tapPosition;
        this.tapPosition = tapPosition;
        
        //initialize array
        seedIntArr = new int[seed.length()];
        
        //convert string to an int array
        for (int i = 0; i < seed.length(); i++) {
            int value = (int) (seed.charAt(i) - 48);
            seedIntArr[i] = value;
        }
    }
    
    /**
     * Description: constructor that creates a LFSR by generating a random
     * seed
     * 
     * Input: an int representing seedlength and an int representing tap 
     * position
     * 
     * Output: Creates a LFSR in the form of an int array by generating a 
     * random seed
     */
    public LFSR(int seedLength, int tapPosition) {
        //safety check to see if seedLength is an impossible number
        if (seedLength <= 0) {
            throw new RuntimeException("ERROR: Seed has invalid length");
        } 
        //safety check to see if tapPosition is an impossible number
        else if (tapPosition >= seedLength || tapPosition < 0) {
            throw new RuntimeException("ERROR: tapPosition refers to an " +
                                       "imposible position");
        } else {
            ////update tap position;
            this.tapPosition = tapPosition;
            
            //initialize seed int array
            seedIntArr = new int[seedLength];
            
            //randomly fill seed int array with 0s and 1s
            for (int i = 0; i < seedLength; i++) {
                double random = Math.random();
                
                //will randomly put in 1s or 0s
                if (random < .5) seedIntArr[i] = 0;
                else seedIntArr[i] = 1;
            }
        }
    }
    
    /**
     * Description: returns the current bit sequence in the shift register as 
     * a String of 1s and 0s
     * 
     * Input: none
     * 
     * Output: a string representation of the LFSR
     */
    public String toString() {
        //create a string 
        String temp = "";
        
        //iterate through the string to fill it with values
        for (int i = 0; i < seedIntArr.length; i++) {
            temp += (char) (seedIntArr[i] + 48);
        }
        return temp;
    }
    
    
    /**
     * Description: returns the tap position, as given by the constructor
     * 
     * Input: none
     * 
     * Output: an int representing the tap position
     */
    public int getTapPosition() {
        return tapPosition;
    }
    
    /**
     * Description: Shift all bits by one place to the left and will replace 
     * the least significant bit with the XOR of the most significant bit and  
     * the bit previously in the tap position. 
     * 
     * Input: none
     * 
     * Output: an int representing least significant bit after the shift 
     * register
     */
    public int nextBit() {
        //initialize and declare the variables
        leastSig = seedIntArr[seedIntArr.length - 1];
        int tapped = seedIntArr.length - 1 - tapPosition;
        
        //XOR of tapped position and most significant bit
        leastSig = seedIntArr[tapped] ^ seedIntArr[0];
        
        //performs the shift register
        for (int i = 0; i < seedIntArr.length - 1; i++) {
            seedIntArr[i] = seedIntArr[i + 1];
            
        }
        
        //updates the least significant bit
        seedIntArr[seedIntArr.length - 1] = leastSig;
        
        //returns the least significant bit
        return leastSig;
    }
    
    public static void main(String [] args) {
        //TEST CODE
        /*LFSR lfsr = new LFSR("01101000010", 8);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr.nextBit();
            System.out.println(lfsr.toString() + " " + bit);
        }
        // LFSR lfsr = new LFSR("011", 2);
        //System.out.print(lfsr.getTapPosition());
        //LFSR random = new LFSR(9, 3);
        //System.out.print(ranom.getTapPosition());
        //System.out.println(lfsr.toString());
        //System.out.println(random.toString()); */  
    }
}
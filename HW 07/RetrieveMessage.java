/**
 * Name : Hector Herrera
 * PennKey : Hectorh
 * Recitation : 216
 * 
 * Execution: java RetrieveMessage
 * 
 * This program will load the specified image, extract the embedded cipher, 
 * and print out the decrypted message.
 */
public class RetrieveMessage {
    public static void main(String[] args) {
        //take in a filename
        int [][] arr = ImageData.load(args[0]);
        
        //create and declare variables
        int hold;
        int count = 0;
        int loop = arr.length * arr[arr.length - 1].length;
         int stopPrinting = 0;
        
        //int array to hold the least significant bits
        int [] newArr = new int [loop - loop % 7];
        
        //iterate through the 2D array to see if the last bit is even or odd
        for (int i = 0; i < arr.length; i++) {
            for (int z = 0; z < arr[i].length; z++) {
                //stops at the furthest when the array is a multiple of 7 
                if (count < loop - loop % 7) {
                    //retrieves the least significant bits
                    if (arr[i][z] % 2 == 0) hold = 0;
                    else hold = 1;
                    
                    //places the least significant bits into the array
                    newArr[count] = hold;
                    count++;   
                }
                else break;
            }
        }

        //checks to see if decrpyt will be needed
        if (args.length > 1) {
            //take in the seed for the LFSR as a string
            String seed = args[1];
            
            //take the tap position for the LFSR as an int
            int tapPosition = Integer.parseInt(args[2]);
            
            //decrypt the message
            Codec.decrypt(newArr, seed, tapPosition);
        }
        
        String str = Codec.decode(newArr);
        String message = "";
        
        //iterates through the message to find the Null character
        for (int i = 0; i < str.length(); i++) {
            if ((int) str.charAt(i) == 0) break;
            else message += str.charAt(i);
        }
        System.out.print(message);
    }
}
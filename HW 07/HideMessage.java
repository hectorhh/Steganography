/**
 * Name : Hector Herrera
 * PennKey : Hectorh
 * Recitation : 216
 * 
 * Execution: java HideMessage
 * 
 * This program will load the specified image, get the message, encode it and 
 * embed it into the image.
 */
public class HideMessage {
    public static void main(String [] args) {
        //get the file and create the 2d array
        int [][] img = ImageData.load(args[0]);
        
        //get the message
        String file = args[1];
        In inStream = new In(file);
        String message = inStream.readAll();
        
        //saftey checks that the message is not too long for the image
        if (message.length() * 7 > img.length * img[0].length) {
            throw new RuntimeException("ERROR: message is too long for the " + 
                                       "image");
        }
        
        //null terminate the message
        message += (char) 0;
        
        //encode the message 
        int [] encoded = Codec.encode(message);
        
        //encrypt the message if provided
        if (args.length > 2) {
            //seed for LFSR as a string of 0 and 1
            String seed = args[2];
            
            //tap position for the LFSR
            int tapPosition = Integer.parseInt(args[3]);
            
            //encrypt the message
            Codec.encrypt(encoded, seed, tapPosition);  
        }
        
        //will act as a variable to hold the value of the number of iterations
        int count = 0;
        
        //embed the message into the image
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                if (count < encoded.length) {
                    
                    if (img[i][j] % 2 == 1 && encoded[count] == 0) {
                        img[i][j] -= 1;
                    }
                    if (encoded[count] == 1 && img[i][j] % 2 == 0) {  
                        img[i][j] += 1;
                    }
                    count++; 
                }
                else break;
            }
        }
        //display the string
        ImageData.show(img);  
    }
}
/*
* @Author: Andrew Alleyne
* @University: CUNY Queens College
* The goal of this project is to identify the different MIPS instructions from the
* given hexadecimal digits.
*/
public class Main {


    private static void fromHexToBinary(StringBuilder stringBuilder, String userInput, int hexLength) {
        /*Convert each of the hexadecimal field into binary fields.*/
        while(hexLength != userInput.length()){

            //Get the first character
            char characterPos = userInput.charAt(hexLength);

            //Convert from character to binary string
            String binaryOutput =  Integer.toBinaryString(Integer.parseInt(Character.toString(characterPos)));
            String padding = "";


            for(int i = 0; i < binaryOutput.length(); i++){

                padding = buildBinaryNumber(i,binaryOutput);

            }
//            System.out.print(binaryOutput.length() + "-BO  \t");
//             System.out.println(padding  + ", ");

            stringBuilder.append(padding);
            hexLength++;
        }
    }

    /* Builds the binary number from the hexadecimal output
    * and pads it with needed 0's
    * */

    private static String buildBinaryNumber(int i, String binaryOutput) {

        StringBuilder sb = new StringBuilder(binaryOutput);
        String sbReturn = "";

        // Pad the binary output with the necessary zeros.
        while(sb.length() < 4){
            sb.insert(0,"0");
            sbReturn = sb.toString();
        }
        return sbReturn;
    }

    public static void main(String[] args) {

        //We need to scan through all the numbers in the hexadecimal digits.
        int hexLength = 0;

        //Hexadecimal input give through command line argument or inline
        String userInput  = "";

        //Needed to construct final decoding of hexadecimal to binary
        StringBuilder stringBuilder = new StringBuilder();


        //User has option of running in line input as well as command line
        if(args.length < 0){
            System.out.println("Missing hexadecimal input for decoding.");
            userInput = args[0];
        }else {
            userInput = "02324022";
        }

        //Takes user input and decodes in into a binary using toBinary from the Integer wrapper
        fromHexToBinary(stringBuilder,userInput, hexLength);


        System.out.println(stringBuilder);

        /*Parse stringBuilder a separate different fields
        * opCode --> rs --> rt --> rd --> shift_amt --> funcCode
        * 6          5      5      5      5             6
        */

        delimiter(stringBuilder);
    }

    private static void delimiter(StringBuilder stringBuilder) {
        int fieldBits = 0;

        StringBuilder buildFields = new StringBuilder();

        for( int i = 0; i <= 5; i ++){

            switch (i){
                case 0:
                    while(fieldBits <= 5){
                        buildFields.append(stringBuilder.charAt(fieldBits));
                        fieldBits++;
                    }
                    System.out.println(fieldBits);

                    detectInstruction(buildFields);
                    buildFields.delete(0,5);
                    break;

                case 1:
                    fieldBits = 6;
                    while(fieldBits < 11){
                        buildFields.append(stringBuilder.charAt(fieldBits));
                        fieldBits++;
                    }
                    System.out.println(fieldBits);
                    detectInstruction(buildFields);
                    buildFields.delete(0,11);

                    break;

                case 2:
                    fieldBits = 11;
                    while(fieldBits < 17){
                        buildFields.append(stringBuilder.charAt(fieldBits));
                        fieldBits++;
                    }
                    System.out.println(fieldBits);
                    detectInstruction(buildFields);
                    break;

                    case 3:
                    fieldBits = 16;
                    while(fieldBits < 23){
                        buildFields.append(stringBuilder.charAt(fieldBits));
                        fieldBits++;
                    }
                    System.out.println(fieldBits);
                    detectInstruction(buildFields);
                    break;

            }

        }
    }

    private static void detectInstruction(StringBuilder  buildFields) {
        System.out.print(buildFields + " ");


    }
}

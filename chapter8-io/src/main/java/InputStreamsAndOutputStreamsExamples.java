import java.io.*;

public class InputStreamsAndOutputStreamsExamples {

    public static void main(String[] args) throws IOException {
        readSomeBytes();
        copyUsingSimpleISAnOS();
        copyUsingBISAndBOS();
    }

    private static void readSomeBytes() throws IOException {
        byte[] bytes = "TIGERS".getBytes();

        try (ByteArrayInputStream stream = new ByteArrayInputStream(bytes)) {


//            System.out.println((char) stream.read());
//            System.out.println((char) stream.read());
//            System.out.println((char) stream.read());
//            System.out.println((char) stream.read());
//            System.out.println((char) stream.read());
//            System.out.println((char) stream.read());


            byte[] buffer = new byte[8];
            System.out.println(stream.read(buffer));

            for (byte b : buffer) {
                System.out.print((char) b);
            }

            System.out.println();
        }
    }

    private static void copyUsingBISAndBOS() throws IOException {
        System.out.println();
        System.out.println("*******************");
        System.out.println();

        File source = new File("source.txt");
        File secondCopy = new File("secondCopy.txt");
        if (secondCopy.exists()) {
            secondCopy.delete();
        }
        secondCopy.createNewFile();

        try (FileInputStream is = new FileInputStream(source);
             BufferedInputStream bis = new BufferedInputStream(is);
             OutputStream os = new FileOutputStream(secondCopy);
             BufferedOutputStream bos = new BufferedOutputStream(os)) {

            byte[] buffer = new byte[35];
            int lengthRead;

            while ((lengthRead = bis.read(buffer)) > 0) {
                System.out.println(lengthRead);
                bos.write(buffer, 0, lengthRead);
                bos.flush();
                for (int i = 0; i < buffer.length; i++) {
                    System.out.print((char) buffer[i]); // this also prints old information from the array (see page 422 in the book -> orange)
                }
                System.out.println();
            }
        }
    }

    private static void copyUsingSimpleISAnOS() throws IOException {
        System.out.println();
        System.out.println("*******************");
        System.out.println();

        File source = new File("source.txt");
        File firstCopy = new File("firstCopy.txt");
        if (firstCopy.exists()) {
            firstCopy.delete();
        }
        firstCopy.createNewFile();

        try (FileInputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(firstCopy)) {

            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
    }
}

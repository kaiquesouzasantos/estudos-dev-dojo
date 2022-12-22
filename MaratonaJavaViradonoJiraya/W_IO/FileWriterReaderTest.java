package W_IO;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterReaderTest {
    public static void main(String[] args) {
        File file = new File("Arquivo.txt");

        try (
                FileWriter fw = new FileWriter(file);
                FileReader fr = new FileReader(file)
        ) {
            fw.write("Escrevendo uma mensagem no arquivo\nE pulando uma linha 3");
            fw.flush();

            char[] in = new char[500];
            int size = fr.read(in);
            System.out.println("tamanho " + size);

            for (char c : in) {
                System.out.print(c);
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}

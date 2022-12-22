package W_IO;
import java.io.File;
import java.io.IOException;

public class FileDiretorioTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\GCUIT\\Google Drive\\GCU\\Teste");

        String[] list = file.list();
        for (String arquivo : list) {
            System.out.println(arquivo);
        }
    }
}



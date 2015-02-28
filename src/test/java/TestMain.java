import java.io.File;
import java.io.IOException;


public class TestMain {
    public static void main(String[] args) throws IOException {
       File ff =  new File("~/Develop/未命名.txt");
       System.out.println(ff.exists());
       System.out.println(ff.isDirectory());
       System.out.println(ff.isFile());
       ff.createNewFile();
    }
}

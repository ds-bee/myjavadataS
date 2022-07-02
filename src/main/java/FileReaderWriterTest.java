import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderWriterTest {

    @Test
    public void testFileReader() throws IOException {
        File file = new File("helloWorld.txt");//相较于当前工程的路径
        System.out.println(file.getAbsolutePath());
        //实例化file对象
        //提供具体的流
        FileReader fileReader = new FileReader(file);

        //数据的读入过程
        //read方法返回读入的一个字符，如果到达文件末尾，返回-1
        int data = fileReader.read();//字符对应asc码值
        while (data != -1){
            System.out.print((char)data);
            data = fileReader.read();
        }
        //关闭流
        fileReader.close();

    }


}

import com.obj.TestNormalObject;
import org.junit.Test;

import java.io.*;

public class TestObjectStreamClass {

    //反序列化过程
    @Test
    public void  TestObjectInputStreamMethod(){
        File file = new File("1.dat");
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            String str = (String) objectInputStream.readObject();
            char[] dataArr = (char[])objectInputStream.readObject();

            System.out.println(str);
            System.out.println(dataArr);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //序列化过程
    @Test
    public void TestObjectOutputStreamMethod(){
        ObjectOutputStream objectOutputStream = null;
        try {
            File file = new File("1.dat");
            FileOutputStream outputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(new String("fufufu"));
            //objectOutputStream.writeObject(new TestNormalObject("UU", 66));
            objectOutputStream.writeObject(new char[]{'a','先','2'});



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

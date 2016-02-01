package IOUtils;


import objects.ObjectBase;

import java.io.*;

/**
 * Created by Omer on 1/30/2016.
 */
public class ObjectIO {
    public static void writeObject(String path, Object object){
        System.out.println(path);
        File file = new File(path + ".enjo");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static ObjectBase readObject(String path){
        ObjectBase objectBase = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            objectBase = (ObjectBase) objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectBase;
    }
}

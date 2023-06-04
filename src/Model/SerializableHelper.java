package Model;

import java.io.*;
//https://www.digitalocean.com/community/tutorials/serialization-in-java

public class SerializableHelper {
    // deserialize to Object from given file
    public static Object deserialize(final String theFileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(theFileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    // serialize the given object and save it to file
    public static void serialize(final Object theObj, final  String theFileName)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(theFileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(theObj);
        fos.close();
    }
    public static void serializeToFile(final Maze theMaze, final String theFileName) {
        try {
            serialize(theMaze, theFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    public static Maze deserializeFromFile(final String theFileName) {
        Maze savedMaze = null;
        try {
            savedMaze = (Maze) deserialize(theFileName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return savedMaze;
    }
}


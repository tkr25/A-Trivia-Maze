package Controller;

import Model.Maze;

import java.io.*;
//https://www.digitalocean.com/community/tutorials/serialization-in-java

public class SerializableHelper {
    // deserialize to Object from given file
    public static Object deserialize(String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    // serialize the given object and save it to file
    public static void serialize(Object obj, String fileName)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

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
    public static void deserializeFromFile(final String theFileName) {

        Maze savedMaze = null;
        try {
            savedMaze = (Maze) deserialize(theFileName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}


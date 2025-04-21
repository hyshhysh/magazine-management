package helper;

import model.MagazineModel;

import java.io.*;

public class FileHandler {

    public static void serializeMagazineModel(MagazineModel magazineModel, String filename) {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename))) {
            objOut.writeObject(magazineModel);
            System.out.println("MagazineModel has been serialized to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    public static MagazineModel deserializeMagazineModel(String filename) {
        MagazineModel magazineModel = null;
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))) {
            magazineModel = (MagazineModel) objIn.readObject();
            System.out.println("MagazineModel has been deserialized from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return magazineModel;
    }



} // END OF FileHandler CLASS

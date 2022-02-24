package com.group15A;

import com.group15A.DataModel.Patient;

import javax.swing.*;
import java.io.*;
import java.util.jar.JarFile;

/**
 * The session stores the logged in patient and other useful information. It can be easily saved and loaded from a file.
 *
 * @author Andrei
 */
public class Session implements Serializable {
    private Patient loggedInPatient = null;
    private boolean keepLoggedIn = false;

    public Session(Patient patient, boolean keepLoggedIn)
    {
        loggedInPatient = patient;
        this.keepLoggedIn = keepLoggedIn;
    }

    public Patient getLoggedInPatient() {
        return loggedInPatient;
    }

    public boolean isKeepLoggedIn() {
        return keepLoggedIn;
    }

    /**
     * Save the session to disk
     */
    public void saveToFile()
    {
        var fileName = getFileName();
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName, false)))
        {
            outStream.writeObject(this);
        } catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Load the session from disk
     * @return The session. If the session was not found, return null
     */
    public static Session loadFromFile()
    {
        var fileName = getFileName();
        try(ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            return (Session) inStream.readObject();
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Get the file name + path for the session save location
     * @return The file name + path
     */
    private static String getFileName()
    {
        File directory = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString());
        if(!directory.exists())
            directory.mkdir();

        return directory.toString() + "/LoggedUser.bin";
    }
}

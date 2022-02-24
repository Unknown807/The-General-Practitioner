import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Certification;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DataAccessTest extends TestCase {

    private DataAccess dataAccess;

    @Override
    protected void setUp() {
        try {
            super.setUp();
        } catch (Exception e) {
            System.err.println("Error setting up the test");
        }
        try {
            dataAccess = new DataAccess();
        } catch (DatabaseException e) {
            System.err.println(e.getMessage());
        }
    }


    @Test
    public void testCreatePatient()
    {
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            Patient patientFromDb = dataAccess.registerPatient(patient, doctor);

            //See if the patient in the database is the same as the one created earlier
            assertEquals(patient, patientFromDb);

            //Delete the dummy data from the database
            dataAccess.deletePatient(patientFromDb.getPatientID());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void testGetPatient()
    {
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            patient = dataAccess.registerPatient(patient, doctor);

            //Retrieve the patient from the database by id and by email
            Patient patientById = dataAccess.getPatient(patient.getPatientID());
            Patient patientByEmail = dataAccess.getPatient(patient.getEmail(), patient.getPassHash());

            //Check if the patient retrieved by id is the same as the one retrieved by email and password
            assertEquals(patientById, patientByEmail);

            //Delete the dummy data from the database
            dataAccess.deletePatient(patient.getPatientID());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void testUpdatePatient()
    {
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            Patient originalPatient = dataAccess.registerPatient(patient, doctor);
            patient = dataAccess.getPatient(originalPatient.getPatientID());

}
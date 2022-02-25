import com.group15A.CustomExceptions.CustomException;
import com.group15A.CustomExceptions.DatabaseException;
import com.group15A.CustomExceptions.EmailInUseException;
import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Certification;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Data Access Layer
 *
 * @author Andrei
 */
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
        Patient patientFromDb = null;
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            patientFromDb = dataAccess.registerPatient(patient, doctor);

            //See if the patient in the database is the same as the one created earlier
            assertEquals(patient, patientFromDb);
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        } finally {
            //Delete the dummy data from the database
            try {
                if(patientFromDb!=null)
                    dataAccess.deletePatient(patientFromDb.getPatientID());
            } catch (DatabaseException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Test
    public void testCreatePatientExistingEmail()
    {
        Patient patientFromDb = null;
        Patient secondPatient = null;
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            patientFromDb = dataAccess.registerPatient(patient, doctor);

            //Try registering the same patient
            secondPatient = dataAccess.registerPatient(patient, doctor);

        } catch(EmailInUseException ex) {
            //An EmailInUseException is expected
            assertTrue(true);
        } catch (Exception ex) {
            fail();
        } finally {
            //Delete the dummy data from the database
            try {
                if(patientFromDb!=null)
                    dataAccess.deletePatient(patientFromDb.getPatientID());
                if(secondPatient!=null)
                    dataAccess.deletePatient(secondPatient.getPatientID());
            } catch (DatabaseException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Test
    public void testCreatePatientNullEmail()
    {
        Patient patient = new Patient(null, "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
        assertTrue(testCreatePatientNullInfo(patient));
    }

    private boolean testCreatePatientNullInfo(Patient patient)
    {
        Patient patientFromDb = null;
        try {
            //Create a new patient
            Doctor doctor = dataAccess.getDoctors().get(0);
            patientFromDb = dataAccess.registerPatient(patient, doctor);
        } catch (Exception ex) {
            //We expect an exception
            try {
                if(patientFromDb!=null)
                    dataAccess.deletePatient(patientFromDb.getPatientID());
            } catch (DatabaseException dbEx) {
                System.err.println(dbEx.getMessage());
            }

            return true;
        } finally {
            //Delete the dummy data from the database
            try {
                if(patientFromDb!=null)
                    dataAccess.deletePatient(patientFromDb.getPatientID());
            } catch (DatabaseException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return false;
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

            //Modify the patient
            patient.setMiddleName("MiddleTest");
            patient = dataAccess.updatePatient(patient);

            //Check that the patient from the database is different from the original database
            assertNotEquals(patient, originalPatient);

            //Check if modifying the originalPatient to the new information will make the patient one and the same
            originalPatient.setMiddleName("MiddleTest");
            assertEquals(patient, originalPatient);

            //Delete the dummy data from the database
            dataAccess.deletePatient(originalPatient.getPatientID());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void testChangeDoctor()
    {
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            patient = dataAccess.registerPatient(patient, doctor);
            Doctor originalDoctor = dataAccess.getDoctor(patient);

            //Change the doctor
            doctor = dataAccess.getDoctors().get(1);
            patient = dataAccess.changeDoctor(patient, doctor);

            //Check that the new doctor is different from the original doctor
            assertNotEquals(doctor, originalDoctor);

            //Delete the dummy data from the database
            dataAccess.deletePatient(patient.getPatientID());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void testGetDoctor()
    {
        try
        {
            //Create a new patient
            Patient patient = new Patient("mynewmail1@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            patient = dataAccess.registerPatient(patient, doctor);

            //Retrieve the doctor from the database by patient and by id
            Doctor doctorByPatient = dataAccess.getDoctor(patient);
            Doctor doctorById = dataAccess.getDoctor(doctor.getDoctorID());

            //Check if the patient retrieved by id is the same as the one retrieved by email and password
            assertEquals(doctorById, doctorByPatient);

            //Delete the dummy data from the database
            dataAccess.deletePatient(patient.getPatientID());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }
}
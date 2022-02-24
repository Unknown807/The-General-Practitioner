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
            Patient patient = new Patient("mynewmail3@mail.com", "myPass", "Test", null, "Testing", new Date(), "Male", "08858271");
            Doctor doctor = dataAccess.getDoctors().get(0);
            Patient patientFromDb = dataAccess.registerPatient(patient, doctor);
            assertEquals(patient, patientFromDb);

            var patientByEmailAndPass = dataAccess.getPatient(patient.getEmail(), patient.getPassHash());
            assertEquals(patient, patientByEmailAndPass);
            dataAccess.deletePatient(patientFromDb.getPatientID());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }


}
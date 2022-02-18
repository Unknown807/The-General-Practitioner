import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Certification;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DataAccessTest{

    //TODO Mock database for proper unit testing
    @Test
    public void getDoctorsTest()
    {
        try {
            DataAccess access = new DataAccess();
            var doctors = access.getDoctors();
            for(var doctor: doctors)
                System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
        } catch (Exception e) {
            System.err.println("Failed to query the database.");
        }
    }

    @Test
    public void getPatientTest()
    {
        try{
            DataAccess access = new DataAccess();
            Patient patient = access.getPatient("superemail@hotmail.com", "superPass");
            System.out.println(patient.getFirstName());
        }catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }


    /*@Test
    public void registerPatientTest()
    {
        try{
            DataAccess access = new DataAccess();
            Patient patient = access.registerPatient(new Patient("test@test.com", "testPass", "Johhny", null, "Depp", new Date(), "Male", "0859565785"));
            assertEquals(patient.getPatientID(), access.getPatient(patient.getEmail(), patient.getPassHash()).getPatientID());
        }catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }*/

    @Test
    public void updatePatientTest()
    {
        try{
            DataAccess access = new DataAccess();
            Patient patient = access.getPatient("test@test.com", "testPass");
            patient.setMiddleName("Christopher");
            patient = access.updatePatient(patient);
            assertEquals(patient.getMiddleName(), access.getPatient(patient.getEmail(), patient.getPassHash()).getMiddleName());
        }catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void getCertificationsTest()
    {
        try{
            DataAccess access = new DataAccess();
            Doctor doctor = access.getDoctors().get(0);
            var certs = access.getCertifications(doctor);
            for(var cert: certs)
                System.out.println(cert.getName() + " " + cert.getField());
        }catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}

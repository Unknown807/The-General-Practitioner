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
    public void getDoctorsTest() throws Exception {
        try {
            DataAccess access = new DataAccess();
            var doctors = access.getDoctors();
            for(var doctor: doctors)
                System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
        } catch (Exception ex) {
            System.err.println("Failed to query the database.");
            
        }
    }

    @Test
    public void getPatientTest() throws Exception {
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
    }*/

    @Test
    public void getCertificationsTest() throws Exception {
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

    @Test
    public void changeDoctorTest() throws Exception {
        try
        {
            DataAccess access = new DataAccess();
            Patient patient = access.getPatient(1);
            Doctor doctor = access.getDoctor(3);
            System.out.println(access.getDoctor(patient).getFirstName());
            access.changeDoctor(patient, doctor);
            System.out.println(access.getDoctor(patient).getFirstName());
        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
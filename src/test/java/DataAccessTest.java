import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import com.group15A.DataModel.Patient;
import org.junit.Test;

import java.util.ArrayList;
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
}

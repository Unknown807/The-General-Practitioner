import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataAccessTest{
    @Test
    public void getDoctorsTest()
    {
        //TODO Mock database for proper unit testing
        try {
            DataAccess access = new DataAccess();
            var doctors = access.getDoctors();
            for(var doctor: doctors)
                System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

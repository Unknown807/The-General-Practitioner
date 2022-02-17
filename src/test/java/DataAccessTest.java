import com.group15A.DataAccess.DataAccess;
import com.group15A.DataAccess.IDataAccess;
import com.group15A.DataModel.Doctor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataAccessTest {
    @Test
    public void getDoctorsTest()
    {
        var access = new DataAccess();
        assertEquals(access.getDoctors(), new ArrayList<Doctor>());
    }

}

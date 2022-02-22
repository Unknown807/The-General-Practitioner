package com.group15A.BusinessLogic;

import com.group15A.DataAccess.DataAccess;
import com.group15A.DataModel.Doctor;

import java.util.List;

public class DoctorLogic implements IDoctor {
    private DataAccess dataAccessLayer;

    public DoctorLogic() throws Exception {
        this.dataAccessLayer = new DataAccess();
    }

    @Override
    public List<Doctor> getDoctors() throws Exception {
        return null;
    }
}

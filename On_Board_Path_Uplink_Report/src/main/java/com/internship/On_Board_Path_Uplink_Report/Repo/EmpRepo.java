package com.internship.On_Board_Path_Uplink_Report.Repo;

import com.internship.On_Board_Path_Uplink_Report.Model.Empdetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Empdetails,Integer> {
    //CRUD Operations done here
    public boolean existsByEmail(String email);

    Empdetails findByUsername(String username);

    public static Empdetails findByEmail(String email) {
        return null;
    }
}

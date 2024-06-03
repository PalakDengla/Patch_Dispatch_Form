package com.internship.On_Board_Path_Uplink_Report.Repo;

import com.internship.On_Board_Path_Uplink_Report.Model.FormDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepo extends JpaRepository<FormDetails,Integer> {



}

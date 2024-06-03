package com.internship.On_Board_Path_Uplink_Report.Repo;

import com.internship.On_Board_Path_Uplink_Report.Model.FormDetails;
import com.internship.On_Board_Path_Uplink_Report.Model.FormOfTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormOfTableRepo extends JpaRepository<FormOfTable,Integer> {



}
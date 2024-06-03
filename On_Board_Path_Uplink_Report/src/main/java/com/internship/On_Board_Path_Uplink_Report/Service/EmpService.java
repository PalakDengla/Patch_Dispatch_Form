package com.internship.On_Board_Path_Uplink_Report.Service;

import com.internship.On_Board_Path_Uplink_Report.Model.Empdetails;

public interface EmpService {


   public static Empdetails loadUserByEmail(String email) {
       return null;
   };

    public  Empdetails createUser(Empdetails user);

    public  boolean checkEmail(String email);



}

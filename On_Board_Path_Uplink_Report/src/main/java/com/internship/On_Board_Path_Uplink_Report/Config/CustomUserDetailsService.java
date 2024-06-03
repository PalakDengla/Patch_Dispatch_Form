package com.internship.On_Board_Path_Uplink_Report.Config;

import com.internship.On_Board_Path_Uplink_Report.Model.Empdetails;
import com.internship.On_Board_Path_Uplink_Report.Repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmpRepo empRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        Empdetails emp = empRepo.findByUsername(username);
        //System.out.println(emp.toString());
        if (emp == null) {
            throw new UsernameNotFoundException("user name not found");
        }
        else {
            return new org.springframework.security.core.userdetails.User(emp.getUsername(), emp.getPassword(), new ArrayList<>());
        }

    }
}

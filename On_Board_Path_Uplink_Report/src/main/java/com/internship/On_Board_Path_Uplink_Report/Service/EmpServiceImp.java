package com.internship.On_Board_Path_Uplink_Report.Service;

import com.internship.On_Board_Path_Uplink_Report.Model.Empdetails;
import com.internship.On_Board_Path_Uplink_Report.Repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

class EmpServiceImp implements EmpService {

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Empdetails createUser(Empdetails user) {
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        Empdetails newuser=empRepo.save(user);
        return newuser;
    }

    @Override
    public boolean checkEmail(String email) {
        return empRepo.existsByEmail(email) ;
    }

    public Empdetails loadUserByEmail(String email) {
        Optional<Empdetails> empDetailsOptional = Optional.ofNullable(EmpRepo.findByEmail(email));
        if (empDetailsOptional.isPresent()) {
            return empDetailsOptional.get();
        } else {
            throw new UsernameNotFoundException("Email not found: " + email);
        }
    }

}
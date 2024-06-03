package com.internship.On_Board_Path_Uplink_Report.Controller;

import com.internship.On_Board_Path_Uplink_Report.Model.Empdetails;
import com.internship.On_Board_Path_Uplink_Report.Service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class parent {

    private final EmpService empService;

    @Autowired
    public parent(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/user/form")
//    public String form() {
//        return "form";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute Empdetails user, HttpSession session) {

        try {
            // Attempt to create the user
            Empdetails empdetails = empService.createUser(user);
            session.setAttribute("msg", "Successful registration");
        } catch (DataIntegrityViolationException e) {
            // Check if the exception is due to duplicate entry for email or empid
            if (e.getMessage().contains("Duplicate entry")) {
                // Set a custom error message for frontend display
                session.setAttribute("msg", "Email or EmpID already exists");
            }
        } catch (Exception e) {
            // Handle other exceptions
            session.setAttribute("msg", "Error occurred during registration: " + e.getMessage());
        }
        return "redirect:/registration"; // Redirect back to registration page
    }


}


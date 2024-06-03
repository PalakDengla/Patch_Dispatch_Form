package com.internship.On_Board_Path_Uplink_Report.Controller;

import com.internship.On_Board_Path_Uplink_Report.Model.FormDetails;
import com.internship.On_Board_Path_Uplink_Report.Model.FormOfTable;
import com.internship.On_Board_Path_Uplink_Report.Service.FormService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("formDetails", new FormDetails());
        return "form"; // Ensure you have form.html in your templates folder
    }

    @PostMapping("/combinedformfill")
    public String combinedFormFill(@ModelAttribute("formDetails") FormDetails formDetails, HttpSession session, Model model) {
        try {
            // Ensure the relationship between FormDetails and FormOfTable is set
            for (FormOfTable formOfTable : formDetails.getFormOfTables()) {
                formOfTable.setFormDetails(formDetails); // Ensure the parent is set for each child
            }
            formService.formregister(formDetails);
            session.setAttribute("msg", "Form submitted successfully");

            // Add any necessary attributes to the model to be used in pdf.html
            model.addAttribute("formDetails", formDetails);
        } catch (DataIntegrityViolationException e) {
            session.setAttribute("errorMessage", "Data integrity violation occurred.");
            return "redirect:/user/form"; // Redirect back to the form page in case of error
        }
        return "pdf"; // Return the view name for pdf.html
    }
}

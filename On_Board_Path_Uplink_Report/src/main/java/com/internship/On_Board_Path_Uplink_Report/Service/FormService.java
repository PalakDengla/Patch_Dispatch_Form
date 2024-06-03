package com.internship.On_Board_Path_Uplink_Report.Service;

import com.internship.On_Board_Path_Uplink_Report.Model.FormDetails;
import com.internship.On_Board_Path_Uplink_Report.Repo.FormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    private final FormRepo formRepo;

    @Autowired
    public FormService(FormRepo formRepo) {
        this.formRepo = formRepo;
    }

    public FormDetails formregister(FormDetails formDetails) {
        return formRepo.save(formDetails);
    }
}




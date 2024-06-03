package com.internship.On_Board_Path_Uplink_Report.Service;

import com.internship.On_Board_Path_Uplink_Report.Model.FormOfTable;
import com.internship.On_Board_Path_Uplink_Report.Repo.FormOfTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormOfTableService {
    private final FormOfTableRepo formOfTableRepo;

    @Autowired
    public FormOfTableService(FormOfTableRepo formOfTableRepo) {
        this.formOfTableRepo = formOfTableRepo;
    }

    public FormOfTable formregisterTable(FormOfTable formOfTable) {
        return formOfTableRepo.save(formOfTable);
    }
}

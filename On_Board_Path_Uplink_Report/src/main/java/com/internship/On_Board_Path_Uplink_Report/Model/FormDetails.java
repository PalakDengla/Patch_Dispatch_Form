package com.internship.On_Board_Path_Uplink_Report.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name="formdetails")
@Entity
@Data
public class FormDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String project;
    private String subsystem;
    private String codeversion;
    private String date;
    private String PIP;
    private String RPID;
    private String RPF;

    @OneToMany(mappedBy = "formDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormOfTable> formOfTables;
}

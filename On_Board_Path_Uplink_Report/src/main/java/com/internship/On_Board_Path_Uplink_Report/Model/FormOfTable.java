package com.internship.On_Board_Path_Uplink_Report.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class FormOfTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String checklistDescription;
    private String compiled;
    private String reference;
    private String remark;
    private int sino; // Assuming this field represents the serial number

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_details_id")
    private FormDetails formDetails; // Reference to the parent form details

    // Getters and setters
}

package com.internship.On_Board_Path_Uplink_Report.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="empdetails")
public class Empdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//maked id as unique of very employee as unique
    private int id;
    private String username;
    private int empid;
    private int supid;
    private String role;
    private String address;
    private String email;
    private String password;




}

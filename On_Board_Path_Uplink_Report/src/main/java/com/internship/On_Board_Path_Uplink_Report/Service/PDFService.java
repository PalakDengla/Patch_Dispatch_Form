package com.internship.On_Board_Path_Uplink_Report.Service;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class PDFService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public byte[] generatePdfReport(int formId) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            Connection conn = setupDatabaseConnection();
            if (conn != null) {
                generatePDFReport(conn, document, formId);
                conn.close();
            }

            document.close();
        } catch (DocumentException | SQLException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    private Connection setupDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    private void generatePDFReport(Connection conn, Document document, int formId) throws SQLException, DocumentException {
        String formDetailsQuery = "SELECT * FROM formdetails WHERE id = " + formId;
        Statement stmt = conn.createStatement();
        ResultSet formDetailsRs = stmt.executeQuery(formDetailsQuery);

        if (formDetailsRs.next()) {
            document.add(new Paragraph("Form Details Data:"));
            document.add(new Paragraph("ID: " + formDetailsRs.getString("id")));
            document.add(new Paragraph("Project: " + formDetailsRs.getString("project")));
            document.add(new Paragraph("Code Version:" + formDetailsRs.getString("codeversion")));
            document.add(new Paragraph("Date:" + formDetailsRs.getString("date")));
            document.add(new Paragraph("SubSystem:" + formDetailsRs.getString("subsystem")));
            document.add(new Paragraph("PIP:" + formDetailsRs.getString("pip")));
            document.add(new Paragraph("RPF:" + formDetailsRs.getString("rpf")));
            document.add(new Paragraph("RPID:" + formDetailsRs.getString("rpid")));

            String relatedQuery = "SELECT * FROM form_of_table WHERE form_details_id = " + formDetailsRs.getString("id");
            ResultSet relatedRs = stmt.executeQuery(relatedQuery);

            document.add(new Paragraph("Related Form Of Table Data:"));
            while (relatedRs.next()) {
                document.add(new Paragraph("SI.NO: " + relatedRs.getString("sino")));
                document.add(new Paragraph("Checklist Description: " + relatedRs.getString("checklist_description")));
                document.add(new Paragraph("Compiled/Not Compiled: " + relatedRs.getString("compiled")));
                document.add(new Paragraph("Related Info: " + relatedRs.getString("reference")));
                document.add(new Paragraph("Remark: " + relatedRs.getString("remark")));
            }
        } else {
            document.add(new Paragraph("No data found for form ID: " + formId));
        }
    }
}


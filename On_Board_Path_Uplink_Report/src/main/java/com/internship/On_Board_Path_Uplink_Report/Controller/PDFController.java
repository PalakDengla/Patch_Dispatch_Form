package com.internship.On_Board_Path_Uplink_Report.Controller;




import com.internship.On_Board_Path_Uplink_Report.Service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PDFController {

    @Autowired
    private PDFService pdfReportService;

    @GetMapping("/generateReport")
    public ResponseEntity<byte[]> generateReport(@RequestParam int formId) {
        byte[] pdfContent = pdfReportService.generatePdfReport(formId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.pdf");
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }
}

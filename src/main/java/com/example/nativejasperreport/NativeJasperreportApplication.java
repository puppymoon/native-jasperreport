package com.example.nativejasperreport;

import com.example.nativejasperreport.service.ReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SpringBootApplication
public class NativeJasperreportApplication {

    @Autowired
    private ReportGenerator reportGenerator;

    public static void main(String[] args) {
        SpringApplication.run(NativeJasperreportApplication.class, args);
    }

    @RequestMapping(value = "/getpdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF() throws JRException, IOException {

        byte[] contents = reportGenerator.generatePdfReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String filename = "CarReport.pdf";
        headers.setContentDispositionFormData(filename, filename);
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

}

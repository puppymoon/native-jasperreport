package com.example.nativejasperreport;

import com.example.nativejasperreport.service.ReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NativeJasperreportApplicationTests {

    @Autowired
    private ReportGenerator reportGenerator;

    @Test
    void contextLoads() throws JRException, IOException {
        byte[] pdfData= reportGenerator.generatePdfReport();
        assertThat(pdfData).isNotNull();
    }

}

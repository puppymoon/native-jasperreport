package com.example.nativejasperreport.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class ReportGenerator {

    @Autowired
    private ResourceLoader loader;

    public byte[] generatePdfReport() throws JRException, IOException {

        var report = JasperCompileManager.compileReport(loader.getResource("classpath:report.xml").getInputStream());
        var columnNames = new String[]{"Id", "Name", "Price"};

        var fileName = "cars.csv";
        var ds = new JRCsvDataSource(fileName);
        ds.setColumnNames(columnNames);

        var params = new HashMap<String, Object>();
        var jasperPrint = JasperFillManager.fillReport(report,
                params, ds);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
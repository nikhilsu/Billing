package com.billing.view;

import com.billing.helper.Masker;
import com.billing.model.BillCategory;
import com.billing.model.Patient;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class PdfRevenueReportView extends AbstractPdfView {

    @Override
    @SuppressWarnings("unchecked")
    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        List<BillCategory> billCategories = (List<BillCategory>) model.get("categories");
        Patient patient = (Patient) model.get("patient");
        Integer billId = (Integer) model.get("billId");

        URL logoURL = getClass().getClassLoader().getResource("/images_for_reports/logo_in_pdf.png");
        if (logoURL == null)
            throw new Exception("Logo resource not found!");

        Image logo = Image.getInstance(logoURL);
        logo.setAlignment(Element.ALIGN_CENTER);

        Paragraph tagLine = new Paragraph("Centre of Endoscopy, Colonoscopy and Gastro-Enterology.\nA Health Care Centre for Children.");
        tagLine.setAlignment(Element.ALIGN_CENTER);

        Font addressAndLandmarkFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 10);
        Paragraph address = new Paragraph("575, MC LAYOUT, 8TH MAIN, VIJAYNAGAR, BANGALORE - 560040", addressAndLandmarkFont);
        address.setAlignment(Element.ALIGN_CENTER);

        Paragraph landmark = new Paragraph("(BEHIND VIJAYNAGAR MAIN BUS STOP, WATER TANK ROAD, NEAR GANESHA/SHIVA TEMPLE)", addressAndLandmarkFont);
        landmark.setAlignment(Element.ALIGN_CENTER);

        Font contactFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 11);
        Paragraph contactInfo = new Paragraph("Ph: +91-9620204210, 23109999  Email: gubbisgastro@gmail.com", contactFont);
        contactInfo.setAlignment(Element.ALIGN_CENTER);

        Font billTextFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.UNDERLINE);
        Paragraph billText = new Paragraph("BILL", billTextFont);
        billText.setSpacingBefore(15);
        billText.setSpacingAfter(20);
        billText.setAlignment(Element.ALIGN_CENTER);


        PdfPTable patientInfoTable = new PdfPTable(2);
        patientInfoTable.setWidthPercentage(100);

        PdfPCell billNumberCell = createHelveticaBoldCell("BILL No.: " + Masker.maskDbId(billId), 10);
        removeBorder(billNumberCell);

        PdfPCell dateCell = createHelveticaBoldCell("DATE: " + currentDateInIndia(), 10);
        dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        removeBorder(dateCell);

        PdfPCell nameCell = createHelveticaBoldCell("NAME: " + patient.getName(), 10);
        nameCell.setColspan(2);
        removeBorder(nameCell);

        PdfPCell ageCell = createHelveticaBoldCell("AGE: " + patient.getAge(), 10);
        ageCell.setColspan(2);
        removeBorder(ageCell);

        PdfPCell phoneNumberCell = createHelveticaBoldCell("MOBILE No.: " + patient.getPhoneNumber(), 10);
        removeBorder(phoneNumberCell);

        PdfPCell emailCell = createHelveticaBoldCell("EMAIL ID: " + patient.getEmail(), 10);
        emailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        removeBorder(emailCell);

        patientInfoTable.addCell(billNumberCell);
        patientInfoTable.addCell(dateCell);
        patientInfoTable.addCell(nameCell);
        patientInfoTable.addCell(ageCell);
        patientInfoTable.addCell(phoneNumberCell);
        patientInfoTable.addCell(emailCell);
        patientInfoTable.setSpacingAfter(40);

        PdfPTable billDetailsTable = new PdfPTable(new float[]{10, 70, 20});
        billDetailsTable.setWidthPercentage(90);
        PdfPCell slNoCell = createHelveticaBoldCell("SL.No.", 12);
        slNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell descriptionCell = createHelveticaBoldCell("DESCRIPTION", 12);
        descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell amountCell = createHelveticaBoldCell("AMOUNT", 12);
        amountCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        billDetailsTable.addCell(slNoCell);
        billDetailsTable.addCell(descriptionCell);
        billDetailsTable.addCell(amountCell);

        float total = 0;
        for (int i = 0; i < billCategories.size(); i++) {

            PdfPCell slNoValueCell = new PdfPCell(new Paragraph(Integer.toString(i + 1)));
            slNoValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            String name = billCategories.get(i).getName();
            PdfPCell nameValueCell = new PdfPCell(new Paragraph(name));
            nameValueCell.setHorizontalAlignment(Element.ALIGN_LEFT);

            double cost = billCategories.get(i).getCost();
            PdfPCell costCell = new PdfPCell(new Paragraph(Double.toString(cost)));
            costCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            billDetailsTable.addCell(slNoValueCell);
            billDetailsTable.addCell(nameValueCell);
            billDetailsTable.addCell(costCell);
            total += cost;
        }
        PdfPCell totalCell = createHelveticaBoldCell("Total", 12);
        totalCell.setColspan(2);
        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell totalValue = createHelveticaBoldCell(Float.toString(total), 12);
        totalValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
        billDetailsTable.addCell(totalCell);
        billDetailsTable.addCell(totalValue);

        Paragraph authorized_signatory = new Paragraph("Authorized Signatory", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
        authorized_signatory.setAlignment(Element.ALIGN_RIGHT);
        authorized_signatory.setSpacingBefore(60);

        document.add(logo);
        document.add(tagLine);
        document.add(address);
        document.add(landmark);
        document.add(contactInfo);
        document.add(billText);
        document.add(patientInfoTable);
        document.add(billDetailsTable);
        document.add(authorized_signatory);
    }

    private static PdfPCell createHelveticaBoldCell(String value, int fontSize) {
        return new PdfPCell(new Paragraph(value, FontFactory.getFont(FontFactory.HELVETICA_BOLD, fontSize)));
    }

    private static void removeBorder(PdfPCell cell) {
        cell.setBorder(Rectangle.NO_BORDER);
    }

    private String currentDateInIndia() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        TimeZone timeZone = TimeZone.getTimeZone("IST");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }
}

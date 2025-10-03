package com.ppp.billing.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.itextpdf.io.IOException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.ppp.billing.model.Consultation;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.Facture;
import com.ppp.billing.model.FactureItem;
import com.ppp.billing.model.Payment;
import com.ppp.billing.repository.PaymentRepository;
import com.ppp.printable.PrintableElement;

@Service
public class PdfService {
	
	@Value("${folder.receipt}")
	private String receiptDir;
	
	@Value("${folder.service.receipt}")
	private String serviceReceiptDir;
	
	
	@Autowired
	private PaymentRepository paymentRepository;
	

 
 
 public File SendBack (Long id) throws FileNotFoundException {
	
//	Sale sale = saleService.findById(id);
	  //	PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	 //   Document document = new Document(pdfDocument, PageSize.A4);
	 //   System.out.println(document.getWidth());
	 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

	 try {
			PdfWriter pdfWriter = new PdfWriter(receiptDir+1+".pdf");
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
//			 document.setMargins(25, 25, 297-156, 50);

			PrintableElement printer = new PrintableElement();	
			 // HEADER: Medical Center Info and Logo
		    float[] headerWidths = {3, 2, 3};
		    Table headerTable = new Table(headerWidths);
		    headerTable.setWidth(UnitValue.createPercentValue(100));

		    // Left Column - Center Info
		    Cell leftCell = new Cell();
		    leftCell.setBorder(Border.NO_BORDER);
		    leftCell.add(new Paragraph("CENTRE MÉDICAL QUEEN MARY")
		            .setFontSize(12)
		            .setBold()
		            .setFontColor(new DeviceRgb(0, 51, 102))); // dark blue
		    leftCell.add(new Paragraph("Pour des Soins Plus Humains")
		            .setFontSize(10)
		            .setItalic());
		    leftCell.add(new Paragraph("RC No RC/YAO/2021/B/2230").setFontSize(9));
		    leftCell.add(new Paragraph("NUI M112116710688d").setFontSize(9));
		    headerTable.addCell(leftCell);

		    // Center Column - Logo
		    Cell centerCell = new Cell();
		    centerCell.setBorder(Border.NO_BORDER);
		    centerCell.setTextAlignment(TextAlignment.CENTER);
		    try {
		        ClassPathResource resource = new ClassPathResource("static/img/queen.png");
		        Image logo = new Image(ImageDataFactory.create(resource.getFile().getAbsolutePath()));
		        logo.setWidth(100);
		        logo.setHeight(50);
		        centerCell.add(logo);
		    } catch (Exception e) {
		        centerCell.add(new Paragraph("Logo not available").setFontSize(10).setItalic());
		    }
		    headerTable.addCell(centerCell);

		    // Right Column - Contact Info
		    Cell rightCell = new Cell();
		    rightCell.setBorder(Border.NO_BORDER);
		    rightCell.setTextAlignment(TextAlignment.RIGHT);
		    rightCell.add(new Paragraph("Shell Nsimeyong, Yaoundé").setFontSize(9).setBold());
		    rightCell.add(new Paragraph("(+237) 659 439 160").setFontSize(9));
		    rightCell.add(new Paragraph("(+237) 675 124 157").setFontSize(9));
		    rightCell.add(new Paragraph("BP 31431").setFontSize(9));
		    rightCell.add(new Paragraph("cmqueenmary@gmail.com").setFontSize(9));
		    headerTable.addCell(rightCell);

		    document.add(headerTable);

	
			document.add(new Paragraph("\n"));

          //  printer.printHeader(document, " Queen Mary Hospital");
            printer.printHeader(document, " Pharmacy Receipt");
//
//            printer.printParagraphe(document, "Receipt No: " + sale.getReceiptNumber());
//            printer.printParagraphe(document, "Customer: " + sale.getCustomerName());
//
//            printer.printParagraphe(document, "Date: " + sale.getSaleDate());
//
//            printer.printParagraphe(document, "Payment: " + sale.getPaymentMethod());
            
            
            Table table = new Table(6);
            table.addCell("No");
            table.addCell("Medicine");
            table.addCell("Unit Type");
            table.addCell("Qty");
            table.addCell("Price");
            table.addCell("Total");
            int num = 1; 
//            for (SaleItem item : sale.getItems()) {
//            	table.addCell(String.valueOf( num));
//                table.addCell(item.getMedicine().getName());
//                table.addCell(item.getUnitType());
//                table.addCell(String.valueOf(item.getQuantity()));
//                table.addCell(item.getMedicine().getUnitPrice().toString());
//                table.addCell(item.getSubtotal().toString());
//                num++;
//            }

            Cell totalCell = new Cell(1, 6)  // 1 row, spans 4 columns
                    .add("Grand Total: " )
                    .setTextAlignment(TextAlignment.RIGHT);

            table.addCell(totalCell);
            printer.printTable(document, table);
            
       //     printer.printHeader(document, "Amount Received : " + sale.getAmountPaid()); AccountingService accountingService

       //     printer.printHeader(document, "Ballance  : " +  sale.getBallance());

            document.add(new Paragraph("\nThank you for your purchase!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setItalic());
            document.close();
            
            
			File file = new File(receiptDir+ 1+".pdf");


          
			 return file;

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF receipt", e);
        }
    }
 
 
 

    public  File generateReceipt(long id) throws FileNotFoundException {
        // 1. Create PDF writer + document
    //	Sale sale = saleService.findById(id);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(receiptDir+1+ ".pdf");
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        float[] columnWidths = {4, 2, 4}; // Left, Logo, Right
		Table headerTable = new Table(columnWidths);
		headerTable.setWidth(UnitValue.createPercentValue(100));

		// LEFT SIDE
		Cell leftCell = new Cell();
		leftCell.setBorder(Border.NO_BORDER);
		leftCell.add(new Paragraph("CENTRE MÉDICAL QUEEN MARY")
		        .setFontSize(12)
		        .setBold()
		        .setFontColor(new DeviceRgb(0, 128, 0))); // Green title
		leftCell.add(new Paragraph("Pour des Soins Plus Humains")
		        .setFontSize(9));
		leftCell.add(new Paragraph("Simbock Nkomo, Yaoundé")
		        .setFontSize(9));
		leftCell.add(new Paragraph("Tel: (+237) 693 973 130 / 675 131 197")
		        .setFontSize(9));
		leftCell.add(new Paragraph("cmqueenmary@gmail.com")
		        .setFontSize(9));
		leftCell.add(new Paragraph("RC: RC/YAO/2021/B/2209")
		        .setFontSize(9));
		headerTable.addCell(leftCell);

		// CENTER (LOGO PLACEHOLDER)
		Cell logoCell = new Cell();
		logoCell.setBorder(Border.NO_BORDER);
		logoCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
		logoCell.setTextAlignment(TextAlignment.CENTER);
		logoCell.add(new Paragraph("[ LOGO HERE ]")
		        .setItalic()
		        .setFontSize(10)
		        .setFontColor(new DeviceRgb(150, 150, 150)));
		headerTable.addCell(logoCell);

		// RIGHT SIDE
		Cell rightCell = new Cell();
		rightCell.setBorder(Border.NO_BORDER);
		rightCell.setTextAlignment(TextAlignment.RIGHT);
		rightCell.add(new Paragraph("Shell Nsimeyong, Yaoundé")
		        .setFontSize(9)
		        .setBold());
		rightCell.add(new Paragraph("(+237) 659 439 160")
		        .setFontSize(9));
		rightCell.add(new Paragraph("(+237) 675 124 157")
		        .setFontSize(9));
		rightCell.add(new Paragraph("BP 31431")
		        .setFontSize(9));
		
		rightCell.add(new Paragraph("cmqueenmary@gmail.com")
		        .setFontSize(9));
		headerTable.addCell(rightCell);


		document.add(headerTable);

		// --- COLOR LINES (Green + Blue) ---
		float y = pdfDoc.getDefaultPageSize().getTop() - 120; // Position below header
		PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());

		// Green line
		canvas.setStrokeColor(new DeviceRgb(0, 128, 0));
		canvas.setLineWidth(3f);
		canvas.moveTo(document.getLeftMargin(), y);
		canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - document.getRightMargin(), y);
		canvas.stroke();

		// Blue line below
		canvas.setStrokeColor(new DeviceRgb(0, 102, 204));
		canvas.setLineWidth(3f);
		canvas.moveTo(document.getLeftMargin(), y - 5);
		canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - document.getRightMargin(), y - 5);
		canvas.stroke();

		document.add(new Paragraph("\n"));


        // 3. Sale Info
//        document.add(new Paragraph("Receipt No: " + sale.getReceiptNumber()));
//        document.add(new Paragraph("Customer: " + sale.getCustomerName()));
//        document.add(new Paragraph("Pharmacist: " + sale.getPharmacist().getUsername()));
//        document.add(new Paragraph("Date: " + sale.getSaleDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
//        document.add(new Paragraph("Payment Method: " + sale.getPaymentMethod()));
//        document.add(new Paragraph("\n"));

    
        // 4. Table Header (5 columns)
        float[] columnWidths2 = {200F, 80F, 80F, 100F};
        Table table = new Table(columnWidths2);
        table.addHeaderCell(new Cell().add("Medicine").setBold());
        table.addHeaderCell(new Cell().add("Qty").setBold());
        table.addHeaderCell(new Cell().add("Unit Price").setBold());
//        table.addHeaderCell(new Cell().add("Discount").setBold());
        table.addHeaderCell(new Cell().add("Total").setBold());

        // 5. Table Rows (Sale Items)
//        for (SaleItem item : sale.getItems()) {
//            BigDecimal itemTotal = item.getMedicine().getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
////            BigDecimal discount = item.getDiscount() != null ? item.getDiscount() : BigDecimal.ZERO;
//
//            table.addCell(new Cell().add(item.getMedicine().getName()));
//            table.addCell(new Cell().add(String.valueOf(item.getQuantity())));
//            table.addCell(new Cell().add(item.getMedicine().getUnitPrice().toString()));
////            table.addCell(new Cell().add(discount.toString()));
////            table.addCell(new Cell().add(itemTotal.subtract(discount).toString()));
//        }

        // 6. Grand Total Row
        table.addCell(new Cell(1, 4).add("").setBorder(Border.NO_BORDER)); // empty space
        table.addCell(new Cell().add("------------"));

        table.addCell(new Cell(1, 4).add("Grand Total").setBold().setTextAlignment(TextAlignment.RIGHT));
      //  table.addCell(new Cell().add(sale.getTotal().toString()).setBold());

        // 7. Add table to document
        document.add(table);

        // 8. Footer
        document.add(new Paragraph("\nThank you for your purchase!")
                .setTextAlignment(TextAlignment.CENTER)
                .setItalic());
	
				document.close();
				File file = new File(receiptDir+1+".pdf");

			 return file;
			} 
			




		public File printRecept(Long paymentId) throws FileNotFoundException {
	//		PaymentItem payment = paymentItemService.getPaymentById(paymentId);
			  //	PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			 //   Document document = new Document(pdfDocument, PageSize.A4);
			 //   System.out.println(document.getWidth());
			 
				//	ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 
			
					 PdfWriter writer = new PdfWriter(receiptDir+2+ ".pdf");
				        PdfDocument pdfDoc = new PdfDocument(writer);
				        Document document = new Document(pdfDoc);

				        // 2. Title
				        float[] columnWidths = {4, 2, 4}; // Left, Logo, Right
						Table headerTable = new Table(columnWidths);
						headerTable.setWidth(UnitValue.createPercentValue(100));

						// LEFT SIDE
						Cell leftCell = new Cell();
						leftCell.setBorder(Border.NO_BORDER);
						leftCell.add(new Paragraph("CENTRE MÉDICAL QUEEN MARY")
						        .setFontSize(12)
						        .setBold()
						        .setFontColor(new DeviceRgb(0, 128, 0))); // Green title
						leftCell.add(new Paragraph("Pour des Soins Plus Humains")
						        .setFontSize(9));
						leftCell.add(new Paragraph("RC No RC/YAO/2021/B/2230")
						        .setFontSize(9));
						leftCell.add(new Paragraph("NUI M112116710688d")
						        .setFontSize(9));
						headerTable.addCell(leftCell);

						// CENTER (LOGO PLACEHOLDER)
						Cell logoCell = new Cell();
						logoCell.setBorder(Border.NO_BORDER);
						logoCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
						logoCell.setTextAlignment(TextAlignment.CENTER);
						
					Image logoImage = null;
					try {
						ClassPathResource resource =  new ClassPathResource("static/img/queen.png");
				         logoImage = new Image(ImageDataFactory.create(resource.getFile().getAbsolutePath()));
				        
					} catch (IOException | java.io.IOException  e) {
							e.printStackTrace();
						}
						logoImage.setWidth(100); 
						logoImage.setHeight(50); 
						logoCell.add(logoImage);
						headerTable.addCell(logoCell);

						// RIGHT SIDE
						Cell rightCell = new Cell();
						rightCell.setBorder(Border.NO_BORDER);
						rightCell.setTextAlignment(TextAlignment.RIGHT);
						rightCell.add(new Paragraph("Shell Nsimeyong, Yaoundé")
						        .setFontSize(9)
						        .setBold());
						rightCell.add(new Paragraph("(+237) 659 439 160")
						        .setFontSize(9));
						rightCell.add(new Paragraph("(+237) 675 124 157")
						        .setFontSize(9));
						rightCell.add(new Paragraph("BP 31431")
						        .setFontSize(9));
						
						rightCell.add(new Paragraph("cmqueenmary@gmail.com")
						        .setFontSize(9));
						headerTable.addCell(rightCell);

						document.add(headerTable);

						// --- COLOR LINES (Green + Blue) ---
						float y = pdfDoc.getDefaultPageSize().getTop() - 120; // Position below header
						PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());

						// Green line
						canvas.setStrokeColor(new DeviceRgb(0, 102, 204));
						canvas.setLineWidth(3f);
						canvas.moveTo(document.getLeftMargin(), y);
						canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - document.getRightMargin(), y);
						canvas.stroke();

						// Blue line below
						canvas.setStrokeColor(new DeviceRgb(0, 128,0));
						canvas.setLineWidth(3f);
						canvas.moveTo(document.getLeftMargin(), y - 5);
						canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - document.getRightMargin(), y - 5);
						canvas.stroke();

						document.add(new Paragraph("\n"));


				    //    document.add(new Paragraph("\n"));

				        // Patient & payment info
				   //     document.add(new Paragraph("Receipt No:");
				        document.add(new Paragraph("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
				        
//				        String name = "";
//				        if (payment.getPatient() != null && payment.getPatient().getName() != null && !payment.getPatient().getName().isEmpty()) {
//				            name = payment.getPatient().getName();
//				        } else {
//				            name = payment.getUnregisteredPatientName();
//				        }
				//        document.add(new Paragraph("Patient: " + name));
				        document.add(new Paragraph("Cashier:  Cashier" ));
//				        document.add(new Paragraph("\n"));

				      //  document.add(new Paragraph("Pharmacist: " + sale.getPharmacist().getUsername()));
//				        document.add(new Paragraph("Date: " +payment.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
//				        document.add(new Paragraph("Payment Method: " +payment.getPaymentMethod()));
//				        document.add(new Paragraph("Payment Status: " +payment.getStatus()));

				        document.add(new Paragraph("\n"));
				       
				        
				        Table table = new Table(new float[]{4, 2, 2, 2});
				        table.setWidthPercent(100);

				        table.addHeaderCell(new Cell().add(new Paragraph("Service Description").setBold()));
				        table.addHeaderCell(new Cell().add(new Paragraph("Unit Price").setBold()));
				        table.addHeaderCell(new Cell().add(new Paragraph("Qty").setBold()));
				        table.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));

//				        for (ServiceItem item : payment.getServiceItems()) {
//				            table.addCell(item.getName());
//				            table.addCell(String.format("%.2f", item.getPrice()));
//				            table.addCell("1"); // or actual qty
//				            table.addCell(String.format("%.2f", item.getPrice())); // price * qty
//				        }

				        document.add(table);

				        document.add(new Paragraph("\n"));

				        // Total
//				        document.add(new Paragraph("Grand Total: " + String.format("%.2f",   " FCFA")
//				                .setFontSize(12)
//				                .setBold()
//				                .setTextAlignment(TextAlignment.RIGHT));
//				        document.add(new Paragraph("\n"));

				        // Footer
				        document.add(new Paragraph("Thank you for choosing Queen Mary Hospital.")
				                .setFontSize(10)
				                .setItalic()
				                .setTextAlignment(TextAlignment.CENTER));
								document.close();
								
								
								File file = new File(receiptDir+ 2 +".pdf");
				
			return file;
		}
   
	
		
		public File printConsultationReceipt(Consultation consultation) throws IOException, FileNotFoundException {
		    String outputPath = receiptDir  + consultation.getReferenceNumber() + ".pdf";
		    PdfWriter writer = new PdfWriter(outputPath);
		    PdfDocument pdfDoc = new PdfDocument(writer);
		    Document document = new Document(pdfDoc, PageSize.A4);
		    document.setMargins(40, 40, 40, 40);

		    // HEADER: Medical Center Info and Logo
		    float[] headerWidths = {3, 2, 3};
		    Table headerTable = new Table(headerWidths);
		    headerTable.setWidth(UnitValue.createPercentValue(100));

		    // Left Column - Center Info
		    Cell leftCell = new Cell();
		    leftCell.setBorder(Border.NO_BORDER);
		    leftCell.add(new Paragraph("CENTRE MÉDICAL QUEEN MARY")
		            .setFontSize(14)
		            .setBold()
		            .setFontColor(new DeviceRgb(0, 51, 102))); // dark blue
		    leftCell.add(new Paragraph("Pour des Soins Plus Humains")
		            .setFontSize(10)
		            .setItalic());
		    leftCell.add(new Paragraph("RC No RC/YAO/2021/B/2230").setFontSize(9));
		    leftCell.add(new Paragraph("NUI M112116710688d").setFontSize(9));
		    headerTable.addCell(leftCell);

		    // Center Column - Logo
		    Cell centerCell = new Cell();
		    centerCell.setBorder(Border.NO_BORDER);
		    centerCell.setTextAlignment(TextAlignment.CENTER);
		    try {
		        ClassPathResource resource = new ClassPathResource("static/img/queen.png");
		        Image logo = new Image(ImageDataFactory.create(resource.getFile().getAbsolutePath()));
		        logo.setWidth(100);
		        logo.setHeight(50);
		        centerCell.add(logo);
		    } catch (Exception e) {
		        centerCell.add(new Paragraph("Logo not available").setFontSize(10).setItalic());
		    }
		    headerTable.addCell(centerCell);

		    // Right Column - Contact Info
		    Cell rightCell = new Cell();
		    rightCell.setBorder(Border.NO_BORDER);
		    rightCell.setTextAlignment(TextAlignment.RIGHT);
		    rightCell.add(new Paragraph("Shell Nsimeyong, Yaoundé").setFontSize(9).setBold());
		    rightCell.add(new Paragraph("(+237) 659 439 160").setFontSize(9));
		    rightCell.add(new Paragraph("(+237) 675 124 157").setFontSize(9));
		    rightCell.add(new Paragraph("BP 31431").setFontSize(9));
		    rightCell.add(new Paragraph("cmqueenmary@gmail.com").setFontSize(9));
		    headerTable.addCell(rightCell);

		    document.add(headerTable);

		    // Decorative lines below header
		    float y = pdfDoc.getDefaultPageSize().getTop() - 150;
		    PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());

		    canvas.setStrokeColor(new DeviceRgb(0, 102, 204)); // Blue
		    canvas.setLineWidth(3f);
		    canvas.moveTo(document.getLeftMargin(), y);
		    canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - document.getRightMargin(), y);
		    canvas.stroke();

		    canvas.setStrokeColor(new DeviceRgb(0, 128, 0)); // Green
		    canvas.setLineWidth(3f);
		    canvas.moveTo(document.getLeftMargin(), y - 5);
		    canvas.lineTo(pdfDoc.getDefaultPageSize().getWidth() - document.getRightMargin(), y - 5);
		    canvas.stroke();

		    document.add(new Paragraph("\n"));

		    // TITLE
		    document.add(new Paragraph("Consultation Receipt")
		            .setTextAlignment(TextAlignment.CENTER)
		            .setFontSize(16)
		            .setBold()
		            .setFontColor(new DeviceRgb(0, 51, 102)));
		    document.add(new Paragraph("\n"));

		    // Consultation Details Table
		    Table detailsTable = new Table(new float[]{3, 7});
		    detailsTable.setWidth(UnitValue.createPercentValue(60));

		    detailsTable.addCell(createLabelCell("Reference No:"));
		    detailsTable.addCell(createValueCell(consultation.getReferenceNumber()));

		    detailsTable.addCell(createLabelCell("Date:"));
		    detailsTable.addCell(createValueCell(consultation.getConsultationDate()
		        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));

		    String patientName = consultation.getPatient() != null ?
		            consultation.getPatient().getName() :
		            consultation.getUnreegisteredPatientName();

		    	
		    	 detailsTable.addCell(createLabelCell("Patient:"));
				    detailsTable.addCell(createValueCell(patientName != null ? patientName : "N/A"));

		    if(consultation.getPhoneNumber() != null) {
		    	
		    	 detailsTable.addCell(createLabelCell("Phone Number:"));
				    detailsTable.addCell(createValueCell(consultation.getPhoneNumber() != null ? consultation.getPhoneNumber() : "N/A"));

		    }
		   
		   
		    detailsTable.addCell(createLabelCell("Doctor:"));
		    detailsTable.addCell(createValueCell(
		        consultation.getDoctor() != null ? consultation.getDoctor().getName() : "N/A"));

		    detailsTable.addCell(createLabelCell("Consultation Type:"));
		    detailsTable.addCell(createValueCell(consultation.getType()));

		    detailsTable.addCell(createLabelCell("Payment Type:"));
		    detailsTable.addCell(createValueCell(consultation.getPaymentType()));

		    detailsTable.addCell(createLabelCell("Amount Paid:"));
		    detailsTable.addCell(createValueCell(consultation.getAmountPaid() != null ?
		        consultation.getAmountPaid().toString() + " FCFA" : "0 FCFA"));

		    if (consultation.getNotes() != null && !consultation.getNotes().isEmpty()) {
		        detailsTable.addCell(createLabelCell("Notes:"));
		        detailsTable.addCell(createValueCell(consultation.getNotes()));
		    }

		    document.add(detailsTable);

		    document.add(new Paragraph("\n"));

		    // Footer message
		    document.add(new Paragraph("Thank you for choosing Queen Mary Hospital.")
		            .setFontSize(12)
		            .setItalic()
		            .setFontColor(new DeviceRgb(0, 102, 51))
		            .setTextAlignment(TextAlignment.CENTER));

		    document.close();

		    return new File(outputPath);
		}

		// Helper methods to style cells
		private Cell createValueCell(String text) {
		    return new Cell()
		        .add(new Paragraph(text != null ? text : "N/A"))
		        .setBorder(Border.NO_BORDER)
		        .setTextAlignment(TextAlignment.LEFT);
		}
		private Cell createLabelCell(String text) {
		    return new Cell()
		        .add(new Paragraph(text != null ? text : ""))
		        .setBorder(Border.NO_BORDER)
		        .setTextAlignment(TextAlignment.RIGHT)
		        .setBold();
		}



		public File generatePaymentReceipt(Long paymentId) throws IOException, java.io.IOException {
		    Payment payment = paymentRepository.findById(paymentId)
		            .orElseThrow(() -> new RuntimeException("Payment not found"));

		    Facture facture = payment.getFacture();
		    String outputPath = receiptDir + payment.getReference() + ".pdf";

		    PdfWriter writer = new PdfWriter(outputPath);
		    PdfDocument pdfDoc = new PdfDocument(writer);
		    Document doc = new Document(pdfDoc, PageSize.A4.rotate());
		    doc.setMargins(20, 20, 20, 20);

		    try {
		        // 1. Header with bilingual hospital info
		        Table header = new Table(new float[]{3, 2, 3});
		        header.setWidth(UnitValue.createPercentValue(100));

		        Cell left = new Cell().setBorder(Border.NO_BORDER);
		        left.add(new Paragraph("QUEEN MARY MEDICAL CENTRE / CENTRE MÉDICAL QUEEN MARY")
		                .setFontSize(14).setBold().setFontColor(new DeviceRgb(0, 51, 102)));
		        left.add(new Paragraph("For More Human Care / Pour des Soins Plus Humains").setFontSize(11).setItalic());
		        left.add(new Paragraph("RC No RC/YAO/2021/B-2230 | NUI M112116710688D").setFontSize(9));
		        left.add(new Paragraph("Shell Nsimeyong, Yaoundé").setFontSize(9));
		        left.add(new Paragraph("cmqueenmary@gmail.com").setFontSize(9));
		        header.addCell(left);

		        Cell center = new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
		        try {
		            Image logo = new Image(ImageDataFactory.create(
		                    new ClassPathResource("static/img/queen.png").getFile().getAbsolutePath()));
		            logo.setWidth(90).setHeight(50);
		            center.add(logo);
		        } catch (Exception e) {
		            center.add(new Paragraph("LOGO").setFontSize(10).setItalic());
		        }
		        header.addCell(center);

		        Cell right = new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
		        right.add(new Paragraph("Tel: (+237) 659 439 160 / 675 124 157").setFontSize(9));
		        right.add(new Paragraph("BP 31431 Yaoundé, Cameroon").setFontSize(9));
		        header.addCell(right);
		        doc.add(header);
		      //  doc.add(new Paragraph("\n"));

		        // 2. Title
		        doc.add(new Paragraph("PAYMENT RECEIPT / REÇU DE PAIEMENT")
		                .setTextAlignment(TextAlignment.CENTER)
		                .setFontSize(20)
		                .setBold()
		                .setFontColor(new DeviceRgb(0, 51, 102)));

		        doc.add(new Paragraph(payment.getReference())
		                .setTextAlignment(TextAlignment.CENTER)
		                .setFontSize(24)
		                .setBold()
		                .setFontColor(new DeviceRgb(0, 102, 204)));

		     //   doc.add(new Paragraph("\n"));

		        // 3. Patient & Invoice Info (bilingual)
		        Table info = new Table(new float[]{3, 3, 3});
		        info.setWidth(UnitValue.createPercentValue(100));

		        if (facture.getVisit() != null) {                       // =====  CLINIC  =====
		            info.addCell(headerCell("Patient / Patient"));
		            info.addCell(headerCell("Invoice No. / N° Facture"));
		            info.addCell(headerCell("Doctor / Médecin"));

		            String patientName = getSafe(() -> facture.getVisit().getPatient().getName());
		            String invoiceNo   = getSafe(() -> facture.getReferenceNumber());
		            String doctorName  = getSafe(() -> facture.getVisit().getAttendingStaff().getFirstName());

		            info.addCell(normalCell("Name: " + patientName));
		            info.addCell(normalCell(invoiceNo));
		            info.addCell(normalCell(doctorName));

		            String ageSex = "Age: " + getSafe(() -> facture.getVisit().getPatient().getAge())
		                          + " | Sex: " + getSafe(() -> facture.getVisit().getPatient().getGender());
		            String invDate = "Date: " + getSafe(() ->
		                    facture.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		            String contact = getSafe(() -> facture.getVisit().getPatient().getContact());

		            info.addCell(normalCell(ageSex));
		            info.addCell(normalCell(invDate));
		            info.addCell(normalCell(contact));

		        } else {                                                // =====  PHARMACY  =====
		            info.addCell(headerCell("Customer / Client"));
		            info.addCell(headerCell("Invoice No. / N° Facture"));
		            info.addCell(headerCell("Date"));

		            String customerName = getSafe(() -> facture.getCustomerName()); // <-- add this field if missing
		            String invoiceNo    = getSafe(() -> facture.getReferenceNumber());
		            String invDate      = getSafe(() ->
		                    facture.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

		            info.addCell(normalCell(customerName));
		            info.addCell(normalCell(invoiceNo));
		            info.addCell(normalCell(invDate));

		            // two empty rows to keep table height similar
		            info.addCell(normalCell(""));
		            info.addCell(normalCell(""));
		            info.addCell(normalCell(""));
		        }
		        doc.add(info);
		        doc.add(new Paragraph("\n"));

		        
		        if(facture.getVisit()!=null) {
		        Table svcTable = new Table(new float[]{1, 5, 1, 2});
		        svcTable.setWidth(UnitValue.createPercentValue(100));
		        svcTable.addHeaderCell(headerCell("No."));
		        svcTable.addHeaderCell(headerCell("Service / Service"));
		        svcTable.addHeaderCell(headerCell("Qty / Qté"));
		        svcTable.addHeaderCell(headerCell("Price (XAF) / Prix (XAF)"));

		        int idx = 1;
		        for (ConsultationSubtype s : facture.getVisit().getSubtypes()) {
		            svcTable.addCell(normalCell(String.valueOf(idx++)));
		            svcTable.addCell(normalCell(s.getName()));
		            svcTable.addCell(normalCell("1"));
		            svcTable.addCell(moneyCell(s.getPrice()));
		        }
		        doc.add(svcTable);
		        }
		        
		        /* =====  3b.  PHARMACY SECTION (when no visit = pharmacy sale)  ===== */
		        if (facture.getVisit() == null) {
		            Table medTable = new Table(new float[]{1, 5, 1, 2, 2});   // No | Name | Qty | Unit price | Sub-total
		            medTable.setWidth(UnitValue.createPercentValue(100));
		            medTable.addHeaderCell(headerCell("No."));
		            medTable.addHeaderCell(headerCell("Medicine / Médicament"));
		            medTable.addHeaderCell(headerCell("Qty"));
		            medTable.addHeaderCell(headerCell("Unit Price (XAF)"));
		            medTable.addHeaderCell(headerCell("Sub-Total (XAF)"));

		            int idx = 1;
		            for (FactureItem it : facture.getItems()) {          // already filled
		                medTable.addCell(normalCell(String.valueOf(idx++)));
		                medTable.addCell(normalCell(it.getDescription()));
		                medTable.addCell(normalCell(String.valueOf(it.getQuantity())));
		                medTable.addCell(normalCell((it.getUnitPrice() + "")));
		                medTable.addCell(moneyCell(it.getSubTotal()));
		            }
		            doc.add(medTable);
		            doc.add(new Paragraph("\n"));
		        }
		        // 4. Invoice Breakdown
		        Table breakdown = new Table(new float[]{3, 2, 2, 2, 2, 2});
		        breakdown.setWidth(UnitValue.createPercentValue(100));
		        breakdown.addHeaderCell(headerCell("Description"));
		        breakdown.addHeaderCell(headerCell("Amount (XAF)"));
		        breakdown.addHeaderCell(headerCell("Discount % "));
		        breakdown.addHeaderCell(headerCell("Net Amount "));
		        breakdown.addHeaderCell(headerCell("Paid"));
		        breakdown.addHeaderCell(headerCell("Balance"));

		        breakdown.addCell(normalCell("Total Invoice"));
		        breakdown.addCell(moneyCell(facture.getTotalAmount()));
		        breakdown.addCell(percentCell(facture.getDiscount()));
		        breakdown.addCell(moneyCell(facture.getNetAmount()));
		        breakdown.addCell(moneyCell(facture.getAmountPaid()));  
		        BigDecimal bal = payment.getFacture().getNetAmount().subtract(payment.getAmountPaid());
		        breakdown.addCell(normalCell("-"));

		        breakdown.addCell(normalCell("This Payment"));
		        breakdown.addCell(moneyCell(payment.getAmountPaid()));
		        breakdown.addCell(normalCell(""));
		        breakdown.addCell(normalCell(""));
		        breakdown.addCell(normalCell(""));
		        breakdown.addCell(moneyCell(facture.getBalance()));
		        doc.add(breakdown);
		      //  doc.add(new Paragraph("\n"));

		        // 5. Payment Method
		        Table payBox = new Table(new float[]{2, 2});
		        payBox.setWidth(UnitValue.createPercentValue(60));
		        payBox.setHorizontalAlignment(HorizontalAlignment.RIGHT);

		        payBox.addCell(headerCell("Payment Method / Méthode de Paiement"));
		        payBox.addCell(headerCell("Received By / Reçu Par"));

		        String staffName = getSafe(() -> payment.getReceivedBy().getStaff().getFirstName() );
		        payBox.addCell(normalCell(payment.getMethod().toString()));
		        payBox.addCell(normalCell(staffName));

		        payBox.addCell(headerCell("Amount Paid / Montant Payé"));
		        payBox.addCell(headerCell("New Balance / Nouveau Solde"));

		        payBox.addCell(moneyCell(payment.getAmountPaid()));
		        payBox.addCell(moneyCell(facture.getBalance()));
		        doc.add(payBox);
		       // doc.add(new Paragraph("\n"));

		        // 6. Services Table
		       
		        doc.add(new Paragraph("\n"));

		        // 7. Footer
		        Table footer = new Table(new float[]{4, 1});
		        footer.setWidth(UnitValue.createPercentValue(100));
		        footer.addCell(new Cell().add(new Paragraph(
		                "Thank you for choosing Queen Mary Medical Centre! / Merci d’avoir choisi Queen Mary.")
		                .setItalic().setFontSize(10)).setBorder(Border.NO_BORDER));
		        footer.addCell(new Cell().setBorder(Border.NO_BORDER)); // QR placeholder
		        doc.add(footer);

		        // 8. Watermark
		        addWatermark(pdfDoc);

		    } finally {
		        doc.close();
		        pdfDoc.close();
		    }

		    return new File(outputPath);
		}
		
		private void addWatermark(PdfDocument pdf) throws java.io.IOException {
		    PdfFont font;
		    try {
		        font = PdfFontFactory.createFont();
		    } catch (IOException e) {
		        throw new RuntimeException("Font load failed", e);
		    }

		    for (int i = 1; i <= pdf.getNumberOfPages(); i++) {
		        PdfCanvas canvas = new PdfCanvas(pdf.getPage(i));
		        canvas.saveState();
		        canvas.setFontAndSize(font, 48);
		        canvas.setColor(new DeviceRgb(200, 200, 200), true); // light gray
		        canvas.beginText();
//		        canvas.showTextAligned("QUEEN MARY MEDICAL CENTRE – CONFIDENTIAL",
//		                420, 300, i,
//		                TextAlignment.CENTER,
//		                VerticalAlignment.MIDDLE,
//		                (float) Math.toRadians(45));
		        canvas.endText();
		        canvas.restoreState();
		    }
		}

		private Cell headerCell(String text) {
		    return new Cell().add(new Paragraph(text).setBold())
		            .setBackgroundColor(Color.LIGHT_GRAY)
		            .setTextAlignment(TextAlignment.LEFT)
		            .setBorder(Border.NO_BORDER);
		}

		private Cell normalCell(String text) {
		    return new Cell().add(new Paragraph(text))
		            .setTextAlignment(TextAlignment.LEFT)
		            .setBorder(Border.NO_BORDER);
		}

		private Cell moneyCell(BigDecimal amount) {
		    return new Cell().add(new Paragraph(fmt(amount)))
		            .setTextAlignment(TextAlignment.LEFT)
		            .setBorder(Border.NO_BORDER);
		}

		private Cell percentCell(double percent) {
		    return new Cell().add(new Paragraph(fmtPercent(percent)))
		            .setTextAlignment(TextAlignment.LEFT)
		            .setBorder(Border.NO_BORDER);
		}

		private String fmt(BigDecimal bd) {
		    return new java.text.DecimalFormat("#,##0").format(bd);
		}

		private String fmtPercent(double pct) {
		    return new java.text.DecimalFormat("#0'%'").format(pct);
		}

		private <T> String getSafe(Supplier<T> supplier) {
		    try {
		        T value = supplier.get();
		        return value == null ? "" : value.toString();
		    } catch (Exception e) {
		        return "";
		    }
		}


		}

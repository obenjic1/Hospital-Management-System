package com.ppp.billing.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.StyleConstants.ColorConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.ppp.billing.model.PaymentItem;
import com.ppp.billing.model.Sale;
import com.ppp.billing.model.SaleItem;
import com.ppp.billing.model.ServiceItem;
import com.ppp.printable.PrintableElement;

@Service
public class PdfService {
	
	@Value("${folder.receipt}")
	private String receiptDir;
	
	@Value("${folder.service.receipt}")
	private String serviceReceiptDir;
	
 @Autowired
 private SalesService saleService ;
 
 @Autowired
 private PaymentItemService paymentItemService ;
 
 
 public File SendBack (Long id) throws FileNotFoundException {
	
	Sale sale = saleService.findById(id);
	  //	PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	 //   Document document = new Document(pdfDocument, PageSize.A4);
	 //   System.out.println(document.getWidth());
	 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 
	 try {
			PdfWriter pdfWriter = new PdfWriter(receiptDir+ sale.getReceiptNumber()+".pdf");
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
//			 document.setMargins(25, 25, 297-156, 50);

			PrintableElement printer = new PrintableElement();	
			
		

			 document.add(new Paragraph("Queen Mary Hospital ")
		                .setBold()
		                .setFontSize(16)
		                .setTextAlignment(TextAlignment.CENTER)
		                .setMarginBottom(20));
			 document.add(new Paragraph(" Pharmacy Receipt ")
		                .setBold()
		                .setFontSize(14)
		                .setTextAlignment(TextAlignment.CENTER)
		                .setMarginBottom(10));
          //  printer.printHeader(document, " Queen Mary Hospital");
            printer.printHeader(document, " Pharmacy Receipt");

            printer.printParagraphe(document, "Receipt No: " + sale.getReceiptNumber());
            printer.printParagraphe(document, "Customer: " + sale.getCustomerName());

            printer.printParagraphe(document, "Date: " + sale.getSaleDate());

            printer.printParagraphe(document, "Payment: " + sale.getPaymentMethod());


            Table table = new Table(4);
            table.addCell("Medicine");
            table.addCell("Qty");
            table.addCell("Price");
            table.addCell("Total");

            for (SaleItem item : sale.getItems()) {
                table.addCell(item.getMedicine().getName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(item.getMedicine().getPrice().toString());
                table.addCell(item.getSubtotal().toString());
            }

            Cell totalCell = new Cell(1, 4)  // 1 row, spans 4 columns
                    .add("Grand Total: " + sale.getTotal())
                    .setTextAlignment(TextAlignment.RIGHT);

            table.addCell(totalCell);
            

            printer.printTable(document, table);
            document.add(new Paragraph("\nThank you for your purchase!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setItalic());
            document.close();
            
            
			File file = new File(receiptDir+ sale.getReceiptNumber()+".pdf");


          
			 return file;

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF receipt", e);
        }
    }
 
 
 

    public  File generateReceipt(long id) throws FileNotFoundException {
        // 1. Create PDF writer + document
    	Sale sale = saleService.findById(id);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(receiptDir+sale.getReceiptNumber()+ ".pdf");
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // 2. Title
        document.add(new Paragraph("Queen Mary Hospital Pharmacy Receipt")
                .setBold()
                .setFontSize(16)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        // 3. Sale Info
        document.add(new Paragraph("Receipt No: " + sale.getReceiptNumber()));
        document.add(new Paragraph("Customer: " + sale.getCustomerName()));
        document.add(new Paragraph("Pharmacist: " + sale.getPharmacist().getUsername()));
        document.add(new Paragraph("Date: " + sale.getSaleDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        document.add(new Paragraph("Payment Method: " + sale.getPaymentMethod()));
        document.add(new Paragraph("\n"));

    
        // 4. Table Header (5 columns)
        float[] columnWidths = {200F, 80F, 80F, 100F};
        Table table = new Table(columnWidths);
        table.addHeaderCell(new Cell().add("Medicine").setBold());
        table.addHeaderCell(new Cell().add("Qty").setBold());
        table.addHeaderCell(new Cell().add("Unit Price").setBold());
//        table.addHeaderCell(new Cell().add("Discount").setBold());
        table.addHeaderCell(new Cell().add("Total").setBold());

        // 5. Table Rows (Sale Items)
        for (SaleItem item : sale.getItems()) {
            BigDecimal itemTotal = item.getMedicine().getPrice().multiply(new BigDecimal(item.getQuantity()));
//            BigDecimal discount = item.getDiscount() != null ? item.getDiscount() : BigDecimal.ZERO;

            table.addCell(new Cell().add(item.getMedicine().getName()));
            table.addCell(new Cell().add(String.valueOf(item.getQuantity())));
            table.addCell(new Cell().add(item.getMedicine().getPrice().toString()));
//            table.addCell(new Cell().add(discount.toString()));
//            table.addCell(new Cell().add(itemTotal.subtract(discount).toString()));
        }

        // 6. Grand Total Row
        table.addCell(new Cell(1, 4).add("").setBorder(Border.NO_BORDER)); // empty space
        table.addCell(new Cell().add("------------"));

        table.addCell(new Cell(1, 4).add("Grand Total").setBold().setTextAlignment(TextAlignment.RIGHT));
        table.addCell(new Cell().add(sale.getTotal().toString()).setBold());

        // 7. Add table to document
        document.add(table);

        // 8. Footer
        document.add(new Paragraph("\nThank you for your purchase!")
                .setTextAlignment(TextAlignment.CENTER)
                .setItalic());
	
				document.close();
				File file = new File(receiptDir+ sale.getReceiptNumber()+".pdf");

			 return file;
			} 
			




		public File printRecept(Long paymentId) throws FileNotFoundException {
			PaymentItem payment = paymentItemService.getPaymentById(paymentId);
			  //	PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			 //   Document document = new Document(pdfDocument, PageSize.A4);
			 //   System.out.println(document.getWidth());
			 
				//	ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 
			
					 PdfWriter writer = new PdfWriter(receiptDir+payment.getReferenceNumber()+ ".pdf");
				        PdfDocument pdfDoc = new PdfDocument(writer);
				        Document document = new Document(pdfDoc);

				        // 2. Title
				        document.add(new Paragraph("Queen Mary Hospital ")
				                .setBold()
				                .setFontSize(16)
				                .setTextAlignment(TextAlignment.CENTER)
				                .setMarginBottom(8));
				        document.add(new Paragraph("Behind Dove Nsimeyong , Yaounde -Cameroon | Tel: +237 695 697 830  +237 679 625 303")
				                .setFontSize(12)
				                .setTextAlignment(TextAlignment.CENTER));
				        
				        document.add(new Paragraph("PAYMENT RECEIPT")
				                .setFontSize(12)
				                .setBold()
				                .setTextAlignment(TextAlignment.CENTER));
				    //    document.add(new Paragraph("\n"));

				        // Patient & payment info
				        document.add(new Paragraph("Receipt No: " + payment.getReferenceNumber()));
				        document.add(new Paragraph("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
				        
				        String name = "";
				        if (payment.getPatient() != null && payment.getPatient().getName() != null && !payment.getPatient().getName().isEmpty()) {
				            name = payment.getPatient().getName();
				        } else {
				            name = payment.getUnregisteredPatientName();
				        }
				        document.add(new Paragraph("Patient: " + name));
				        document.add(new Paragraph("Cashier:  Cashier" ));
//				        document.add(new Paragraph("\n"));

				      //  document.add(new Paragraph("Pharmacist: " + sale.getPharmacist().getUsername()));
//				        document.add(new Paragraph("Date: " +payment.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
				        document.add(new Paragraph("Payment Method: " +payment.getPaymentMethod()));
				        document.add(new Paragraph("Payment Status: " +payment.getStatus()));

				        document.add(new Paragraph("\n"));
				       
				        
				        Table table = new Table(new float[]{4, 2, 2, 2});
				        table.setWidthPercent(100);

				        table.addHeaderCell(new Cell().add(new Paragraph("Service Description").setBold()));
				        table.addHeaderCell(new Cell().add(new Paragraph("Unit Price").setBold()));
				        table.addHeaderCell(new Cell().add(new Paragraph("Qty").setBold()));
				        table.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));

				        for (ServiceItem item : payment.getServiceItems()) {
				            table.addCell(item.getName());
				            table.addCell(String.format("%.2f", item.getPrice()));
				            table.addCell("1"); // or actual qty
				            table.addCell(String.format("%.2f", item.getPrice())); // price * qty
				        }

				        document.add(table);

				        document.add(new Paragraph("\n"));

				        // Total
				        document.add(new Paragraph("Grand Total: " + String.format("%.2f", payment.getAmount()) + " FCFA")
				                .setFontSize(12)
				                .setBold()
				                .setTextAlignment(TextAlignment.RIGHT));
				        document.add(new Paragraph("\n"));

				        // Footer
				        document.add(new Paragraph("Thank you for choosing Queen Mary Hospital.")
				                .setFontSize(10)
				                .setItalic()
				                .setTextAlignment(TextAlignment.CENTER));
								document.close();
								File file = new File(receiptDir+ payment.getReferenceNumber()+".pdf");
				
			return file;
		}
   
}

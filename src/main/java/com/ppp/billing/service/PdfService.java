package com.ppp.billing.service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.ppp.billing.model.Sale;
import com.ppp.billing.model.SaleItem;
import com.ppp.printable.PrintableElement;

public class PdfService {
	
	@Value("${folder.receipt}")
	private String receiptDir;
	

	
	 public byte[] generateReceiptPdf(Sale sale) {
		 
		
		 
	        try {PdfWriter pdfWriter = new PdfWriter(receiptDir+sale.getReceiptNumber()+ ".pdf");
		     	PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		  	    Document document = new Document(pdfDocument, PageSize.A4);
	        	
	        	
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
				PrintableElement printer = new PrintableElement();	

	            
	    	    PdfFont fontTitle = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
	    	    PdfFont fontNormal = PdfFontFactory.createFont(FontConstants.HELVETICA,"12");


	            printer.printHeader(document, "Hospital Pharmacy Receipt");
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
	            document.close();

	            return  out.toByteArray();

	        } catch (Exception e) {
	            throw new RuntimeException("Error generating PDF receipt", e);
	        }
	    }
	 

	    public  byte[] generateReceipt(Sale sale) throws FileNotFoundException {
	        // 1. Create PDF writer + document
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();

	        PdfWriter writer = new PdfWriter(receiptDir+sale.getReceiptNumber()+ ".pdf");
	        PdfDocument pdfDoc = new PdfDocument(writer);
	        Document document = new Document(pdfDoc);

	        // 2. Title
	        document.add(new Paragraph("Hospital Pharmacy Receipt")
	                .setBold()
	                .setFontSize(16)
	                .setTextAlignment(TextAlignment.CENTER)
	                .setMarginBottom(20));

	        // 3. Sale Info
	        document.add(new Paragraph("Receipt No: " + sale.getReceiptNumber()));
	        document.add(new Paragraph("Customer: " + sale.getCustomerName()));
	        document.add(new Paragraph("Pharmacist: " + sale.getPharmacist().getFirstName()));
	        document.add(new Paragraph("Date: " + sale.getSaleDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
	        document.add(new Paragraph("Payment Method: " + sale.getPaymentMethod()));
	        document.add(new Paragraph("\n"));

	        // 4. Table Header (5 columns)
	        float[] columnWidths = {200F, 80F, 80F, 100F};
	        Table table = new Table(columnWidths);
	        table.addHeaderCell(new Cell().add("Medicine").setBold());
	        table.addHeaderCell(new Cell().add("Qty").setBold());
	        table.addHeaderCell(new Cell().add("Unit Price").setBold());
//	        table.addHeaderCell(new Cell().add("Discount").setBold());
	        table.addHeaderCell(new Cell().add("Total").setBold());

	        // 5. Table Rows (Sale Items)
	        for (SaleItem item : sale.getItems()) {
	            BigDecimal itemTotal = item.getMedicine().getPrice().multiply(new BigDecimal(item.getQuantity()));
//	            BigDecimal discount = item.getDiscount() != null ? item.getDiscount() : BigDecimal.ZERO;

	            table.addCell(new Cell().add(item.getMedicine().getName()));
	            table.addCell(new Cell().add(String.valueOf(item.getQuantity())));
	            table.addCell(new Cell().add(item.getMedicine().getPrice().toString()));
//	            table.addCell(new Cell().add(discount.toString()));
//	            table.addCell(new Cell().add(itemTotal.subtract(discount).toString()));
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
	        return baos.toByteArray();
	    }

}

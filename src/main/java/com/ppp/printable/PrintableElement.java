package com.ppp.printable;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import net.sf.saxon.ma.arrays.ArrayFunctionSet.ArrayFoldRight;

public class PrintableElement {
	private final float mmToPoint = 2.83465f;
	

	private String message;
	private float xAxis;
	private float yAxis;
	private double money;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public float getxAxis() {
		return xAxis;
	}
	public void setxAxis(float xAxis) {
		this.xAxis = xAxis*mmToPoint;
	}
	public float getyAxis() {
		return yAxis;
	}
	public void setyAxis(float yAxis) {
		this.yAxis = yAxis*mmToPoint;
	}
		

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void print(Document document) {
		
		document.add(new Paragraph(this.message).setFixedPosition(xAxis, yAxis, 595));
	
	}
	
  public void print(Document document, String message, float xAxis, float yAxis) throws IOException {
	    PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.message= message;
		document.setFont(font);
		document.add(new Paragraph(this.message).setFixedPosition(this.xAxis, this.yAxis, 595));
	
	}
  public void printParagraphe(Document document, String message, float xAxis, float yAxis) throws IOException {
	    PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.message= message;
		document.setFont(font);
		Paragraph  paragraph = new Paragraph(this.message).setFixedPosition(this.xAxis, this.yAxis, 595);
//		paragraph.setTextAlignment(TextAlignment)
		//paragraph.setMarginRight(300);
		paragraph.setPaddingRight(250);
		document.add(paragraph);
	
	}
  
  

  public void print(Document document, Table table, float xAxis, float yAxis) throws IOException {
	    PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		document.setFont(font);
		document.add(table.setFixedPosition(this.xAxis, this.yAxis, 595));
	
	}
  
  public void printHeader(Document document, String message, float xAxis, float yAxis) throws IOException {
	    PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.message= message;
		document.setFont(font);
		document.add(new Paragraph(this.message).setFixedPosition(this.xAxis, this.yAxis, 595));
	
	}
  
  public void printMoney(Document document, double money, float xAxis, float yAxis) throws IOException {
	    PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.money= (long) money;
		String message =money+"";
		Locale locale = new Locale("en", "EN");
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		 numberFormat.format(this.money);
		document.setFont(font);
		Paragraph  paragraph = new Paragraph(numberFormat.format(this.money)).setFixedPosition(this.xAxis, this.yAxis, 595);
		paragraph.setPaddingRight(250);
		//paragraph.setTextAlignment();
		document.add(paragraph);
	
	}

public void printJobRef(Document document, String message, int i, float f) throws IOException {
	PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER);
	this.setxAxis(i);
	this.setyAxis(f);
	document.setFont(font);
	document.add(new Paragraph(message).setFontColor(Color.RED).setFixedPosition(this.xAxis, this.yAxis, 595));
	
}
 
}

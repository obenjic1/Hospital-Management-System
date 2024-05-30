package com.ppp.printable;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PrintableElement {
	private final float mmToPoint = 2.83465f;
	
	private String message ;
	private float xAxis;
	private float yAxis;
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
	
	public void print(Document document) {
		
		document.add(new Paragraph(this.message).setFixedPosition(xAxis, yAxis, 595));
	
	}
	
  public void print(Document document, String message, float xAxis, float yAxis) {
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.message= message;
		document.add(new Paragraph(this.message).setFixedPosition(this.xAxis, this.yAxis, 595));
	
	}

}

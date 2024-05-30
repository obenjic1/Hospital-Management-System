package com.ppp.printable;

import java.util.Hashtable;

import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.PrintingMachine;

public class PlateMakingCosting {
	private int plates;
	private int basic;
	private double signatures;
	private PrintingMachine printingMachine;
	private JobPaper jobPaper;
	private Hashtable<Integer, Integer> gtoBasicValues = new Hashtable<Integer, Integer>();
	private Hashtable<Integer, Integer> soSpBasicValues = new Hashtable<Integer, Integer>();
	{
		gtoBasicValues.put(2, 450);
		gtoBasicValues.put(4, 1300);
		gtoBasicValues.put(8, 1800);
		gtoBasicValues.put(16, 2500);
		
		soSpBasicValues.put(2, 800);
		soSpBasicValues.put(4, 1800);
		soSpBasicValues.put(8, 2000);
		soSpBasicValues.put(16, 2500);
		soSpBasicValues.put(32, 3500);
	}
	
	
	public PlateMakingCosting(JobPaper jobPaper) {
		this.jobPaper = jobPaper;
		this.printingMachine = jobPaper.getJobColorCombinations().get(0).getPrintingMachine();
		float machinePlateLength = printingMachine.getPlateLength();
		float machinePlateWidth = printingMachine.getPlateLength();
		float closeLength = (float) jobPaper.getJob().getCloseLength();
		float closeWidth = (float) jobPaper.getJob().getCloseWidth();
		double logP = Math.log((machinePlateLength*machinePlateWidth)/(closeLength*closeWidth));
		logP = logP/Math.log(2);
		logP= Math.floor(logP)+1;
		double pagesPerSignature = Math.pow(2,logP);
		this.basic = (int) (pagesPerSignature/2);
		plates=0;
		jobPaper.getJobColorCombinations().forEach(colorCombination->{
			int signature = (int) Math.ceil(colorCombination.getNumberOfSignature());
			this.signatures+=colorCombination.getNumberOfSignature();
			plates += signature*(colorCombination.getBackColorNumber()+colorCombination.getFrontColorNumber());
		});
	}
	public float generateBasicCost() {
		
		if(this.jobPaper.getContentType().getName().equals("Cover"))
			this.basic = 2;
		if(printingMachine.getAbbreviation().equals("GTO")) {
			if(this.basic > 16)
				return gtoBasicValues.get(16);
			else {
				return  gtoBasicValues.get(this.basic);
			}
		}
		else
		return soSpBasicValues.get(this.basic);
	}
	
	public float generateExposureCost() {
		
		
		if(printingMachine.getAbbreviation().equals("GTO"))
			return this.plates*3800;
			else
			return this.plates*5000;
	}
	
	public static int getPlates(JobPaper jobPaper) {
		int plates=0;
		for(JobColorCombination colorCombination: jobPaper.getJobColorCombinations()) {
			int signature =(int) Math.ceil(colorCombination.getNumberOfSignature());
			plates+= signature*(colorCombination.getBackColorNumber()+colorCombination.getFrontColorNumber());
		};
		return plates;
	}
	
	
	
	public int getPlates() {
		return plates;
	}
	public void setplateChangePlates(int plates) {
		this.plates = plates;
	}
	public int getBasic() {
		return basic;
	}
	public void setBasic(int basic) {
		this.basic = basic;
	}
	public double getSignatures() {
		return signatures;
	}
	public void setSignatures(double signatures) {
		this.signatures = signatures;
	}
	public PrintingMachine getPrintingMachine() {
		return printingMachine;
	}
	public void setPrintingMachine(PrintingMachine printingMachine) {
		this.printingMachine = printingMachine;
	}
	public JobPaper getJobPaper() {
		return jobPaper;
	}
	public void setJobPaper(JobPaper jobPaper) {
		this.jobPaper = jobPaper;
	}
	public Hashtable<Integer, Integer> getGtoBasicValues() {
		return gtoBasicValues;
	}
	public void setGtoBasicValues(Hashtable<Integer, Integer> gtoBasicValues) {
		this.gtoBasicValues = gtoBasicValues;
	}
	public Hashtable<Integer, Integer> getSoSpBasicValues() {
		return soSpBasicValues;
	}
	public void setSoSpBasicValues(Hashtable<Integer, Integer> soSpBasicValues) {
		this.soSpBasicValues = soSpBasicValues;
	}
	

}

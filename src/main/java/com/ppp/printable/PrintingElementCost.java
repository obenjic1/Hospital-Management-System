package com.ppp.printable;


import java.util.Hashtable;

import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.PrintingMachine;

public class PrintingElementCost {
	
	private int basic;
	private String preparation;
	private double plateChange;
	private int inckChange;
	private double run;
	private int trimming;
	private int numbering;
	private int perforating;
	private int ups;
	private float upsUnitCost;
	private int finishinFolde;
	private int finishingRun;
	private String paperFormat;	
	private float creasinPreparetion1;
	private float creasinPreparetion2;
	private JobColorCombination colorCombination;
	private Hashtable<String, Integer> gtoPrintTypeCosting = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> sormPrintTypeCosting = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> spPrintTypeCosting = new Hashtable<String, Integer>();
	private Hashtable<Integer, Float> upsUnitPrice100g = new Hashtable<Integer, Float>();
	private Hashtable<Integer, Float> upsUnitPrice200g = new Hashtable<Integer, Float>();
	private Hashtable<Integer, Float> upsUnitPrice400g = new Hashtable<Integer, Float>();
	{
		gtoPrintTypeCosting.put("BWK", 2700);
		gtoPrintTypeCosting.put("SCRN", 3600);
		gtoPrintTypeCosting.put("SCRN20", 4500);
		gtoPrintTypeCosting.put("SCRN20+", 5400);
		gtoPrintTypeCosting.put("POLY", 5400);
		gtoPrintTypeCosting.put("POLY20", 6400);
		gtoPrintTypeCosting.put("POLY20+", 7400);
		
		sormPrintTypeCosting.put("BWK", 4500);
		sormPrintTypeCosting.put("SCRN", 5200);
		sormPrintTypeCosting.put("SCRN20", 6000);
		sormPrintTypeCosting.put("SCRN20+", 9000);
		sormPrintTypeCosting.put("POLY", 7500);
		sormPrintTypeCosting.put("POLY20", 8200);
		sormPrintTypeCosting.put("POLY20+", 9700);
		
		spPrintTypeCosting.put("BWK", 11700);
		spPrintTypeCosting.put("SCRN", 12700);
		spPrintTypeCosting.put("SCRN20", 14800);
		spPrintTypeCosting.put("SCRN20+", 23200);
		spPrintTypeCosting.put("POLY", 19000);
		spPrintTypeCosting.put("POLY20", 21000);
		spPrintTypeCosting.put("POLY20+", 25000);
		
		upsUnitPrice100g.put(0, 0.0f);
		upsUnitPrice100g.put(1, 0.9f);
		upsUnitPrice100g.put(2, 1.2f);
		upsUnitPrice100g.put(4, 1.4f);
		upsUnitPrice100g.put(8, 1.7f);
		upsUnitPrice100g.put(16, 2.3f);
		upsUnitPrice100g.put(24, 3.2f);
		upsUnitPrice100g.put(32, 3.7f);
		
		upsUnitPrice200g.put(0, 0.0f);
		upsUnitPrice200g.put(1, 0.9f);
		upsUnitPrice200g.put(2, 1.2f);
		upsUnitPrice200g.put(4, 1.4f);
		upsUnitPrice200g.put(8, 1.7f);
		upsUnitPrice200g.put(16, 2.3f);
		upsUnitPrice200g.put(24, 3.2f);
		upsUnitPrice200g.put(32, 3.7f);
		
		upsUnitPrice400g.put(0, 0.0f);
		upsUnitPrice400g.put(1, 0.9f);
		upsUnitPrice400g.put(2, 1.2f);
		upsUnitPrice400g.put(4, 1.4f);
		upsUnitPrice400g.put(8, 1.7f);
		upsUnitPrice400g.put(16, 2.3f);
		upsUnitPrice400g.put(24, 3.2f);
		upsUnitPrice400g.put(32, 3.7f);

	}
	
	
	public PrintingElementCost(JobColorCombination colorCombination) {
		this.colorCombination = colorCombination;
		this.basic = colorCombination.getJobPaper().getGrammage();
		double signature =colorCombination.getNumberOfSignature();
		double signTeorique =signature;
		if(colorCombination.getJobPaper().getContentType().getId()==2&&colorCombination.getJobPaper().getJob().getJobType().getCategory()==3) {
			signature = 1;
		}
		this.plateChange= Math.ceil(signature)*(colorCombination.getBackColorNumber()+colorCombination.getFrontColorNumber())-1;
		this.inckChange = colorCombination.getBackColorNumber() > colorCombination.getFrontColorNumber() ? colorCombination.getBackColorNumber()-1 : colorCombination.getFrontColorNumber()-1;
		this.run= Math.ceil(signTeorique)*(colorCombination.getBackColorNumber()+colorCombination.getFrontColorNumber());
		if(colorCombination.getPrintingMachine().getAbbreviation().equals("SPM5")) {
			this.run = Math.ceil((this.run)/4.0);
		}
		run = Math.ceil(run);
		this.preparation = colorCombination.getPrintType().getName();
		this.numbering = colorCombination.getJobPaper().getJob().getJobActivity().getXNumbered();
		this.perforating = colorCombination.getJobPaper().getJob().getJobActivity().getXPerforated();
		PrintingMachine printingMachine = colorCombination.getPrintingMachine();
		float machinePlateLength = printingMachine.getPlateLength();
		float machinePlateWidth = printingMachine.getPlateLength();
		
		double minimumLenght = Math.min(Math.min( colorCombination.getJobPaper().getPaperSizeWidth()-20,  colorCombination.getJobPaper().getPaperSizeLength()-20),Math.min(machinePlateLength, machinePlateWidth));
		double maximumLength =  Math.min(Math.max( colorCombination.getJobPaper().getPaperSizeWidth()-20,  colorCombination.getJobPaper().getPaperSizeLength()-20),Math.max(machinePlateLength, machinePlateWidth));
	 	machinePlateLength = (float) minimumLenght;
	 	machinePlateWidth =  (float)maximumLength;
	 	
		
		float closeLength = (float) colorCombination.getJobPaper().getJob().getCloseLength();
		float closeWidth = (float) colorCombination.getJobPaper().getJob().getCloseWidth();
		double logP = Math.log((machinePlateLength*machinePlateWidth)/(closeLength*closeWidth));
		logP = logP/Math.log(2);
		logP= Math.floor(logP)+1;
		double pagesPerSignature = Math.pow(2,logP);
		this.ups = (int) (pagesPerSignature/4);
		this.creasinPreparetion1 = 1000*2;
		this.creasinPreparetion2 = 1000*4;
		colorCombination.getJobPaper().getJob().getJobPapers().forEach(jobPaper->{
				PlateMakingCosting plcM = new PlateMakingCosting(jobPaper);
				this.finishinFolde = Math.max(this.finishinFolde, (int) (Math.log(plcM.getBasic())/Math.log(2)));
		});
		
		this.finishingRun = (int) Math.ceil(signature);
		
		double closewidthFormat = colorCombination.getJobPaper().getJob().getOpenWidth();
		if(closewidthFormat<=74)
			this.paperFormat = 8+"";
		else if(closewidthFormat<=105)
			this.paperFormat = 7+"";
		else if(closewidthFormat<=145)
			this.paperFormat = 6+"";
		else if(closewidthFormat<=210)
			this.paperFormat = 5+"";
		else if(closewidthFormat<=297)
			this.paperFormat = 4+"";
		else if(closewidthFormat<=420)
			this.paperFormat = 3+"";
		else if(closewidthFormat>594)
			this.paperFormat = 2+"";
		
	}

	
	public float getBasicUnitCost() {
		String machine = this.colorCombination.getPrintingMachine().getAbbreviation();
		switch (machine) {
		case "GTO":
			return this.basic<70? 2300: this.basic<160?1800:2300;
		case "SO":
			return this.basic<70? 8300: this.basic<160?7600:9100;
		default:
			return this.basic<70?13900: this.basic<160?12900:15000;

			
		}
	}
	
	public float getPreparationUnitCost() {
		String machine = this.colorCombination.getPrintingMachine().getAbbreviation();
		switch (machine) {
		case "GTO":
			return gtoPrintTypeCosting.get(this.colorCombination.getPrintType().getAbreviation());
		case "SO":
			return sormPrintTypeCosting.get(this.colorCombination.getPrintType().getAbreviation());
		default:
			return spPrintTypeCosting.get(this.colorCombination.getPrintType().getAbreviation());
		}
	}
	
	public float getInckChangeUnitCost() {
		String machine = this.colorCombination.getPrintingMachine().getAbbreviation();
		switch (machine) {
		case "GTO":
			return colorCombination.getPrintType().getAbreviation().equals("BWK")? 0:colorCombination.getPrintType().getAbreviation().equals("POLY")?2800:5600;
		case "SPM":
			return colorCombination.getPrintType().getAbreviation().equals("BWK")? 0:colorCombination.getPrintType().getAbreviation().equals("POLY")?9700:13000;
		default:
			return colorCombination.getPrintType().getAbreviation().equals("BWK")? 0:colorCombination.getPrintType().getAbreviation().equals("POLY")?5300:7700;
		}
	}
	
	public float getPlateChangeUnitCost() {
		String machine = this.colorCombination.getPrintingMachine().getAbbreviation();
		switch (machine) {
		case "GTO":
			return colorCombination.getPrintType().getAbreviation().equals("BWK")? 2000:colorCombination.getPrintType().getAbreviation().indexOf("POLY")!=-1?4700:3400;
		case "SO":
			return colorCombination.getPrintType().getAbreviation().equals("BWK")? 3500:colorCombination.getPrintType().getAbreviation().indexOf("POLY")!=-1?7200:4500;
		default:
			return colorCombination.getPrintType().getAbreviation().equals("BWK")? 8800:colorCombination.getPrintType().getAbreviation().indexOf("POLY")!=-1?19000:11700;
		}
	}
	
	public float getRunUnitCost() {
		String machine = this.colorCombination.getPrintingMachine().getAbbreviation();
		switch (machine) {
		case "GTO":
			if(this.colorCombination.getPrintType().getAbreviation().equals("BWK")) {
					return this.basic<70?5400:this.basic<160?4000:4400;
			}
			if(this.colorCombination.getPrintType().getAbreviation().indexOf("POLY")!=-1) {
				if(colorCombination.getJobPaper().getPaperType().getName().toLowerCase().indexOf("Glazed".toLowerCase())!=-1)
					return 6200;
				else
					return this.basic<160?5200:5600;
			}
			else
				if(colorCombination.getJobPaper().getPaperType().getName().toLowerCase().indexOf("Glazed".toLowerCase())!=-1)
					  return 5400;
					else
					 return this.basic<70?6000:basic<160?4600:5000;
		case "SO":
			
			if(this.colorCombination.getPrintType().getAbreviation().equals("BWK")) {
				return this.basic<70?6800:this.basic<160?5200:5800;
		}
		if(this.colorCombination.getPrintType().getAbreviation().indexOf("POLY")!=-1) {
			if(colorCombination.getJobPaper().getPaperType().getName().toLowerCase().indexOf("Glazed".toLowerCase())!=-1)
				return 7800;
			else
				return this.basic<160?6400:7000;
		}
		else
			if(colorCombination.getJobPaper().getPaperType().getName().toLowerCase().indexOf("Glazed".toLowerCase())!=-1)
				  return 7200;
				else
				 return this.basic<70?7600:basic<160?6000:6600;
			
		default:
			/*
			 * debut
			 */
			if(this.colorCombination.getPrintType().getAbreviation().equals("BWK")) {
				return this.basic<70?9800:this.basic<160?7600:8200;
		}
		if(this.colorCombination.getPrintType().getAbreviation().indexOf("POLY")!=-1) {
			if(colorCombination.getJobPaper().getPaperType().getName().toLowerCase().indexOf("Glazed".toLowerCase())!=-1)
				return 10800;
			else
				return this.basic<160?9200:9800;
		}
		else
			if(colorCombination.getJobPaper().getPaperType().getName().toLowerCase().indexOf("Glazed".toLowerCase())!=-1)
				  return 10400;
				else
				 return this.basic<70?11000:basic<160?8600:9400;
			
			/*
			 * Fin
			 */
			
		}
	}
	
	public float getFoldedFixeCost() {
		return this.finishinFolde<1?0:this.finishinFolde==1?4600:this.finishinFolde==2?7100:8600;
	}
	
	public float getFinishingRunFixCost() {
		return 2500;
	}
	
	public float getFinishingRunVaribleCost() {
		return ((int) Math.ceil(colorCombination.getNumberOfSignature()))*(getFinishingRunFixCost());
	}

	
	public float getTrimmingUpsUnitCost() {
		if(this.basic<=100)
		return upsUnitPrice100g.get(this.ups);
		if(this.basic<=200)
			return upsUnitPrice200g.get(this.ups);
		return upsUnitPrice400g.get(this.ups);
	}
	
	public float getTrimmingFixCost() {
		return 1000;
		
	}
	public float getTrimmingVarCost() {
		return this.getTrimmingUpsUnitCost()*500;
		
	}
	

	public float getFixNumberingCoast() {
		
		return this.numbering*0.5f;
	}
	
	public float getVarNumberingCoast() {
		return this.numbering*1000;
	}
	
	
	public float getFixPerforatingCost() {
		return this.perforating*0.5f;
	}
	
	public float getVarPerforatingCost() {
		return this.perforating*1000;
	}
	
	
	
	public int getBasic() {
		return basic;
	}


	public void setBasic(int basic) {
		this.basic = basic;
	}


	public String getPreparation() {
		return preparation;
	}


	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}


	public double getPlateChange() {
		return plateChange;
	}


	public void setPlateChange(int plateChange) {
		this.plateChange = plateChange;
	}


	public int getInckChange() {
		return inckChange;
	}


	public void setInckChange(int inckChange) {
		this.inckChange = inckChange;
	}


	public double getRun() {
		return run;
	}


	public void setRun(int run) {
		this.run = run;
	}


	public JobColorCombination getColorCombination() {
		return colorCombination;
	}


	public void setColorCombination(JobColorCombination colorCombination) {
		this.colorCombination = colorCombination;
	}


	public int getTrimming() {
		return trimming;
	}


	public void setTrimming(int trimming) {
		this.trimming = trimming;
	}


	public int getNumbering() {
		return numbering;
	}


	public void setNumbering(int numbering) {
		this.numbering = numbering;
	}


	public int getPerforating() {
		return perforating;
	}


	public void setPerforating(int perforating) {
		this.perforating = perforating;
	}


	public int getUps() {
		return ups;
	}


	public void setUps(int ups) {
		this.ups = ups;
	}


	public void setUpsUnitCost(float upsUnitCost) {
		this.upsUnitCost = upsUnitCost;
	}


	public float getCreasinPreparetion1() {
		return creasinPreparetion1;
	}


	public void setCreasinPreparetion1(float creasinPreparetion1) {
		this.creasinPreparetion1 = creasinPreparetion1;
	}


	public float getCreasinPreparetion2() {
		return creasinPreparetion2;
	}


	public void setCreasinPreparetion2(float creasinPreparetion2) {
		this.creasinPreparetion2 = creasinPreparetion2;
	}


	public int getFinishinFolde() {
		return finishinFolde;
	}


	public void setFinishinFolde(int finishinFoldes) {
		this.finishinFolde = finishinFoldes;
	}

	public String getPaperFormat() {
		return paperFormat;
	}


	public void setPaperFormat(String paperFormat) {
		this.paperFormat = paperFormat;
	}


	public int getFinishingRun() {
		return finishingRun;
	}


	public void setFinishingRun(int finishingRun) {
		this.finishingRun = finishingRun;
	}

	
	
	
}

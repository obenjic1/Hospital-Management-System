package com.ppp.billing.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.net.MalformedURLException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.EstimateDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.serviceImpl.BindingTypeserviceImpl;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;
import com.ppp.billing.serviceImpl.JobColorCombinationServiceImpl;
import com.ppp.billing.serviceImpl.JobEstimateServiceImpl;
import com.ppp.billing.serviceImpl.JobPaperServiceImpl;
import com.ppp.billing.serviceImpl.JobServiceImpl;
import com.ppp.billing.serviceImpl.JobTypeServiceImpl;
import com.ppp.billing.serviceImpl.PaperFormatServiceImpl;
import com.ppp.billing.serviceImpl.PaperGrammageServiceImpl;
import com.ppp.billing.serviceImpl.PaperTypeServiceImpl;
import com.ppp.billing.serviceImpl.PrintTypeServiceImpl;
import com.ppp.billing.serviceImpl.PrintingMachineServiceImpl;
import com.ppp.printable.PlateMakingCosting;
import com.ppp.printable.PrintableElement;
import com.ppp.printable.PrintingElementCost;

@Controller
@RequestMapping("/job")
@CrossOrigin(origins="*")
public class JobController {
	
	@Value("${folder.image.background}")
	private String backgroundFolder;
	@Value("${folder.controlSheet}")
	private String controlSheetDir;
	@Autowired
	private JobServiceImpl jobServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private JobTypeServiceImpl jobTypeServiceImpl;
	@Autowired
	private PaperFormatServiceImpl paperFormatServiceImpl;
	@Autowired
	private JobPaperServiceImpl jobPaperServiceImpl;
	@Autowired
	private PaperTypeServiceImpl paperTypeServiceImpl;
	@Autowired
	private PrintingMachineServiceImpl printingMachineServiceImpl;
	@Autowired
	private PrintTypeServiceImpl printTypeServiceImpl;
	@Autowired
	private JobColorCombinationServiceImpl jobColorCombinationServiceImpl;
	@Autowired 
	private PaperGrammageServiceImpl paperGrammageServiceImpl;
	@Autowired
	private BindingTypeserviceImpl bindingTypeserviceImpl;
	@Autowired
	private JobEstimateServiceImpl jobEstimateServiceImpl;
	
//<--------------------- Collect datas form @Vincent------------------------------>
	@GetMapping("/displayform")
	public String displayFormInterface(Model model) {
		List<Customer> customerResult = customerServiceImpl.findAll();
		List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
		List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
		List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
		List<PaperType>  paperTypeResult = paperTypeServiceImpl.listAll();
		List<PrintingMachine> printingMachineResult = printingMachineServiceImpl.listMachines();
		List<PrintType> printTypeResult = printTypeServiceImpl.findAll();
		List<JobColorCombination> jobColorCombinationResult = jobColorCombinationServiceImpl.findAll();
		List<PaperGrammage> paperGrammageResult = paperGrammageServiceImpl.findAll();
		List<BindingType> bindingTypeResult = bindingTypeserviceImpl.listAll();

		
		model.addAttribute("customers", customerResult);
		model.addAttribute("jobTypes", jobTypeResult);
		model.addAttribute("paperFormats", paperFormatResult);
		model.addAttribute("bindingTypes", bindingTypeResult);
		model.addAttribute("jobPaperResults", jobPaperResult);
		model.addAttribute("paperTypes", paperTypeResult);
		model.addAttribute("printingMachines", printingMachineResult);
		model.addAttribute("printTypes", printTypeResult);
		model.addAttribute("jobColorCombinations", jobColorCombinationResult);
		model.addAttribute("paperGrammages", paperGrammageResult);
		
		return "billing/display-job-form-interface";
	}

//<--------------------- Save data collected to the data base @Vincent ------------------------------>
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> saveJob(@RequestBody JobDTO jobDTO,Model model){
		try {
			jobServiceImpl.saveJob(jobDTO);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("KO" + e.getMessage());
		}
	}

	@GetMapping("/list-job")
	public String listJob(Model model) {
		List<Job> result = jobServiceImpl.listAllJob();
		model.addAttribute("jobs", result);
		return "billing/list-job";
	}

////<--------------------- Generate a job pdf @Vincent ------------------------------>
	@GetMapping("/generate-pdf/{id}")
	@ResponseBody
	public String generatePdf(@PathVariable long id) throws IOException {
		try {
			
			String file=createJobDataPdf(id);
			return "file="+file+"&dir=folder.controlSheet";
			
		}catch (Exception e) {
			return "file=error.pdf&dir=folder.controlSheet";
		}
		
	}	
	private String createJobDataPdf(Long id) throws FileNotFoundException, MalformedURLException {
		Job job = jobServiceImpl.findById(id).get();
		double fixePrice = 0;
		double variablePrice = 0;
		String pdf = controlSheetDir+job.getReferenceNumber()+".pdf";
		PdfWriter pdfWriter = new PdfWriter(pdf);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument, PageSize.A4);
		PdfCanvas canvas= new PdfCanvas(pdfDocument.addNewPage());
		ImageData data = ImageDataFactory.create(backgroundFolder + "P1.jpg");
		canvas.addImage(data, PageSize.A4, false);
		double p1=0;
		double p2=0;
		double p3=0;
		double p4=0;
		try {
			/**
			 * Impression de la premiere page du controle sheet
			 */
			PrintableElement printer = new PrintableElement();	
			printer.print(document, job.getCustomer().getName(), 9, 297-16);
			printer.print(document, job.getCustomer().getAddress(), 9, 297-25);
			printer.print(document, job.getCustomer().getTelephone(), 9, 297-35);
			printer.print(document, job.getTitle(), 80, 297-66);
			printer.print(document, job.getOpenWidth()+"", 83, 297-88);
			printer.print(document, job.getOpenLength()+"", 109, 297-88);
			printer.print(document, job.getCloseWidth()+"", 157, 297-88);
			printer.print(document, job.getCloseLength()+"", 182, 297-88);
			printer.print(document, "Cover : "+job.getCoverVolume()+" Pages", 69, 297-99);
			printer.print(document, "Content : "+job.getContentVolume()+" Pages", 69, 297-108);
			
			printer.print(document, "Cover : "+ job.getJobPapers().get(0).getJobColorCombinations().get(0).getFrontColorNumber()+"/"+job.getJobPapers().get(0).getJobColorCombinations().get(0).getBackColorNumber()+" "+job.getJobPapers().get(0).getJobColorCombinations().get(0).getPrintType().getName(), 35, 297-163.5f);
			List<JobPaper> jobPapers = job.getJobPapers();
			String message = "Content : ";
			for(int i=1; i<jobPapers.size(); i++) {
				for(int j=0; j<jobPapers.get(i).getJobColorCombinations().size(); j++) {
					message+=jobPapers.get(i).getJobColorCombinations().get(j).getFrontColorNumber()+"/" + jobPapers.get(i).getJobColorCombinations().get(j).getBackColorNumber()+ " "+jobPapers.get(i).getJobColorCombinations().get(j).getPrintType().getName()+", ";
				}
			}
			printer.print(document, message, 35, 297-173.5f);
			float yx = 297-188;
			printer.print(document, job.getJobActivity().getXPerforated()+ "", 34, yx-8);
			printer.print(document, job.getJobActivity().getXNumbered()+"", 69, yx-8);
			if(job.getJobActivity().getGlueOption().toLowerCase().equals("Head".toLowerCase()))
			printer.print(document, "X",  111, yx-8.5f);
			
			if(job.getJobActivity().getGlueOption().toLowerCase().equals("Left side".toLowerCase()))
				printer.print(document, "X",  128, yx-8.5f);
			
			if(job.getJobActivity().getGlueOption().toLowerCase().equals("Glue-bound".toLowerCase()))
				printer.print(document, "X",  181.5f, yx-17);
			
			if(job.getJobActivity().isSelloptaped())
				printer.print(document, "X",  183, yx-8.5f);
			
			if(job.getJobActivity().isTrimmed()) {
				printer.print(document, "X",  149.5f, yx-8.5f);
				printer.print(document, "Trimmed",  154, yx-8);
			}

			if(job.getJobActivity().isSewn())
				printer.print(document, "X",  164, yx-17);

			printer.print(document, "" +job.getJobActivity().getXCross(),  11, 297-204);
			printer.print(document, "" +job.getJobActivity().getXCreased(),  100, 297-204);
			printer.print(document, "" +job.getJobActivity().getXWiredStiched(),  131, 297-204);
			
			printer.print(document, "Cover : "+ job.getJobPapers().get(0).getPaperType().getName()+"   "+job.getJobPapers().get(0).getGrammage()+"g", 34, 297-235);
			List<JobPaper> jobPapers_ =job.getJobPapers();
			 message = "Content : ";
			for(int i=1; i<jobPapers_.size(); i++) {
					message +=jobPapers_.get(i).getPaperType().getName()+"   "+jobPapers_.get(i).getGrammage()+"g, ";
			}
			printer.print(document, message,  34, 297-243);
			
			/**
			 * Printing Prepress 
			 */
			document.add(new AreaBreak());
			PdfCanvas canvas_= new PdfCanvas(pdfDocument.getLastPage());
			data = ImageDataFactory.create(backgroundFolder + "P2.jpg");
			canvas_.addImage(data, PageSize.A4, false);
			jobPapers = job.getJobPapers();
			float vecteur = -24;
			float vecteur2 = -73;
			 float vecteur3 = -73;
			for(int i=0; i<jobPapers.size(); i++) {
				PlateMakingCosting plateMakingCosting = new PlateMakingCosting(jobPapers.get(i));
				vecteur +=24;
				vecteur2 +=73;
				vecteur3 +=73;
				int basic = plateMakingCosting.getBasic();
				float basicCost = plateMakingCosting.generateBasicCost();
				
				String machine = plateMakingCosting.getPrintingMachine().getAbbreviation();
				
				int exposior = plateMakingCosting.getPlates();
				float exposiorCusting = plateMakingCosting.generateExposureCost();
				//fix price
				fixePrice+=exposiorCusting;
				
				String contentType = plateMakingCosting.getJobPaper().getContentType().getName();
				double signature = plateMakingCosting.getSignatures();
				int run = exposior;
				
				float y1 = 120.5f;
				printer.print(document, machine, 27, y1-vecteur);
				printer.print(document, basic+"", 55, y1-vecteur);
				printer.print(document, basicCost+"", 128, y1-vecteur);
				//fix price
				fixePrice+=basicCost;
				float y2 = 103;
				printer.print(document, exposior+"", 55, y2-vecteur);
				printer.print(document, exposiorCusting+"", 127, y2-vecteur);
				
				printer.print(document, contentType, 15, 115-vecteur);
				
				printer.print(document, machine+"("+contentType+")",  165, 297-35-vecteur2);
				
				printer.print(document, exposior+"",  180, 297-78-vecteur2);
				printer.print(document, signature+"",  180, 297-85-vecteur2);
				printer.print(document,run+"",  180, 297-91-vecteur2);
			
				 float mmToPoint = 2.83465f;
				if(basic>0) {
					int kx = (int) (Math.log(basic)/Math.log(2));
					int ax =kx/2;
					int bx=kx-ax;
					int horizontalLignes = (int) Math.pow(2, ax)-1;
					int verticalLigne = (int) Math.pow(2, bx)-1;
					
					double max = Math.max(job.getCloseLength(), job.getCloseWidth());
					double min = Math.min(job.getCloseLength(), job.getCloseWidth());
					double lengthFoldedJob =min/(horizontalLignes+1);
					double widthFoldedJob =max/(verticalLigne+1);
					if(widthFoldedJob>lengthFoldedJob) {
						widthFoldedJob = Math.ceil(max*(verticalLigne+1)/10)+2;
						lengthFoldedJob = Math.ceil(min*(horizontalLignes+1)/10)+2;
					}else {
						widthFoldedJob=Math.ceil(min*(verticalLigne+1)/10)+2;
						lengthFoldedJob=Math.ceil(max*(horizontalLignes+1)/10)+2;
					}
					
					printer.print(document, widthFoldedJob+"",  175, 297-42-vecteur2);
					printer.print(document,lengthFoldedJob +"",  197, 297-59-vecteur2);					
					float step = 24/(horizontalLignes+1);
					float acc = 0;
					for(int i1=1; i1<=horizontalLignes; i1++) {
						acc += step;
						canvas_.moveTo(155*mmToPoint, (297-46-acc-vecteur3)*mmToPoint);
						canvas_.lineTo(196*mmToPoint, (297-46-acc-vecteur3)*mmToPoint);
					}
					step = 40/(verticalLigne+1);
					acc=0;
					for(int i1=1; i1<=verticalLigne; i1++) {
						acc += step;
						canvas_.moveTo((156+acc)*mmToPoint, (297-45-vecteur3)*mmToPoint);
						canvas_.lineTo((156+acc)*mmToPoint, (297-72-vecteur3)*mmToPoint);
						
					}
					canvas_.closePathStroke();
				}
				printer.print(document, job.getCtpFees()+"", 126, 171);
				//fix price
				
				

			}
			fixePrice+=job.getCtpFees();
			printer.print(document, "CTP", 107, 171);
			p1=variablePrice;
			//System.out.println(p1+"------------- Fixe Price   1-----------");
			
			/**
			 * Print Printing Elements
			 */
			document.add(new AreaBreak());
			PdfCanvas canvas1= new PdfCanvas(pdfDocument.getLastPage());
			data = ImageDataFactory.create(backgroundFolder + "P3.jpg");
			canvas1.addImage(data, PageSize.A4, false);
			float decalage = -30;
			for(int i= 0; i<jobPapers.size(); i++) {
				JobPaper jobPeper = jobPapers.get(i);
				for(int j = 0; j<jobPeper.getJobColorCombinations().size(); j++)
				{
					decalage+=30;
					JobColorCombination jobColorCombination = jobPeper.getJobColorCombinations().get(j);
					PrintingElementCost printinElementCost = new PrintingElementCost(jobColorCombination);
					
					String machine = jobColorCombination.getPrintingMachine().getAbbreviation();
					int grammage = jobColorCombination.getJobPaper().getGrammage();
					float basicPrice = printinElementCost.getBasicUnitCost();
					
					printer.print(document, machine, 22, 297-23-decalage);
					printer.print(document, grammage+"g", 60, 297-23-decalage);
					printer.print(document, basicPrice+"", 134, 297-23-decalage);
					//fix price
					fixePrice+=basicPrice;

					printer.print(document, jobPeper.getContentType().getName(), 10, 297-29-decalage);
					printer.print(document, jobColorCombination.getPrintType().getName(), 54, 297-29-decalage);
					printer.print(document, printinElementCost.getPreparationUnitCost()+"", 134, 297-29-decalage);	
					//fix price
					fixePrice+=printinElementCost.getPreparationUnitCost();
					printer.print(document, printinElementCost.getInckChange()+"", 55, 297-35f-decalage);
					printer.print(document, printinElementCost.getInckChangeUnitCost()+"", 75, 297-35f-decalage);
					
					printer.print(document, printinElementCost.getPlateChange()+"", 55, 297-41f-decalage);
					printer.print(document, printinElementCost.getPlateChangeUnitCost()+"", 75, 297-41-decalage);
					
					printer.print(document, printinElementCost.getRun()+"", 55, 297-47f-decalage);
					printer.print(document, printinElementCost.getRunUnitCost()+"", 75, 297-47f-decalage);
					
					printer.print(document, printinElementCost.getInckChange()*printinElementCost.getInckChangeUnitCost()+"", 135, 297-35-decalage);
					//fix price
					fixePrice+=printinElementCost.getInckChange()*printinElementCost.getInckChangeUnitCost();
					printer.print(document, printinElementCost.getPlateChange()*printinElementCost.getPlateChangeUnitCost()+"", 135, 297-41-decalage);
					//fix price
					fixePrice+=printinElementCost.getPlateChange()*printinElementCost.getPlateChangeUnitCost();
					printer.print(document, printinElementCost.getRun()*printinElementCost.getRunUnitCost()+"", 175, 297-47-decalage);
					//var price
					variablePrice+=printinElementCost.getRun()*printinElementCost.getRunUnitCost();
					if(Math.ceil(jobColorCombination.getNumberOfSignature())!=Math.floor(jobColorCombination.getNumberOfSignature())) {
						printer.print(document, 500+"", 38, 297-150);
						PlateMakingCosting pmcst= new PlateMakingCosting(jobPeper);
						printer.print(document,pmcst.getBasic()/2+"", 67, 297-150);
						//fix cost
						fixePrice+=1000;
						printer.print(document,printinElementCost.getTrimmingUpsUnitCost()+"", 95, 297-150);
						//variable cust
						variablePrice+=printinElementCost.getTrimmingUpsUnitCost()*(pmcst.getBasic()/2)*1000;
						printer.print(document,printinElementCost.getTrimmingUpsUnitCost()*(pmcst.getBasic()/2)*1000+"", 180, 297-150);
					}
					
				}
				
			}
			p2=variablePrice-p1;
			//System.out.println(p2+"------------- Fix Price  2-----------");
			

			/**
			 * Print Finishing Elements
			 */
			
			document.add(new AreaBreak());
			PdfCanvas canvas2= new PdfCanvas(pdfDocument.getLastPage());
			data = ImageDataFactory.create(backgroundFolder + "P4.jpg");
			canvas2.addImage(data, PageSize.A4, false);
			float translation = -5;
			String paperFormat = "";
			for(int i3=1; i3<jobPapers.size(); i3++) {
				translation +=5;
				JobPaper jobPeper = jobPapers.get(i3);
				JobColorCombination jobColorCombination = jobPeper.getJobColorCombinations().get(0);
				PrintingElementCost printinElementCost = new PrintingElementCost(jobColorCombination);
				PlateMakingCosting pmc = new PlateMakingCosting(jobPeper);
				printer.print(document, printinElementCost.getFinishinFolde()+"", 47, 297-20 );
				printer.print(document, printinElementCost.getFoldedFixeCost()+"", 132, 297-20 );
				//fixCost
				fixePrice+=printinElementCost.getFoldedFixeCost();
				paperFormat=printinElementCost.getPaperFormat();
				printer.print(document, printinElementCost.getFinishingRun()+"", 45, 297-25.5f-translation);
				printer.print(document, paperFormat, 70f, 297-25.5f-translation);
				printer.print(document, printinElementCost.getFinishingRunVaribleCost()+"", 173, 297-26-translation);
				//VariableCost
				variablePrice+=printinElementCost.getFinishingRunVaribleCost();
				printer.print(document, printinElementCost.getFinishingRunFixCost()+"", 83, 297-26-translation);

				
				
			}

			printer.print(document,2000+"", 132, 297-38);
			//fixePrice
			fixePrice+=2000;
			printer.print(document, 4000+"", 175, 297-38);
			//variablePrice
			variablePrice+=4000;
			
			printer.print(document, job.getJobActivity().getHandFoldingCov()+"", 43, 297-43 );
			printer.print(document, job.getJobActivity().getHandFoldingCov()*2000+"", 175, 297-44);
			//variablePrice
			variablePrice+=job.getJobActivity().getHandFoldingCov()*2000;
			int plates=0;
			for(int i=0; i<jobPapers.size(); i++) {
				JobPaper jpr = jobPapers.get(i);
				PlateMakingCosting plmCst = new PlateMakingCosting(jpr);
				plates+=plmCst.getPlates();
			}
			if(job.getJobActivity().getXWiredStiched()>0) {
				printer.print(document, 4500+"", 135, 297-50);
				//fixePrice
				fixePrice+=4500;
				boolean isCover =false;
				isCover=job.getJobPapers().stream().anyMatch(t->t.getContentType().getId()==1);
				if(isCover) {
					printer.print(document, 15000+"", 175, 297-55);
					//variableCost
					variablePrice+=15000;
				}else {
					printer.print(document, 12000+"", 175, 297-55);
					//variableCost
					variablePrice+=12000;
				}
				
				printer.print(document, plates+"", 50, 297-62);
				printer.print(document, plates*4700+"", 175, 297-62);
				//variablePrice
				variablePrice+=plates*4700;
			}
			
			if(job.getJobActivity().isSewn()) {
				float totalContentOfSignature = 0;
			    for(int i=0; i<job.getJobPapers().size();i++) {
			    	if(job.getJobPapers().get(i).getContentType().getId()!=1) {
			    		for(int j=0;j<job.getJobPapers().get(i).getJobColorCombinations().size(); j++) {
			    			totalContentOfSignature+=job.getJobPapers().get(i).getJobColorCombinations().get(j).getNumberOfSignature();
			    		}
			    	}
			    }
			    printer.print(document, totalContentOfSignature+"", 50, 297-67);
				printer.print(document, totalContentOfSignature*2000+"", 175, 297-67);
				//variableCost
				variablePrice+=totalContentOfSignature*2000;
			}
			
			if(job.getJobActivity().isHandgather()) {
				float totalContentOfSignature = 0;
			    for(int i=0; i<job.getJobPapers().size();i++) {
			    		for(int j=0;j<job.getJobPapers().get(i).getJobColorCombinations().size(); j++) {
			    			totalContentOfSignature+=job.getJobPapers().get(i).getJobColorCombinations().get(j).getNumberOfSignature();
			    	}
			    }
				printer.print(document, totalContentOfSignature+"", 50, 297-74);
				printer.print(document, totalContentOfSignature*1000+"", 175, 297-74);
				//variablePrice
				variablePrice+=totalContentOfSignature*1000;
			}
			
			if(job.getJobActivity().getGlueOption()!=null&&job.getJobActivity().getGlueOption().toLowerCase().equals("glue-bound")) {
				printer.print(document, 10000+"", 130, 297-79.5f);
				//fixePrice
				fixePrice+=10000;
				printer.print(document, 80000+"", 175, 297-79.5f);
				//variablePrice
				variablePrice+=80000;
			}
			if(job.getJobActivity().getLamination()>0) {
				printer.print(document, paperFormat, 40, 297-91);
				printer.print(document, job.getJobActivity().getLamination()+"", 57, 297-92);
				int laminationUnitePrice = 0;
				if(job.getJobActivity().getLamination()==1) {
					laminationUnitePrice =  Integer.valueOf(paperFormat)>=6?30:Integer.valueOf(paperFormat)>=5?50:Integer.valueOf(paperFormat)>=4?100:Integer.valueOf(paperFormat)>=3?170:300;
				}else {
					laminationUnitePrice =  Integer.valueOf(paperFormat)>=6?50:Integer.valueOf(paperFormat)>=5?90:Integer.valueOf(paperFormat)>=4?150:Integer.valueOf(paperFormat)>=3?270:500;	
				}
				printer.print(document, laminationUnitePrice/1000.0+"", 85, 297-91);
				printer.print(document, job.getJobActivity().getLamination()*(laminationUnitePrice/1000.0)*1000_000+"", 175, 297-92);
				//variablePrice
				variablePrice+=job.getJobActivity().getLamination()*(laminationUnitePrice/1000.0)*1000_000;
			
			}

			/**
			 * Print  Paper Element
			 */
			p3=variablePrice-p1-p2;
			//System.out.println(p3+"------------- Fix Price  3-----------");
			document.add(new AreaBreak());
			PdfCanvas canvas3= new PdfCanvas(pdfDocument.getLastPage());
			data = ImageDataFactory.create(backgroundFolder + "P5.jpg");
			canvas3.addImage(data, PageSize.A4, false);
			 List<JobPaper> papers= job.getJobPapers();
			 float vct=-56;
			 for(int i=0; i<papers.size(); i++) {
				 JobPaper paper = papers.get(i);
				 for(int j=0; j<paper.getJobColorCombinations().size(); j++) {
				 vct+=56;
				 JobColorCombination cb= paper.getJobColorCombinations().get(j);
				 printer.print(document, paper.getContentType().getName(), 20, 297-24.5f-vct);
				 printer.print(document, paper.getPaperType().getName(), 65, 297-24-vct);
				 printer.print(document, paper.getGrammage()+"", 123, 297-23-vct);
				 printer.print(document, 65+"", 155, 297-23-vct);
				 printer.print(document, 92+"", 180, 297-23-vct);
				 
				 int maxC = Math.max(cb.getBackColorNumber(), cb.getFrontColorNumber());
				 int percentage = maxC +6;
				 printer.print(document, percentage+"", 180, 297-28-vct);
				 printer.print(document, cb.getNumberOfSignature()*1000+"", 25, 297-30-vct);
				 printer.print(document, (cb.getNumberOfSignature()*10)*percentage+"", 25, 297-36-vct);
				 int totalOversh = (int) (cb.getNumberOfSignature()*10*percentage + cb.getNumberOfSignature()*1000);
				 printer.print(document, totalOversh+"", 29, 297-43-vct);
				 int up = 65*92;
				 PlateMakingCosting pltc = new PlateMakingCosting(paper);
				 int kx = (int) (Math.log(pltc.getBasic())/Math.log(2));
					int ax =kx/2;
					int bx=kx-ax;
					int horizontalLignes = (int) Math.pow(2, ax)-1;
					int verticalLigne = (int) Math.pow(2, bx)-1;
					
					double max = Math.max(job.getCloseLength(), job.getCloseWidth());
					double min = Math.min(job.getCloseLength(), job.getCloseWidth());
					double lengthFoldedJob =min/(horizontalLignes+1);
					double widthFoldedJob =max/(verticalLigne+1);
					if(widthFoldedJob>lengthFoldedJob) {
						widthFoldedJob = Math.ceil(max*(verticalLigne+1)/10)+2;
						lengthFoldedJob = Math.ceil(min*(horizontalLignes+1)/10)+2;
					}else {
						widthFoldedJob=Math.ceil(min*(verticalLigne+1)/10)+2;
						lengthFoldedJob=Math.ceil(max*(horizontalLignes+1)/10)+2;
					}
					
					up = (int) Math.floor(up/(lengthFoldedJob*widthFoldedJob)) ;
					DecimalFormat dcf= new DecimalFormat("#.###");
					dcf.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
					dcf.setRoundingMode(RoundingMode.CEILING);
					printer.print(document, up+"", 56, 297-42-vct);
					double variableC= totalOversh/(up*1000.0);
					printer.print(document, dcf.format(variableC)+"", 80, 297-42-vct);
					printer.print(document, paper.getUnitPrice()+"", 120, 297-42-vct);
					//fixeCost
					//fixePrice+=paper.getUnitPrice();
					printer.print(document, paper.getUnitPrice()*Double.valueOf(dcf.format(variableC))+"", 175, 297-40-vct);
					//variableCost
					variablePrice+=paper.getUnitPrice()*Double.valueOf(dcf.format(variableC));
					printer.print(document, cb.getNumberOfSignature()+"", 13, 297-53-vct);
					
					int ps = maxC*50;
				
					if(cb.getPrintingMachine().getAbbreviation().equals("SPM"))
						ps+=50;
					
					if(cb.getBackColorNumber()!=0&&cb.getFrontColorNumber()!=0)
						ps=ps*2;
					
					printer.print(document, ps+"", 43, 297-53-vct);
					printer.print(document, cb.getNumberOfSignature()*ps+"", 95, 297-51.5f-vct);
					
					printer.print(document, 40+"", 43, 297-58-vct);
					printer.print(document, 40*cb.getNumberOfSignature()+"", 95, 297-58-vct);
					
					printer.print(document, (40+ps)*cb.getNumberOfSignature()+"", 29, 297-64-vct);
					printer.print(document, up+"", 66, 297-64-vct);
					printer.print(document, dcf.format((40+ps)*cb.getNumberOfSignature()/(up*1000.0)), 95, 297-64-vct);
					
					printer.print(document, Double.valueOf(dcf.format((40+ps)*cb.getNumberOfSignature()/(up*1000.0)))*paper.getUnitPrice()+"", 130, 297-64-vct);
					//fixeCost
					fixePrice+=Double.valueOf(dcf.format((40+ps)*cb.getNumberOfSignature()/(up*1000.0)))*paper.getUnitPrice();
					
				 }
				 
			 }
			 p4=variablePrice-p1-p2-p3;
				//System.out.println(p4+"------------- Fix Price  4-----------");
			printer.print(document, fixePrice+"", 135, 99);
			printer.print(document, variablePrice+"", 175, 99);
			job.setFixCost((float) fixePrice);
			job.setVariableCost((float) variablePrice);
			jobServiceImpl.save(job);
			document.close();
			return job.getReferenceNumber()+".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Error", e);
		}
	}

	@GetMapping("/error-pdf")
	@ResponseBody
	public String fileError(Model model) throws FileNotFoundException {

		return "file=error.pdf&dir=folder.controlSheet";
	}

	@GetMapping("/viewJob/{id}")
	public String viewJobDetails(@PathVariable long id, Model model) {
		Job findJob = jobServiceImpl.findById(id).get();
		List<JobPaper> jobPapers = findJob.getJobPapers();
		JobPaper cover = jobPapers.remove(0);
		List<String> jobEstimates = new ArrayList<>();
		String countRef ="";

		for(JobEstimate jobEstimate : findJob.getJobEstimates()){
			if(!jobEstimate.getReference().equals(countRef)){
				jobEstimates.add(jobEstimate.getReference());
				countRef = jobEstimate.getReference();
			}
		}

		model.addAttribute("job",findJob);
		model.addAttribute("jobPapers",jobPapers);
		model.addAttribute("coverjobPapers",cover);
		model.addAttribute("jobEstimates",jobEstimates);

    return "/billing/view-job-profile";
	}
	
	@PostMapping(value="/delete/{id}")
	public void delete(@PathVariable long id) {
	  Optional<Job> job = jobServiceImpl.findById(id);
	    if (job.isPresent()) 
	    	jobServiceImpl.deleteById(id);
	}
	
	// to get the update page of job
	
		@GetMapping("/update-form/{id}")
		public String getUpdateForm(@PathVariable Long id, Model model) {
			List<Customer> customerResult = customerServiceImpl.findAll();
			List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
			List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
			List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
			List<PaperType>  paperTypeResult = paperTypeServiceImpl.listAll();
			List<PrintingMachine> printingMachineResult = printingMachineServiceImpl.listMachines();
			List<PrintType> printTypeResult = printTypeServiceImpl.findAll();
			List<JobColorCombination> jobColorCombinationResult = jobColorCombinationServiceImpl.findAll();
			List<PaperGrammage> paperGrammageResult = paperGrammageServiceImpl.findAll();
			List<BindingType> bindingTypeResult = bindingTypeserviceImpl.listAll();
			
			Job existingJob = jobServiceImpl.findById(id).get();
			JobPaper existingJobPaper = existingJob.getJobPapers().remove(0);
			model.addAttribute("job", existingJob);
			model.addAttribute("customers", customerResult);
			model.addAttribute("jobTypes", jobTypeResult);
			model.addAttribute("paperFormats", paperFormatResult);
			model.addAttribute("bindingTypes", bindingTypeResult);
			model.addAttribute("coverJobPaper", existingJobPaper);
			model.addAttribute("jobPaperResults", jobPaperResult);
			model.addAttribute("paperTypes", paperTypeResult);
			model.addAttribute("printingMachines", printingMachineResult);
			model.addAttribute("printTypes", printTypeResult);
			model.addAttribute("jobColorCombinations", jobColorCombinationResult);
			model.addAttribute("paperGrammages", paperGrammageResult);
			
		    return "/billing/job-update-form";
		}
	
		@GetMapping("/estimate/{id}")
		public String getEstimateForm (@PathVariable long id, Model model) {
			Job findJob = jobServiceImpl.findById(id).get();
			model.addAttribute("job", findJob);		

			return "/billing/estimate/job-estimate";
		}
		
		@GetMapping("/generate/{id}")
		public String generateEstimate(@PathVariable long id,@RequestParam("quantities") String quantities, 
				@RequestParam("extraFee") int extraFee, @RequestParam("extraFeeDescription") String extraFeeDescription, Model model) {
			
			Job job = jobServiceImpl.findById(id).get();
			//Get and structure typesetting values
			List<String> typsettingActivities = new ArrayList<String>();
			if(job.isTypesettingByUs()) typsettingActivities.add("Typesetting by us");
			if(job.isDataSuppliedByCustomer()) typsettingActivities.add("Data Supplied by customer");
			if(job.isLayOutByUs()) typsettingActivities.add("Layout by us");
			if(job.isExistingPlate()) typsettingActivities.add("Has existing Plates");
			
			//Get and Structure printing 
			List<JobPaper> jobPapers = job.getJobPapers();
			JobPaper coverJobPaper = jobPapers.remove(0);
			
			//Get Finishing structure
			String finishingActivities = "";
			JobActivity jobActivity = job.getJobActivity();
			
			if(jobActivity.getXCross()>0) finishingActivities += "Signatures " + jobActivity.getXCross()+ " x cross-folded, " ;
			if(jobActivity.getXCreased()>0) finishingActivities += "Cover " + jobActivity.getXCreased()+ " x creased, " ;
			if(jobActivity.getLamination()>0) finishingActivities += "Cover " + jobActivity.getLamination()+ " side laminated, " ;
			if(jobActivity.getXWiredStiched()>0) finishingActivities += "Booklets " + jobActivity.getXWiredStiched()+ " x wire-stitched, " ;
			if(jobActivity.isSewn()) finishingActivities += "Booklets sewn, " ;
			if(jobActivity.isHandgather()) finishingActivities += "hand-gathered, " ;
			if(jobActivity.isSelloptaped()) finishingActivities += "sellotaped, " ;
			if(jobActivity.isTrimmed()) finishingActivities += "trimmed, " ;
			if(jobActivity.getXPerforated()>0) finishingActivities += "Job perforated " + jobActivity.getXPerforated()+ " times, " ;
			if(!(jobActivity.getGlueOption().isEmpty())) finishingActivities += "Glue option is" + jobActivity.getGlueOption()+ ", " ;
			if(jobActivity.getBindingType()!=null) finishingActivities += "final binding: " + jobActivity.getBindingType().getName()+ " " ;
			
			

			String[] qty = quantities.split("@");
			List<EstimateDTO> estimates = new ArrayList<EstimateDTO>();
			for(int i=0; i<qty.length;i++) {
				EstimateDTO estimateDTO = new EstimateDTO();
				estimateDTO.setQuantity(Integer.parseInt(qty[i]));
				int totalPrice= Math.round(((job.getVariableCost()/1000) * Integer.parseInt(qty[i])) +(job.getFixCost()+ extraFee));
				estimateDTO.setTotalPrice(totalPrice);
				estimateDTO.setUnitPrice(Math.round(totalPrice/Integer.parseInt(qty[i])));
				estimates.add(estimateDTO);
			}

			Job findJob = jobServiceImpl.findById(id).get();
			model.addAttribute("job", findJob);
			model.addAttribute("estimates", estimates);
			model.addAttribute("typeSettingActivities", typsettingActivities);
			model.addAttribute("finishingActivities", finishingActivities);
			model.addAttribute("coverJobPaper", coverJobPaper);			
			model.addAttribute("contentJobPapers", jobPapers);
			return "/billing/estimate/generated-estimate-result";
		}

	@PostMapping("/estimate/confirm/{id}")
	@ResponseBody
	public String confirmEstimate(@PathVariable long id,@RequestParam("quantities") String quantities,
								   @RequestParam("extraFee") int extraFee, @RequestParam("extraFeeDescription") String extraFeeDescription, Model model) {

		Job job = jobServiceImpl.findById(id).get();
		String[] qty = quantities.split("@");
/*
		Date today = Calendar.getInstance().getTime();
		System.out.println(today.getTime()/(60*60*60));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String dateString = today.;
		int currentCount = (int) (job.getId()%999);
		String countString = String.format("%03d", currentCount);
		String reference = dateString + "ESN-" + countString;
		System.out.println(dateString);

*/

		String countRef = " ";
		int count = 1 ;
		for(JobEstimate jobEstimate : job.getJobEstimates()){
			if(!jobEstimate.getReference().equals(countRef)){
				countRef = jobEstimate.getReference();
				count++;
			}

		}
		String reference = "ESN-" + job.getReferenceNumber()+ "-" +count;


		List<JobEstimate> estimates = new ArrayList<JobEstimate>();
		for(int i=0; i<qty.length;i++) {
			JobEstimate estimate = new JobEstimate();
			estimate.setReference(reference);
			estimate.setQuantity(Integer.parseInt(qty[i]));
			int totalPrice= Math.round(((job.getVariableCost()/1000) * Integer.parseInt(qty[i])) +(job.getFixCost()+ extraFee));
			estimate.setTotalPrice(totalPrice);
			estimate.setUnitPrice(Math.round(totalPrice/Integer.parseInt(qty[i])));
			estimate.setCreatedDate(new Date());
			estimate.setJob(job);
			jobEstimateServiceImpl.save(estimate);
			estimates.add(estimate);
		}
		Job findJob = jobServiceImpl.findById(id).get();
		if (estimates.size()==0) return "ko@" +job.getReferenceNumber();
		else
		return "ok@"+reference;
	}
		
}

package com.ppp.billing.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Invoice;
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
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.serviceImpl.BindingTypeserviceImpl;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;
import com.ppp.billing.serviceImpl.EstimatePricingServiceImpl;
import com.ppp.billing.serviceImpl.InvoiceServiceImpl;
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
	@Value("${folder.estimate}")
	private String estimateDir;
	
	@Value("${folder.invoice}")
	private String invoiceDir;
	
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
	private JobEstimateRepository jobEstimateRepository; 
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;  
	
	@Autowired
	private EstimatePricingServiceImpl estimatePricingServiceImpl;
	
	@Autowired
	private JobEstimateServiceImpl jobEstimateServiceImpl;
	
	
//	
	
//<--------------------- Collect datas form @Vincent------------------------------>
	@PreAuthorize("hasAuthority('ROLE_REGISTER_NEW_JOB')")
	@GetMapping("/displayform")
	public String displayFormInterface(Model model) {
		List<Customer> customerResult = customerServiceImpl.findAll();
		List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
		List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
		List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
		List<PaperType>  paperTypeResult = paperTypeServiceImpl.listAll();
		List<PrintingMachine> printingMachineResult = printingMachineServiceImpl.findByIsActive();
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
	
	/*
	 * Working with Draft Job
	 * */
	@PreAuthorize("hasAuthority('ROLE_REGISTER_NEW_JOB')")
	@GetMapping("/displayform-draft")
	public String displayDraftFormInterface(Model model) {
		List<Customer> customerResult = customerServiceImpl.findAll();
		List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
		List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
		List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
		List<PrintType> printTypeResult = printTypeServiceImpl.findAll();
		List<PaperGrammage> paperGrammageResult = paperGrammageServiceImpl.findAll();
		List<BindingType> bindingTypeResult = bindingTypeserviceImpl.listAll();

		
		model.addAttribute("customers", customerResult);
		model.addAttribute("jobTypes", jobTypeResult);
		model.addAttribute("paperFormats", paperFormatResult);
		model.addAttribute("bindingTypes", bindingTypeResult);
		model.addAttribute("jobPaperResults", jobPaperResult);
		model.addAttribute("paperGrammages", paperGrammageResult);
		
		return "billing/display-daftjob-form-interface";
	}

//<--------------------- Save data collected to the data base @Vincent ------------------------------>
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveJob(@RequestBody JobDTO jobDTO,Model model){
		try {
			jobServiceImpl.saveJob(jobDTO);
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "KO";
		}
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_REGISTER_NEW_JOB')")
	@GetMapping("/list-job")
	public String listJob(Model model) {
		List<Job> result = jobServiceImpl.listAllJob();
		List<Invoice> invoices = invoiceServiceImpl.listInvoice();
		int totalElement = result.size();
		Collections.reverse(result);
		model.addAttribute("jobs", result);
		model.addAttribute("totalElement", totalElement);
		model.addAttribute("invoice", invoices);
		return "billing/list-job";
	}

////<--------------------- Generate a job pdf @Vincent ------------------------------>
	@PreAuthorize("hasAuthority('ROLE_REGISTER_NEW_JOB')")
	@GetMapping("/generate-pdf/{id}")
	@ResponseBody
	public String generatePdf(@PathVariable long id) throws IOException {
		try {			
			String file=createJobDataPdf(id);
			return "file="+file+"&dir=folder.controlSheet";
			
		}catch (Exception e) {
			e.printStackTrace();
			return "file=error.pdf&dir=folder.controlSheet";
		}
		
	}	
	private String createJobDataPdf(Long id) throws IOException {
	
		Job job = jobServiceImpl.findById(id).get();
//		 DecimalFormat formateur = new DecimalFormat("0.00");
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
			
			JobPaper jobPaper =new JobPaper();
			for(JobPaper pp : job.getJobPapers()) {
				if(pp.getContentType().getId()==1) {
					jobPaper =pp;
					printer.print(document, "Cover : "+ jobPaper.getPaperType().getName()+"   "+jobPaper.getGrammage()+"g", 34, 297-235);
					printer.print(document, "Cover : "+job.getCoverVolume()+" Pages", 69, 297-99);
					printer.print(document, "Cover : "+ jobPaper.getJobColorCombinations().get(0).getFrontColorNumber()+"/"+jobPaper.getJobColorCombinations().get(0).getBackColorNumber()+" "+jobPaper.getJobColorCombinations().get(0).getPrintType().getName(), 35, 297-163.5f);
				}
					
			}
			
			
			List<JobPaper> jobPapers = job.getJobPapers();
			String message = "Cotent :";
			boolean isContent=false;
			for(int i=0; i<jobPapers.size(); i++) {
				if(jobPapers.get(i).getContentType().getId()==2) {
					isContent=true;
				for(int j=0; j<jobPapers.get(i).getJobColorCombinations().size(); j++) {
				//	if(jobPapers.get(i).getContentType().getId()==1) messageCover+
					message+=jobPapers.get(i).getJobColorCombinations().get(j).getFrontColorNumber()+"/" + jobPapers.get(i).getJobColorCombinations().get(j).getBackColorNumber()+ " "+jobPapers.get(i).getJobColorCombinations().get(j).getPrintType().getName()+", ";
				}
			}}
			if(isContent)
			{
				printer.print(document, "Content : "+job.getContentVolume()+" Pages", 69, 297-108);
				printer.print(document, message, 35, 297-173.5f);
			}
			
			float yx = 297-188;
			printer.print(document, job.getJobActivity().getXPerforated()+ "", 34, yx-8);
			printer.print(document, job.getJobActivity().getXNumbered()+"", 69, yx-8);
			//use binding type instead
			if(job.getJobActivity().getBindingType()!=null) {
				if (job.getJobActivity().getBindingType().getName().equals("Glue-Head"))				
			printer.print(document, "X",  111, yx-8.5f);
			
			if (job.getJobActivity().getBindingType().getName().equals("Glue-Left"))	
				printer.print(document, "X",  128, yx-8.5f);
			
			if (job.getJobActivity().getBindingType().getName().equals("Glue-Bound"))	
				printer.print(document, "X",  181.5f, yx-17);
			}
			//end using binding type
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
			
			List<JobPaper> jobPapers_ =job.getJobPapers();
			 message = "Content : ";
			for(int i=0; i<jobPapers_.size(); i++) {
				if(jobPapers_.get(i).getContentType().getId()==2)
					message +=jobPapers_.get(i).getPaperType().getName()+"   "+jobPapers_.get(i).getGrammage()+"g, ";
			}
			if(isContent)
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
			 float vecteurex =-47;
			for(int i=0; i<jobPapers.size(); i++) {
				PlateMakingCosting plateMakingCosting = new PlateMakingCosting(jobPapers.get(i));
				vecteur +=24;
				vecteur2 +=73;
				vecteur3 +=73;
				vecteurex += 47;
				int basic = plateMakingCosting.getBasic();
				float basicCost = plateMakingCosting.generateBasicCost();
				
				String machine = plateMakingCosting.getPrintingMachine().getAbbreviation();
				
				double exposior = plateMakingCosting.getPlates();
				float exposiorCusting = plateMakingCosting.generateExposureCost();
				//fix price
				fixePrice+=exposiorCusting;
				
				String contentType = plateMakingCosting.getJobPaper().getContentType().getName();
				double signature = plateMakingCosting.getSignatures();
				double run = exposior;
				
				float y1 = 120.5f;
				printer.print(document, machine, 27, y1-vecteur);
				printer.print(document, basic+"", 55, y1-vecteur);
				printer.printMoney(document, basicCost, 128, y1-vecteur);
				//fix price
				fixePrice+=basicCost;
				
				//**********************//
				float y4 = 115;
				printer.print(document, exposior+"", 75, y4-vecteur);
				/**
				 * to review after bug fix
				printer.print(document, plateMakingCosting.foilPreparation()+"", 98, y4-vecteur);
				printer.printMoney(document, exposior*plateMakingCosting.foilPreparation(), 128, y4-vecteur);
				//fix price
				fixePrice+=exposior*plateMakingCosting.foilPreparation();
					*/

				float yex = 113;
				//printer.print(document, exposior+"", 55, yex-vecteurex);
				//printer.print(document, messageResult+"", 127, yex-vecteurex);
				
				float y2 = 103;
				printer.print(document, exposior+"", 55, y2-vecteur);
				printer.printMoney(document, exposiorCusting, 127, y2-vecteur);
				
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
				printer.printMoney(document, job.getCtpFees(), 126, 171);
				//fix price
				
				

			}
			fixePrice+=job.getCtpFees();
			printer.print(document, "CTP", 107, 171);
			p1=variablePrice;
			
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
					printer.printMoney(document, basicPrice, 134, 297-23-decalage);
					//fix price
					fixePrice+=basicPrice;

					printer.print(document, jobPeper.getContentType().getName(), 10, 297-29-decalage);
					printer.print(document, jobColorCombination.getPrintType().getName(), 54, 297-29-decalage);
					printer.printMoney(document,printinElementCost.getPreparationUnitCost(), 134, 297-29-decalage);	
					//fix price
					fixePrice+=printinElementCost.getPreparationUnitCost();
					printer.print(document, printinElementCost.getInckChange()+"", 55, 297-35f-decalage);
					printer.print(document, printinElementCost.getInckChangeUnitCost()+"", 75, 297-35f-decalage);
					
					printer.print(document, printinElementCost.getPlateChange()+"", 55, 297-41f-decalage);
					printer.print(document, printinElementCost.getPlateChangeUnitCost()+"", 75, 297-41-decalage);
					
					printer.print(document, printinElementCost.getRun()+"", 55, 297-47f-decalage);
					printer.print(document, printinElementCost.getRunUnitCost()+"", 75, 297-47f-decalage);
					
					printer.printMoney(document, printinElementCost.getInckChange()*printinElementCost.getInckChangeUnitCost(), 135, 297-35-decalage);
					//fix price
					fixePrice+=printinElementCost.getInckChange()*printinElementCost.getInckChangeUnitCost();
					printer.printMoney(document, printinElementCost.getPlateChange()*printinElementCost.getPlateChangeUnitCost(), 135, 297-41-decalage);
					//fix price
					fixePrice+=printinElementCost.getPlateChange()*printinElementCost.getPlateChangeUnitCost();
					printer.printMoney(document, printinElementCost.getRun()*printinElementCost.getRunUnitCost(), 175, 297-47-decalage);
					//var price
					variablePrice+=printinElementCost.getRun()*printinElementCost.getRunUnitCost();
					if(Math.ceil(jobColorCombination.getNumberOfSignature())!=Math.floor(jobColorCombination.getNumberOfSignature())) {
						printer.print(document, 500+"", 38, 297-150);
						PlateMakingCosting pmcst= new PlateMakingCosting(jobPeper);
						printer.print(document, pmcst.getBasic()/2+"", 67, 297-150);
						//fix cost
						fixePrice+= 1000;
						printer.print(document,printinElementCost.getTrimmingUpsUnitCost()+"", 95, 297-150);
						//variable cust
						variablePrice+=printinElementCost.getTrimmingUpsUnitCost()*(pmcst.getBasic()/2)*1000;
						printer.printMoney(document,printinElementCost.getTrimmingUpsUnitCost()*(pmcst.getBasic()/2)*1000, 180, 297-150);
					}
					
				}
				
			}
			p2=variablePrice-p1;
			
			/**
			 * Print Finishing Elements
			 */
			
			document.add(new AreaBreak());
			PdfCanvas canvas2= new PdfCanvas(pdfDocument.getLastPage());
			data = ImageDataFactory.create(backgroundFolder + "P4.jpg");
			canvas2.addImage(data, PageSize.A4, false);
			float translation = -5;
			String paperFormat ="";
			double closewidthFormat =Math.max(job.getOpenWidth(), job.getOpenLength()) ;
			if(closewidthFormat<=74)
				paperFormat = 8+"";
			else if(closewidthFormat<=105)
				paperFormat = 7+"";
			else if(closewidthFormat<=145)
				paperFormat = 6+"";
			else if(closewidthFormat<=210)
				paperFormat = 5+"";
			else if(closewidthFormat<=297)
				paperFormat = 4+"";
			else if(closewidthFormat<=420)
				paperFormat = 3+"";
			else if(closewidthFormat>594)
				paperFormat = 2+"";
			
			for(int i3=0; i3<jobPapers.size(); i3++) {
				if(jobPapers.get(i3).getContentType().getId()==2)
				{
				translation +=5;
				JobPaper jobPeper = jobPapers.get(i3);
				JobColorCombination jobColorCombination = jobPeper.getJobColorCombinations().get(0);
				PrintingElementCost printinElementCost = new PrintingElementCost(jobColorCombination);
				PlateMakingCosting pmc = new PlateMakingCosting(jobPeper);
				printer.print(document, printinElementCost.getFinishinFolde()+"", 47, 297-20 );
				printer.printMoney(document, printinElementCost.getFoldedFixeCost(), 132, 297-20 );
				//fixCost
				fixePrice+=printinElementCost.getFoldedFixeCost();
				printer.print(document, printinElementCost.getFinishingRun()+"", 45, 297-25.5f-translation);
				printer.print(document, paperFormat, 70f, 297-25.5f-translation);
				printer.printMoney(document, printinElementCost.getFinishingRunVaribleCost(), 173, 297-26-translation);
				//VariableCost
				variablePrice+=printinElementCost.getFinishingRunVaribleCost();
				printer.printMoney(document, printinElementCost.getFinishingRunFixCost() , 83, 297-26-translation);

			}
				
			}

			printer.printMoney(document,2000, 132, 297-38);
			//fixePrice
			fixePrice+=2000;
			printer.printMoney(document, 4000, 175, 297-38);
			//variablePrice
			variablePrice+=4000;
			
			printer.print(document, job.getJobActivity().getHandFoldingCov()+"", 43, 297-43 );
			printer.printMoney(document, job.getJobActivity().getHandFoldingCov()*2000, 175, 297-44);
			//variablePrice
			variablePrice+=job.getJobActivity().getHandFoldingCov()*2000;
			int plates=0;
			for(int i=0; i<jobPapers.size(); i++) {
				JobPaper jpr = jobPapers.get(i);
				PlateMakingCosting plmCst = new PlateMakingCosting(jpr);
				plates+=plmCst.getPlates();
			}
			if(job.getJobActivity().getXWiredStiched()>0) {
				printer.printMoney(document, 4500, 135, 297-50);
				//fixePrice
				fixePrice+=4500;
				boolean isCover =false;
				isCover=job.getJobPapers().stream().anyMatch(t->t.getContentType().getId()==1);
				if(isCover) {
					printer.printMoney(document, 15000, 175, 297-55);
					//variableCost
					variablePrice+=15000;
				}else {
					printer.printMoney(document, 12000, 175, 297-55);
					//variableCost
					variablePrice+=12000;
				}
				
				printer.print(document, plates+"", 50, 297-62);
				printer.printMoney(document, plates*4700, 175, 297-62);
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
			    printer.print(document, Math.ceil(totalContentOfSignature)+"", 50, 297-67);
				printer.printMoney(document,  Math.ceil(totalContentOfSignature)*2000 , 175, 297-67);
				//variableCost
				variablePrice+= Math.ceil(totalContentOfSignature)*2000;
			}
			
			if(job.getJobActivity().isHandgather()) {
				float totalContentOfSignature = 0;
			    for(int i=0; i<job.getJobPapers().size();i++) {
			    		for(int j=0;j<job.getJobPapers().get(i).getJobColorCombinations().size(); j++) {
			    			totalContentOfSignature+=job.getJobPapers().get(i).getJobColorCombinations().get(j).getNumberOfSignature();
			    	}
			    }
				printer.print(document, Math.ceil(totalContentOfSignature)+"", 50, 297-74);
				printer.printMoney(document, Math.ceil(totalContentOfSignature)*1000, 175, 297-74);
				//variablePrice
				variablePrice+=Math.ceil(totalContentOfSignature)*1000;
			}
		
			float totalNumberOfSignature = 0;
			for(int f=0; f<job.getJobPapers().size(); f++) {
				if(job.getJobPapers().get(f).getContentType().getId()==2) {
					for(int fk=0; fk<job.getJobPapers().get(f).getJobColorCombinations().size(); fk++) {
						totalNumberOfSignature+=job.getJobPapers().get(f).getJobColorCombinations().get(fk).getNumberOfSignature();
					}
				}
			}
			int totalNumberOfSignature_ = (int) Math.ceil(totalNumberOfSignature);
			int glueBondPreparationFactor= (int) Math.ceil(totalNumberOfSignature_/10.0);
			//use binding type instead jobactivty glue option
			if (job.getJobActivity().getBindingType()!=null && job.getJobActivity().getBindingType().getName().equals("Glue-Bound"))	 {
				printer.printMoney(document, 10000, 130, 297-79.5f);
				//fixePrice
				fixePrice+=10000;
				printer.printMoney(document, 80000*glueBondPreparationFactor , 175, 297-79.5f);
				//variablePrice
				variablePrice+=80000*glueBondPreparationFactor;
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
				printer.printMoney(document,  job.getJobActivity().getLamination()*(laminationUnitePrice/1000.0)*1000_000, 175, 297-92);
				//variablePrice
				variablePrice+=job.getJobActivity().getLamination()*(laminationUnitePrice/1000.0)*1000_000;
			
			}

			/**
			 * Print  Paper Element
			 */
			p3=variablePrice-p1-p2;
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
				 printer.printMoney(document,  cb.getNumberOfSignature()*1000, 25, 297-30-vct);
				 printer.printMoney(document,  (cb.getNumberOfSignature()*10)*percentage , 25, 297-36-vct);
				 int totalOversh = (int) (cb.getNumberOfSignature()*10*percentage + cb.getNumberOfSignature()*1000);
				 printer.printMoney(document,  totalOversh, 29, 297-43-vct);
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
					
					up = (int) Math.floor(up/(lengthFoldedJob*widthFoldedJob));
					DecimalFormat dcf= new DecimalFormat("#.###");
					dcf.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
					dcf.setRoundingMode(RoundingMode.CEILING);
					printer.print(document, up+"", 56, 297-42-vct);
					double variableC= totalOversh/(up*1000.0);
					printer.print(document, Double.valueOf(dcf.format(variableC))+"", 80, 297-42-vct);
					printer.printMoney(document, paper.getUnitPrice(), 120, 297-42-vct);
					//fixeCost
					//fixePrice+=paper.getUnitPrice();
					printer.printMoney(document,  paper.getUnitPrice()*Double.valueOf(dcf.format(variableC)) , 175, 297-40-vct);
					//variableCost
					variablePrice+=paper.getUnitPrice()*Double.valueOf(dcf.format(variableC));
					printer.print(document, cb.getNumberOfSignature()+"", 13, 297-53-vct);
					
					int ps = maxC*50;
				
					if(cb.getPrintingMachine().getAbbreviation().contains("SPM"))
						ps+=50;
					
					if(cb.getBackColorNumber()!=0&&cb.getFrontColorNumber()!=0)
						ps=ps*2;
					
					printer.print(document, ps+"", 43, 297-53-vct);
					printer.printMoney(document, cb.getNumberOfSignature()*ps, 95, 297-51.5f-vct);
					
					printer.print(document, 40+"", 43, 297-58-vct);
					printer.printMoney(document,  40*cb.getNumberOfSignature() , 95, 297-58-vct);
					
					printer.printMoney(document,  (40+ps)*cb.getNumberOfSignature() , 29, 297-64-vct);
					printer.print(document, up+"", 66, 297-64-vct);
					printer.print(document, dcf.format((40+ps)*cb.getNumberOfSignature()/(up*1000.0)), 95, 297-64-vct);
					
					printer.printMoney(document,Double.valueOf(dcf.format((40+ps)*cb.getNumberOfSignature()/(up*1000.0)))*paper.getUnitPrice() , 130, 297-64-vct);
					//fixeCost
					fixePrice+=Double.valueOf(dcf.format((40+ps)*cb.getNumberOfSignature()/(up*1000.0)))*paper.getUnitPrice();
					
				 }
			 }
			 p4=variablePrice-p1-p2-p3;
			printer.printMoney(document, fixePrice , 135, 99);
			printer.printMoney(document, variablePrice , 175, 99);
			job.setFixCost((float) fixePrice);
			job.setVariableCost((float) variablePrice);
			job.setControlSheetGenerated(true);
			jobServiceImpl.save(job);
			document.close();
			return job.getReferenceNumber()+".pdf";
		
	}

	@GetMapping("/error-pdf")
	@ResponseBody
	public String fileError(Model model) throws FileNotFoundException {

		return "file=error.pdf&dir=folder.controlSheet";
	}
	@GetMapping("/viewJob/{id}")
	public String viewJobDetails(@PathVariable long id, Model model) {
		Job job = jobServiceImpl.findById(id).get();
		List<JobPaper> jobPapers = job.getJobPapers();
		JobPaper cover = null;
		for(JobPaper jp : jobPapers) {
			if(jp.getContentType().getId()==1) {
				cover=jp;
				jobPapers.remove(jp);
			}
		}
		List<JobEstimate>  jobEstimates = job.getJobEstimates();
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		for (JobEstimate jobEstimate: jobEstimates) {
			for (EstimatePricing estimatePricing: jobEstimate.getEstimatePricings()) {
				for (Invoice invoice: estimatePricing.getInvoices()) {
					invoices.add(invoice);
				}
			}
		}
		model.addAttribute("invoices",invoices);
		model.addAttribute("job",job);
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
		// to get the update page of job
		
		@PreAuthorize("hasAuthority('ROLE_REGISTER_NEW_JOB')")
		@GetMapping("/update-draft/{id}")
		public String getUpdateDraftForm(@PathVariable Long id, Model model) {
			List<Customer> customerResult = customerServiceImpl.findAll();
			List<JobType> jobTypeResult = jobTypeServiceImpl.findAll();
			List<PaperFormat> paperFormatResult = paperFormatServiceImpl.findAll();
			List<JobPaper> jobPaperResult = jobPaperServiceImpl.findAll();
			List<PaperType>  paperTypeResult = paperTypeServiceImpl.listAll();
			
			Job existingJob = jobServiceImpl.findById(id).get();
			model.addAttribute("job", existingJob);
			model.addAttribute("customers", customerResult);
			model.addAttribute("jobTypes", jobTypeResult);
			model.addAttribute("paperFormats", paperFormatResult);
			model.addAttribute("jobPaperResults", jobPaperResult);
			model.addAttribute("paperTypes", paperTypeResult);
			
		    return "/billing/draft-update--form";
		}
		
		
		@GetMapping("/estimate/{id}")
		public String getEstimateForm (@PathVariable long id, Model model) {
			Job findJob = jobServiceImpl.findById(id).get();
			model.addAttribute("job", findJob);		

			return "/billing/estimate/job-estimate";
		}
		

		@GetMapping("/estimateRef/{ref}")
		public String getEstimate (@PathVariable String ref, Model model) {
			
			JobEstimate estimate = jobEstimateRepository.findByReference(ref).get();
			Job job = estimate.getJob();
			List<EstimatePricing> estimates = estimate.getEstimatePricings();
			
			List<String> typsettingActivities = jobServiceImpl.gettypsettingActivities(job);
			
			//Get and Structure printing 
			List<JobPaper> jobPapers = job.getJobPapers();
			List<JobPaper> jobPapersResult= new ArrayList<JobPaper>();
			
			JobPaper coverJobPaper = new JobPaper();
			for(JobPaper jj : jobPapers) {
				if(jj.getContentType().getId()==1) coverJobPaper=jj;
				else jobPapersResult.add(jj);			}

			String finishingActivities =jobServiceImpl.getFinishingActivities(job);

			model.addAttribute("typeSettingActivities", typsettingActivities);
			model.addAttribute("finishingActivities", finishingActivities);
			model.addAttribute("finishingActivities", finishingActivities);
			model.addAttribute("coverJobPaper", coverJobPaper);			
			model.addAttribute("contentJobPapers", jobPapersResult);

			model.addAttribute("JobEstimateP", estimate);	
			model.addAttribute("estimates", estimates);		
			model.addAttribute("job", job);		
			return "/billing/estimate/estimate-view";
		}
		
		@GetMapping("/generate/{id}")
		public String generateEstimate(@PathVariable long id, @RequestParam("quantities") String quantities, 
				@RequestParam("extraFee") int extraFee, @RequestParam("extraFeeDescription") Optional<String> extraFeeDescription,
				@RequestParam("advancePercentage") float advancePercentage, Model model) {
			
			Job job = jobServiceImpl.findById(id).get();
			//Get and structure typesetting values
			List<String> typsettingActivities = jobServiceImpl.gettypsettingActivities(job);

			//Get and Structure printing 
			List<JobPaper> jobPapers = job.getJobPapers();
			List<JobPaper> jobPapersResult= new ArrayList<JobPaper>();
			
			JobPaper coverJobPaper = new JobPaper();
			for(JobPaper jj : jobPapers) {
				if(jj.getContentType().getId()==1) coverJobPaper=jj;
				else jobPapersResult.add(jj);			}
			
			//Get Finishing structure
			String finishingActivities =jobServiceImpl.getFinishingActivities(job);


			String[] qty = quantities.split("@");
			List<EstimateDTO> estimates = new ArrayList<EstimateDTO>();
			for(int i=0; i<qty.length;i++) {
				EstimateDTO estimateDTO = new EstimateDTO();
				estimateDTO.setCreatedDate(new Date());	
				estimateDTO.setAdvancePercentage(advancePercentage);
				estimateDTO.setQuantity(Integer.parseInt(qty[i]));
				int totalPrice=  Math.round(((job.getVariableCost()/1000) * Integer.parseInt(qty[i])) +(job.getFixCost()+ extraFee));
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
			model.addAttribute("contentJobPapers", jobPapersResult);
			return "/billing/estimate/generated-estimate-result";
		}

	@PostMapping("/estimate/confirm/{id}")
	@ResponseBody
	public String confirmEstimate(@PathVariable Long id,@RequestParam("quantities") String quantities,
								   @RequestParam("extraFee") int extraFee, @RequestParam("extraFeeDescription")  Optional<String>  extraFeeDescription,
								   @RequestParam("advancePercentage") float advancePercentage, Model model) {
		try {
		Job job = jobServiceImpl.findById(id).get();
		String[] qty = quantities.split("@");
		String updateRef = job.getReferenceNumber();
		String reference =updateRef.replace('J', 'E');
//		if(job.getJobEstimates().size()==count)count++;
	    reference = reference+"-"+(job.getJobEstimates().size()+1);
		JobEstimate estimate = new JobEstimate();
		estimate.setReference(reference);
		estimate.setAdvancePercentage(advancePercentage);
		estimate.setCreatedDate(new Date());
		List<EstimatePricing> estimatePricings = new ArrayList<EstimatePricing>();
		for(int i=0; i<qty.length;i++) {
			EstimatePricing estimatePricing =  new EstimatePricing();
			estimatePricing.setQuantity(Integer.parseInt(qty[i]));
			int totalPrice= Math.round(((job.getVariableCost()/1000) * Integer.parseInt(qty[i])) +(job.getFixCost()+ extraFee));
			estimatePricing.setTotalPrice(totalPrice);
			estimatePricing.setUnitPrice(Math.round(totalPrice/Integer.parseInt(qty[i])));
			estimatePricing.setJobEstimate(estimate);
			estimatePricings.add(estimatePricing);
		}
		estimate.setEstimatePricings(estimatePricings);
		estimate.setJob(job);
		jobEstimateRepository.save(estimate);
		//jobEstimateServiceImpl.save(estimate); 

		return reference;
		}catch (Exception e) {
			e.printStackTrace();
			return "k0###";
		}
	}

	@GetMapping("/estimate-pdf/{reference}")
	@ResponseBody
	public String generateEstimatePdf(@PathVariable String reference) throws IOException {
		JobEstimate jobEstimate=jobEstimateRepository.findByReference(reference).get();
		if(jobEstimate.getJob().isControlSheetGenerated()) {
			return createEstimateDataPdf(reference);
		}
		else {
			return "The controlsheet must be Generated before";
		}	
			
	}	
	
		
	/*
	 * Print the estimate pdf
	 */
	public String createEstimateDataPdf(String estimateName) throws IOException{
	 try {
		PdfWriter pdfWriter = new PdfWriter(estimateDir+estimateName+".pdf");
		JobEstimate jobEstimate=jobEstimateRepository.findByReference(estimateName).get();
		Job job=jobEstimate.getJob();
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument, PageSize.A4);
//		 document.setMargins(25, 25, 297-156, 50);

		PrintableElement printer = new PrintableElement();	
		JobActivity jobActivity = job.getJobActivity();
		List<JobPaper> jcc = job.getJobPapers();
		JobPaper coverPaper = null;
		for(JobPaper pp : jcc) {
			if(pp.getContentType().getId()==1)
			{
				coverPaper=pp;
				printer.print(document,"Cover: "+ job.getOpenLength()+" X "+job.getOpenWidth()+" mm", 73, 297-93);
				printer.print(document, "Cover: " +job.getCoverVolume()+" Pages", 73, 297-110);
				printer.print(document,"Cover: "+  coverPaper.getJobColorCombinations().get(0).getFrontColorNumber() + "/" +coverPaper.getJobColorCombinations().get(0).getBackColorNumber()+" " + coverPaper.getJobColorCombinations().get(0).getPrintType().getName(), 73, 297-136);
				printer.printHeader(document,"Paper", 38, 297-182);
			    printer.print(document, "Cover : "+ job.getJobPapers().get(0).getPaperType().getName(), 73, 297-182);
				printer.print(document, job.getJobPapers().get(0).getGrammage()+" GSM", 168, 297-182);
		}}
		String jobActivities = "";
		String typeSettings = "";
		String reproduction = "";
			
			printer.printHeader(document, "Estimate ".toUpperCase() , 73, 297-42);
		 	printer.print(document, "("+jobEstimate.getReference().toUpperCase()+")", 97, 297-42);

		 	printer.printHeader(document,job.getCustomer().getName().toUpperCase(), 123, 297-52);
			printer.printHeader(document,job.getCustomer().getAddress().toUpperCase(), 123, 297-58);

			printer.printHeader(document, "Description", 38, 297-83);
			printer.print(document, job.getJobType().getName(), 73, 297-83);

			if(job.isTypesettingByUs()||job.isLayOutByUs()) typeSettings =	typeSettings + "By us,";
			if(job.isExistingPlate()) {
				reproduction = reproduction +  "Existing Plate";
				
			}else reproduction = reproduction +  "Data supplied By Customer";

			printer.printHeader(document, "Typesetting ", 38, 297-123);
			printer.print(document, typeSettings, 73, 297-123);
			
			printer.printHeader(document, " Reproduction", 38, 297-128 );
			printer.print(document, reproduction, 73, 297-128);

			printer.printHeader(document, "Format", 38, 297-93);
			printer.printHeader(document, "Volume", 38, 297-110);
			
			printer.printHeader(document, "Printing", 38, 297-137);

				String message_ =" ";
				boolean isContent =false;
				for(JobPaper pp:jcc) {
				if(pp.getContentType().getId()!=1)
					{
					isContent=true;
					for(int j=0; j<pp.getJobColorCombinations().size(); j++) {
					message_+=  pp.getJobColorCombinations().get(j).getFrontColorNumber()+"/"+ pp.getJobColorCombinations().get(j).getBackColorNumber()+" "+pp.getJobColorCombinations().get(j).getPrintType().getName()+"";
				}}
				
			}
				if(isContent) {
					printer.print(document, message_, 90, 297-142);
					printer.print(document, "Content", 73, 297-142);
					printer.print(document,"Content: "+  job.getCloseLength()+" X "+ job.getCloseWidth()+ " mm", 73, 297-99);
					printer.print(document, "Content: " +job.getContentVolume()+" Pages", 73, 297-116);
				}
			
			printer.printHeader(document, "Finishing", 38, 297-161);
			
			if(jobActivity.isHandgather()) jobActivities =	jobActivities + "hand-gatherd, ";
			if(jobActivity.isSelloptaped()) jobActivities =	jobActivities + " Selloptaped, ";
			if(jobActivity.isSewn()) jobActivities =	jobActivities + " Sewn,";
			if(jobActivity.isTrimmed()) jobActivities =	jobActivities + " trimmed, ";
			if(jobActivity.isStitching()) jobActivities =	jobActivities + " Stitched, ";
			//if(!jobActivity.getGlueOption().isEmpty()) jobActivities =	jobActivities + jobActivity.getGlueOption()+ ", ";
			if(jobActivity.getXWiredStiched()>0) jobActivities =	jobActivities + jobActivity.getXWiredStiched()+ " x Stiched, ";
			if(jobActivity.getXCreased()>0) jobActivities =	jobActivities + " Cover "+ jobActivity.getXCreased()+ " x creased, ";
			if(jobActivity.getXCross()>0) jobActivities =	jobActivities + jobActivity.getXCross()+ " x folded,";
			if(jobActivity.getXNumbered()>0) jobActivities =	jobActivities + jobActivity.getXNumbered()+ " x Numbered, ";
			if(jobActivity.getBindingType()!=null) jobActivities =	jobActivities + jobActivity.getBindingType().getName()+" ";
			if(jobActivity.getLamination()>0) jobActivities =	jobActivities +" Cover " + jobActivity.getLamination() + " side(s) laminated, ";

		 printer.printParagraphe(document,jobActivities, 73, 297-170);

		   if(isContent)			
			printer.printParagraphe(document,"Content : ", 73, 297-187);
			float vecto = -5;
			for(JobPaper pp:jcc) {
				if(pp.getContentType().getId()!=1) {
					vecto+=5;
					printer.print(document, pp.getPaperType().getName(),  90, 297-187-vecto);
					printer.print(document, pp.getGrammage()+" GSM",  168, 297-187-vecto);
				}

			}
			printer.printHeader(document, "Quantity",  38, 297-200);
			printer.printHeader(document, "Unit(XAF)",  132, 297-200);
			printer.printHeader(document, "Total(XAF)",  172, 297-200);
			
			String messagesAdvancePayment="";
			List<EstimatePricing> estimates =jobEstimate.getEstimatePricings();
			float vect = -5;
			DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
			printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
			for(int i=0; i<estimates.size(); i++) {
				vect+=5;
				printer.printMoney(document,estimates.get(i).getQuantity(), 82, 297-207-vect);
				printer.printMoney(document,estimates.get(i).getUnitPrice(), 132, 297-207-vect);
				printer.printMoney(document,estimates.get(i).getTotalPrice() , 171, 297-207-vect);
				
			}
			if(jobEstimate.getAdvancePercentage()> 0)
				messagesAdvancePayment =" Terms of Payment : "+ jobEstimate.getAdvancePercentage()  + "%" + " in advance, "+(100-jobEstimate.getAdvancePercentage())+ ""+"% on delivery.";
		printer.printHeader(document,messagesAdvancePayment, 38,297-227-vect);
			
			document.close();
			String	file = jobEstimate.getReference()+".pdf";
		 return "file="+file+"&dir=folder.estimate";
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			return "file=error.pdf&dir=folder.estimate";
		}
		
	}
	
	/*
	 * print Estimate with commission
	 */
	
	@GetMapping("/estimate-pdf-commission/{reference}")
	@ResponseBody
	public String generateEstimatePdfWithCommission(@PathVariable String reference) throws IOException {
	
			return createEstimateDataPdfWithCommision(reference);
			
	}
	
	public String createEstimateDataPdfWithCommision(String estimateName) throws IOException{
		 try {
			PdfWriter pdfWriter = new PdfWriter(estimateDir+estimateName+".pdf");
			JobEstimate jobEstimate=jobEstimateRepository.findByReference(estimateName).get();
			
			Job job=jobEstimate.getJob();
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
//			 document.setMargins(25, 25, 297-156, 50);

			PrintableElement printer = new PrintableElement();	
			JobActivity jobActivity = job.getJobActivity();
			List<JobPaper> jcc = job.getJobPapers();
			JobPaper coverPaper = null;
			for(JobPaper pp : jcc) {
				if(pp.getContentType().getId()==1)
				{
					coverPaper=pp;
					printer.print(document,"Cover: "+ job.getOpenLength()+" X "+job.getOpenWidth()+" mm", 73, 297-93);
					printer.print(document, "Cover: " +job.getCoverVolume()+" Pages", 73, 297-110);
					printer.print(document,"Cover: "+  coverPaper.getJobColorCombinations().get(0).getFrontColorNumber() + "/" +coverPaper.getJobColorCombinations().get(0).getBackColorNumber()+" " + coverPaper.getJobColorCombinations().get(0).getPrintType().getName(), 73, 297-136);
					printer.printHeader(document,"Paper", 38, 297-182);
				    printer.print(document, "Cover : "+ job.getJobPapers().get(0).getPaperType().getName(), 73, 297-182);
					printer.print(document, job.getJobPapers().get(0).getGrammage()+" GSM", 168, 297-182);
			}}
			String jobActivities = "";
			String typeSettings = "";
			String reproduction = "";
				
				printer.printHeader(document, "Estimate ".toUpperCase() , 73, 297-42);
			 	printer.print(document, "("+jobEstimate.getReference().toUpperCase()+")", 97, 297-42);

			 	printer.printHeader(document,job.getCustomer().getName().toUpperCase(), 123, 297-52);
				printer.printHeader(document,job.getCustomer().getAddress().toUpperCase(), 123, 297-58);

				printer.printHeader(document, "Description", 38, 297-83);
				printer.print(document, job.getJobType().getName(), 73, 297-83);

				if(job.isTypesettingByUs()||job.isLayOutByUs()) typeSettings =	typeSettings + "By us,";
				if(job.isExistingPlate()) {
					reproduction = reproduction +  "Existing Plate";
					
				}else reproduction = reproduction +  "Data supplied By Customer";

				printer.printHeader(document, "Typesetting ", 38, 297-123);
				printer.print(document, typeSettings, 73, 297-123);
				
				printer.printHeader(document, " Reproduction", 38, 297-128 );
				printer.print(document, reproduction, 73, 297-128);

				printer.printHeader(document, "Format", 38, 297-93);
				printer.printHeader(document, "Volume", 38, 297-110);
				
				printer.printHeader(document, "Printing", 38, 297-137);

					String message_ =" ";
					boolean isContent =false;
					for(JobPaper pp:jcc) {
					if(pp.getContentType().getId()!=1)
						{
						isContent=true;
						for(int j=0; j<pp.getJobColorCombinations().size(); j++) {
						message_+=  pp.getJobColorCombinations().get(j).getFrontColorNumber()+"/"+ pp.getJobColorCombinations().get(j).getBackColorNumber()+" "+pp.getJobColorCombinations().get(j).getPrintType().getName()+"";
					}}
					
				}
					if(isContent) {
						printer.print(document, message_, 90, 297-142);
						printer.print(document, "Content", 73, 297-142);
						printer.print(document,"Content: "+  job.getCloseLength()+" X "+ job.getCloseWidth()+ " mm", 73, 297-99);
						printer.print(document, "Content: " +job.getContentVolume()+" Pages", 73, 297-116);
					}
				
				printer.printHeader(document, "Finishing", 38, 297-161);
				
				if(jobActivity.isHandgather()) jobActivities =	jobActivities + "hand-gatherd, ";
				if(jobActivity.isSelloptaped()) jobActivities =	jobActivities + " Selloptaped, ";
				if(jobActivity.isSewn()) jobActivities =	jobActivities + " Sewn,";
				if(jobActivity.isTrimmed()) jobActivities =	jobActivities + " trimmed, ";
				if(jobActivity.isStitching()) jobActivities =	jobActivities + " Stitched, ";
				//if(!jobActivity.getGlueOption().isEmpty()) jobActivities =	jobActivities + jobActivity.getGlueOption()+ ", ";
				if(jobActivity.getXWiredStiched()>0) jobActivities =	jobActivities + jobActivity.getXWiredStiched()+ " x Stiched, ";
				if(jobActivity.getXCreased()>0) jobActivities =	jobActivities + " Cover "+ jobActivity.getXCreased()+ " x creased, ";
				if(jobActivity.getXCross()>0) jobActivities =	jobActivities + jobActivity.getXCross()+ " x folded,";
				if(jobActivity.getXNumbered()>0) jobActivities =	jobActivities + jobActivity.getXNumbered()+ " x Numbered, ";
				if(jobActivity.getBindingType()!=null) jobActivities =	jobActivities + jobActivity.getBindingType().getName()+" ";
				if(jobActivity.getLamination()>0) jobActivities =	jobActivities +" Cover " + jobActivity.getLamination() + " side(s) laminated, ";

			 printer.printParagraphe(document,jobActivities, 73, 297-170);
			 
			   if(isContent)			
				printer.printParagraphe(document,"Content : ", 73, 297-187);
				float vecto = -5;
				for(JobPaper pp:jcc) {
					if(pp.getContentType().getId()!=1) {
						vecto+=5;
						printer.print(document, pp.getPaperType().getName(),  90, 297-187-vecto);
						printer.print(document, pp.getGrammage()+" GSM",  168, 297-187-vecto);
					}

				}
				printer.printHeader(document, "Quantity",  38, 297-200);
				printer.printHeader(document, "Unit(XAF)",  132, 297-200);
				printer.printHeader(document, "Total(XAF)",  172, 297-200);
				
				String messagesAdvancePayment="";
				
				List<EstimatePricing> estimates =jobEstimateServiceImpl.generateCommissionEstimate(jobEstimate.getId(), jobEstimate.getCommission(), jobEstimate.getDiscountValue());
				float vect = -5;
				DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
				printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
				for(int i=0; i<estimates.size(); i++) {
					vect+=5;
					printer.printMoney(document,estimates.get(i).getQuantity(), 82, 297-207-vect);
					printer.printMoney(document,estimates.get(i).getUnitPrice(), 132, 297-207-vect);
					printer.printMoney(document,estimates.get(i).getTotalPrice() , 171, 297-207-vect);
					
				}
				if(jobEstimate.getAdvancePercentage()> 0)
					messagesAdvancePayment =" Terms of Payment : "+ jobEstimate.getAdvancePercentage()  + "%" + " in advance, "+(100-jobEstimate.getAdvancePercentage())+ ""+"% on delivery.";
			printer.printHeader(document,messagesAdvancePayment, 38,297-227-vect);
				
				document.close();
				String	file = jobEstimate.getReference()+".pdf";
			 return "file="+file+"&dir=folder.estimate";
			} 
			
			catch (Exception e) {
				e.printStackTrace();
				return "file=error.pdf&dir=folder.estimate";
			}
			
		}
		
	@GetMapping("/search-by/{reference}")
	public String findJobByReferenceNumber(@PathVariable String reference, Model model) {
		try {
		Optional<Job> results = jobServiceImpl.findJobByReferenceNumber(reference);
			if (results.isPresent()) {	
				Job result = results.get();
				model.addAttribute("result", result);
				return "billing/job-by-reference-number";
			}
			return "billing/job-by-reference-number";
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/*
	 * Invoices Methodes
	 */
	@GetMapping("/generate/invoice/{id}")
	public String generateInvoice(@PathVariable long id, Invoice invoice, Model model) {
		Invoice invoicefinded =	invoiceServiceImpl.saveInvoice(id);
		EstimatePricing estimate = estimatePricingServiceImpl.findById(id).get();
		Job job = estimate.getJobEstimate().getJob();
		
		//Get and structure typesetting values
		List<String> typsettingActivities = jobServiceImpl.gettypsettingActivities(job);
		
		//Get and Structure printing 
		List<JobPaper> jobPapers = job.getJobPapers();
		List<JobPaper> jobPapersResult= new ArrayList<JobPaper>();
		
		JobPaper coverJobPaper = new JobPaper();
		for(JobPaper jj : jobPapers) {
			if(jj.getContentType().getId()==1) coverJobPaper=jj;
			else jobPapersResult.add(jj);			}
		
		//Get Finishing structure
		String finishingActivities = jobServiceImpl.getFinishingActivities(job);
		model.addAttribute("typeSettingActivities", typsettingActivities);
		model.addAttribute("finishingActivities", finishingActivities);
		model.addAttribute("coverJobPaper", coverJobPaper);			
		model.addAttribute("contentJobPapers", jobPapersResult);
		
		model.addAttribute("invoices", invoicefinded);
		model.addAttribute("job", job);
		return "billing/estimate/invoice-view";
	}
	
/*
 *   Estimate function  
 */
	@GetMapping("/get-estimate/{id}")
	public String displayEstimates(@PathVariable long id,Model model) {
		Job job = jobServiceImpl.findById(id).get();
		List<Integer> jobEstimateList = new ArrayList<Integer>();
		for (JobEstimate jobEstimate :job.getJobEstimates()) {
			for(EstimatePricing estimatePricing : jobEstimate.getEstimatePricings()) {
				jobEstimateList.add(estimatePricing.getInvoices().size());
			}
		}
		model.addAttribute("jobEstimateList", jobEstimateList);
		model.addAttribute("job", job);
		return "billing/estimate/view-estimate";
	}

	@GetMapping("/find-by/creationdate/{startDate}/{endDate}")
	public String findeByCreationDate(@PathVariable Date startDate, @PathVariable  Date endDate, Model model) {
		try {
			List<Job> results = jobServiceImpl.findByCreationDateBetween(startDate, endDate);
			model.addAttribute("results",results);
			return "billing/job-by-creation-date";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@InitBinder
	public void customDateEditor(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
	}
	
	@GetMapping("/estimateRef/commission/{ref}")
	public String getCommissionForm (@PathVariable String ref, Model model) {
		
		JobEstimate estimate = jobEstimateRepository.findByReference(ref).get();
		Job job = estimate.getJob();
		List<EstimatePricing> estimates = estimate.getEstimatePricings();

		model.addAttribute("estimates", estimates);		
		model.addAttribute("estimate", estimate);	
		model.addAttribute("job", job);		
		return "/billing/estimate/commission-view";
	}
	
	@GetMapping("/estimate/commission/{id}/{commissionValue}/{discountValue}")
	public String findeByCreationDate(@PathVariable long id,@PathVariable double commissionValue, @PathVariable  double discountValue, Model model) {
		
		try {
			List<EstimatePricing> estimateP = jobEstimateServiceImpl.generateCommissionEstimate(id, commissionValue, discountValue);
			Job job = jobEstimateServiceImpl.findById(id).getJob();
			JobEstimate jobEstimate = jobEstimateServiceImpl.findById(id);

			model.addAttribute("estimateP",estimateP);
			model.addAttribute("jobEstimate",jobEstimate);

			model.addAttribute("job",job);

			return "/billing/estimate/commission-result-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/search-estimate-by/{reference}")
	public String displayEstimates(@PathVariable String reference,Model model) {
		try {
			Optional<JobEstimate> estimate = jobEstimateServiceImpl.findByReferenceNumber(reference);	
			if(estimate.isPresent()) {
				JobEstimate jobEstimate = estimate.get();
				Job job = jobEstimate.getJob();
				model.addAttribute("jobEstimates", jobEstimate);
				model.addAttribute("job", job);
				return "billing/estimate/view-estimate-search-result";
			}
			else 
			//	model.addAttribute("job", job);
				return "billing/estimate/view-estimate-search-result";
			
		} catch (Exception e) {
			throw e;
		}
			
		}

	//<--------------------- Save DraftJob ------------------------------>
			@PostMapping(value="/save-draft", consumes=MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public String saveDraft(@RequestBody JobDTO jobDTO,Model model){
				try {
					jobServiceImpl.saveDraft(jobDTO);
					return "OK";
				} catch (Exception e) {
					e.printStackTrace();
					return "KO";
				}
			}
			
			//<--------------------- Update DraftJob ------------------------------>
			@PostMapping(value="/update-draft/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody
			public String updateDraft(@PathVariable Long id,@RequestBody JobDTO jobDTO){
				try {
					jobServiceImpl.updateDraft(jobDTO,id);
					return "OK";
				} catch (Exception e) {
					e.printStackTrace();
					return "KO";
				}
			}

	
}

package com.ppp.billing.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.serviceImpl.BindingTypeserviceImpl;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;
import com.ppp.billing.serviceImpl.JobColorCombinationServiceImpl;
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
public class JobController {
	
	@Value("${pdf.path}")
    private String pdfStoragePath;
	
	@Value("${folder.image.background}")
	private String backgroundFolder;
	@Autowired
	private JobServiceImpl jobServiceImpl;
	@Autowired
	private JobRepository jobRepository;
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
	public ResponseEntity<FileSystemResource> generatePdf(@PathVariable long id) throws IOException {
		 String pdfFile = createJobDataPdf(id);
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Disposition", "inline");
		 
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new FileSystemResource(pdfFile));
	}	
	private String createJobDataPdf(Long id) throws FileNotFoundException, MalformedURLException {
		String pdf = "Hello.pdf";
		PdfWriter pdfWriter = new PdfWriter(pdf);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument, PageSize.A4);
		PdfCanvas canvas= new PdfCanvas(pdfDocument.addNewPage());
		ImageData data = ImageDataFactory.create(backgroundFolder + "P1.jpg");
		canvas.addImage(data, PageSize.A4, false);

		try {
			/**
			 * Impression de la premiere page du controle sheet
			 */
			Job job = jobServiceImpl.findById(id).get();
			PrintableElement printer = new PrintableElement();	
			printer.print(document, job.getCustomer().getName(), 9, 297-16);
			printer.print(document, job.getCustomer().getAddress(), 9, 297-25);
			printer.print(document, job.getCustomer().getTelephone(), 9, 297-35);
			printer.print(document, job.getTitle(), 80, 297-66);
			printer.print(document, job.getOpenLength()+"", 83, 297-88);
			printer.print(document, job.getOpenWidth()+"", 109, 297-88);
			printer.print(document, job.getCloseLength()+"", 157, 297-88);
			printer.print(document, job.getCloseWidth()+"", 182, 297-88);
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
			float y = 297-188;
			printer.print(document, job.getJobActivity().getXPerforated()+ "", 34, y-8);
			printer.print(document, job.getJobActivity().getXNumbered()+"", 69, y-8);
			if(job.getJobActivity().getGlueOption().toLowerCase().equals("Head".toLowerCase()))
			printer.print(document, "X",  111, y-8.5f);
			
			if(job.getJobActivity().getGlueOption().toLowerCase().equals("Left side".toLowerCase()))
				printer.print(document, "X",  128, y-8.5f);
			
			if(job.getJobActivity().getGlueOption().toLowerCase().equals("Glue-bound".toLowerCase()))
				printer.print(document, "X",  181.5f, y-17);
			
			if(job.getJobActivity().isSelloptaped())
				printer.print(document, "X",  183, y-8.5f);
			
			if(job.getJobActivity().isTrimmed()) {
				printer.print(document, "X",  149.5f, y-8.5f);
				printer.print(document, "Trimmed",  154, y-8);
			}

			if(job.getJobActivity().isSewn())
				printer.print(document, "X",  164, y-17);

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
				
				
				String contentType = plateMakingCosting.getJobPaper().getContentType().getName();
				double signature = plateMakingCosting.getSignatures();
				int run = exposior;
				
				float y1 = 120.5f;
				printer.print(document, machine, 27, y1-vecteur);
				printer.print(document, basic+"", 55, y1-vecteur);
				printer.print(document, basicCost+"", 128, y1-vecteur);
				
				float y2 = 103;
				printer.print(document, exposior+"", 55, y2-vecteur);
				printer.print(document, exposiorCusting+"", 127, y2-vecteur);
				
				printer.print(document, contentType, 15, 115-vecteur);
				
				printer.print(document, machine+"("+contentType+")",  165, 297-35-vecteur2);
				
				printer.print(document, plateMakingCosting.getPrintingMachine().getPlateLength()+"",  175, 297-42-vecteur2);
				printer.print(document, plateMakingCosting.getPrintingMachine().getPlateWidth()+"",  197, 297-59-vecteur2);
				
				printer.print(document, exposior+"",  180, 297-78-vecteur2);
				printer.print(document, signature+"",  180, 297-85-vecteur2);
				printer.print(document,run+"",  180, 297-91-vecteur2);
			
				 float mmToPoint = 2.83465f;
				if(basic>0) {
					int k = (int) (Math.log(basic)/Math.log(2));
					int a =k/2;
					int b=k-a;
					int horizontalLignes = (int) Math.pow(2, a)-1;
					int verticalLigne = (int) Math.pow(2, b)-1;
					float step = 24/(horizontalLignes+1);
					float acc = 0;
					for(int i1=1; i1<=horizontalLignes; i1++) {
						acc += step;
						canvas_.moveTo(155*mmToPoint, (297-46-acc-vecteur3)*mmToPoint);
						canvas_.lineTo(196*mmToPoint, (297-46-acc-vecteur3)*mmToPoint);
//						canvas_.closePathStroke();
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
				printer.print(document, "CTP", 107, 171);
				

			}
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
					

					printer.print(document, jobPeper.getContentType().getName(), 10, 297-29-decalage);
					printer.print(document, jobPeper.getPaperType().getName(), 54, 297-29-decalage);
					printer.print(document, printinElementCost.getPreparationUnitCost()+"", 134, 297-29-decalage);	
					
					printer.print(document, printinElementCost.getInckChange()+"", 55, 297-35f-decalage);
					printer.print(document, printinElementCost.getInckChangeUnitCost()+"", 75, 297-35f-decalage);
					
					printer.print(document, printinElementCost.getPlateChange()+"", 55, 297-41f-decalage);
					printer.print(document, printinElementCost.getPlateChangeUnitCost()+"", 75, 297-41-decalage);
					
					printer.print(document, printinElementCost.getRun()+"", 55, 297-47f-decalage);
					printer.print(document, printinElementCost.getRunUnitCost()+"", 75, 297-47f-decalage);
					
					printer.print(document, printinElementCost.getInckChange()*printinElementCost.getInckChangeUnitCost()+"", 135, 297-35-decalage);
					printer.print(document, printinElementCost.getPlateChange()*printinElementCost.getPlateChangeUnitCost()+"", 135, 297-41-decalage);
					printer.print(document, printinElementCost.getRun()*printinElementCost.getRunUnitCost()+"", 175, 297-47-decalage);

				}
				
			}
			
			document.close();
			
		} catch (Exception e) {
			throw new NotFoundException("Error", e);
		}
		return null;
	}
	
//	public String createPrepressPdf(long id) throws FileNotFoundException, MalformedURLException {
//		String pdf = "prepress.pdf";
//		PdfWriter pdfWriter = new PdfWriter(pdf);
//		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//		Document document = new Document(pdfDocument, PageSize.A4);
//		PdfCanvas canvas= new PdfCanvas(pdfDocument.addNewPage());
//		ImageData data = ImageDataFactory.create(backgroundFolder + "P2.jpg");
//		canvas.addImage(data, PageSize.A4, false);
//		try {
//			document.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		return null;
//	}


	@GetMapping("/viewJob/{id}")
	public String viewJobDetails(@PathVariable long id, Model model) {
		Job findJob = jobServiceImpl.findById(id).get();
		List<JobPaper> jobPapers = findJob.getJobPapers();
		JobPaper cover = jobPapers.remove(0);
		model.addAttribute("job",findJob);
		model.addAttribute("jobPapers",jobPapers);
		model.addAttribute("coverjobPapers",cover);

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
	
	
	}

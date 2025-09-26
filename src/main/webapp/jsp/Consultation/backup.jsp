	  <!-- Consultation -->
			  <div id="ConsultationForm" class="reason-form row" style="display:none;">
			    <h6 class="text-primary">Consultation</h6>
			    <div class=" row">
			    <div class="col-md-6">
			    <select class="form-select" name="visitServices[0].serviceTypeId" onchange="updatePrice(this, 'consultationPrice')">
			      <option value="1" data-price="5000">General Consultation - 5000</option>
			      <option value="2" data-price="8000">Specialist Consultation - 8000</option>
			    </select>
			    
			    </div>
			     <div class="col-md-6">
			      <input type="number" id="consultationPrice" name="visitServices[0].price" class="form-control mt-2" readonly value="0">
			     
			     </div>
			    </div>
			  </div>
			  
			   <div id="Consultation-PrénataleForm" class="reason-form row" style="display:none;">
			    <h6 class="text-primary">Consultation Prenantal </h6>
			    <div class=" row">
			    <div class="col-md-6">
			    <select class="form-select" name="visitServices[0].serviceTypeId" onchange="updatePrice(this, 'consultation-PrénatalePrice')">
			      <option value="1" data-price="5000">General Consultation - 5000</option>
			      <option value="2" data-price="8000">Specialist Consultation - 8000</option>
			    </select>
			    
			    </div>
			     <div class="col-md-6">
			      <input type="number" id="consultation-PrénatalePrice" name="visitServices[0].price" class="form-control mt-2" readonly value="0">
			     
			     </div>
			    </div>
			  </div>
			
			  <!-- Examen -->
			<div id="ExamenForm" class="reason-form" style="display:none;">
			    <h6 class="text-primary">Examen</h6>
			    <button type="button" class="btn btn-sm btn-primary mb-2" onclick="addExamRow(currentReasonId)">Add Exam</button>
			    <div id="examenList"></div>
			</div>

			
			  <!-- Echographie -->
			  <div id="EchographieForm" class="reason-form row" style="display:none; ">
			     <h6 class="text-primary">Echographie</h6>
			   <div class=" row">
					    <div class="col-md-6">  <select class="form-select" name="visitServices[0].serviceTypeId" onchange="updatePrice(this, 'echoPrice')">
					      <option value="3" data-price="10000">Pelvic Echography - 10000</option>
					      <option value="4" data-price="12000">Abdominal Echography - 12000</option>
					    </select></div>
					     <div class="col-md-6">
					      <input type="number" id="echoPrice" name="visitServices[0].price" class="form-control mt-2" readonly value="0">
					     </div>
					   
			  </div>
			    </div>
			
			  <!-- Pharmacy -->
			  <div id="OrdonnanceForm" class="reason-form" style="display:none;">
			      <h6 class="text-primary">Pharmacy (Sale)</h6>
			    <button type="button" class="btn btn-sm btn-primary mb-2" onclick="addMedicineRow()">Add Medicine</button>
			    <div id="pharmacyList"></div>
			  </div>
			
			  <!-- Vaccination -->
			  <div id="VaccinationForm" class="reason-form" style="display:none;">
			     <h6 class="text-primary">Vaccination</h6>
			    <p>Vaccination form goes here</p>
			  </div>
			
			  <!-- Other -->
			  <div id="AutreForm" class="reason-form" style="display:none">
			      <h6 class="text-primary">Other</h6>
			     <div class=" row">
			     <div class=" row">
					    <div class="col-md-6">  <select class="form-select" name="visitServices[0].serviceTypeId" onchange="updatePrice(this, 'autrePrice')">
					      <option value="3" data-price="10000">Pelvic Echography - 10000</option>
					      <option value="4" data-price="12000">Abdominal Echography - 12000</option>
					    </select>
					    </div>
					     <div class="col-md-6">
					      <input type="number" id="autrePrice" name="visitServices[0].price" class="form-control mt-2" readonly value="0">
					     </div>
					   
			  </div>
			  </div>
			  </div>

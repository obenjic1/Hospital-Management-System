		
<!-- 		main duplicated div -->
			 <div style="display:none" id="eachSignatureDiv"> 
			 <hr style="margin-top: 10px; ">	
			  <div class="row py-3" style="display:none">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select contentPrintingMachine name="name" class="form-select" disabled="disabled" >
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTdivypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
					 <div class="row">
				   <div class="col-6 content-cover-l">
                     <input contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div> 
				  <input type="number" step=".1" delContentSign style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	           
				</div>

			  <div class="row py-3" id="other-signature" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select  contentPrintingMachine name="name" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
				    <option selected>Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    	<c:if test=""></c:if>
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option selected>Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div> <input type="number" step=".1" id="" style="width:70px;color:red; text-align:center" inputSignReadonly>
				  <span> <button   type="button" id="duplicateButton" style="display: inline;" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,1,this.parentNode.parentNode.parentNode.parentNode)"><i class="ri-add-fill"></i></button> </span>
				 </div> 
	            </div>
				</div>
			</div>	
			
			 <div>
			 
			 
			  <div class="row py-3" style="display:none">
			 
			    <div class ="col-lg-3 px8">
				  <label for="" class="form-label"><fmt:message key="printing.machine"/></label> 
				  <select contentPrintingMachine name="name" class="form-select"  disabled="disabled" >
				   <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
		                <option value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for=div"" class="form-label"><fmt:message key="content.print.type"/> </label>
				 <select contentPrintType name="name" updateContentSignature class="form-select">
				  <option >Choose...</option>
				   <c:forEach items="${printTypes}" var="printType">
                     <option value="${printType.id}">${printType.name}</option>
                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><fmt:message key="content.color.combination"/></label>
				<div class="row">
				   <div class="col-6 volume-cover-l">
                     <input contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">                 
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input type="number" min="0" max="5"  contentBackColorNumber placeholder="<fmt:message key='back'/>">  
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><fmt:message key="signature"/></label>
				  <div>
				  <input type="number" step=".1" delContentSign style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode, oldValue)">
				  <span> <button style="background:transparent;border-style: none;color: orange; font-size: 20px;" type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	          		  
				</div>
				
				<c:set var="flag" value="1"></c:set>
				<c:forEach items="${contentJobPaper}" var="contentJPaper">
				<c:set var="counter" value="1"/>
				
				<c:forEach items="${contentJPaper.jobColorCombinations}" var="jobColorCombinaition">
				
				 <div  id="eachSignatureDiv"> 
					<hr style="margin-top: 10px; ">	
				<c:if test="${flag==1}">
<!-- 				 <div  id="signatureDiv">  -->
				</c:if>	
			  <div class="row py-3" style="display:none">
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="name" class="form-select" disabled="disabled">
				  <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				 <select contentPrintType name="name" class="form-select">
				 	<option >Choose...</option>
				 
				   <c:forEach items="${printTypes}" var="printType">
				    	<option value="${printType.id}">${printType.name}</option>

                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 content-cover-l">
                     <input  contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input   type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> 
				  <input type="number" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	
				</div>

			  <div class="row py-3" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select  contentPrintingMachine name="name" class="form-select" onchange="signatureCalculation(this.value,this.parentNode.parentNode.parentNode)">
			      <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/> </a></label>
				 <select contentPrintType name="name" class="form-select">
				   <option >Choose...</option>
				  <c:forEach items="${printTypes}" var="printType">

                     <c:if test="${jobColorCombinaition.printType.id==printType.id}">
				    	<option selected value="${printType.id}">${printType.name}</option>
				    </c:if>
				    <c:if test="${jobColorCombinaition.printType.id!=printType.id}">
				    	<option value="${printType.id}">${printType.name}</option>
				    </c:if>

                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 volume-cover-l">
                     <input  value="${jobColorCombinaition.frontColorNumber}" contentFrontColorNumber type="number" min="0" placeholder="<fmt:message key='front'/>" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input  value="${jobColorCombinaition.backColorNumber}" type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> <input  value="${jobColorCombinaition.numberOfSignature}" type="number" step=".1" id="" style="width:70px;color:red; text-align:center" allSignatures inputSignReadonly>
				  <span> <button   type="button" id="duplicateButton" style="display: inline;" onclick="updateContentSignature(this.parentNode.parentNode.parentNode.parentNode.parentNode,1,this.parentNode.parentNode.parentNode.parentNode)"><i class="ri-add-fill"></i></button> </span>
				 </div> 
				</div>
				</div>
				 </div> 
			
				
				
				
			<c:if test="${counter!=1}">
			  <div class="row py-3" >
			    <div class ="col-lg-3 px8" >
				  <label for="" class="form-label"><a><fmt:message key="printing.machine"/></a></label> 
				  <select contentPrintingMachine name="name" class="form-select" disabled="disabled">
				   <option >Choose...</option>
				    <c:forEach items="${printingMachines}" var="printingMachine">
				    <c:if test="${jobColorCombinaition.printingMachine.id==printingMachine.id}">
				    	<option selected value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
				     <c:if test="${jobColorCombinaition.printingMachine.id!=printingMachine.id}">
				    	<option  value="${printingMachine.id},${printingMachine.plateLength},${printingMachine.plateWidth}">${printingMachine.name}</option>
				    </c:if>
		                
		            </c:forEach>
		         </select>
			   </div>
			   <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
				 <label for="" class="form-label"><a><fmt:message key="content.print.type"/></a> </label>
				 <select contentPrintType name="name" class="form-select">
				   <option >Choose...</option>
				  <c:forEach items="${printTypes}" var="printType">

                     <c:if test="${jobColorCombinaition.printType.id==printType.id}">
				    	<option selected value="${printType.id}">${printType.name}</option>
				    </c:if>
				    <c:if test="${jobColorCombinaition.printType.id!=printType.id}">
				    	<option value="${printType.id}">${printType.name}</option>
				    </c:if>

                   </c:forEach>
                 </select>
               </div>
				  <div class ="col-lg-3 px8" style="position: relative; left: 10px;">
					<label for="" class="form-label"><a><fmt:message key="content.color.combination"/></a></label>
					 <div class="row">
				   <div class="col-6 content-cover-l">
                     <input  value="${jobColorCombinaition.frontColorNumber}"  contentFrontColorNumber placeholder="<fmt:message key='front'/>" type="number" min="0" max="5" style="postion-relative-left:2px;position: relative;left: 4px;">
					                  
				     </div>
				     <div class="col-6 volume-cover-w">
				       <input  value="${jobColorCombinaition.backColorNumber}" type="number" min="0" max="5" placeholder="<fmt:message key='back'/>" contentBackColorNumber>
					     
                    </div>
				  </div>
                  </div>
                  <div class ="col-lg-3 px8" style="position: relative; left:10px;">
				  <label for="" class="form-label"><a><fmt:message key="signature"/></a></label>
				  <div> 
				  <input type="number" value="${jobColorCombinaition.numberOfSignature}" step=".1" delContentSign allSignatures style="width:70px;color:red; text-align:center" onclick="this.oldValue=this.value" onchange="signatureChange(this.value,this.parentNode.parentNode.parentNode.parentNode,oldValue)">
				  <span> <button  type="button" onclick="deleteContentsignature(this.parentNode.parentNode.parentNode.parentNode,this.parentNode.parentNode.parentNode.parentNode.parentNode)" style="background:red"><i class="ri-delete-bin-3-line"></i></i></button> </span>
				 </div> 
	            </div>
	           
				</div>
				
				
			</c:if>	
		   <c:set var="counter" value="2"/>
				
			</c:forEach>
			 <c:set var="flag" value="2"/>
		</c:forEach>
		
				</div>
					
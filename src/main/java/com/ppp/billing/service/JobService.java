package com.ppp.billing.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ppp.billing.model.Job;
import com.ppp.billing.model.dto.JobDTO;

public interface JobService {

	Job saveJob(JobDTO jobDTO);
	List<Job> listAllJob();
    Optional <Job> findJobByReferenceNumber(String referenceNumber);
    List<Job> findByCreationDateBetween(Date startDate, Date endDate);
    Job saveDraft(JobDTO jobDTO);
    void abortJob(long id);
    void proofreadByTheCustomer(long id);
}

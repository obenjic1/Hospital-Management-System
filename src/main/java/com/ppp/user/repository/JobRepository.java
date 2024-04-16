package com.ppp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}

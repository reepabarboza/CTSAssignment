package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.entity.BatchJobExecution;

@Repository
public interface BatchJobExecutionRepository extends JpaRepository<BatchJobExecution, Long>{
	
}

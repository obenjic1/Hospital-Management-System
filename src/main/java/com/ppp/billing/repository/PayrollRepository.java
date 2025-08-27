package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppp.billing.model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByStaffId(Long staffId);
    
    
    @Query("SELECT p.staff.department.name, FUNCTION('MONTH', p.payDate), SUM(p.netSalary) " +
            "FROM Payroll p " +
            "GROUP BY p.staff.department.name, FUNCTION('MONTH', p.payDate) " +
            "ORDER BY FUNCTION('MONTH', p.payDate)")
     List<Object[]> getDepartmentPayrollSummary();
}

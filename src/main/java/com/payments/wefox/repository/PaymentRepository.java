package com.payments.wefox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payments.wefox.entity.Payments;

/**
 * 
 * @author Sakharam
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payments, String>
{
	Payments save(Payments payments);
	
	@Query(value = "truncate table payments", nativeQuery = true)
	void truncateTable();
	
	@Query(value = "SELECT *From payments WHERE payment_id = ?1", nativeQuery = true)
	Payments getById(String paymentId);
}

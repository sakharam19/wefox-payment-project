package com.payments.wefox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payments.wefox.entity.Accounts;

/**
 * 
 * @author Sakharam
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long>
{
	Accounts findByAccountId(Long deptId);
	
	@Modifying
	@Query(value = "update Accounts set last_payment_date = now() where account_id = ?1", nativeQuery = true)
	void updateById(Long accountId);
}

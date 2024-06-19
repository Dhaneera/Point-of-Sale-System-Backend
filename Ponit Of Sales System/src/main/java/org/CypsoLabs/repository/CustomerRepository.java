package org.CypsoLabs.repository;

import org.CypsoLabs.entity.Category;
import org.CypsoLabs.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer getByName(String name);

}

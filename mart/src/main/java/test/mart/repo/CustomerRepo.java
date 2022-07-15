package test.mart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.mart.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

}

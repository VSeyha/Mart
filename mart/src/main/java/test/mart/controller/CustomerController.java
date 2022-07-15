package test.mart.controller;


import org.springframework.web.bind.annotation.*;
import test.mart.domain.Customer;
import test.mart.repo.CustomerRepo;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepo customerRepo ;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @GetMapping("/customer")
    List<Customer> all(){
    return customerRepo.findAll();
    }
    @PostMapping("/customer")
    Customer customer(@RequestBody Customer employee){
        return customerRepo.save(employee);
    }
    @GetMapping("/customer/{id}")
    Customer customer(@PathVariable Long id){
        return customerRepo.findById(id).orElse(null);
    }
    @PutMapping("/customer/{id}")
    Customer customer(@RequestBody Customer customer,@PathVariable Long id){
        return customerRepo.findById(id)
                .map(cus->{
                    cus.setName(customer.getName());
                    cus.setEmail(customer.getEmail());
                    cus.setDescription(customer.getDescription());
                    return customerRepo.save(cus);
                }).orElseGet(()-> {
                    customer.setId(id);
                    return customerRepo.save(customer);
                });
    }
    @DeleteMapping("/customer/{id}")
    void deleteCustomer(@PathVariable Long id){
       customerRepo.deleteById(id);
    }

}

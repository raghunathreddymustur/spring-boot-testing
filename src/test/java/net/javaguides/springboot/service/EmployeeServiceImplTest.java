package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setup()
    {
//        employeeRepository= Mockito.mock(EmployeeRepository.class);
//        employeeService=new EmployeeServiceImpl(employeeRepository);
    }
    @Test
    void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject()
    {
        //given
        Employee employee=Employee.builder().email("raghu@gmail.com").firstName("Raghu").lastName("Reddy").build();
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when

        Employee savedEmployee=employeeService.saveEmployee(employee);
        //then
        Assertions.assertThat(savedEmployee);
    }

    @Test
    void givenEmployeeObject_whenSaveEmployee_thenReturnException()
    {
        //given
        Employee employee=Employee.builder().email("raghu@gmail.com").firstName("Raghu").lastName("Reddy").build();
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        //BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when then
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,()->{employeeService.saveEmployee(employee);});
        //then
        Mockito.verify(employeeRepository,Mockito.never()).save(any(Employee.class));

    }


}

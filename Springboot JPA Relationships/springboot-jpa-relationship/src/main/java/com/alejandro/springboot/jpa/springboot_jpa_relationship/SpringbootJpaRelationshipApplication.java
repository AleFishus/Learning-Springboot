package com.alejandro.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alejandro.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.alejandro.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyBidireccionalFind();
	}

	@Transactional
	public void manyToManyBidireccionalFind(){
		Optional<Student> optionalStudent1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> optionalStudent2 = studentRepository.findOneWithCourses(2L);

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		Course curso1 = courseRepository.findOneWithStudents(1L).get();
		Course curso2 = courseRepository.findOneWithStudents(2L).get();
		
		student1.addCourse(curso1);
		student1.addCourse(curso2);
		student2.addCourse(curso1);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@Transactional
	public void manyToManyBidireccionalRemove(){
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course curso1 = new Course("Curso de Java Master", "Andres");
		Course curso2 = new Course("Curso de Spring Boot", "Andres");
		
		student1.addCourse(curso1);
		student1.addCourse(curso2);
		student2.addCourse(curso2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}
	
	@Transactional
	public void manyToManyBidireccional(){
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course curso1 = new Course("Curso de Java Master", "Andres");
		Course curso2 = new Course("Curso de Spring Boot", "Andres");
		
		student1.addCourse(curso1);
		student1.addCourse(curso2);
		student2.addCourse(curso2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyRemove(){
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course curso1 = new Course("Curso de Java Master", "Andres");
		Course curso2 = new Course("Curso de Spring Boot", "Andres");
		
		student1.setCourses(Set.of(curso1, curso2));
		student2.setCourses(Set.of(curso2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@Transactional
	public void manyToManyRemoveFind(){
		Optional<Student> optionalStudent1 = studentRepository.findById(1L);
		Optional<Student> optionalStudent2 = studentRepository.findById(2L);

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		Course curso1 = courseRepository.findById(1L).get();
		Course curso2 = courseRepository.findById(2L).get();
		
		student1.setCourses(Set.of(curso1, curso2));
		student2.setCourses(Set.of(curso2));
		
		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if(studentOptionalDb.isPresent()){
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}
	}

	@Transactional
	public void manyToManyFind(){
		Optional<Student> optionalStudent1 = studentRepository.findById(1L);
		Optional<Student> optionalStudent2 = studentRepository.findById(2L);

		Student student1 = optionalStudent1.get();
		Student student2 = optionalStudent2.get();

		Course curso1 = courseRepository.findById(1L).get();
		Course curso2 = courseRepository.findById(2L).get();
		
		student1.setCourses(Set.of(curso1, curso2));
		student2.setCourses(Set.of(curso2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}


	@Transactional
	public void manyToMany(){
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course curso1 = new Course("Curso de Java Master", "Andres");
		Course curso2 = new Course("Curso de Spring Boot", "Andres");
		
		student1.setCourses(Set.of(curso1, curso2));
		student2.setCourses(Set.of(curso2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(2L);
		
		optionalClient.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);
			client.setClientDetails(clientDetails);
			clientDetails.setClient(client);

			clientRepository.save(client);
			
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOneBidireccional(){
		Client client = new Client("Erba","Pura");
		
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		client.setClientDetails(clientDetails);
		clientDetails.setClient(client);

		clientRepository.save(client);
		
		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById(){
		ClientDetails clientDetails = new ClientDetails(true,5000);
		clientDetailsRepository.save(clientDetails);
		
		Optional<Client> optionalClient = clientRepository.findOne(2L);
		optionalClient.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne(){
		ClientDetails clientDetails = new ClientDetails(true,5000);
		clientDetailsRepository.save(clientDetails);
		
		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);
		
		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidireccional(){
		Optional<Client> optionalClient = Optional.of(new Client("Frank", "Moras"));

		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

			Set<Invoice> invoices = new HashSet<>();
			invoices.add(invoice1);
			invoices.add(invoice2);
			client.setInvoices(invoices);

			invoice1.setClient(client);
			invoice2.setClient(client);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOne(1L);
		optionalClientDb.ifPresent(client -> {
			Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
			optionalInvoice.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				invoice.setClient(null);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

			Set<Invoice> invoices = new HashSet<>();
			invoices.add(invoice1);
			invoices.add(invoice2);
			client.setInvoices(invoices);

			invoice1.setClient(client);
			invoice2.setClient(client);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOne(1L);
		optionalClientDb.ifPresent(client -> {
			Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
			optionalInvoice.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				invoice.setClient(null);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

			Set<Invoice> invoices = new HashSet<>();
			invoices.add(invoice1);
			invoices.add(invoice2);
			client.setInvoices(invoices);

			invoice1.setClient(client);
			invoice2.setClient(client);

			clientRepository.save(client);

			System.out.println(client);
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccional(){
		Client client = new Client("Frank", "Moras");
		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L);


		Set<Invoice> invoices = new HashSet<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);

		invoice1.setClient(client);
		invoice2.setClient(client);

		clientRepository.save(client);

		System.out.println(client);
	}


	@Transactional
	public void removeAddressFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);
		
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);
			clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});	
		});
	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Frank", "Moras");
		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);
		
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		Client clientDB = clientRepository.save(client);
		System.out.println(clientDB);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);
		
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);
			Client clientDB = clientRepository.save(client);
			System.out.println(clientDB);
		});
		
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Frank", "Moras");
		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);
		
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		Client clientDB = clientRepository.save(client);
		System.out.println(clientDB);
	}

	@Transactional
	public void manyToOne(){
		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindById(){
		Optional<Client> optionalClient = clientRepository.findById(1L);
		
		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			
			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}
}

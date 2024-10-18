package com.alejandro.spring.jpa.spring_jpa;

import java.util.Arrays;
// import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.spring.jpa.spring_jpa.dto.PersonDto;
import com.alejandro.spring.jpa.spring_jpa.entities.Person;
import com.alejandro.spring.jpa.spring_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personalizedQueries();

	}

	@Transactional(readOnly = true)
	public void whereIn(){
		System.out.println("========= Consulta where in =========");
		List<Person> registers = repository.getPersonByIds(Arrays.asList(1l,2l,5L));
		registers.stream().forEach(person -> System.out.println(person));
	}

	@Transactional(readOnly = true)
	public void subQueries(){
		System.out.println("========= Consulta por el nombre mas corto y su largo =========");
		List<Object[]> registers = repository.getShorterName();
		registers.stream().forEach(reg -> System.out.println("name= " + (String) reg[0] + ", length= " + (Integer) reg[1]));
	}

	@Transactional(readOnly = true)
	public void queriesConFuncionesDeAgregacion(){
		Long totalPersonas = repository.getTotalPerson();
		Long min = repository.getMaxId();
		Long max = repository.getMaxId();

		System.out.println("========= Consultas total de registros =========");
		System.out.println(totalPersonas);
		System.out.println("========= Consultas id minimo =========");
		System.out.println(min);
		System.out.println("========= Consultas id maximo =========");
		System.out.println(max);

		System.out.println("========= Consultas de nombres y longitud =========");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.stream().forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name= " + name + " ,length= " + length);
		});
		
		System.out.println("========= Consultas con el nombre minimo =========");
		Integer minNameLength = repository.getMinLengthNames();
		System.out.println(minNameLength);
		System.out.println("========= Consultas con el nombre maximo =========");	
		Integer maxNameLength = repository.getMaxLengthNames();
		System.out.println(maxNameLength);

		System.out.println("========= Consultas resumen de funciones de agregacion min, max, sum, avg, count =========");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunction();
		System.out.println("min= " + resumeReg[0] + "max= " + resumeReg[1] + "sum= " + resumeReg[2] + "avg= " + resumeReg[3] + "count= " + resumeReg[4]);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween(){
		System.out.println("========= Consultas de personas between 2 y 5 =========");
		List<Person> persons = repository.findAllPersonBetweenId(2,5);
		persons.forEach(person -> System.out.println(person));
		
		System.out.println("========= Consultas de personas between 'J' y Q =========");
		List<Person> personsReg = repository.findAllPersonBetweenName("J", "Q");
		personsReg.forEach(person -> System.out.println(person));

		System.out.println("========= Consultas de personas between 2 y 5 =========");
		List<Person> personsA = repository.findByIdBetween(2L,5L);
		personsA.forEach(person -> System.out.println(person));
		
		System.out.println("========= Consultas de personas between 'J' y Q =========");
		List<Person> personsRegA = repository.findByNameBetween("J", "Q");
		personsRegA.forEach(person -> System.out.println(person));

		System.out.println("========= Consultas de personas ordenadas =========");
		List<Person> allPersons = repository.getAllOrdered();
		allPersons.forEach(person -> System.out.println(person));
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperLower(){
		System.out.println("========= Consultas con nombres y apellidos concat =========");
		List<String> personNames = repository.findAllFullNameConcat();
		personNames.forEach(person -> System.out.println(person));

		System.out.println("========= Consultas con nombres y apellidos concat upper case =========");
		List<String> personNamesUpper = repository.findAllFullNameConcatUpper();
		personNamesUpper.forEach(person -> System.out.println(person));

		System.out.println("========= Consultas con nombres y apellidos concat lower case =========");
		List<String> personNamesLower = repository.findAllFullNameConcatLower();
		personNamesLower.forEach(person -> System.out.println(person));
	}
	
	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){
		System.out.println("========= Consultas con nombres de personas =========");
		List<String> personNames = repository.findAllNames();
		personNames.forEach(person -> System.out.println(person));
		
		System.out.println("========= Consultas con nombres distintos de personas =========");
		List<String> personDistinctNames = repository.findAllDistinctNames();
		personDistinctNames.forEach(person -> System.out.println(person));

		System.out.println("========= Consultas con lenguajes de programacion distintos =========");
		List<String> personDistinctLanguage = repository.findAllDistincProgrammingLanguage();
		personDistinctLanguage.forEach(person -> System.out.println(person));

		System.out.println("========= Total de lenguajes de programacion =========");
		Long totalLanguage = repository.findAllDistincProgrammingLanguageCount();
		System.out.println("Total de lenguajes= " + totalLanguage);

	}

	@Transactional(readOnly = true)
	public void personalizedQueries2(){
		System.out.println("========= Consulta por objeto persona y lenguaje =========");
		List<Object[]> personRegs = repository.findAllMixPerson();

		personRegs.forEach(reg -> {
			System.out.println("person= " + reg[0]);
			System.out.println("lenguaje de programacion= " + reg[1]);
		});

		System.out.println("========= Consulta que puebla y devuelve objeto entity de una instancia personalizada =========");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(person -> System.out.println(person));

		System.out.println("========= Consulta que puebla y devuelve objeto dto de una clase dto =========");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(person -> System.out.println(person));
	}

	@Transactional(readOnly = true)
	public void personalizedQueries(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el id: ");
		Long id = sc.nextLong();
		sc.close();

		System.out.println("=========== CONSULTA DE NOMBRE POR EL ID ===========");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("=========== CONSULTA DE ID POR EL ID ===========");
		Long idDB = repository.getIdById(id);
		System.out.println(idDB);

		System.out.println("=========== CONSULTA DE FULLNAME POR EL ID ===========");
		String fullnameDB = repository.getFullNameById(id);
		System.out.println(fullnameDB);
		
		System.out.println("=========== CONSULTA POR CAMPOS PERSONALIZADOS POR ID ===========");
		Object[] personReg = (Object[]) repository.obtenerPersonDataById(id);
		System.out.println("id= " + personReg[0] + ", nombre= " + personReg[1] + ", apellido= " + personReg[2] + ", lenguaje= " + personReg[3]);

		System.out.println("=========== CONSULTA POR CAMPOS PERSONALIZADOS LISTA ===========");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id= " + reg[0] + ", nombre= " + reg[1] + ", apellido= " + reg[2] + ", lenguaje= " + reg[3]));
	}

	@Transactional
	public void delete1(){
		repository.findAll().forEach(System.out::println);

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = sc.nextLong();

		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);
		sc.close();
	}

	@Transactional
	public void delete2(){
		repository.findAll().forEach(System.out::println);

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = sc.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(person ->{
			System.out.println(person);
			repository.delete(person);
		}, () -> System.out.println("No se encontro a la persona"));

		repository.findAll().forEach(System.out::println);
		sc.close();
	}


	@Transactional
	public void update(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = sc.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		optionalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion: ");
			String newProgrammingLanguage = sc.next();
			person.setProgrammingLanguage(newProgrammingLanguage);
			Person newPerson = repository.save(person);
			System.out.println(newPerson);
		});
		sc.close();
	}

		@Transactional
		public void create(){
			Scanner sc = new Scanner(System.in);
			System.out.println("Ingrese el nombre: ");
			String name = sc.next();
			System.out.println("Ingrese el apellido: ");
			String lastname = sc.next();
			System.out.println("Ingrese el lenguage de programacion: ");
			String programmingLanguage = sc.next();
			sc.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne(){
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(8L);

		// if(optionalPerson.isPresent()){
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);
		repository.findByNameContaining("Pepe").ifPresent(person -> System.out.println(person));
		
	}

	@Transactional(readOnly = true)
	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java", "Andres");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");
		
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValues = repository.obtenerPersonData();
		personsValues.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
	}
}

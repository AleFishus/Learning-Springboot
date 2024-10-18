package com.alejandro.spring.jpa.spring_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alejandro.spring.jpa.spring_jpa.dto.PersonDto;
import com.alejandro.spring.jpa.spring_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id in ?1")
    public List<Person> getPersonByIds(List<Long> ids);

    @Query("select p.name, length(p.name) from Person p where (length(p.name) = (select min(length(p.name)) from Person p))")
    public List<Object[]> getShorterName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthNames();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthNames();

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select p from Person p order by p.name desc")
    List<Person> getAllOrdered();

    List<Person> findByIdBetween(Long id1, Long id2);
    
    List<Person> findByNameBetween(String nam1, String name2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc")
    List<Person> findAllPersonBetweenName(String c1, String c2);

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc, p.lastname asc")
    List<Person> findAllPersonBetweenId(Integer i1, Integer i2);

    // @Query("select concat(p.name, ' ', p.lastname) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(concat(p.name || ' ' || p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct (p.name) from Person p")
    List<String> findAllDistinctNames();

    @Query("select distinct (p.programmingLanguage) from Person p")
    List<String> findAllDistincProgrammingLanguage();

    @Query("select count( distinct (p.programmingLanguage) ) from Person p")
    Long findAllDistincProgrammingLanguageCount();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select new com.alejandro.spring.jpa.spring_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByName(String name);

    Optional<Person> findByNameContaining(String name);
    
    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p, p.programmingLanguage from Person p ")
    List<Object[]> findAllMixPerson();

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);
    
    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Object obtenerPersonDataById(Long id);

}

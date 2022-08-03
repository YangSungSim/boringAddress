package com.example.lazyaddress.repository;

import com.example.lazyaddress.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressJpaDataRepository extends JpaRepository<Address, Long> {

    //@Query("select m from Address m where m.name = :name")
    //List<Address> findByName(@Param("name") String name);

    @Query("select m from Address m where m.name = :name")
    Page<Address> findByName(@Param("name") String name, Pageable pageable);

    //벌크성 수정쿼리
    @Modifying
    @Query("update Address m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

}

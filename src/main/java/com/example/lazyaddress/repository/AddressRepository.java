package com.example.lazyaddress.repository;

import com.example.lazyaddress.domain.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepository {
   //initial jpql
    private final EntityManager em;

    public Address findOne(Long id) {
        return em.find(Address.class, id);
    }

    public List<Address> findAll() {
        return em.createQuery("select m from Address m", Address.class)
                .getResultList();
    }

    //이름으로 조회
    public Address findByName(String name) {
        return em.createQuery("select m from Address m where m.name = :name", Address.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public void save(Address address) {
        em.persist(address);
        em.flush();
        em.close();
    }

    public void deleteAddress(Long addressId) {
        int affectedRow = em.createQuery("delete from Address m where m.id =:addressId")
                .setParameter("addressId", addressId)
                .executeUpdate();

        System.out.println("deleted row num:"+affectedRow);
    }

    public void update(Address address) {

        int affectedRow = em.createQuery("update Address m set m.name=:name, m.age=:age, m.phone=:phone  where m.id =:addressId")
                .setParameter("name", address.getName())
                .setParameter("age", address.getAge())
                .setParameter("phone", address.getPhone())
                .setParameter("addressId", address.getId())
                .executeUpdate();

        em.flush();
        em.close();
        System.out.println("updated getName:"+address.getName());

        System.out.println("updated row num:"+affectedRow);
    }
}

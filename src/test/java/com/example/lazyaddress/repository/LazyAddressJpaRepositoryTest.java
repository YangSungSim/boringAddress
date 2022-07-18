package com.example.lazyaddress.repository;

import com.example.lazyaddress.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
public class LazyAddressJpaRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void createAddresss() {
        Address address = new Address("홍길동", 15, "01098753333");
        addressRepository.save(address);

        Address findAddress = addressRepository.findByName("홍길동");
        assertThat(findAddress.getAge()).isEqualTo(address.getAge());
        assertThat(findAddress.getName()).isEqualTo(address.getName());
    }

    @Test
    public void updateAddress() {
        Address address = new Address(1L,"친구가",18,"01074123698");
        addressRepository.update(address);

        Address findAddressChanged = addressRepository.findOne(1L);


        assertThat(findAddressChanged.getName()).isEqualTo("친구가");
    }

    @Test
    public void deleteAddress() {
        addressRepository.deleteAddress(1L);
        Address address = addressRepository.findOne(1L);

        assertThat(address).isEqualTo(null);
    }
}

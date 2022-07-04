package com.example.lazyaddress.service;

import com.example.lazyaddress.domain.Address;
import com.example.lazyaddress.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    //전체조회
    public List<Address> findAddress() {
        return addressRepository.findAll();
    }

    //단일 조회
    public Address findOne(Long addressId) {
        return addressRepository.findOne(addressId);
    }

    public Address findByName(String name) {
        return addressRepository.findByName(name);
    }

    public void join(Address address) {

        //validateDuplicateMember(address); //중복 주소록 검증
        addressRepository.save(address);
    };

    public void update(Address address) {

        addressRepository.update(address);
    };

    public void deleteAddress(Long id) {
        addressRepository.deleteAddress(id);
    }

    private void validateDuplicateMember(Address address) {
        try {
            Address findMembers = addressRepository.findByName(address.getName());
        } catch (Exception e) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}

package com.example.lazyaddress;

import com.example.lazyaddress.domain.Address;
import com.example.lazyaddress.repository.AddressJpaDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
class LazyAddressApplicationTests {

    @Autowired
    AddressJpaDataRepository addressJpaDataRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void page() throws Exception {
        addressJpaDataRepository.save(new Address("코브라", 19, "01076507082"));
        addressJpaDataRepository.save(new Address("동굴이", 18, "01075307082"));
        addressJpaDataRepository.save(new Address("네모나", 17, "01095107082"));
        addressJpaDataRepository.save(new Address("레모나", 16, "01085207082"));

        PageRequest pageRequest = PageRequest.of(0,3, Sort.by(Sort.Direction.DESC, "name"));
        Page<Address> page = addressJpaDataRepository.findByName("코브라", pageRequest);

        List<Address> content = page.getContent();
        assertThat(content.size()).isEqualTo(1); //조회된 데이터 수

    }

    @Test
    public void bulkAge() throws Exception {
        addressJpaDataRepository.save(new Address("코브라", 19, "01076507082"));
        addressJpaDataRepository.save(new Address("동굴이", 18, "01075307082"));
        addressJpaDataRepository.save(new Address("네모나", 17, "01095107082"));
        addressJpaDataRepository.save(new Address("레모나", 16, "01085207082"));

        int resultCount = addressJpaDataRepository.bulkAgePlus(17);
        //then
        assertThat(resultCount).isEqualTo(3);

    }

}

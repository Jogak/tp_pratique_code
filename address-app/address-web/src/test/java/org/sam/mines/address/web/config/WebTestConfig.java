package org.sam.mines.address.web.config;

import org.sam.mines.address.persistence.AddressRepository;
import org.sam.mines.address.persistence.TownRepository;
import org.sam.mines.address.service.AddressService;
import org.sam.mines.address.service.TownService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebTestConfig {
    @MockBean
    private TownService townService;

    @MockBean
    private TownRepository townRepository;

    @MockBean
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;
}

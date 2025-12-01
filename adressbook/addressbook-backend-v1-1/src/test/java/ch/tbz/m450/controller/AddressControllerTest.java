package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void createAddressReturns201() throws Exception {
        Address input = new Address(1, "Max", "Muster", "111", new Date());
        when(addressService.save(any(Address.class))).thenReturn(input);

        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Max"));
    }

    @Test
    void getAddressesReturns200() throws Exception {
        List<Address> items = List.of(
                new Address(1, "A", "A", "", new Date()),
                new Address(2, "B", "B", "", new Date()));
        when(addressService.getAll()).thenReturn(items);

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void getAddressByIdFoundReturns200() throws Exception {
        Address a = new Address(9, "Zoe", "Zed", "", new Date());
        when(addressService.getAddress(9)).thenReturn(Optional.of(a));

        mockMvc.perform(get("/address/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9));
    }

    @Test
    void getAddressByIdNotFoundReturns404() throws Exception {
        when(addressService.getAddress(404)).thenReturn(Optional.empty());

        mockMvc.perform(get("/address/404"))
                .andExpect(status().isNotFound());
    }
}

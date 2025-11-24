package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTests {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address a1, a2, a3;

    @BeforeEach
    public void setUp() {
        a1 = new Address(1, "John", "Doe", "123", new Date(1000));
        a2 = new Address(2, "Alice", "Alpha", "111", new Date(500));
        a3 = new Address(3, "Bob", "Doe", "222", new Date(800));
    }

    @Test
    public void testGetAllSorted() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(a1, a2, a3));

        List<Address> result = addressService.getAll();

        // sortiert erwartet
        assertEquals(3, result.size());
        assertEquals(a2, result.get(0));
        assertEquals(a3, result.get(1));
        assertEquals(a1, result.get(2));

        verify(addressRepository).findAll();
    }

    @Test
    public void testSaveAndGet() {
        when(addressRepository.save(a1)).thenReturn(a1);
        when(addressRepository.findById(1)).thenReturn(Optional.of(a1));

        Address saved = addressService.save(a1);
        assertSame(a1, saved);

        Optional<Address> opt = addressService.getAddress(1);
        assertTrue(opt.isPresent());
        assertEquals(a1, opt.get());

        verify(addressRepository).save(a1);
        verify(addressRepository).findById(1);
    }

}

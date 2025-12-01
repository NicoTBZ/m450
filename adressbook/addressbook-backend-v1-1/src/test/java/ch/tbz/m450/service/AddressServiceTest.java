package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    private AddressRepository repository;
    private AddressService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(AddressRepository.class);
        service = new AddressService(repository);
    }

    @Test
    void saveDelegatesToRepository() {
        Address a = new Address(1, "Max", "Muster", "111", new Date());
        when(repository.save(any(Address.class))).thenAnswer(inv -> inv.getArgument(0));

        Address saved = service.save(a);

        ArgumentCaptor<Address> captor = ArgumentCaptor.forClass(Address.class);
        verify(repository, times(1)).save(captor.capture());
        assertEquals(a, captor.getValue());
        assertEquals(1, saved.getId());
    }

    @Test
    void getAllReturnsSorted() {
        Address a = new Address(3, "Max", "Muster", "333", new Date());
        Address b = new Address(2, "Erika", "Muster", "222", new Date());
        Address c = new Address(4, "Adam", "Aarau", "444", new Date());
        when(repository.findAll()).thenReturn(List.of(a, b, c));

        List<Address> result = service.getAll();
        assertEquals(List.of(c, b, a), result);

        verify(repository, times(1)).findAll();
    }

    @Test
    void getAddressByIdDelegates() {
        Address a = new Address(7, "Zoe", "Zed", "000", new Date());
        when(repository.findById(7)).thenReturn(Optional.of(a));

        Optional<Address> found = service.getAddress(7);
        assertTrue(found.isPresent());
        assertEquals(7, found.get().getId());
        verify(repository, times(1)).findById(7);
    }
}

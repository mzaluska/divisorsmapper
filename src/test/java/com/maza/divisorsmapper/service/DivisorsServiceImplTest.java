package com.maza.divisorsmapper.service;

import com.maza.divisorsmapper.repository.MappingRepository;
import com.maza.divisorsmapper.service.impl.DivisorsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class DivisorsServiceImplTest {
    // todo write some tests
    @MockBean
    private MappingRepository mappingRepository = Mockito.mock(MappingRepository.class);

    @InjectMocks
    private DivisorsService service = new DivisorsServiceImpl(mappingRepository);

    @Test
    public void shouldReturn() {
//        service.mapNumbers(MappingCategory.ANIMALS, List.of(1,2));
    }

}

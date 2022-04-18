package com.maza.divisorsmapper.service;

import com.maza.divisorsmapper.dto.MapNumbersResponse;
import com.maza.divisorsmapper.model.Category;
import com.maza.divisorsmapper.model.Mapping;
import com.maza.divisorsmapper.repository.MappingRepository;
import com.maza.divisorsmapper.service.impl.DivisorsServiceImpl;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class DivisorsServiceImplTest {

    private static final String FURNITURE_CATEGORY = "furniture";
    private static final String CHAIR = "Chair";
    private static final String SOFA = "Sofa";
    private static final String TABLE = "Table";

    @MockBean
    private final MappingRepository mappingRepository = Mockito.mock(MappingRepository.class);

    @InjectMocks
    private DivisorsService service = new DivisorsServiceImpl(mappingRepository);

    @Test
    public void givenMapNumbers_whenValidInput_shouldReturnMappedResult() {
        final List<Mapping> mappingResult = prepareMappingResult();
        Mockito.when(mappingRepository.findAllByCategoryName(FURNITURE_CATEGORY)).thenReturn(mappingResult);

        final MapNumbersResponse response = service.mapNumbers(FURNITURE_CATEGORY, List.of(1, 4));

        assertThat(response.getResponse(), notNullValue());
        final Map<Integer, List<String>> map = response.getResponse();
        assertThat(map.size(), is(2));
        assertThat(map, IsMapContaining.hasEntry(1, List.of(CHAIR)));
        assertThat(map, IsMapContaining.hasEntry(4, List.of(CHAIR, SOFA, TABLE)));

    }

    private List<Mapping> prepareMappingResult() {
        final Category furnitureCategory = new Category();
        furnitureCategory.setId(1);
        furnitureCategory.setName(FURNITURE_CATEGORY);

        final Mapping mappingOne = new Mapping();
        mappingOne.setId(1);
        mappingOne.setCategory(furnitureCategory);
        mappingOne.setNumber(1);
        mappingOne.setWord(CHAIR);

        final Mapping mappingTwo = new Mapping();
        mappingTwo.setId(1);
        mappingTwo.setCategory(furnitureCategory);
        mappingTwo.setNumber(2);
        mappingTwo.setWord(SOFA);

        final Mapping mappingFour = new Mapping();
        mappingFour.setId(1);
        mappingFour.setCategory(furnitureCategory);
        mappingFour.setNumber(4);
        mappingFour.setWord(TABLE);

        return List.of(mappingOne, mappingTwo, mappingFour);
    }

}

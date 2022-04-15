package com.maza.divisorsmapper.service.impl;

import com.maza.divisorsmapper.dto.MapNumbersResponse;
import com.maza.divisorsmapper.exception.NoSuchMappingForNumberException;
import com.maza.divisorsmapper.model.Mapping;
import com.maza.divisorsmapper.repository.MappingRepository;
import com.maza.divisorsmapper.service.DivisorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class DivisorsServiceImpl implements DivisorsService {

    private static final Map<Integer, Set<Integer>> storedDivisors = new HashMap<>();
    private final MappingRepository mappingRepository;

    public DivisorsServiceImpl(final MappingRepository mappingRepository) {
        this.mappingRepository = mappingRepository;
    }

    @Override
    public MapNumbersResponse mapNumbers(final String category, final List<Integer> numbers) {
        final Map<Integer, Set<Integer>> divisors = findDivisors(numbers);
        final Map<Integer, List<String>> mappedDivisors = mapDivisors(category, divisors);
        return MapNumbersResponse
            .builder()
            .response(mappedDivisors)
            .build();
    }

    private Map<Integer, List<String>> mapDivisors(final String category, final Map<Integer, Set<Integer>> divisors) {
        final Map<Integer, String> mapping = findMappingsForCategory(category);
        final Map<Integer, List<String>> result = new HashMap<>();
        for (final Map.Entry<Integer, Set<Integer>> entry : divisors.entrySet()) {
            final List<String> mappedDivisors = mapDivisorsToStrings(entry.getValue(), mapping);
            result.put(entry.getKey(), mappedDivisors);
        }
        return result;
    }

    private Map<Integer, String> findMappingsForCategory(final String category) {
        final List<Mapping> allByCategoryName = mappingRepository.findAllByCategoryName(category);
        return allByCategoryName.stream().collect(Collectors.toMap(Mapping::getNumber, Mapping::getWord));
    }

    private List<String> mapDivisorsToStrings(final Set<Integer> divisors, final Map<Integer, String> mapping) {
        final List<String> mappedDivisors = new ArrayList<>();
        for (final Integer divisor : divisors) {
            if (!mapping.containsKey(divisor)) {
                log.error("No mapping found for given number: {}, mapping: {}", divisor, mapping);
                throw new NoSuchMappingForNumberException();
            }
            mappedDivisors.add(mapping.get(divisor));
        }
        return mappedDivisors;
    }

    private Map<Integer, Set<Integer>> findDivisors(final List<Integer> numbers) {
        final Map<Integer, Set<Integer>> divisors = new HashMap<>();
        numbers.forEach(number -> divisors.put(number, findDivisorsForNumber(number)));
        return divisors;
    }

    private Set<Integer> findDivisorsForNumber(final Integer number) {
        if (storedDivisors.containsKey(number)) {
            return storedDivisors.get(number);
        }
        final Set<Integer> divisors = IntStream.range(1, number + 1)
            .filter(i -> number % i == 0)
            .boxed()
            .collect(Collectors.toCollection(TreeSet::new));
        storedDivisors.put(number, divisors);
        return divisors;
    }
}

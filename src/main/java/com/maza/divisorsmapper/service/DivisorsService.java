package com.maza.divisorsmapper.service;

import com.maza.divisorsmapper.dto.MapNumbersResponse;

import java.util.List;

public interface DivisorsService {

    MapNumbersResponse mapNumbers(String category, List<Integer> numbers);

}

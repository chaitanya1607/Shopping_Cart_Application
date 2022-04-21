package com.mindtree.shoppingapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingapp.dto.ApparalProductDto;
import com.mindtree.shoppingapp.entity.Apparal;

@Component
public class ApparalConverter {
	public Apparal dtoToEntity(ApparalProductDto apparalProductDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(apparalProductDto, Apparal.class);
	}
}

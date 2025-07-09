package com.livro.api.livroapi.beans;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansUtils {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setMethodAccessLevel(AccessLevel.PUBLIC)
			.setPreferNestedProperties(false);
		return modelMapper;
	}
	
}

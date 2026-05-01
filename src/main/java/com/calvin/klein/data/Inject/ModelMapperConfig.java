package com.calvin.klein.data.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.domain.entities.Address;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // 👇 RESOLVENDO O CONFLITO
        modelMapper.getConfiguration()
        .setAmbiguityIgnored(true);

        modelMapper.typeMap(Address.class, AddressDTO.class)
        .addMappings(mapper -> {
            mapper.map(
                src -> src.getUser() != null ? src.getUser().getId() : null,
                AddressDTO::setUserId
            );
        });

        return modelMapper;
    }

    
}
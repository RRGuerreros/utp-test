package com.utp.infrastructure.config;

import com.utp.application.mapper.*;
import com.utp.infrastructure.security.mapper.AuthRestMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public NoteMapper noteMapper(){
        return Mappers.getMapper(NoteMapper.class);
    }
    @Bean
    public TagMapper tagMapper(){
        return Mappers.getMapper(TagMapper.class);
    }
}

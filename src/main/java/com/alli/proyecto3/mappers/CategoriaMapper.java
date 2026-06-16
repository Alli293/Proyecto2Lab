package com.alli.proyecto3.mappers;

import com.alli.proyecto3.models.dtos.CategoriaDTO;
import com.alli.proyecto3.models.entities.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO categoriaToDTO(Categoria categoria);
    Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO);
}
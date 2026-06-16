package com.alli.proyecto3.mappers;

import com.alli.proyecto3.models.dtos.ProductoDTO;
import com.alli.proyecto3.models.entities.Categoria;
import com.alli.proyecto3.models.entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "categoria.id", target = "categoriaId")
    ProductoDTO productoToDTO(Producto producto);

    @Mapping(source = "categoriaId", target = "categoria.id")
    Producto productoDTOToProducto(ProductoDTO productoDTO);
}
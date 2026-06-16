package com.alli.proyecto3.service;

import com.alli.proyecto3.mappers.ProductoMapper;
import com.alli.proyecto3.models.dtos.ProductoDTO;
import com.alli.proyecto3.models.entities.Categoria;
import com.alli.proyecto3.models.entities.Producto;
import com.alli.proyecto3.repository.CategoriaRepository;
import com.alli.proyecto3.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository,
                           ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::productoToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::productoToDTO);
    }

    public Optional<ProductoDTO> crear(ProductoDTO productoDTO) {
        Optional<Categoria> categoria = categoriaRepository.findById(productoDTO.getCategoriaId());
        if (categoria.isEmpty()) {
            return Optional.empty();
        }
        Producto producto = productoMapper.productoDTOToProducto(productoDTO);
        producto.setCategoria(categoria.get());
        Producto guardado = productoRepository.save(producto);
        return Optional.of(productoMapper.productoToDTO(guardado));
    }

    public Optional<ProductoDTO> actualizar(Long id, ProductoDTO productoDTO) {
        return productoRepository.findById(id).map(productoExistente -> {
            Optional<Categoria> categoria = categoriaRepository.findById(productoDTO.getCategoriaId());
            categoria.ifPresent(productoExistente::setCategoria);
            productoExistente.setNombre(productoDTO.getNombre());
            productoExistente.setDescripcion(productoDTO.getDescripcion());
            productoExistente.setPrecio(productoDTO.getPrecio());
            productoExistente.setCantidadEnStock(productoDTO.getCantidadEnStock());
            Producto actualizado = productoRepository.save(productoExistente);
            return productoMapper.productoToDTO(actualizado);
        });
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
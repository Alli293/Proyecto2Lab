package com.alli.proyecto3.service;

import com.alli.proyecto3.mappers.CategoriaMapper;
import com.alli.proyecto3.models.dtos.CategoriaDTO;
import com.alli.proyecto3.models.entities.Categoria;
import com.alli.proyecto3.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaDTO> obtenerTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::categoriaToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::categoriaToDTO);
    }

    public CategoriaDTO crear(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.categoriaDTOToCategoria(categoriaDTO);
        Categoria guardada = categoriaRepository.save(categoria);
        return categoriaMapper.categoriaToDTO(guardada);
    }

    public Optional<CategoriaDTO> actualizar(Long id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id).map(categoriaExistente -> {
            categoriaExistente.setNombre(categoriaDTO.getNombre());
            categoriaExistente.setDescripcion(categoriaDTO.getDescripcion());
            Categoria actualizada = categoriaRepository.save(categoriaExistente);
            return categoriaMapper.categoriaToDTO(actualizada);
        });
    }

    public boolean eliminar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
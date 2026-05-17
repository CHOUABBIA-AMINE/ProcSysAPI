/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: GenericController
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Abstract Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dz.procsys.api.platform.kernel.dto.GenericDTO;
import dz.procsys.api.platform.kernel.mapper.GenericMapper;
import dz.procsys.api.platform.kernel.model.GenericModel;
import dz.procsys.api.platform.kernel.service.GenericService;

/**
 * Generic REST controller providing CRUD endpoints for aggregate roots.
 *
 * @param <E> entity type
 * @param <D> DTO type
 */
public abstract class GenericController<E extends GenericModel, D extends GenericDTO> {

    private final GenericService<E, Long> service;
    private final GenericMapper<E, D> mapper;

    protected GenericController(GenericService<E, Long> service, GenericMapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Page<D>> list(Pageable pageable) {
        Page<E> page = service.findAll(pageable);
        Page<D> dtoPage = page.map(mapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> get(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        E entity = mapper.toEntity(dto);
        E saved = service.create(entity);
        D savedDto = mapper.toDto(saved);
        return ResponseEntity
                .created(URI.create("/" + savedDto.getId()))
                .body(savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody D dto) {
        E entity = mapper.toEntity(dto);
        E updated = service.update(id, entity);
        D updatedDto = mapper.toDto(updated);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
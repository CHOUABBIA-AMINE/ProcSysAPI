package dz.procsys.api.core.procurement.archive.controller;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.request.UpdateArchiveFolderRequestDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderDetailsResponseDTO;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.service.ArchiveFolderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/procurement/archive/folders")
@RequiredArgsConstructor
public class ArchiveFolderController {

    private final ArchiveFolderService archiveFolderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArchiveFolderResponse create(@Valid @RequestBody CreateArchiveFolderRequestDTO request) {
        return archiveFolderService.create(request);
    }

    @PutMapping("/{id}")
    public ArchiveFolderResponse update(@PathVariable Long id, @Valid @RequestBody UpdateArchiveFolderRequestDTO request) {
        return archiveFolderService.update(id, request);
    }

    @GetMapping("/{id}")
    public ArchiveFolderDetailsResponseDTO getById(@PathVariable Long id) {
        return archiveFolderService.findById(id);
    }

    @GetMapping("/by-code/{code}")
    public ArchiveFolderDetailsResponseDTO getByCode(@PathVariable String code) {
        return archiveFolderService.findByCode(code);
    }

    @GetMapping
    public Page<ArchiveFolderDetailsResponseDTO> search(
        @RequestParam(required = false) String code,
        @RequestParam(required = false) String facilityCode,
        @RequestParam(required = false) String roomCode,
        @RequestParam(required = false) String shelfCode,
        @RequestParam(required = false) String boxCode,
        Pageable pageable
    ) {
        return archiveFolderService.search(code, facilityCode, roomCode, shelfCode, boxCode, pageable);
    }
}

package dz.procsys.api.core.procurement.archive.controller;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequest;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.service.ArchiveFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/procurement/archive/folders")
@RequiredArgsConstructor
public class ArchiveFolderController {

    private final ArchiveFolderService archiveFolderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArchiveFolderResponse create(@RequestBody CreateArchiveFolderRequest request) {
        return archiveFolderService.create(request);
    }
}

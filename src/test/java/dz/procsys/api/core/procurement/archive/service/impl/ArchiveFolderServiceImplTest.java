package dz.procsys.api.core.procurement.archive.service.impl;

import dz.procsys.api.core.procurement.archive.dto.request.CreateArchiveFolderRequest;
import dz.procsys.api.core.procurement.archive.dto.response.ArchiveFolderResponse;
import dz.procsys.api.core.procurement.archive.model.ArchiveBox;
import dz.procsys.api.core.procurement.archive.model.ArchiveFolder;
import dz.procsys.api.core.procurement.archive.repository.ArchiveBoxRepository;
import dz.procsys.api.core.procurement.archive.repository.ArchiveFolderRepository;
import dz.procsys.api.exception.business.BusinessException;
import dz.procsys.api.exception.business.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArchiveFolderServiceImplTest {

    @Mock
    private ArchiveFolderRepository archiveFolderRepository;
    @Mock
    private ArchiveBoxRepository archiveBoxRepository;

    @InjectMocks
    private ArchiveFolderServiceImpl service;

    @Test
    void create_shouldPersistAndReturnResponse_whenRequestIsValid() {
        CreateArchiveFolderRequest request = new CreateArchiveFolderRequest("AF-001", "Folder", 10L);
        ArchiveBox archiveBox = new ArchiveBox();
        archiveBox.setId(10L);

        ArchiveFolder saved = new ArchiveFolder();
        saved.setId(100L);
        saved.setCode("AF-001");
        saved.setDescription("Folder");
        saved.setArchiveBox(archiveBox);

        when(archiveFolderRepository.existsByCode("AF-001")).thenReturn(false);
        when(archiveBoxRepository.findById(10L)).thenReturn(Optional.of(archiveBox));
        when(archiveFolderRepository.save(any(ArchiveFolder.class))).thenReturn(saved);

        ArchiveFolderResponse response = service.create(request);

        assertEquals(100L, response.id());
        assertEquals("AF-001", response.code());
        assertEquals("Folder", response.description());
        assertEquals(10L, response.archiveBoxId());

        ArgumentCaptor<ArchiveFolder> folderCaptor = ArgumentCaptor.forClass(ArchiveFolder.class);
        verify(archiveFolderRepository).save(folderCaptor.capture());
        assertEquals("AF-001", folderCaptor.getValue().getCode());
        assertEquals("Folder", folderCaptor.getValue().getDescription());
        assertEquals(10L, folderCaptor.getValue().getArchiveBox().getId());
    }

    @Test
    void create_shouldThrowConflict_whenFolderCodeAlreadyExists() {
        CreateArchiveFolderRequest request = new CreateArchiveFolderRequest("AF-001", "Folder", 10L);
        when(archiveFolderRepository.existsByCode("AF-001")).thenReturn(true);

        BusinessException ex = assertThrows(BusinessException.class, () -> service.create(request));

        assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
        verify(archiveBoxRepository, never()).findById(any());
        verify(archiveFolderRepository, never()).save(any());
    }

    @Test
    void create_shouldThrowNotFound_whenArchiveBoxDoesNotExist() {
        CreateArchiveFolderRequest request = new CreateArchiveFolderRequest("AF-001", "Folder", 10L);
        when(archiveFolderRepository.existsByCode("AF-001")).thenReturn(false);
        when(archiveBoxRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.create(request));
        verify(archiveFolderRepository, never()).save(any());
    }
}

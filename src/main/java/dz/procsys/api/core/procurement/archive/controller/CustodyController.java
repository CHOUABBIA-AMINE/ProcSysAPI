package dz.procsys.api.core.procurement.archive.controller;

import dz.procsys.api.core.procurement.archive.dto.request.CustodyActionRequest;
import dz.procsys.api.core.procurement.archive.dto.response.CustodyEventResponse;
import dz.procsys.api.core.procurement.archive.service.CustodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/procurement/archive/custody")
@RequiredArgsConstructor
public class CustodyController {

    private final CustodyService custodyService;

    @PostMapping("/checkout")
    public CustodyEventResponse checkout(@RequestBody CustodyActionRequest request) {
        return custodyService.checkout(request);
    }

    @PostMapping("/transfer")
    public CustodyEventResponse transfer(@RequestBody CustodyActionRequest request) {
        return custodyService.transfer(request);
    }

    @PostMapping("/return")
    public CustodyEventResponse returnItem(@RequestBody CustodyActionRequest request) {
        return custodyService.returnItem(request);
    }

    @GetMapping("/history/folders/{folderId}")
    public List<CustodyEventResponse> historyByFolder(@PathVariable Long folderId) {
        return custodyService.historyByFolder(folderId);
    }

    @GetMapping("/history/documents/{documentRecordId}")
    public List<CustodyEventResponse> historyByDocument(@PathVariable Long documentRecordId) {
        return custodyService.historyByDocument(documentRecordId);
    }

    @GetMapping("/current-holder/folders/{folderId}")
    public Map<String, String> currentHolderByFolder(@PathVariable Long folderId) {
        return Map.of("actorIdentifier", custodyService.currentHolderByFolder(folderId).orElse(""));
    }

    @GetMapping("/current-holder/documents/{documentRecordId}")
    public Map<String, String> currentHolderByDocument(@PathVariable Long documentRecordId) {
        return Map.of("actorIdentifier", custodyService.currentHolderByDocument(documentRecordId).orElse(""));
    }
}

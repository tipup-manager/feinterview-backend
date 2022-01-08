package com.fei.api.web;

import com.fei.api.service.scrap.ScrapService;
import com.fei.api.web.dto.scrap.ScrapListResponseDto;
import com.fei.api.web.dto.scrap.ScrapResponseDto;
import com.fei.api.web.dto.scrap.ScrapSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapService scrapService;

    @GetMapping("/api/v1/scrap/{id}")
    public ScrapResponseDto getScrap(@PathVariable Long id) {
        return scrapService.getScrap(id);
    }

    @PostMapping("/api/v1/scrap")
    public ScrapResponseDto save(@RequestBody ScrapSaveRequestDto requestDto) {
        return scrapService.save(requestDto);
    }

    @GetMapping("/api/v1/scrap/list")
    public ScrapListResponseDto getScraps(@RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("CREATED_DATE").descending());
        return scrapService.getScraps(sortedByModifiedDateDesc);
    }

    @GetMapping("/api/v1/scrap/list/{userNumberId}")
    public ScrapListResponseDto getBlogsByUserNumberId(@PathVariable Long userNumberId, @RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("CREATED_DATE").descending());
        return scrapService.getScrapsByUserNumberId(userNumberId, sortedByModifiedDateDesc);
    }

    @DeleteMapping("/api/v1/scrap/{id}")
    public Long delete(@PathVariable Long id){
        scrapService.delete(id);
        return id;
    }
}

package com.fei.api.service.scrap;

import com.fei.api.domain.blog.Blog;
import com.fei.api.domain.blog.BlogRepository;
import com.fei.api.domain.scrap.Scrap;
import com.fei.api.domain.scrap.ScrapRepository;
import com.fei.api.web.dto.blog.BlogListResponseDto;
import com.fei.api.web.dto.blog.BlogResponseDto;
import com.fei.api.web.dto.blog.BlogSaveRequestDto;
import com.fei.api.web.dto.scrap.ScrapListResponseDto;
import com.fei.api.web.dto.scrap.ScrapResponseDto;
import com.fei.api.web.dto.scrap.ScrapSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;

    @Transactional
    public ScrapResponseDto save(ScrapSaveRequestDto scrapSaveRequestDto) {
        if (scrapSaveRequestDto == null || "".equals(scrapSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Scrap scrap = scrapRepository.save(scrapSaveRequestDto.toEntity());
        return new ScrapResponseDto(scrap);
    }
    @Transactional
    public ScrapResponseDto getScrap(Long id) {
        Scrap scrap = scrapRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 블로그가 없습니다.id="+id)
        );
        return new ScrapResponseDto(scrap);
    }

    @Transactional
    public ScrapListResponseDto getScraps(Pageable pageable) {
        Page<Scrap> pageData = scrapRepository.findScrapList(pageable);
        return new ScrapListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public ScrapListResponseDto getScrapsByUserNumberId(Long userNumberId, Pageable pageable) {
        Page<Scrap> pageData = scrapRepository.findByUserNumberId(userNumberId, pageable);
        return new ScrapListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public void delete(Long id){
        Scrap scrap = scrapRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. id="+id));

        scrapRepository.delete(scrap);
    }
}

package com.fei.api.service.language;

import com.fei.api.domain.category.Category;
import com.fei.api.domain.category.CategoryRepository;
import com.fei.api.domain.language.Language;
import com.fei.api.domain.language.LanguageRepository;
import com.fei.api.web.dto.category.CategoryListResponseDto;
import com.fei.api.web.dto.category.CategorySaveRequestDto;
import com.fei.api.web.dto.language.LanguageInfoSaveRequestDto;
import com.fei.api.web.dto.language.LanguageListResponseDto;
import com.fei.api.web.dto.language.LanguageResponseDto;
import com.fei.api.web.dto.language.LanguageSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Transactional
    public void save(LanguageInfoSaveRequestDto languageInfoSaveRequestDto) {
        String[] categoryNames = null;
        String[] categoryIds = null;
        Long userNumberId = languageInfoSaveRequestDto.getUserNumberId();
        String userEmail = languageInfoSaveRequestDto.getUserEmail();
        String userImg = languageInfoSaveRequestDto.getUserImg();
        String userId = languageInfoSaveRequestDto.getUserId();
        String userMyPage = "/blogger/" + userId;

        List<Language> languages = new ArrayList<>();

        try {
            categoryNames = languageInfoSaveRequestDto.getCategoryNames().split(",");
            categoryIds = languageInfoSaveRequestDto.getCategoryIds().split(",");

            for (int i = 0 ; i < categoryNames.length ; i++) {
                Language language = Language.builder()
                        .userNumberId(userNumberId)
                        .userEmail(userEmail)
                        .userImg(userImg)
                        .categoryName(categoryNames[i])
                        .userId(userId)
                        .categoryId(categoryIds[i])
                        .userMyPage(userMyPage)
                        .build();
                languages.add(language);
            }

            languageRepository.saveAll(languages);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Transactional(readOnly = true)
    public List<LanguageResponseDto> getLanguage(Long userNumberId) {
        return languageRepository.findByUserNumberId(userNumberId).stream()
                .map(LanguageResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public void modifySave(LanguageInfoSaveRequestDto languageInfoSaveRequestDto) {
        String[] categoryNames = null;
        String[] categoryIds = null;
        Long userNumberId = languageInfoSaveRequestDto.getUserNumberId();
        String userEmail = languageInfoSaveRequestDto.getUserEmail();
        String userImg = languageInfoSaveRequestDto.getUserImg();
        String userId = languageInfoSaveRequestDto.getUserId();
        String userMyPage = "/blogger/" + userId;

        List<Language> languages = new ArrayList<>();

        try {

            languageRepository.deleteByUserNumberId(userNumberId);

            categoryNames = languageInfoSaveRequestDto.getCategoryNames().split(",");
            categoryIds = languageInfoSaveRequestDto.getCategoryIds().split(",");

            for (int i = 0 ; i < categoryNames.length ; i++) {
                Language language = Language.builder()
                        .userNumberId(userNumberId)
                        .userEmail(userEmail)
                        .userImg(userImg)
                        .categoryName(categoryNames[i])
                        .userId(userId)
                        .categoryId(categoryIds[i])
                        .userMyPage(userMyPage)
                        .build();
                languages.add(language);
            }

            languageRepository.saveAll(languages);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}

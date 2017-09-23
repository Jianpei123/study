package com.arcsoft.study.service;

import com.arcsoft.study.domain.Section;

public interface ISectionService {

    Long addSection(Section section);

    int updateUrl(Long sectionId, String url);

    int deleteSection(Section section);

    int updateSection(Section section);
}

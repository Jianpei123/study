package com.arcsoft.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arcsoft.study.domain.Section;
import com.arcsoft.study.mapper.SectionMapper;
import com.arcsoft.study.service.ISectionService;

@Service
public class SectionServiceImpl implements ISectionService {

    @Resource
    private SectionMapper sectionMapper;

    @Override
    public Long addSection(Section section) {
        // TODO 自动生成的方法存根
        sectionMapper.addSection(section);
        return section.getId();
    }

    @Override
    public int updateUrl(Long sectionId, String url) {
        // TODO 自动生成的方法存根
        return sectionMapper.updateUrl(sectionId, url);
    }

    @Override
    public int deleteSection(Section section) {
        // TODO 自动生成的方法存根
        return sectionMapper.deleteSection(section);
    }

    @Override
    public int updateSection(Section section) {
        // TODO 自动生成的方法存根
        return sectionMapper.updateSection(section);
    }

}

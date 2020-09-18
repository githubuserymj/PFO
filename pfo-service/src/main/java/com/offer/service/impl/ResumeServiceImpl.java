package com.offer.service.impl;

import com.offer.mapper.PfoResumeMapper;
import com.offer.pojo.PfoResume;
import com.offer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    PfoResumeMapper resumeMapper;
    @Override
    public List<PfoResume> queryResumes(PfoResume resume) {
        return resumeMapper.queryResumes(resume);
    }

    @Override
    public int addResume(PfoResume resume) {
        return resumeMapper.insertSelective(resume);
    }

    @Override
    public int updateResume(PfoResume resume) {
        return resumeMapper.updateByPrimaryKeySelective(resume);
    }

    @Override
    public int deleteResume(Long resumeId) {
        return resumeMapper.deleteByPrimaryKey(resumeId);
    }
}

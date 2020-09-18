package com.offer.service;

import com.offer.pojo.PfoResume;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by YMJ on 2019-09-11.
 */
@Component
public interface ResumeService {
    /**
     *按条件查询用户简历
     * @return
     */
    List<PfoResume> queryResumes(PfoResume resume);

    /**
     * 增加新用户
     * @param resume
     * @return
     */
    int addResume(PfoResume resume);

    /**
     * 更改简历信息
     * @param resume
     * @return
     */
    int updateResume(PfoResume resume);

    /**
     * 按照简历id删除信息
     * @param resumeId
     * @return
     */
    int deleteResume(Long resumeId);

}

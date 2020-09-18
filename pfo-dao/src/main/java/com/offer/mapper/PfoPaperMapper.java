package com.offer.mapper;

import com.offer.pojo.PfoPaper;
import com.offer.pojo.PfoPaperExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PfoPaperMapper {
    int countByExample(PfoPaperExample example);

    int deleteByExample(PfoPaperExample example);

    int deleteByPrimaryKey(Long paperId);

    int insert(PfoPaper record);

    int insertSelective(PfoPaper record);

    List<PfoPaper> selectByExample(PfoPaperExample example);

    /*多条件分页排序查询所有试卷*/
    List<PfoPaper> queryAllPapers(@Param("conditions")List<String> conditions,@Param("orderMap")Map orderMap);

    PfoPaper selectByPrimaryKey(Long paperId);

    /**
     * 根据公司试卷id列表获取试卷列表  公司表与试卷表的关联
     * @param idList
     * @return
     */
    List<PfoPaper> selectPapersByPrimaryKeys(List<Long> idList);

    int updateByExampleSelective(@Param("record") PfoPaper record, @Param("example") PfoPaperExample example);

    int updateByExample(@Param("record") PfoPaper record, @Param("example") PfoPaperExample example);

    int updateByPrimaryKeySelective(PfoPaper record);

    int updateByPrimaryKey(PfoPaper record);
}
package com.offer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offer.dta.PageInfoResult;
import com.offer.mapper.PfoCommentMapper;
import com.offer.mapper.PfoCompanyMapper;
import com.offer.mapper.PfoPaperMapper;
import com.offer.pojo.PfoCompany;
import com.offer.pojo.PfoCompanyExample;
import com.offer.pojo.PfoPaper;
import com.offer.pojo.PfoUser;
import com.offer.service.CompanyService;
import com.offer.service.UserService;
import com.offer.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import com.offer.constant.ConstantValue;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by YMJ on 2019-09-12.
 */
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    PfoCompanyMapper pfoCompanyMapper;

    @Autowired
    PfoPaperMapper pfoPaperMapper;

    @Autowired
    UserService userService;

    /**
     * 根据pfoCompany参数进行公司信息查询
     * @param pfoCompany
     * @return 找到的公司信息list集合
     */
    @Override
    public List<PfoCompany> queryCompany(PfoCompany pfoCompany) {
        return pfoCompanyMapper.queryCompany(pfoCompany);
    }

    /**
     * 查看公司详情信息  selectById
     * @param companyId
     * @return
     */
    public PfoCompany selectByPrimaryKey(Long companyId) {
        return pfoCompanyMapper.selectByPrimaryKey(companyId);
    }

    /**
     * 根据公司查找试卷列表
     * @param pageParameter
     * @param ids
     * @return
     */
    public PageInfoResult<PfoPaper> queryPaperListByCompany(PageInfoResult pageParameter, String ids) {
        PageHelper.startPage(pageParameter.getCurrPage(), pageParameter.getPageSize());

        List<Long> idList = new ArrayList<>();
        String idStr[] = ids.split(",");
        Long id = 0L;

        for (int i = 0; i < idStr.length; i++) {
            id = Long.parseLong(idStr[i]);
            idList.add(id);
        }

        List<PfoPaper> paperList = pfoPaperMapper.selectPapersByPrimaryKeys(idList);
        PageInfo<PfoPaper> pageInfo = new PageInfo<>(paperList);

        // 分页后的试卷列表
        List<PfoPaper> papers = pageInfo.getList();
        pageParameter.setTotalSize(pageInfo.getTotal());
        pageParameter.setDataList(papers);
        return pageParameter;
    }

    /**
     * 公司注册首先添加到用户表，添加成功后然后添加公司数据到公司表
     * 当添加公司数据到公司表出错时，会发生回滚操作，即回滚插入用户表的操作
     * @Transactional 注解表示方法内是一个事务
     * @param pfoUser
     * @param pfoCompany
     * @return
     */
    @Override
    @Transactional
    public int addCompany(PfoUser pfoUser,PfoCompany pfoCompany) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        pfoUser.setUserType(ConstantValue.COMPANY_USER);
        int insertStatus = userService.addPfoUser(pfoUser);
        pfoCompany.setUserId(pfoUser.getUserId());//需要mapper下配置主键回填，不然获取不到主键
        if(insertStatus>0){
            return pfoCompanyMapper.insertSelective(pfoCompany);
        }
        return 0;
    }

    @Override
    public int updateCompany(PfoCompany pfoCompany) {
        return pfoCompanyMapper.updateByPrimaryKeySelective(pfoCompany);
    }

    /**
     * 注销公司账号流程：1.删除公司表的账号信息2.删除对应userId的用户信息
     * @param companyId
     * @return
     */
    @Override
    @Transactional
    public int deleteCompany(Long companyId) {
        PfoCompany pfoCompany = new PfoCompany();
        pfoCompany.setCompanyId(companyId);
        List<PfoCompany> companyList = queryCompany(pfoCompany);//先查询
        if(null != companyList && companyList.size()>0){
            PfoCompany company = companyList.get(0);
            System.out.println(company.toString());
            int deleteStatus  = pfoCompanyMapper.deleteByPrimaryKey(companyId);
            if(deleteStatus>0){
                return userService.deletePfoUser(company.getUserId());
            }else{
                return 0;
            }
        }
        return 0;
    }
}

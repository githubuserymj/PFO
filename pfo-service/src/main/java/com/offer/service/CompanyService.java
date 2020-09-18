package com.offer.service;

import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoCompany;
import com.offer.pojo.PfoPaper;
import com.offer.pojo.PfoUser;
import com.offer.vo.ResultData;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by YMJ on 2019-09-12.
 */
@Component
public interface CompanyService {
    /**
     * 根据pfoCompany)进行任意条件查询
     * @param pfoCompany
     * @return
     */
    public List<PfoCompany> queryCompany(PfoCompany pfoCompany);

    PfoCompany selectByPrimaryKey(Long companyId);

    /**
     * 根据公司获取试卷列表
     * @param pageParameter
     * @param ids
     * @return
     */
    PageInfoResult<PfoPaper> queryPaperListByCompany(PageInfoResult pageParameter, String ids);

    /**
     * 增加pfoCompany
     * @param pfoCompany
     * @return
     */
    public int addCompany(PfoUser pfoUser,PfoCompany pfoCompany) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException;

    /**
     * 根据pfoCompany参数的信息更新公司信息
     * @param pfoCompany
     * @return
     */
    public int updateCompany(PfoCompany pfoCompany);

    /**
     * 根据id删除公司账号
     * @param companyId
     * @return
     */
    public int deleteCompany(Long companyId);
}

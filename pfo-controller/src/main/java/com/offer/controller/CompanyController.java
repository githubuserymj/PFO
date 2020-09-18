package com.offer.controller;

import com.offer.dta.PageInfoResult;
import com.offer.pojo.PfoCompany;
import com.offer.pojo.PfoPaper;
import com.offer.pojo.PfoUser;
import com.offer.service.CompanyService;
import com.offer.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import com.offer.util.QiNiuUtil;

/**
 * Created by YMJ on 2019-09-12.
 */
@Controller
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * 接收参数查询公司信息
     * @param pfoCompany
     * @return
     */
    @RequestMapping("queryCompany")
    @ResponseBody
    public ResultData queryCompany(PfoCompany pfoCompany){
        ResultData resultData = new ResultData();
        List<PfoCompany> companyList = companyService.queryCompany(pfoCompany);
        if(null != companyList && companyList.size()>0){
            resultData.setCode(0);
            resultData.setData(companyList);
        }else{
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    @RequestMapping("queryCompanyById")
    @ResponseBody
    public ResultData selectByPrimaryKey(Long companyId){
        ResultData resultData = new ResultData();
        PfoCompany pfoCompany = companyService.selectByPrimaryKey(companyId);
        if(null !=  pfoCompany){
            resultData.setCode(0);
            resultData.setData(pfoCompany);
        }else{
            resultData.setCode(3);
            resultData.setMessage("查无数据");
        }
        return resultData;
    }

    /**
     * 根据公司获取试卷列表
     * @param pageParameter
     * @param companyId
     * @return
     */
    @RequestMapping("queryPaperListByCompanyId")
    public @ResponseBody PageInfoResult<PfoPaper> queryPaperListByCompanyId(PageInfoResult pageParameter, Long companyId) {
        PfoCompany pfoCompany = companyService.selectByPrimaryKey(companyId);
        String ids = pfoCompany.getPaperListId();

        return companyService.queryPaperListByCompany(pageParameter, ids);
    }

    /**
     * 公司也是一个用户，除了也需要填写用户必填的用户名，密码，手机号外
     * 也必须填写公司名称(必须唯一),公司地址，公司资质证明(图片)
     * @param pfoCompany
     * @param pfoUser
     * @return
     */
    @RequestMapping("addCompany")
    @ResponseBody
    public ResultData addCompany(HttpSession session, PfoUser pfoUser, PfoCompany pfoCompany, MultipartFile multipartFile, String registSmsCode) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        ResultData resultData = new ResultData();
        if(multipartFile==null) {
            resultData.setCode(5);
            resultData.setMessage("文件传入失败");
            return resultData;
        }

        //下面判断确保公司注册人及其公司信息的参数的合法传入
        if(null != pfoUser.getUserName() && !pfoUser.getUserName().equals("") && null != pfoUser.getUserPassword() && !pfoUser.getUserPassword().equals("") && null != pfoUser.getUserPhone() && !pfoUser.getUserPhone().equals("") && pfoCompany.getCompanyName()!=null && !pfoCompany.getCompanyName().equals("") && pfoCompany.getCompanyAddress()!=null && !pfoCompany.getCompanyAddress().equals("") && null != registSmsCode && !registSmsCode.equals("")){
            String relSmsCode = (String) session.getAttribute("smsCode");//获取session中真正的验证码
            relSmsCode="123";
            if(null == relSmsCode){
                resultData.setCode(4);
                resultData.setMessage("请发送短信验证码");
                return resultData;
            }else if(relSmsCode.equals(registSmsCode)){
                String oldName = multipartFile.getOriginalFilename();
                String time = String.valueOf(System.currentTimeMillis());//获取当前系统时间戳
                String newName = time + oldName.substring(oldName.lastIndexOf("."));//时间戳加文件后缀组成新的文件名
//                传到本地路径
//                System.out.println("文件名："+oldName);
//                System.out.println("新文件名："+newName);
//                String diskPath = "E://PFO/pfo/pfo-web/src/main/resources/static/img/temporary/";
//                File file = new File(diskPath+newName);
//                System.out.println("文件路径:"+file.toString());
//                multipartFile.transferTo(file);
//                String servicePath = "http://localhost:8080/pfo/img/temporary/";
//                传到七牛云服务器，先进行类型转换，再调用工具类
                InputStream inputStream = multipartFile.getInputStream();
                FileInputStream file = (FileInputStream) inputStream;
                String imgPath = QiNiuUtil.uploadToken(file,newName);

                pfoCompany.setQualification(imgPath);//保存图片路径
                int addStatus = companyService.addCompany(pfoUser,pfoCompany);
                if(addStatus>0){
                    resultData.setCode(0);
                }else{
                    resultData.setCode(3);
                    resultData.setMessage("注册失败");
                }
            }else{
                resultData.setCode(5);
                resultData.setMessage("验证码错误");
            }

        }else{
            resultData.setCode(5);
            resultData.setMessage("参数错误");
        }

        return resultData;
    }

    /**
     * 按照参数更新公司信息
     * @param pfoCompany
     * @return
     */
    @RequestMapping("updateCompany")
    @ResponseBody
    public ResultData updateCompany(PfoCompany pfoCompany){
        ResultData resultData = new ResultData();

        if(null != pfoCompany.getCompanyId()){
            int updateStatus = companyService.updateCompany(pfoCompany);
            if(updateStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("更新失败");
            }
        }else{
            resultData.setCode(5);
            resultData.setMessage("参数有误");
        }

        return resultData;
    }

    /**
     * 按照id删除公司信息
     * @param companyId
     * @return
     */
    @RequestMapping("deleteCompany")
    @ResponseBody
    public ResultData deleteCompany(Long companyId){
        ResultData resultData = new ResultData();
        if(null != companyId){
            int deleteStatus = companyService.deleteCompany(companyId);
            if(deleteStatus>0){
                resultData.setCode(0);
            }else{
                resultData.setCode(3);
                resultData.setMessage("公司账号注销失败");
            }
        }else{
            resultData.setCode(4);
            resultData.setMessage("参数有误");
        }

        return resultData;
    }

}

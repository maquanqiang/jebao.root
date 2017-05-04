package com.jebao.jebaodb.dao;

import com.jebao.jebaodb.dao.base._BaseUnitTest;
import com.jebao.jebaodb.dao.dao.loaner.TbLoanerDao;
import com.jebao.jebaodb.dao.dao.loanmanage.TbBidPlanDao;
import com.jebao.jebaodb.entity.extEntity.PageWhere;
import com.jebao.jebaodb.entity.loaner.LoanTotal;
import com.jebao.jebaodb.entity.loaner.TbLoaner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2016/11/14.
 */
public class TbLoanerDao_UnitTest extends _BaseUnitTest {
    @Autowired
    private TbLoanerDao tbLoanerDao;
    @Autowired
    private TbBidPlanDao tbBidPlanDao;
    @Test
    public void testExample(){
/*        BigDecimal bNum1 = new BigDecimal(5123l);
        BigDecimal bNum2  = new BigDecimal(0.135);
        BigDecimal newNum = bNum1.multiply(bNum2).setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(newNum.toString());*/
        PageWhere page = new PageWhere(0, 4);
        List<TbLoaner> loanerList = tbLoanerDao.selectByParamsForPage(null, page);
        List<LoanerVM> viewModelList = new ArrayList<>();
        List<Long> loanerIds = new ArrayList<>();
        loanerList.forEach(o -> loanerIds.add(o.getlId()));
        List<LoanTotal> loanTotalList = tbBidPlanDao.selectLoanTotalByLoanerIds(loanerIds);

        for(int i = 0; i < loanerList.size(); i++){
            LoanerVM vm = new LoanerVM(loanerList.get(i));
            for (int j=0;j<loanTotalList.size();j++){
                if(loanTotalList.get(j).getLoanerId() ==  vm.getId()){
                    vm.setBorrowCount(loanTotalList.get(j).getTotalTrades());
                    vm.setBorrowAmount(loanTotalList.get(j).getTotalAmounts());
                    break;
                }
            }
            viewModelList.add(vm);
        }
        assertThat(viewModelList).isNotEqualTo(null);
        System.out.println("viewModelList:" + viewModelList.size());
    }

    @Test
    public void insertExample()
    {
        TbLoaner record = new TbLoaner();
        record.setlAge(32);
        record.setlBankCardNo("55555555555555555555");
        record.setlBankCityCode("10000");
        record.setlBankCityName("北京");
        record.setlBankParentBankCode("");
        record.setlBankParentBankName("");
        record.setlBankProvincesCode("");
        record.setlBankProvincesName("");
        record.setlCreateTime(new Date());
        record.setlCreditStatus("");
        record.setlEmail("");
        record.setlHkadr("");
        record.setlHomeAdd("");
        record.setlIdNumber("");
        record.setlIsDel(1);
        record.setlIshaveCar(1);
        record.setlIshaveHouse(1);
        record.setlLastLoginTime(new Date());
        record.setlLoginId((long)1);
        record.setlMaritalStatus(1);
        record.setlMonthlySalary(1);
        record.setlNickName("三刀");
        record.setlPhone("15901048116");
        record.setlPoliticsStatus(1);
        record.setlSex(1);
        record.setlThirdAccount("");
        record.setlThirdLoginPassword("");
        record.setlThirdPayPassword("");
        record.setlTrueName("王伟");
        record.setlWorkCity("");
        long result= tbLoanerDao.insert(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getlId());
    }
    @Test
    public void insertSelectiveExample()
    {
      /*  TbLoaner record = new TbLoaner();
        record.setUsername("Selective2016102001");
        int result= tbLoanerDao.insertSelective(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getId());*/
    }

    @Test
    public void selectByPrimaryKeyExample() {
      /*  TbLoaner result= tbLoanerDao.selectByPrimaryKey(105);
        assertThat(result).isNotEqualTo(null);*/
    }
    @Test
    public void updateByPrimaryKeySelectiveExample() {
       /* TbLoaner record= tbLoanerDao.selectByPrimaryKey(105);
        record.setPassword("updateByPrimaryKeySelective20161020");
        int result= tbLoanerDao.updateByPrimaryKeySelective(record);
        assertThat(result).isNotEqualTo(null);*/
    }
    @Test
    public void updateByPrimaryKeyExample() {
        /*TbLoaner record= tbLoanerDao.selectByPrimaryKey(105);
        record.setPassword("primaryKey2016102001");
        int result= tbLoanerDao.updateByPrimaryKey(record);
        assertThat(result).isNotEqualTo(null);*/
    }
    @Test
    public void deleteByPrimaryKeyExample() {
       /* int result= tbLoanerDao.deleteByPrimaryKey(113);
        assertThat(result).isNotEqualTo(null);*/
    }
    @Test
    public void selectForPageExample() {
        PageWhere pageWhere=new PageWhere(1,10);
        List<TbLoaner> result = tbLoanerDao.selectForPage(pageWhere);
        assertThat(result).isNotEqualTo(null);
    }
    @Test
    public void selectByUserNameForPageExample() {
       /* PageWhere pageWhere=new PageWhere(0,10);
        TbLoaner record = new TbLoaner();
        record.setUsername("us2016102001");
        List<TbLoaner> result = tbLoanerDao.selectByUserNameForPage(record, pageWhere);
        assertThat(result).isNotEqualTo(null);*/
    }
    @Test
    public void selectByParamsForPageCountExample() {
        TbLoaner record = new TbLoaner();
       // record.setlPhone("15901048116");
        int result = tbLoanerDao.selectByParamsForPageCount(record);
        assertThat(result).isNotEqualTo(null);
        System.out.println("result:"+result);
    }
    /**
     * Spring Boot中的事务管理
     * 事务使用
     * */
    @Test
    public void insertForTransactionalExample()
    {
        /*TbLoaner record = new TbLoaner();
        record.setUsername("us2016102001");
        record.setPassword("pwd2016102001");
        int result= tbLoanerDao.insertForTransactional(record);
        assertThat(result).isEqualTo(1);
        System.out.println(record.getId());*/
    }
}

class LoanerVM {
    public LoanerVM(TbLoaner entity){
        this.id = entity.getlId();
        this.loginId = entity.getlLoginId();
        this.nickName = entity.getlNickName();
        this.phone = entity.getlPhone();
        this.trueName = entity.getlTrueName();
        this.idNumber = entity.getlIdNumber();
        this.email = entity.getlEmail();
        this.sex = entity.getlSex();
        this.age = entity.getlAge();
        this.registerTime = entity.getlRegisterTime();
        this.lastLoginTime = entity.getlLastLoginTime();
        this.homeAdd = entity.getlHomeAdd();
        this.hkadr = entity.getlHkadr();
        this.maritalStatus = entity.getlMaritalStatus();
        this.ishaveHouse = entity.getlIshaveHouse();
        this.ishaveCar = entity.getlIshaveCar();
        this.politicsStatus = entity.getlPoliticsStatus();
        this.creditStatus = entity.getlCreditStatus();
        this.monthlySalary = entity.getlMonthlySalary();
        this.education = entity.getlEducation();
        this.workCity = entity.getlWorkCity();
        this.borrowCount = 0;
        this.borrowAmount = new BigDecimal(0);
    }
    private Long id;

    private Long loginId;

    private String nickName;

    private String phone;

    private String trueName;

    private String idNumber;

    private String email;

    private Integer sex;

    private Integer age;

    private Date registerTime;

    private Date lastLoginTime;

    private String homeAdd;

    private String hkadr;

    private Integer maritalStatus;

    private Integer ishaveHouse;

    private Integer ishaveCar;

    private Integer politicsStatus;

    private String creditStatus;

    private Integer monthlySalary;

    private Integer education;

    private String workCity;

    //实际借款笔数
    private Integer borrowCount;

    //实际借款金额
    private BigDecimal borrowAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getHkadr() {
        return hkadr;
    }

    public void setHkadr(String hkadr) {
        this.hkadr = hkadr;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getIshaveHouse() {
        return ishaveHouse;
    }

    public void setIshaveHouse(Integer ishaveHouse) {
        this.ishaveHouse = ishaveHouse;
    }

    public Integer getIshaveCar() {
        return ishaveCar;
    }

    public void setIshaveCar(Integer ishaveCar) {
        this.ishaveCar = ishaveCar;
    }

    public Integer getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(Integer politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Integer monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }
}

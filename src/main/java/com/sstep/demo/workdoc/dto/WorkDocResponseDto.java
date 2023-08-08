package com.sstep.demo.workdoc.dto;

import java.time.LocalDate;

public class WorkDocResponseDto {
    private String storeName; //사업체 명 => Store
    private String storeAddress; //사업체 주소 => Store
    private String storePhoneNum; //사업체 연락처 => Member
    private String storeOwnerName; //사업체 대표자 이름 => Member
    private LocalDate startDay; //입사일 => Staff
    private int hourMoney; //시급 => Staff
    private int wageType; //급여 지급 방식 => Staff
    private String paymentDate; //급여지급일 => Staff

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public void setStorePhoneNum(String storePhoneNum) {
        this.storePhoneNum = storePhoneNum;
    }

    public void setStoreOwnerName(String storeOwnerName) {
        this.storeOwnerName = storeOwnerName;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public void setHourMoney(int hourMoney) {
        this.hourMoney = hourMoney;
    }

    public void setWageType(int wageType) {
        this.wageType = wageType;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}

package com.sstep.demo.staff.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public class StaffResponseDto {
    private Date startDay; //입사일
    private int hourMoney; //시급
    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    private String paymentDate; //급여지급일
    private boolean ownerStatus; //사장 여부
    private boolean joinStatus; //초대 여부, 초대를 보냈으면 true
    private boolean submitStatus; //코드 입력 여부, 코드를 입력했으면 true

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public int getHourMoney() {
        return hourMoney;
    }

    public void setHourMoney(int hourMoney) {
        this.hourMoney = hourMoney;
    }

    public int getWageType() {
        return wageType;
    }

    public void setWageType(int wageType) {
        this.wageType = wageType;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isOwnerStatus() {
        return ownerStatus;
    }

    public void setOwnerStatus(boolean ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public boolean isJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

    public boolean isSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(boolean submitStatus) {
        this.submitStatus = submitStatus;
    }
}


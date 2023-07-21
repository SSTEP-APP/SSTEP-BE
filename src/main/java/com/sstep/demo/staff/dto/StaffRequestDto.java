package com.sstep.demo.staff.dto;


import java.sql.Date;

public class StaffRequestDto {
    private long id; //직원 고유번호
    private Date startDay; //입사일
    private String paymentDate; //급여지급일

    private int hourMoney; //시급

    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    private boolean ownerStatus; //사장 여부
    private boolean joinStatus; //합류 여부

    public void setJoinStatus(boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

    public void setOwnerStatus(boolean ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Date getStartDay() {
        return startDay;
    }

    public int getHourMoney() {
        return hourMoney;
    }

    public int getWageType() {
        return wageType;
    }

}

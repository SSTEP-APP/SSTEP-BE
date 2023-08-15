package com.sstep.demo.staff.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StaffRequestDto {
    private String username; //회원 아이디
    private long code; //사업장 초대 코드 번호
    private long id; //직원 고유번호
    private Date startDay; //입사일
    private String paymentDate; //급여지급일

    private int hourMoney; //시급

    private int wageType; //급여 지급 방식 일급(1), 주급(2), 월급(3)
    private boolean ownerStatus; //사장 여부
    private boolean joinStatus; //합류 여부
    private boolean submitStatus; //코드 입력 여부

    public String getPaymentDate() {
        return paymentDate;
    }

    public long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public long getCode() {
        return code;
    }
}

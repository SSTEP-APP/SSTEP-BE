package com.sstep.demo.staff;

import com.sstep.demo.calendar.domain.Calendar;
import com.sstep.demo.calendar.dto.CalendarRequestDto;
import com.sstep.demo.commute.domain.Commute;
import com.sstep.demo.commute.dto.CommuteRequestDto;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import com.sstep.demo.schedule.domain.Schedule;
import com.sstep.demo.schedule.dto.ScheduleRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Notice toNoticeEntity(NoticeRequestDto noticeRequestDto);
}

package com.sstep.demo.notice;

import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.notice.dto.NoticeRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticeMapper {
    Notice toNoticeEntity(NoticeRequestDto noticeRequestDto);
}

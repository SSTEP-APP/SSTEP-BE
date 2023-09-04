package com.sstep.demo.photo.domain;

import com.sstep.demo.checklist.domain.CheckList;
import com.sstep.demo.healthdoc.domain.HealthDoc;
import com.sstep.demo.notice.domain.Notice;
import com.sstep.demo.workdoc.domain.WorkDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Photo {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //사진 고유번호
    private String fileName;
    private String contentType;
    @Lob
    private byte[] data;

    public Photo(String fileName, String contentType, byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void setHealthDoc(HealthDoc healthDoc) {
        this.healthDoc = healthDoc;
    }

    public void setWorkDoc(WorkDoc workDoc) {
        this.workDoc = workDoc;
    }

    @ManyToOne
    private Notice notice;

    @ManyToOne
    private CheckList checkList;

    @OneToOne(mappedBy = "photo")
    private HealthDoc healthDoc;

    @OneToOne(mappedBy = "photo")
    private WorkDoc workDoc;
}

package com.sstep.demo.store.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StoreRequestDto {
    private long id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private boolean scale;
    private boolean plan;
    private long code;
}

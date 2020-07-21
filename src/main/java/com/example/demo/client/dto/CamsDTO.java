package com.example.demo.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CamsDTO {
    private Long id;
    private String sourceDataUrl;
    private String tokenDataUrl;
}

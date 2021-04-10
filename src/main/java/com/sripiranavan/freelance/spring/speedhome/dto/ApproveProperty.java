package com.sripiranavan.freelance.spring.speedhome.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApproveProperty {
    @NotNull
    private Long id;
    @NotNull
    private boolean status;
}

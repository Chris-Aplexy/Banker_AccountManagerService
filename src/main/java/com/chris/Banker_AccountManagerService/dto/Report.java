package com.chris.Banker_AccountManagerService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {

    private String reportDescription;
    private Date generationDate;
}

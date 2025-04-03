package com.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeSegment {
    private String className;
    private String methodName;
    private String originalCode;
    private String optimizedCode;
}
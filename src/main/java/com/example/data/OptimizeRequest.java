package com.example.data;

import lombok.Data;

import java.util.List;

@Data
public class OptimizeRequest {
    private List<CodeSegment> segments;
}
package com.example.service;

import com.example.data.CodeSegment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MergeService {

    public String mergeSegments(List<CodeSegment> segments) {
        Map<String, List<String>> classMap = new LinkedHashMap<>();

        for (CodeSegment s : segments) {
            classMap
                    .computeIfAbsent(s.getClassName(), k -> new ArrayList<>())
                    .add(s.getOptimizedCode());
        }

        StringBuilder result = new StringBuilder();
        classMap.forEach((cls, methods) -> {
            result.append("public class ").append(cls).append(" {\n");
            methods.forEach(m -> result.append("\n").append(m).append("\n"));
            result.append("}\n\n");
        });

        return result.toString();
    }
}
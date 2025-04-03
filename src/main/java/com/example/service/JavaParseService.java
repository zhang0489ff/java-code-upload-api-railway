package com.example.service;

import com.example.data.CodeSegment;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JavaParseService {

    public static JavaParser javaParser = new JavaParser();

    public List<CodeSegment> splitByMethod(String sourceCode) {
        List<CodeSegment> segments = new ArrayList<>();
        CompilationUnit cu = javaParser.parse(sourceCode).getResult().get();

        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> {
            String className = clazz.getNameAsString();
            clazz.getMethods().forEach(method -> {
                String code = method.toString();
                segments.add(new CodeSegment(className, method.getNameAsString(), code, null));
            });
        });

        return segments;
    }
}
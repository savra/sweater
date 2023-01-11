package com.example.sweater.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.sweater.domain.Post.ImgFormat;

@Converter(autoApply = true)
public class ImgFormatConverter implements AttributeConverter<Set<ImgFormat>, String> {
    @Override
    public String convertToDatabaseColumn(Set<ImgFormat> imgFormats) {
        if (imgFormats == null) {
            return null;
        }

        return imgFormats.stream().map(Enum::toString).collect(Collectors.joining(","));
    }

    @Override
    public Set<ImgFormat> convertToEntityAttribute(String s) {
        return Pattern.compile(",")
                .splitAsStream(Optional.ofNullable(s).orElse("")).filter(listItem -> {
                    try {
                        ImgFormat.valueOf(listItem.toUpperCase().trim());
                        return true;
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                }).map(v -> ImgFormat.valueOf(v.toUpperCase().trim()))
                .collect(Collectors.toSet());
    }
}

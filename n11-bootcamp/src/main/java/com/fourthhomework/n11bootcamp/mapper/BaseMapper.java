package com.fourthhomework.n11bootcamp.mapper;

import java.util.List;

public interface BaseMapper<T,G> {

    T toEntity(G g);

    G toDto(T t);

    List<T> toEntity(List<G> gList);

    List<G> toDto(List<T> tList);

}

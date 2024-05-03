package com.example.bo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    // 클라이언트로부터 입력 받은 URL % 파라미터 정보 / controller에서 정보 수집

    @Builder.Default
    private int page = 1; // 현재 페이지

    @Builder.Default
    private int size = 10; // 현재 페이지 글 갯수

    private String type; // 검색 종류 select box <t, c, w, tc, tw, cw, twc>
    private String keyword; // 검색어
    private String link;
    public String[] getTypes(){
        if(type == null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }
    public Pageable getPageable(String...props){
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    public String getLink(){
        if(link == null){
            StringBuffer buffer = new StringBuffer();
            buffer.append("page=" + this.page);
            buffer.append("&size=" + this.size);

            if(type != null && type.length() > 0){
                buffer.append("&type=" + type);
            }
            if(keyword != null){
                try {
                    buffer.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e){}
            }
            link = buffer.toString();
        }
        return link;
    }

}

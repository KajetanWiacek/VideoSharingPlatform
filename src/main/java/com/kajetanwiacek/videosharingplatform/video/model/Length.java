package com.kajetanwiacek.videosharingplatform.video.model;

import com.kajetanwiacek.videosharingplatform.exception.InvalidVideoLengthPatternException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Length {
    private Integer hours;
    private Integer minutes;
    private Integer seconds;

    public static void validate(Length length){
        if(length.getHours()<0||length.getHours()>10){
            throw new InvalidVideoLengthPatternException("hours");
        }
        if(length.getMinutes()<0||length.getMinutes()>59){
            throw new InvalidVideoLengthPatternException("minutes");
        }
        if(length.getSeconds()<0||length.getSeconds()>59){
            throw new InvalidVideoLengthPatternException("seconds");
        }
    }
}

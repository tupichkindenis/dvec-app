package com.whitesoft.domain;

import com.whitesoft.domain.enums.Day;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Workday.
 * Created by tupichkindenis on 19.09.16.
 */
@Embeddable
public class Workday {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day name;

    @Column(nullable = false)
    private String workTime;

    private String dinnerTime;

    /**
     *
     * @param name
     * @param workTime
     * @param dinnerTime
     */
    public Workday(Day name, String workTime, String dinnerTime) {
        Assert.notNull(name);
        Assert.notNull(workTime);
        Assert.hasText(workTime);

        this.name = name;
        this.workTime = workTime;
        this.dinnerTime = dinnerTime;
    }


    protected Workday() {}

    /**
     *
     * @return
     */
    public Day getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getWorkTime() {
        return workTime;
    }

    /**
     *
     * @return
     */
    public String getDinnerTime() {
        return dinnerTime;
    }
}

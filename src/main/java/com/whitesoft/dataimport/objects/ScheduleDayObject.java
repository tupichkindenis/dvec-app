package com.whitesoft.dataimport.objects;

import com.whitesoft.domain.enums.Day;

/**
 * Created by tupichkindenis on 25.10.16.
 */
public class ScheduleDayObject {
    private String name;
    private String worktime;
    private String dinnertime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getDinnertime() {
        return dinnertime;
    }

    public void setDinnertime(String dinnertime) {
        this.dinnertime = dinnertime;
    }

}

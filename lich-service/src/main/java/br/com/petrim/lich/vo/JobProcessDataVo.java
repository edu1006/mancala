package br.com.petrim.lich.vo;

import java.util.List;

public class JobProcessDataVo {

    private List<EnumValueVo<Integer>> periodicities;
    private List<EnumValueVo<Integer>> weekDays;

    public List<EnumValueVo<Integer>> getPeriodicities() {
        return periodicities;
    }

    public void setPeriodicities(List<EnumValueVo<Integer>> periodicities) {
        this.periodicities = periodicities;
    }

    public List<EnumValueVo<Integer>> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<EnumValueVo<Integer>> weekDays) {
        this.weekDays = weekDays;
    }
}

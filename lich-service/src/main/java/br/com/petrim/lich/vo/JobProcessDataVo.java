package br.com.petrim.lich.vo;

import br.com.petrim.lich.model.Parameter;

import java.util.List;

public class JobProcessDataVo {

    private List<EnumValueVo<Integer>> periodicities;
    private List<EnumValueVo<Integer>> weekDays;
    private List<Parameter> parameters;

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

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}

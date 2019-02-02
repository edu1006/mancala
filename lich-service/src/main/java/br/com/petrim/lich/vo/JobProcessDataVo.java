package br.com.petrim.lich.vo;

import java.util.List;

public class JobProcessDataVo {

    private List<EnumValueVo<Integer>> periodicities;

    public List<EnumValueVo<Integer>> getPeriodicities() {
        return periodicities;
    }

    public void setPeriodicities(List<EnumValueVo<Integer>> periodicities) {
        this.periodicities = periodicities;
    }
}

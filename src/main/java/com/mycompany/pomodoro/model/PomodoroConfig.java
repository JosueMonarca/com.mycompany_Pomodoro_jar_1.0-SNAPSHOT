package com.mycompany.pomodoro.model;

public class PomodoroConfig {
    private static PomodoroConfig instance;
    private int workTime;
    private int breakTime;
    private int repetitions;
    
    private PomodoroConfig(){
        this.workTime = 0;
        this.breakTime = 0;
        this.repetitions = 0;
    }
    
    public static PomodoroConfig getInstance(){
        if(instance == null){
            instance = new PomodoroConfig();
        }
        return instance;
    }

    public int getJob() {
        return workTime;
    }

    public void setJob(int work) {
        this.workTime = work;
    }

    public int getBreaktime() {
        return breakTime;
    }

    public void setRest(int descanso) {
        this.breakTime = descanso;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}

package br.ufsc.ine5605.constants;

public enum MissionState {
    STARTED("Started"), ONGOING("Ongoing"), FAILURE("Failure"), SUCCESSFULL("Successful");

    private String message;

    private MissionState(String message) { this.message = message; }

    public String getMessage(){
        return this.message;
    }

}

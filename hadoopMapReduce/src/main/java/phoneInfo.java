public class phoneInfo {
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getMinPhoneVolume() {
        return minPhoneVolume;
    }

    public void setMinPhoneVolume(Integer minPhoneVolume) {
        this.minPhoneVolume = minPhoneVolume;
    }

    public Integer getMaxPhoneVolume() {
        return maxPhoneVolume;
    }

    public void setMaxPhoneVolume(Integer maxPhoneVolume) {
        this.maxPhoneVolume = maxPhoneVolume;
    }

    private String phoneNumber;
    private Integer minPhoneVolume;
    private Integer maxPhoneVolume;

    public double getAvgPhoneVolume() {
        return avgPhoneVolume;
    }

    public void setAvgPhoneVolume(double avgPhoneVolume) {
        this.avgPhoneVolume = avgPhoneVolume;
    }

    private double avgPhoneVolume;
}

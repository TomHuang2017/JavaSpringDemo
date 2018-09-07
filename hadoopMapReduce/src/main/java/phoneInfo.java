import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

//这个自定义的phoneInfo必须继承Writable类，且要实现write和readFields两个方法，
//同时要重写toString()方法，因为该方法决定了输出到最后的文件里的格式
public class phoneInfo implements Writable {
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

    //必须重写该write方法，写入是做序列化
    public void write(DataOutput out) throws IOException{
        out.writeInt(minPhoneVolume);
        out.writeInt(maxPhoneVolume);
        out.writeDouble(avgPhoneVolume);
    }

    //必须重写该readFields方法，读出时做反序列化
    public void readFields(DataInput in ) throws IOException{
        setMinPhoneVolume(in.readInt());
        setMaxPhoneVolume(in.readInt());
        setAvgPhoneVolume(in.readInt());
    }

    @Override
    public String toString()
    {
        //重写toString方法，下面的格式就是最后output文件夹下文件的存储内容里的格式
        //加自定义的方法。
        return getMinPhoneVolume()+","+getMaxPhoneVolume()+","+getAvgPhoneVolume();
    }
}

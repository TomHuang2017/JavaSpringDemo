import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class phonelist {
    //4个参数：输入行偏移量，输入某行内容，输出的手机号作为key,输出key:是一个Writable的类，因为我们要输出的内容包含很多信息，这里我们定义成一个继承自Writable自定义类
    public static class phoneListMaper extends Mapper<LongWritable,Text,Text,phoneInfo> {
        private static String line;
        public void map(LongWritable key, Text value, Context context)
                throws IOException,InterruptedException
        {
            line=value.toString();
            String[] phoneMessage=line.split(",");

            phoneInfo pi=new phoneInfo();
            String phoneNumber="";
            if(phoneMessage.length>=3) {
                phoneNumber = phoneMessage[0];
                Integer minPhoneVolume = Integer.parseInt(phoneMessage[1]);
                Integer maxPhoneVolume = Integer.parseInt(phoneMessage[2]);

                pi.setPhoneNumber(phoneNumber);
                pi.setMinPhoneVolume(minPhoneVolume);
                pi.setMaxPhoneVolume(maxPhoneVolume);
            }
            //下面的key还是手机号，value是一个phoneInfo的类
            context.write(new Text(phoneNumber), pi);
        }
    }
    //reduce4个参数：
    // key手机号key,
    //接收来自map的相同key的values，合成一个phoneInfo实例的数组，
    //输出手机号key
    //输出一个新的phoneInfo类
    public static  class phoneListReduce extends Reducer<Text,phoneInfo,Text,phoneInfo>
    {
        //reduce 方法接收的是一个字符串类型的key,一个可迭代的数据集，即接受到的是一组数据，即key相同，value不同的项
        //如有四个map，那么reduce某次接收到的是(a,phoneInfo实例1),(a,phoneInfo实例2),(a,phoneInfo实例3),(a,phoneInfo实例4)
        //即如下参数是：key=good,value=(phoneInfo实例1,phoneInfo实例2,phoneInfo实例3,phoneInfo实例4)
        protected void reduce (Text key, Iterable<phoneInfo> values,Context context)
                throws IOException,InterruptedException{
            Integer minPhoneVolume=0;
            Integer maxPhoneVolume=0;
            double avgPhoneVolume;
            for(phoneInfo value:values)
            {
                minPhoneVolume=minPhoneVolume+value.getMinPhoneVolume();
                maxPhoneVolume=maxPhoneVolume+value.getMaxPhoneVolume();
            }
            phoneInfo pi=new phoneInfo();

            avgPhoneVolume=(minPhoneVolume+maxPhoneVolume)*1.0/2;
            pi.setAvgPhoneVolume(avgPhoneVolume);
            pi.setMaxPhoneVolume(maxPhoneVolume);
            pi.setMinPhoneVolume(minPhoneVolume);
            //key是手机号，value是相同map reduce后的一个新的phoneInfo类实例
            context.write(key,pi);
        }
    }

    public static void main(String[] args) throws Exception{
        //创建配置对象
        Configuration conf=new Configuration();
        //创建job对象
        Job job=Job.getInstance(conf,"phonelist_job");
        //设置运行job的类
        job.setJarByClass(phonelist.class);
        //设置mapper类
        job.setMapperClass(phonelist.phoneListMaper.class);
        //设置reduce类
        job.setReducerClass(phonelist.phoneListReduce.class);
        //设置map输出的key value，value是phoneInfo类
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(phoneInfo.class);

        //设置reduce输出的key value，value是phoneInfo类
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(phoneInfo.class);

        //设置输入输出的路径
        String inputPathString=args[0];
        String outPutPathString=args[1];
        System.out.println("main->inputPath:"+inputPathString);
        System.out.println("main->outPutPath:"+outPutPathString);

        FileInputFormat.setInputPaths(job,new Path(inputPathString));
        FileOutputFormat.setOutputPath(job,new Path(outPutPathString));

        //MR每次执行的时候，如果output文件夹存在就会报错
        //所以我们在代码里，手动判断，如果存在output文件夹就删除
        Path inPutPath=new Path(inputPathString);
        Path outPutPath=new Path(outPutPathString);
        FileSystem fileSystem=outPutPath.getFileSystem(conf);//根据path找到这个文件
        if(inPutPath.getFileSystem(conf).exists(inPutPath))
        {
            System.out.println("main->inputPathString path ["+inputPathString+"] existed");
        }
        else
        {
            System.out.println("main->inputPathString path ["+inputPathString+"] not existed!!");
        }
        if(fileSystem.exists(outPutPath))
        {
            System.out.println("main->outPutPathString path ["+outPutPathString+"] existed, deleting..");
            fileSystem.delete(outPutPath,true);//true表示，outPutPath里面若有东西，也删除
        }
        boolean b=job.waitForCompletion(true);
        if(!b)
        {
            System.out.println("phonelist task fail!");
        }
        else{
            System.out.println("phonelist task succeed!");
        }
    }
}

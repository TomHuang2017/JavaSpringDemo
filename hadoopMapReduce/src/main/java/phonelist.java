import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class phonelist {
    //其中的4个类型分别是：
    // 输入key类型、输入value类型、（map方法的两个入参）
    // 输出key类型、输出value类型。（map方法最后return的两个形参）
    // 因为MapReduce程序的输出数据需要在不同机器间传输，所以必须是可序列化的，例如Long类型，
    // Hadoop中定义了自己的可序列化类型LongWritable，String对应的是Text，int对应的是IntWritable
    public static class phoneListMaper extends Mapper<LongWritable,Text,Text,phoneInfo> {
        private static String line;
        //key是某一行文本的起始偏移量(Long类型)
        //value默认是这行文本的内容Text类型。
        public void map(LongWritable key, Text value, Context context)
                throws IOException,InterruptedException
        {
            line=value.toString();
            String[] phoneMessage=line.split(",");
            phoneInfo pi=new phoneInfo();
            pi.setPhoneNumber(phoneMessage[1]);
            pi.setPhoneNumber(phoneMessage[2]);
            pi.setPhoneNumber(phoneMessage[3]);
            //Mapper中的key和value的类型可以有最终的需求关联，比如我们是想统计每个手机号的上下流量值
            //那么source 文件中的每条数据都可以进行，每个手机号的，最大和最小值提取，key是手机号，
            //value由于要包含很多信息，如上限值，下限值，所以我们定义成一个类
            //这样，key就是手机号，value就是一个类，在reduce时，会将key的手机号作为一个组，value
            //就是同样手机号的所有上下限值，这样可以在reduce里做那些集合的聚合操作
            context.write(new Text(phoneMessage[1]), pi);
        }
    }
    //4个类型分别指：
    //输入key的类型、输入value的类型（reduce方法的2个入参）、
    //输出key的类型、输出value的类型（reduce 方法结束后的Context.write的两个形参）。
    public static  class phoneListReduce extends Reducer<Text,phonelist,Text,phoneInfo>
    {
        //reduce 方法接收的是一个字符串类型的key,一个可迭代的数据集，即接受到的是一组数据，即key相同，value不同的项
        //如有四个map，那么reduce某次接收到的是(good,1),(good,2),(good,1),(good,1)
        //即如下参数是：key=good,value=(1,2,1,1)
        protected void reduce(Text key, Iterable<phoneInfo> values, Context context)
                throws IOException,InterruptedException{
            Integer minPhoneVolume=0;
            Integer maxPhoneVolume=0;
            Integer avgPhoneVolume=0;
            for(phoneInfo value:values)
            {
                minPhoneVolume=minPhoneVolume+value.getMinPhoneVolume();
                maxPhoneVolume=maxPhoneVolume+value.getMaxPhoneVolume();
            }
            phoneInfo pi=new phoneInfo();
            pi.setAvgPhoneVolume((minPhoneVolume+maxPhoneVolume)*1.0/2);
            pi.setMaxPhoneVolume(maxPhoneVolume);
            pi.setMinPhoneVolume(minPhoneVolume);
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
        //设置map输出的key value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(phoneInfo.class);

        //设置reduce输出的key value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(phoneInfo.class);

        //设置输入输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //MR每次执行的时候，如果output文件夹存在就会报错
        //所以我们在代码里，手动判断，如果存在output文件夹就删除
        Path outPutPath=new Path(args[1]);
        FileSystem fileSystem=outPutPath.getFileSystem(conf);//根据path找到这个文件
        if(fileSystem.exists(outPutPath))
        {
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

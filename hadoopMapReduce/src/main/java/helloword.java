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

public class helloword {
    //其中的4个类型分别是：
    // 输入key类型、输入value类型、（map方法的两个入参）
    // 输出key类型、输出value类型。（map方法最后return的两个形参）
    // 因为MapReduce程序的输出数据需要在不同机器间传输，所以必须是可序列化的，例如Long类型，
    // Hadoop中定义了自己的可序列化类型LongWritable，String对应的是Text，int对应的是IntWritable
    public static class wordCountMaper extends Mapper<LongWritable,Text,Text,IntWritable> {
        private static String line;
        //key是某一行文本的起始偏移量(Long类型)
        //value默认是这行文本的内容Text类型。
        public void map(LongWritable key, Text value, Context context)
                throws IOException,InterruptedException
        {
            line=value.toString();
            String[] words=line.split(",");
            for(String word:words) {
                //返回的key是某个单词，value是计数为1
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }
    //4个类型分别指：
    //输入key的类型、输入value的类型（reduce方法的2个入参）、
    //输出key的类型、输出value的类型（reduce 方法结束后的Context.write的两个形参）。
    public static  class wordCountReduce extends Reducer<Text,IntWritable,Text,IntWritable>
    {
        //reduce 方法接收的是一个字符串类型的key,一个可迭代的数据集，即接受到的是一组数据，即key相同，value不同的项
        //如有四个map，那么reduce某次接收到的是(good,1),(good,2),(good,1),(good,1)
        //即如下参数是：key=good,value=(1,2,1,1)
        protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException,InterruptedException{
            Integer count=0;
            for(IntWritable value:values)
            {
                count=count+value.get();
            }
            context.write(key,new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception{
        //创建配置对象
        Configuration conf=new Configuration();
        //创建job对象
        Job job=Job.getInstance(conf,"wordcount_job");
        //设置运行job的类
        job.setJarByClass(helloword.class);
        //设置mapper类
        job.setMapperClass(helloword.wordCountMaper.class);
        //设置reduce类
        job.setReducerClass(helloword.wordCountReduce.class);
        //设置map输出的key value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce输出的key value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

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
            System.out.println("wordcount task fail!");
        }
    }
}

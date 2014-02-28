package assign3PageRankIterative;

// cc MaxTemperature Application to find the maximum temperature in the weather dataset
// vv MaxTemperature
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PageRank {

  public static void main(String[] args) throws Exception {
	  System.out.println(args.length);
    if (args.length != 2) {
      System.err.println("Usage: PageRank <input path> <output path>");
      System.exit(-1);
    }
    
    Configuration config = new Configuration();
    config.set("mapred.textoutputformat.separator", " ");
    
    Job job = new Job(config);
    
//    Configuration config = new Configuration();
//    config.set("mapred.job.tracker", "local");
//    config.set("fs.default.name", "local");
//    config.set("Token1", "hackathon");
//    job = new Job(config);
    
    job.setJarByClass(PageRank.class);
    job.setJobName("Word Count");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]+"1"));
    
    job.setMapperClass(PageRankMapper.class);
    job.setReducerClass(PageRankReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    job.waitForCompletion(true);
    
    
    job = new Job(config);
    
    job.setJarByClass(PageRank.class);
    job.setJobName("Word Count Level 2");

    FileInputFormat.addInputPath(job, new Path(args[1]+"1"));
    FileOutputFormat.setOutputPath(job, new Path(args[1]+"2"));
    
    job.setMapperClass(PageRankMapper.class);
    job.setReducerClass(PageRankReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    job.waitForCompletion(true);    
    

    job = new Job(config);
    
    job.setJarByClass(PageRank.class);
    job.setJobName("Word Count Level 3");

    FileInputFormat.addInputPath(job, new Path(args[1]+"2"));
    FileOutputFormat.setOutputPath(job, new Path(args[1]+"3"));
    
    job.setMapperClass(PageRankMapper.class);
    job.setReducerClass(PageRankReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    job.waitForCompletion(true);    
    
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
// ^^ PageRank

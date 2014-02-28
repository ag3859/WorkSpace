package assign3PageRankIterative;

// cc MaxTemperatureMapper Mapper for maximum temperature example
// vv MaxTemperatureMapper
import java.io.IOException;
import java.io.StringWriter;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.join.TupleWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class PageRankMapper
  //extends Mapper<LongWritable, Text, Text, IntWritable> {
extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  
	  String []outBoundEntry = value.toString().split(" ");
	  
	  StringBuffer sb = new StringBuffer("");
	  
	  for(int outBoundListIndex = 1; outBoundListIndex<outBoundEntry.length-1; outBoundListIndex++)
	  {
		  sb = sb.append(outBoundEntry[outBoundListIndex]+" ");
		  double outBoundPR = Double.parseDouble(outBoundEntry[outBoundEntry.length-1])/(outBoundEntry.length-2);
		  System.out.println(outBoundEntry[outBoundListIndex]+"::"+ outBoundEntry[0]+" "+outBoundPR);
		  context.write(new Text(outBoundEntry[outBoundListIndex]), new Text(outBoundEntry[0]+" "+outBoundPR));
	  }
	  	  
	  context.write(new Text(outBoundEntry[0]), new Text(sb.toString()));
	  System.out.println(outBoundEntry[0] +":"+ sb.toString());
  }      
    
}
// ^^ PageRankMapper

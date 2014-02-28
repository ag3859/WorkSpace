package assign2;

// cc MaxTemperatureMapper Mapper for maximum temperature example
// vv MaxTemperatureMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class WordCountMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  
	  HashMap<String, String> caseMap = new HashMap();	  

	  //context.getConfiguration().get("token1");
	  
	  caseMap.put("Dec".toLowerCase(), "Dec");
	  caseMap.put("hackathon".toLowerCase(), "hackathon");
	  caseMap.put("Java".toLowerCase(), "Java");
	  caseMap.put("Chicago".toLowerCase(), "Chicago");
	  
	  String testingString = value.toString();
	  
	  //testingString = "31-Dec-14,3:00PM,#Hackatopia,Designers, Developers, Doers, don't miss this upcoming Chicago hackathon";
	  List<String> tokens = new ArrayList<String>();
	  
	  for(String token:caseMap.keySet())
	  {
		  tokens.add(token);
		  context.write(new Text(caseMap.get(token)), new IntWritable(0));
	  }
	  		
	  
//	  tokens.add("hackathon".toLowerCase());
//	  tokens.add("Java".toLowerCase());
//	  tokens.add("Chicago".toLowerCase());
	  
//	  context.write(new Text("Dec".toLowerCase()), new IntWritable(0));
//	  context.write(new Text("hackathon".toLowerCase()), new IntWritable(0));
//	  context.write(new Text("Java".toLowerCase()), new IntWritable(0));
//	  context.write(new Text("Chicago".toLowerCase()), new IntWritable(0));
		
	  String patternString = "\\b(" + StringUtils.join("|", tokens) + ")\\b";
	  Pattern pattern = Pattern.compile(patternString);
	  Matcher matcher = pattern.matcher(testingString.toLowerCase());

		while (matcher.find()) {
			context.write(new Text(caseMap.get(matcher.group())), new IntWritable(1));		
		}
  }      
    
}
// ^^ PageRankMapper

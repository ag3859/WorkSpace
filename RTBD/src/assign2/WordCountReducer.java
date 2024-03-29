package assign2;

// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    
    int count=0;
    for (IntWritable value : values) {
      //maxValue = Math.max(maxValue, value.get());
    	count = count + value.get();
    }
    context.write(key, new IntWritable(count));
  }
}
// ^^ WordCountMapperReducer

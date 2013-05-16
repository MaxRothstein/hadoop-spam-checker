package edu.cooper.ece460.spamcheck;

import java.io.*;
import java.util.*;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SpamCheckReduce extends Reducer<Text, Text, Text, Text> {
	//@Override
	public void reduce(Text key, Iterable<Text> values, Context context) 
		throws IOException, InterruptedException 
	{
		String text;
		
		Path pt = new Path("spamlist/spamlist.txt"); //List of known spammers
		FileSystem fs = FileSystem.get(new Configuration());
		BufferedReader in = new BufferedReader(new InputStreamReader(fs.open(pt)));
		
		//Check if domain is in spam list
		while (in.ready()) {
			text = in.readLine();
			String[] parts = (key.toString()).split("@");
			String address = parts[1];
			address = address.substring(0,(address.length()-1));
			if (text.equals(address)){
				context.write(key, new Text(" is considered a spammer because of their domain."));
				return;
			}
		}
		in.close();
		
		//Check if subjects are similar
		
		//Puts all subjects into a hashmap that counts the number of times the same subject appears
		Map<Text, Integer> count = new HashMap<Text,Integer>();
		
		int numEmails = 0;
		
		for (Text value : values){
			Integer i = count.get(value);
			if (i == null) 
				i = 0;
			count.put(value, i + 1);
			numEmails++;
		}

		//Percentage of a set of similar emails out of all emails
		float percentage = 0;
		
		Iterator iter = count.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			percentage = ((float) Integer.parseInt(mEntry.getValue().toString())/numEmails) *100;
			if (percentage > 60 && numEmails > 5){
				context.write(key, new Text(" is considered a spammer because " + percentage + "% of their emails had the same subject."));
				return;
			}
		}
	}
}


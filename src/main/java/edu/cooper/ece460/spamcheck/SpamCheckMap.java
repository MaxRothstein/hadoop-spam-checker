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

/** This mapper sends out addresses and every subject they have sent */
public class SpamCheckMap extends Mapper<LongWritable, Text, Text, Text> {
    Context context;
	
	int uno = 1;

    @Override
    public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException
	{
		this.context = context;
		
		String line = value.toString();
		String[] parts = line.split(","); //splits up subject and sender address

		String sender = parts[0];
		String subject = parts[1];
		
		context.write(new Text(sender), new Text(subject));
	}
}
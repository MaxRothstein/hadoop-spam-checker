Analyzing auth.log with Hadoop
==============================

Check emails for potential spammers.

##Build

	mvn package

##Run

###Command-line

Run the jar file with Hadoop. Program arguments:

1. Path to the HDFS directory with input file(s)
2. Path to the HDFS directory to save output files (must not already exist)

Example:

	hadoop fs -rmr spamcheck_out
	hadoop jar target/HadoopSpamCheck-1.0.jar edu.cooper.ece460.authlog.HadoopSpamCheck spamcheck_in spamcheck_out
	hadoop fs -getmerge spamcheck_out output/spamcheck_out

###Shell script

The shell script `run.sh` in the root directory can be used to perform the
above steps. The local and HDFS output directories are automatically deleted by 
the script before starting the Hadoop job.

	./run.sh [hdfs-input-dir] [hdfs-output-dir] [local-output-dir]

Default values are `spamcheck_in`, `spamcheck_out`, and `output`, respectively.

###HTTP

	mvn jetty:run


##Output

Output format:

	address		reason why it is considered a spam address

